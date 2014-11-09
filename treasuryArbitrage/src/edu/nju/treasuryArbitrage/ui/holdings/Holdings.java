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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class Holdings extends JPanel implements ComponentPanel{
	private static final long serialVersionUID = 6470810944009110491L;

	/* package */static final Color BACKGROUND_COLOR = new Color(246, 246, 246);
	/* package */static final Color TABLE_DARKER_BACKGROUND_COLOR = new Color(237, 237, 237);
	/* package */static final Color TABLE_HEADER_BACKGROUND_COLOR = new Color(214, 214, 214);
	private static final Color HEADER_BACKGROUND_COLOR = new Color(211, 211, 211);

	private Object holdHeaderData[] = { "套利组合信息", "交易时间", "交易手数", "投入保证金",
			"即时损失/盈利金额", "操作" }, empOb[][] = { { "", "", "", "", "", ""},
			{ "", "", "", "", "", ""},
			{ "", "", "", "", "", ""},
			{ "", "", "", "", "", ""},
			{ "", "", "", "", "", ""},
			{ "", "", "", "", "", ""}};
	private Object historyHeaderData[] = { "套利组合信息", "交易时间", "交易手数", "投入保证金", "交易状态"}, 
			historyDefaultData[][] = { { "", "", "", "", ""}};
	private JTable hTable;
	private JTable historyTable;

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

		DefaultTableModel model = new DefaultTableModel(
				new Object[0][holdHeaderData.length], holdHeaderData) {
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
		headerPanel.add(reposityPageLabel, BorderLayout.CENTER);
		headerPanel.add(historyTableHeader, BorderLayout.SOUTH);
		southPanel.add(headerPanel, BorderLayout.NORTH);
		// ==========================================table header
		// end====================

		final ArrayList<Record> info = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer().getRecordList();// 从服务器获取数据
		historyTable = new JTable(new DefaultTableModel() {
			private static final long serialVersionUID = -335420676543481799L;

			@Override
			public Object getValueAt(int row, int column) {
				return historyDefaultData[row][column];
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				historyDefaultData[row][column] = aValue;
				fireTableCellUpdated(row, column);
			}

			@Override
			public int getRowCount() {
				return info.size();
			}

			@Override
			public int getColumnCount() {
				return 5;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		makeFaceOfTable(historyTable);
		
		updateHistory();

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
		// ==========================================table header
		// end====================

		final ArrayList<Repository> info = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer().getRepoList();// 从服务器获取数据
		hTable = new JTable(new DefaultTableModel() {
			private static final long serialVersionUID = -335420676543481799L;

			@Override
			public Object getValueAt(int row, int column) {
				return empOb[row][column];
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				empOb[row][column] = aValue;
				fireTableCellUpdated(row, column);
			}

			@Override
			public int getRowCount() {
				return info.size();
			}

			@Override
			public int getColumnCount() {
				return 6;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 5) {
					return true;
				} else {
					return false;
				}
			}
		});
		makeFaceOfTable(hTable);
		updateRepoList();

		JScrollPane jsp = new JScrollPane(hTable);
		jsp.getViewport().setBackground(BACKGROUND_COLOR);
		northPanel.add(jsp);
		return northPanel;
	}

	public void updatePage() {
		// 更新持仓情况页面显示
		updateHistory();
		updateRepoList();
	}

	public void updateRepoList() {
		ArrayList<Repository> info = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer().getRepoList();// 从服务器获取数据
		if (info.size() > 0) {
			for (int j = 0; j < info.size(); j++) {
				Date dt = new Date(info.get(j).getTime());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String time = df.format(dt);
				hTable.setValueAt(time, j, 1);
				hTable.setValueAt(info.get(j).getCount(), j, 2);
				hTable.setValueAt(info.get(j).getGuarantee(), j, 3);
				hTable.setValueAt(info.get(j).getProfit(), j, 4);
			}
			hTable.getColumnModel().getColumn(5)
					.setCellEditor(new ButtonEditor(info));
			hTable.getColumnModel().getColumn(5)
					.setCellRenderer(new ButtonCellRenderer());
			hTable.getColumnModel().getColumn(0).setPreferredWidth(ScreenSize.WIDTH / 2);
			hTable.getColumnModel().getColumn(0)
					.setCellEditor(new MyTableEditor(info));
			Repository[] repositories = new Repository[info.size()];
			repositories = info.toArray(repositories);
			hTable.getColumnModel().getColumn(0)
					.setCellRenderer(new MyTableCellRenderer(repositories));//

		}
		hTable.repaint();
	}

	

	public void updateHistory() {
		ArrayList<Record> info = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer().getRecordList();// 从服务器获取数据
		if (info.size() > 0) {
			for (int j = 0; j < info.size(); j++) {
				Date dt = new Date(info.get(j).getTime());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String time = df.format(dt);
				historyTable.setValueAt(time, j, 1);
				historyTable.setValueAt(info.get(j).getCount(), j, 2);
				historyTable.setValueAt(info.get(j).getGuarantee(), j, 3);
				historyTable.setValueAt(info.get(j).getState(), j, 4);
			}
			historyTable.getColumnModel().getColumn(0).setPreferredWidth(ScreenSize.WIDTH / 2);
			Record[] records = new Record[info.size()];
			records = info.toArray(records);
			historyTable.getColumnModel().getColumn(0)
					.setCellRenderer(new MyTableCellRenderer(records));//

		}
		historyTable.repaint();
		
	}

	private void makeFaceOfHeader(JTable table) {
		table.setRowHeight(30);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		table.getColumnModel().getColumn(0).setPreferredWidth(ScreenSize.WIDTH / 2);
		table.setEnabled(false);
		table.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(122,138,153)));
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
		table.setEnabled(false);
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
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr22);
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
