package edu.nju.treasuryArbitrage.ui.futuresMarket;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import edu.nju.treasuryArbitrage.logic.liveUpdate.LiveData;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class FuturesMarketChen extends FuturesMarket implements ComponentPanel {
	private static final long serialVersionUID = 4293989421427626065L;
	private static final int TABLE_HEIGHT = 40;
	private JTable futuersTable;
	private FuturesDetailPanel detailPanel;
	private String[] headerData = { "����", "�����·�", "�ּ�", "�ǵ�", "�ǵ���", "����",
			"���", "����", "����", "�ɽ���", "�ֲ���", "������", "ǰ�����", "��", "���", "���",
			"ʱ��" };
	private Arb_detail[] arb_details = new Arb_detail[3];
	private DefaultTableModel model;
	private LineChart[] charts = new LineChart[3];

	public FuturesMarketChen() {
		init();
		initComponents();
		assemble();
		addListeners();
	}

	private void addListeners() {
		futuersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = futuersTable.getSelectedRow();
				if (r == -1) {
					return;
				}
				detailPanel.setDetail(r);
				detailPanel.update(arb_details[r].getFormattedArb_detail());
				for (int i = 0; i < charts.length; i++) {
					charts[i].setVisible(false);
				}
				charts[r].setVisible(true);
			}
		});
		
	}

	private void init() {
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		ArrayList<Arb_detail> result = LiveData.getInstance().getArb_details();
		for (int i = 0; i < result.size(); i++) {
			arb_details[i] = result.get(i);
		}
	}

	private void updateTable() {

		Object[][] futuresInfo = new Object[arb_details.length][headerData.length];
		for (int i = 0; i < arb_details.length; i++) {
			Arb_detail arb = arb_details[i].getFormattedArb_detail();

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(arb.getTime());
			int hour = calendar.get(Calendar.HOUR);
			int min = calendar.get(Calendar.MINUTE);

			futuresInfo[i] = new Object[] { arb.getSymbol(), arb.getMonth(),
					arb.getPresentPrice(), arb.getPriceChange(),
					arb.getChange(), arb.getBid(), arb.getBidPirce(),
					arb.getAskPrice(), arb.getAsk(), arb.getVol(),
					arb.getRepository(), arb.getDailyWarehouse(),
					arb.getPreSettlePrice(), arb.getOpen(), arb.getHigh(),
					arb.getLow(), hour + ":" + min };
		}

		model.setDataVector(futuresInfo, headerData);

		setColomnWidthAndColor();
	}

	private void initComponents() {
		initTable();

		initCharts();
	}

	private void initTable() {
		futuersTable = new JTable();
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		futuersTable.setModel(model);

		updateTable();

		futuersTable.setShowGrid(false);
		futuersTable.setShowHorizontalLines(false);
		futuersTable.setShowVerticalLines(false);
		futuersTable.setRowHeight(TABLE_HEIGHT);
		futuersTable.setFont(new Font("����", Font.PLAIN, 18));
		futuersTable.setBackground(Color.BLACK);
		futuersTable.setBorder(null);
		futuersTable.setForeground(Color.WHITE);

		JTableHeader header = futuersTable.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(ColorConstants.BRIGHT_BLUE);
		header.setFont(new Font("����", Font.PLAIN, 18));
	}

	private void setColomnWidthAndColor() {
		TableColumn column1 = futuersTable.getColumn(headerData[1]);
		column1.setPreferredWidth(100);

		setColomnColor(0, Color.WHITE);
		setColomnColor(2, Color.RED);
		setColomnColor(3, Color.RED);
		DefaultTableCellRenderer renderer4 = setColomnColor(4, Color.RED);
		renderer4.setBackground(ColorConstants.DARK_FOCUS_BLUE);
		setColomnColor(5, Color.YELLOW);
		setColomnColor(6, Color.RED);
		setColomnColor(7, Color.RED);
		setColomnColor(8, Color.YELLOW);
		setColomnColor(9, Color.YELLOW);
		setColomnColor(10, Color.YELLOW);
		setColomnColor(11, Color.YELLOW);
		setColomnColor(12, Color.RED);
		setColomnColor(13, Color.RED);
		setColomnColor(14, Color.RED);
		setColomnColor(15, Color.RED);
	}

	private DefaultTableCellRenderer setColomnColor(int index, Color color) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setForeground(color);
		futuersTable.getColumn(headerData[index]).setCellRenderer(renderer);
		return renderer;
	}

	private void assemble() {
		JScrollPane scrollPane = new JScrollPane(futuersTable);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 10, ScreenSize.WIDTH, 150);
		this.add(scrollPane);

		int detailPanelWidth = 350;
		int detailPanelHeight = ScreenSize.HEIGHT - 170;
		detailPanel = new FuturesDetailPanel(detailPanelWidth, detailPanelHeight);
		detailPanel.setDetail(0);
		detailPanel.update(arb_details[0].getFormattedArb_detail());
		detailPanel.setBounds(0, 160, detailPanelWidth,detailPanelHeight);
		detailPanel.setBounds(ScreenSize.WIDTH - detailPanelWidth, 160, detailPanelWidth,detailPanelHeight);
		this.add(detailPanel);

		for (int i = 0; i < charts.length; i++) {
			charts[i].setBounds(0, 160, ScreenSize.WIDTH - detailPanelWidth,
					ScreenSize.HEIGHT - 220);
			charts[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, new Color(193,193,193)));
			this.add(charts[i]);
		}

		charts[0].setVisible(true);
		charts[1].setVisible(false);
		charts[2].setVisible(false);
	}

	private void initCharts() {
		charts[0] = new LineChart(Color.YELLOW);
		charts[1] = new LineChart(ColorConstants.BRIGHT_BLUE);
		charts[2] = new LineChart(Color.WHITE);
	}

	/*
	 * class TableSelectionListener1 implements ListSelectionListener {
	 * 
	 * public void valueChanged(ListSelectionEvent arg0) {
	 * tctpTable1.setBackground(Color.DARK_GRAY);
	 * btctpTable1.setBackground(Color.DARK_GRAY);
	 * futuresTable1.setBackground(Color.DARK_GRAY);
	 * rtctpTable1.setBackground(Color.DARK_GRAY);
	 * gtctpTable1.setBackground(Color.DARK_GRAY);
	 * 
	 * tctpTable2.setBackground(Color.BLACK);
	 * btctpTable2.setBackground(Color.BLACK);
	 * futuresTable2.setBackground(Color.BLACK);
	 * rtctpTable2.setBackground(Color.BLACK);
	 * gtctpTable2.setBackground(Color.BLACK);
	 * 
	 * tctpTable3.setBackground(Color.BLACK);
	 * btctpTable3.setBackground(Color.BLACK);
	 * futuresTable3.setBackground(Color.BLACK);
	 * rtctpTable3.setBackground(Color.BLACK);
	 * gtctpTable3.setBackground(Color.BLACK);
	 * 
	 * futuresTable1.clearSelection();
	 * 
	 * detailPanel.setDetail(arb1, 1); chart1.setVisible(true);
	 * chart2.setVisible(false); chart3.setVisible(false); } }
	 * 
	 * class TableSelectionListener2 implements ListSelectionListener {
	 * 
	 * public void valueChanged(ListSelectionEvent arg0) {
	 * tctpTable1.setBackground(Color.BLACK);
	 * btctpTable1.setBackground(Color.BLACK);
	 * futuresTable1.setBackground(Color.BLACK);
	 * rtctpTable1.setBackground(Color.BLACK);
	 * gtctpTable1.setBackground(Color.BLACK);
	 * 
	 * tctpTable2.setBackground(Color.DARK_GRAY);
	 * btctpTable2.setBackground(Color.DARK_GRAY);
	 * futuresTable2.setBackground(Color.DARK_GRAY);
	 * rtctpTable2.setBackground(Color.DARK_GRAY);
	 * gtctpTable2.setBackground(Color.DARK_GRAY);
	 * 
	 * tctpTable3.setBackground(Color.BLACK);
	 * btctpTable3.setBackground(Color.BLACK);
	 * futuresTable3.setBackground(Color.BLACK);
	 * rtctpTable3.setBackground(Color.BLACK);
	 * gtctpTable3.setBackground(Color.BLACK);
	 * 
	 * futuresTable2.clearSelection();
	 * 
	 * detailPanel.setDetail(arb2, 2); chart1.setVisible(false);
	 * chart2.setVisible(true); chart3.setVisible(false); } }
	 * 
	 * class TableSelectionListener3 implements ListSelectionListener {
	 * 
	 * public void valueChanged(ListSelectionEvent arg0) {
	 * tctpTable1.setBackground(Color.BLACK);
	 * btctpTable1.setBackground(Color.BLACK);
	 * futuresTable1.setBackground(Color.BLACK);
	 * rtctpTable1.setBackground(Color.BLACK);
	 * gtctpTable1.setBackground(Color.BLACK);
	 * 
	 * tctpTable2.setBackground(Color.BLACK);
	 * btctpTable2.setBackground(Color.BLACK);
	 * futuresTable2.setBackground(Color.BLACK);
	 * rtctpTable2.setBackground(Color.BLACK);
	 * gtctpTable2.setBackground(Color.BLACK);
	 * 
	 * tctpTable3.setBackground(Color.DARK_GRAY);
	 * btctpTable3.setBackground(Color.DARK_GRAY);
	 * futuresTable3.setBackground(Color.DARK_GRAY);
	 * rtctpTable3.setBackground(Color.DARK_GRAY);
	 * gtctpTable3.setBackground(Color.DARK_GRAY);
	 * 
	 * futuresTable3.clearSelection();
	 * 
	 * detailPanel.setDetail(arb3, 3); chart1.setVisible(false);
	 * chart2.setVisible(false); chart3.setVisible(true);
	 * 
	 * } }
	 */

	@Override
	public void updatePage() {
		ArrayList<Arb_detail> arb_lists = LiveData.getInstance()
				.getArb_details();
		for (int i = 0; i < arb_lists.size(); i++) {
			Arb_detail arb_detail = arb_lists.get(i);
			arb_details[i] = arb_detail;
			charts[i].addNewPrice(arb_detail.getPresentPrice());
		}
		detailPanel.update(arb_lists);
		updateTable();
	}
}
