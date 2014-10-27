package edu.nju.treasuryArbitrage.ui.futuresMarket;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
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
	private static final int LABEL_WIDTH = 60;
	private static final int LABEL_HEIGHT = 20;
	private JTable futuersTable;
	private FuturesPanel detailPanel;
	private String[] headerData = { "代码", "交割月份", "现价", "涨跌", "涨跌幅", "买量",
			"买价", "卖价", "卖量", "成交量", "持仓量", "日增仓", "前结算价", "今开", "最高", "最低",
			"时间" };
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
//		futuersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				  if (e.getSource() == table.getColumnModel().getSelectionModel()
//		                   && table.getColumnSelectionAllowed() ){
//		                int firstRow = e.getFirstIndex();
//		                int lastRow = e.getLastIndex();
//		                // 事件处理...
//		            }
//				
//			}
//		});
		
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
		futuersTable.setFont(new Font("黑体", Font.PLAIN, 18));
		futuersTable.setBackground(Color.BLACK);
		futuersTable.setBorder(null);
		futuersTable.setForeground(Color.WHITE);

		JTableHeader header = futuersTable.getTableHeader();
		header.setBackground(Color.BLACK);
		header.setForeground(ColorConstants.BRIGHT_BLUE);
		header.setFont(new Font("黑体", Font.PLAIN, 18));
	}

	private void setColomnWidthAndColor() {
		TableColumn column1 = futuersTable.getColumn(headerData[1]);
		column1.setPreferredWidth(100);

		setColomnColor(0, ColorConstants.BRIGHT_BLUE);
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

		int detailPanelWidth = 200;
		int detailPanelHeight = ScreenSize.HEIGHT - 170;
		detailPanel = new FuturesPanel(detailPanelWidth, detailPanelHeight);
		detailPanel.setDetail(arb_details[0].getFormattedArb_detail());
		detailPanel.setBounds(ScreenSize.WIDTH - 200, 160, detailPanelWidth,
				detailPanelHeight);
		this.add(detailPanel);

		for (int i = 0; i < charts.length; i++) {
			charts[i].setBounds(0, 160, ScreenSize.WIDTH - 200,
					ScreenSize.HEIGHT - 220);
			this.add(charts[i]);
		}

		charts[0].setVisible(true);
		charts[1].setVisible(false);
		charts[2].setVisible(false);
	}

	private void initCharts() {
		charts[0] = new LineChart(0, Color.YELLOW);
		charts[1] = new LineChart(1, ColorConstants.BRIGHT_BLUE);
		charts[2] = new LineChart(0, Color.WHITE);
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

	class FuturesPanel extends JPanel {
		private static final long serialVersionUID = -4185005520541760560L;

		private JLabel[] data = new JLabel[24];
		public JLabel[] detail = new JLabel[24];
		private JLabel title = new JLabel();
		private int width;
		private int height;

		public FuturesPanel(int width, int height) {
			this.width = width;
			this.height = height;

			data[0] = new JLabel("卖价");
			data[1] = new JLabel("买价");
			data[2] = new JLabel("成交");
			data[3] = new JLabel("涨跌");
			data[4] = new JLabel("涨幅");
			data[5] = new JLabel("振幅");
			data[6] = new JLabel("现手");
			data[7] = new JLabel("总手");
			data[8] = new JLabel("持仓");
			data[9] = new JLabel("昨持仓");
			data[10] = new JLabel("涨停");
			data[11] = new JLabel("外盘");
			data[12] = new JLabel("开盘");
			data[13] = new JLabel("昨收");
			data[14] = new JLabel("最高");
			data[15] = new JLabel("最低");
			data[16] = new JLabel("金额");
			data[17] = new JLabel("均价");
			data[18] = new JLabel("今结");
			data[19] = new JLabel("昨结");
			data[20] = new JLabel("日增仓");
			data[21] = new JLabel("量比");
			data[22] = new JLabel("跌停");
			data[23] = new JLabel("内盘");

			this.setLayout(null);

			for (int i = 0; i < 24; i++) {
				this.add(data[i]);
				data[i].setFont(new Font("微软雅黑", Font.PLAIN, 18));
				detail[i] = new JLabel();
				detail[i].setFont(new Font("微软雅黑", Font.PLAIN, 18));
				detail[i].setHorizontalAlignment(SwingConstants.RIGHT);
				this.add(detail[i]);
				data[i].setForeground(new Color(192, 192, 192));
			}

			this.setBackground(Color.BLACK);

			title.setBounds(0, 0, width, 30);
			this.add(title);
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setForeground(ColorConstants.BRIGHT_BLUE);
			title.setFont(new Font("微软雅黑", Font.PLAIN, 22));
			title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
					new Color(128, 0, 0)));

			for (int i = 0; i < 24; i++) {
				data[i].setBounds(5, 30 + (LABEL_HEIGHT) * i, LABEL_WIDTH,
						LABEL_HEIGHT);
				detail[i].setBounds(70, 30 + (LABEL_HEIGHT) * i, 120,
						LABEL_HEIGHT);
			}
		}

		public void setDetail(Arb_detail arb) {
			title.setText("国债" + arb.getSymbol().substring(2));

			String zhangdie;
			if (arb.getPriceChange() >= 0) {
				zhangdie = "+" + arb.getPriceChange() + "%";
			} else {
				zhangdie = arb.getPriceChange() + "%";
			}
			String zhangfu;
			if (arb.getChange() >= 0) {
				zhangfu = "+" + arb.getChange() + "%";
			} else {
				zhangfu = arb.getChange() + "%";
			}

			if (arb.getPresentPrice() > arb.getSettlePrice()) {
				detail[2].setForeground(Color.RED);
			} else if (arb.getPresentPrice() < arb.getSettlePrice()) {
				detail[2].setForeground(Color.GREEN);
			} else {
				detail[2].setForeground(new Color(10, 156, 211));
			}

			if (arb.getChange() > 0) {
				detail[3].setForeground(Color.RED);
			} else if (arb.getChange() < 0) {
				detail[3].setForeground(Color.GREEN);
			} else {
				detail[3].setForeground(new Color(10, 156, 211));
			}

			detail[0].setForeground(new Color(10, 156, 211));
			detail[1].setForeground(new Color(10, 156, 211));
			for (int j = 4; j < 24; j++) {
				detail[j].setForeground(new Color(10, 156, 211));
			}

			detail[0].setText(String.valueOf(arb.getAskPrice()));
			detail[1].setText(String.valueOf(arb.getBidPirce()));
			detail[2].setText(String.valueOf(arb.getPresentPrice()));
			detail[3].setText(zhangdie);
			detail[4].setText(zhangfu);
			detail[5].setText(String.valueOf(arb.getSwing()) + "%");
			detail[6].setText(String.valueOf(arb.getNvol()));
			detail[7].setText(String.valueOf(arb.getVol()));
			detail[8].setText(String.valueOf(arb.getRepository()));
			detail[9].setText(String.valueOf(arb.getPreRepository()));
			detail[10].setText(String.valueOf(arb.getHardenPrice()));
			detail[11].setText(String.valueOf(arb.getOutvol()));
			detail[12].setText(String.valueOf(arb.getOpen()));
			detail[13].setText(String.valueOf(arb.getPreClose()));
			detail[14].setText(String.valueOf(arb.getHigh()));
			detail[15].setText(String.valueOf(arb.getLow()));
			detail[16].setText(String.valueOf(arb.getFullAmount()) + "亿");
			detail[17].setText(String.valueOf(arb.getAverPrice()));
			detail[18].setText(String.valueOf(arb.getSettlePrice()));
			detail[19].setText(String.valueOf(arb.getPreSettlePrice()));
			detail[20].setText(String.valueOf(arb.getDailyWarehouse()));
			detail[21].setText(String.valueOf(arb.getRatio()));
			detail[22].setText(String.valueOf(arb.getLimitPrice()));
			detail[23].setText(String.valueOf(arb.getInvol()));

		}
	}

	@Override
	public void updatePage() {
		ArrayList<Arb_detail> arb_lists = LiveData.getInstance()
				.getArb_details();
		for (int i = 0; i < arb_lists.size(); i++) {
			Arb_detail arb_detail = arb_lists.get(i);
			arb_details[i] = arb_detail;
			charts[i].addNewPrice(arb_detail.getPresentPrice());
		}
		updateTable();
	}
}
