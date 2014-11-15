package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.logic.liveUpdate.LiveData;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class Holdings extends JPanel implements ComponentPanel {
	private static final long serialVersionUID = 6470810944009110491L;

	/* package */static final Color BACKGROUND_COLOR = new Color(246, 246, 246);
	/* package */static final Color TABLE_DARKER_BACKGROUND_COLOR = new Color(
			237, 237, 237);
	/* package */static final Color TABLE_HEADER_BACKGROUND_COLOR = new Color(
			214, 214, 214);
	private static final Color HEADER_BACKGROUND_COLOR = new Color(211, 211,
			211);

	private String[] holdHeaderData = { "套利组合信息", "交易时间", "交易手数", "投入保证金",
			"即时损失/盈利金额", "操作" };
	private String[] historyHeaderData = { "套利组合信息", "交易时间", "交易手数", "投入保证金",
			"交易状态" };
	private JTable hTable;
	private JTable historyTable;
	private DefaultTableModel repoTableModel;
	private DefaultTableModel historyTableModel;

	private ArrayList<Repository> info;
	private double[] buyPrices;
	private double[] sellPrices;
	private DateChooser historyFromDateChooser = DateChooser.getInstance("yyyy-MM-dd");
	private DateChooser historyToDateChoose = DateChooser.getInstance("yyyy-MM-dd");
	private JTextField historyDateFrom = new JTextField();
	private JTextField historyDateTo = new JTextField();

	public Holdings() {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());

		JPanel centerPanel = initCenter();
		JPanel southPanel = initSouth();
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

	private JPanel initSouth() {
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.setBackground(BACKGROUND_COLOR);
		southPanel.setPreferredSize(new Dimension(ScreenSize.WIDTH,
				(int) (ScreenSize.HEIGHT / 10.0 * 3)));

		JLabel reposityPageLabel = new JLabel(" 历史交易记录", JLabel.LEFT);
		reposityPageLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		reposityPageLabel.setForeground(new Color(247, 68, 97));
		reposityPageLabel.setPreferredSize(new Dimension(ScreenSize.WIDTH,
				(int) (ScreenSize.HEIGHT / 25.0)));

		// TODO
		JPanel dateChooserPanel = new JPanel();
		dateChooserPanel.setOpaque(false);
		JLabel timeTipLabel = new JLabel("时间:");
		timeTipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		dateChooserPanel.add(timeTipLabel);
		
		historyFromDateChooser.register(historyDateFrom);
		historyToDateChoose.register(historyDateTo);
		historyDateFrom.setColumns(6);
		dateChooserPanel.add(historyDateFrom);
		dateChooserPanel.add(new JLabel("-"));
		historyDateTo.setColumns(6);
		dateChooserPanel.add(historyDateTo);

		JPanel southHeaderPanel = new JPanel(new BorderLayout());
		southHeaderPanel.setOpaque(false);
		southHeaderPanel.add(reposityPageLabel, BorderLayout.WEST);
		southHeaderPanel.add(dateChooserPanel, BorderLayout.EAST);

		DefaultTableModel model = new DefaultTableModel(
				new Object[0][historyHeaderData.length], historyHeaderData) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addRow(historyHeaderData);
		JTable historyTableHeader = new JTable();
		historyTableHeader.setModel(model);
		makeFaceOfHeader(historyTableHeader);

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(HEADER_BACKGROUND_COLOR);
		headerPanel.add(southHeaderPanel, BorderLayout.CENTER);
		headerPanel.add(historyTableHeader, BorderLayout.SOUTH);
		southPanel.add(headerPanel, BorderLayout.NORTH);
		// =======table header end====================

		historyTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = -335420676543481799L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		historyTable = new JTable(historyTableModel);
		updateHistory();
		historyTable.setEnabled(false);

		JScrollPane jsp = new JScrollPane(historyTable);
		jsp.getViewport().setBackground(BACKGROUND_COLOR);
		southPanel.add(jsp, BorderLayout.CENTER);
		return southPanel;
	}

	private JPanel initCenter() {
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(BACKGROUND_COLOR);
		northPanel.setPreferredSize(new Dimension(ScreenSize.WIDTH,
				(int) (ScreenSize.HEIGHT / 10.0 * 7)));

		JLabel reposityPageLabel = new JLabel(" 持仓情况", JLabel.LEFT);
		reposityPageLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		reposityPageLabel.setForeground(new Color(247, 68, 97));
		reposityPageLabel.setPreferredSize(new Dimension(ScreenSize.WIDTH,
				(int) (ScreenSize.HEIGHT / 25.0)));

		JButton doBtn = new JButton("平仓");
		doBtn.setFocusPainted(false);

		DefaultTableModel model = new DefaultTableModel(
				new Object[0][holdHeaderData.length], holdHeaderData) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addRow(holdHeaderData);
		JTable hTableHeader = new JTable();
		hTableHeader.setModel(model);
		makeFaceOfHeader(hTableHeader);

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(HEADER_BACKGROUND_COLOR);
		headerPanel.add(reposityPageLabel, BorderLayout.CENTER);
		headerPanel.add(hTableHeader, BorderLayout.SOUTH);
		northPanel.add(headerPanel, BorderLayout.NORTH);
		// =================table header end====================

		repoTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = -335420676543481799L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 5) {
					return true;
				} else {
					return false;
				}
			}
		};
		hTable = new JTable(repoTableModel);
		updateRepoList();

		JScrollPane jsp = new JScrollPane(hTable);
		jsp.getViewport().setBackground(BACKGROUND_COLOR);
		northPanel.add(jsp);
		return northPanel;
	}

	public void liveUpdate() {
		for (int i = 0; i < info.size(); i++) {
			Repository repository = info.get(i);
			Arb_detail buyArb = getLiveArb_detail(repository.getToBuy());
			Arb_detail sellArb = getLiveArb_detail(repository.getToSell());
			double profit = (sellArb.getPresentPrice()
					- repository.gettoBuy_price()
					+ repository.gettoSell_price() - buyArb.getPresentPrice())
					* repository.getCount() * 10000;
			double formatProfit = (int) (profit * 1000) / 1000.0;
			repository.setProfit(formatProfit);
			sellPrices[i] = sellArb.getPresentPrice();
			buyPrices[i] = buyArb.getPresentPrice();
			hTable.setValueAt(formatProfit, i, 4);
		}
	}

	private Arb_detail getLiveArb_detail(String symble) {
		ArrayList<Arb_detail> arb_details = LiveData.getInstance()
				.getArb_details();
		for (Arb_detail arb_detail : arb_details) {
			if (arb_detail.getSymbol().equals(symble)) {
				return arb_detail;
			}
		}
		return null;
	}

	public void updatePage() {
		// 更新持仓情况页面显示
		updateHistory();
		updateRepoList();
	}

	public void updateRepoList() {
		info = DataInterfaceFactory.getInstance().getDataInterfaceToServer()
				.getRepoList();// 从服务器获取数据
		sellPrices = new double[info.size()];
		buyPrices = new double[info.size()];
		
		Object[][] tableData = new Object[info.size()][holdHeaderData.length];
		for (int i = 0; i < info.size(); i++) {
			Repository repository = info.get(i);

			Date dt = new Date(repository.getTime());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String time = df.format(dt);
			tableData[i][0] = "";
			tableData[i][1] = time;
			tableData[i][2] = repository.getCount();
			tableData[i][3] = repository.getGuarantee();

			Arb_detail buyArb = getLiveArb_detail(repository.getToBuy());
			Arb_detail sellArb = getLiveArb_detail(repository.getToSell());
			if (buyArb == null || sellArb == null) {
				tableData[i][4] = "";
			} else {
				double profit = (sellArb.getPresentPrice()
						- repository.gettoBuy_price()
						+ repository.gettoSell_price() - buyArb
							.getPresentPrice()) * repository.getCount() * 10000;
				double formatProfit = (int) (profit * 1000) / 1000.0;
				repository.setProfit(formatProfit);
				sellPrices[i] = sellArb.getPresentPrice();
				buyPrices[i] = buyArb.getPresentPrice();
				tableData[i][4] = formatProfit;
				tableData[i][5] = "";
			}
		}
		repoTableModel.setDataVector(tableData, holdHeaderData);
		makeFaceOfTable(hTable);
		hTable.getColumnModel().getColumn(5)
				.setCellEditor(new ButtonEditor(info, sellPrices, buyPrices));
		hTable.getColumnModel().getColumn(5)
				.setCellRenderer(new ButtonCellRenderer());
		hTable.getColumnModel().getColumn(0)
				.setPreferredWidth(ScreenSize.WIDTH / 2);
		hTable.getColumnModel().getColumn(0)
				.setCellEditor(new MyTableEditor(info));
		Repository[] repositories = new Repository[info.size()];
		repositories = info.toArray(repositories);
		MyTableCellRenderer repoFirstColumnRenderers = new MyTableCellRenderer(
				repositories);
		hTable.getColumnModel().getColumn(0)
				.setCellRenderer(repoFirstColumnRenderers);//
		hTable.repaint();
	}

	public void updateHistory() {
		ArrayList<Record> info = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer().getRecordList();// 从服务器获取数据
		Object[][] tableData = new Object[info.size()][historyHeaderData.length];
		for (int j = 0; j < info.size(); j++) {
			Date dt = new Date(info.get(j).getTime());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String time = df.format(dt);
			tableData[j][0] = "";
			tableData[j][1] = time;
			tableData[j][2] = info.get(j).getCount();
			tableData[j][3] = info.get(j).getGuarantee();
			String state = stateResolve(info.get(j).getState());
			tableData[j][4] = state;
		}
		historyTableModel.setDataVector(tableData, historyHeaderData);
		makeFaceOfTable(historyTable);
		historyTable.getColumnModel().getColumn(0)
				.setPreferredWidth(ScreenSize.WIDTH / 2);
		Record[] records = new Record[info.size()];
		records = info.toArray(records);
		historyTable.getColumnModel().getColumn(0)
				.setCellRenderer(new MyTableCellRenderer(records));

		historyTable.repaint();

	}

	private String stateResolve(String state) {
		if (state.equals("done")) {
			return "已成交";
		} else {
			return "";
		}
	}

	private void makeFaceOfHeader(JTable table) {
		table.setRowHeight(30);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		table.getColumnModel().getColumn(0)
				.setPreferredWidth(ScreenSize.WIDTH / 2);
		table.setEnabled(false);
		table.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(
				122, 138, 153)));
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 2220633049102091416L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				setBackground(TABLE_HEADER_BACKGROUND_COLOR);
				setHorizontalAlignment(SwingConstants.CENTER);
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr1);
		}
	}

	private void makeFaceOfTable(JTable table) {
		table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		table.getTableHeader().setPreferredSize(new Dimension(0, 0));
		table.getTableHeader().setVisible(false);
		table.setRowHeight(60);
		table.setSelectionBackground(BACKGROUND_COLOR);
		DefaultTableCellRenderer tcr22 = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 2220633049102091416L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (row % 2 == 0)
					setBackground(BACKGROUND_COLOR); // 设置奇数行底色 else
				if (row % 2 == 1)
					setBackground(TABLE_DARKER_BACKGROUND_COLOR);
				setHorizontalAlignment(SwingConstants.CENTER);
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column = table.getColumn(table.getColumnName(i));
			column.setCellRenderer(tcr22);
		}

	}

	public static void main(String[] args) {
		JFrame mw = new JFrame("chen");
		mw.setSize(ScreenSize.WIDTH, ScreenSize.HEIGHT);
		JPanel Holdings = new Holdings();
		mw.getContentPane().add(Holdings);
		mw.setVisible(true);
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
