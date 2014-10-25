package edu.nju.treasuryArbitrage.ui.futuresMarket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import edu.nju.treasuryArbitrage.logic.liveUpdate.LiveData;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class FuturesMarketChen extends FuturesMarket implements ComponentPanel {
	private static final long serialVersionUID = 4293989421427626065L;
	private static final int TABLE_HEIGHT = 40;
	private static final int HEADER_HEIGHT = 40;
	private static final int LABEL_WIDTH = 60;
	private static final int LABEL_HEIGHT = 20;
	private JTable futuersTable;
	private FuturesPanel detailPanel;
	private Arb_detail[] arb_details = new Arb_detail[3];
	private DefaultTableModel model;
	private LineChart chart1, chart2, chart3;

	private void init() {
		this.setLayout(null);
		this.setBackground(Color.BLACK);
	}
	
	public void update() {
		String[] headerData = { "代码", "交割月份", "现价", "涨跌", "涨跌幅", "买量", "买价", "卖价",
				"卖量", "成交量", "持仓量", "日增仓", "前结算价", "今开", "最高", "最低", "时间" };
		ArrayList<Arb_detail> result = LiveData.getInstance().getArb_details();
		Object[][] futuresInfo = new Object[result.size()][headerData.length];
		for (int i = 0; i < result.size(); i++) {
			Arb_detail arb = result.get(i).getFormattedArb_detail();
			arb_details[i] = arb;
			
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
		
		model = new DefaultTableModel(futuresInfo, headerData) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		futuersTable.setModel(model);
		futuersTable.repaint();
	}
	
	private void initComponents() {
		futuersTable = new JTable();
		update();
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
	
	private void assemble() {
		JScrollPane scrollPane = new JScrollPane(futuersTable);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 10, ScreenSize.WIDTH, 150);
		
		this.add(scrollPane);
	}
	
	public FuturesMarketChen() {
		init();
		initComponents();
		assemble();
		
		JButton button = new JButton("啊啊啊");
		button.setBounds(0, 160, 100, 100);
		this.add(button);
		
		LinePanel line1 = new LinePanel(0, 10 + HEADER_HEIGHT + 2
				* TABLE_HEIGHT + TABLE_HEIGHT, WIDTH, 10 + HEADER_HEIGHT + 2
				* TABLE_HEIGHT + TABLE_HEIGHT);
		line1.setBounds(0, 10 + HEADER_HEIGHT + 3 * TABLE_HEIGHT + 20, WIDTH, 1);
		this.add(line1);

		detailPanel = new FuturesPanel();
		detailPanel.setBounds(0, 10 + HEADER_HEIGHT + 3 * TABLE_HEIGHT + 20,
				WIDTH / 5 * 2, HEIGHT - 190);
		this.add(detailPanel);

		LinePanel line2 = new LinePanel((WIDTH / 5) * 2, 10 + HEADER_HEIGHT + 2
				* TABLE_HEIGHT + TABLE_HEIGHT, (WIDTH / 5) * 2, HEIGHT);
		this.add(line2);
		line2.setBounds((WIDTH / 5) * 2, 10 + HEADER_HEIGHT + 3 * TABLE_HEIGHT
				+ 20, 1, HEIGHT - 190);

		chart1 = new LineChart(0);
		chart2 = new LineChart(1);
		chart3 = new LineChart(2);
		this.add(chart1);
		this.add(chart2);
		this.add(chart3);
		chart1.setBounds((WIDTH / 5) * 2, 10 + HEADER_HEIGHT + 3 * TABLE_HEIGHT
				+ 20, (WIDTH / 5) * 3, HEIGHT - 190);
		chart2.setBounds((WIDTH / 5) * 2, 10 + HEADER_HEIGHT + 3 * TABLE_HEIGHT
				+ 20, (WIDTH / 5) * 3, HEIGHT - 190);
		chart3.setBounds((WIDTH / 5) * 2, 10 + HEADER_HEIGHT + 3 * TABLE_HEIGHT
				+ 20, (WIDTH / 5) * 3, HEIGHT - 190);
		chart1.setVisible(true);
		chart2.setVisible(false);
		chart3.setVisible(false);

		detailPanel.setDetail(arb_details[0], 1);
	}

	public Object[][] getFuturesInfo(Arb_detail arb, int id) {

		Date date = new Date(arb.getTime());
		int hour = date.getHours();
		int min = date.getMinutes();
		if (id == 1) {
			Object[][] futuresInfo = { new Object[] { "TF1412", "2014年12月",
					arb.getPresentPrice(), arb.getPriceChange(),
					arb.getChange(), arb.getBid(), arb.getBidPirce(),
					arb.getAskPrice(), arb.getAsk(), arb.getVol(),
					arb.getRepository(), arb.getDailyWarehouse(),
					arb.getPreSettlePrice(), arb.getOpen(), arb.getHigh(),
					arb.getLow(), hour + ":" + min } };
			return futuresInfo;
		} else if (id == 2) {
			Object[][] futuresInfo = { new Object[] { "TF1503", "2015年03月",
					arb.getPresentPrice(), arb.getPriceChange(),
					arb.getChange(), arb.getBid(), arb.getBidPirce(),
					arb.getAskPrice(), arb.getAsk(), arb.getVol(),
					arb.getRepository(), arb.getDailyWarehouse(),
					arb.getPreSettlePrice(), arb.getOpen(), arb.getHigh(),
					arb.getLow(), hour + ":" + min } };
			return futuresInfo;
		} else if (id == 3) {
			Object[][] futuresInfo = { new Object[] { "TF1506", "2015年06月",
					arb.getPresentPrice(), arb.getPriceChange(),
					arb.getChange(), arb.getBid(), arb.getBidPirce(),
					arb.getAskPrice(), arb.getAsk(), arb.getVol(),
					arb.getRepository(), arb.getDailyWarehouse(),
					arb.getPreSettlePrice(), arb.getOpen(), arb.getHigh(),
					arb.getLow(), hour + ":" + min } };
			return futuresInfo;
		}
		return null;
	}

	/*class TableSelectionListener1 implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent arg0) {
			tctpTable1.setBackground(Color.DARK_GRAY);
			btctpTable1.setBackground(Color.DARK_GRAY);
			futuresTable1.setBackground(Color.DARK_GRAY);
			rtctpTable1.setBackground(Color.DARK_GRAY);
			gtctpTable1.setBackground(Color.DARK_GRAY);

			tctpTable2.setBackground(Color.BLACK);
			btctpTable2.setBackground(Color.BLACK);
			futuresTable2.setBackground(Color.BLACK);
			rtctpTable2.setBackground(Color.BLACK);
			gtctpTable2.setBackground(Color.BLACK);

			tctpTable3.setBackground(Color.BLACK);
			btctpTable3.setBackground(Color.BLACK);
			futuresTable3.setBackground(Color.BLACK);
			rtctpTable3.setBackground(Color.BLACK);
			gtctpTable3.setBackground(Color.BLACK);

			futuresTable1.clearSelection();

			detailPanel.setDetail(arb1, 1);
			chart1.setVisible(true);
			chart2.setVisible(false);
			chart3.setVisible(false);
		}
	}

	class TableSelectionListener2 implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent arg0) {
			tctpTable1.setBackground(Color.BLACK);
			btctpTable1.setBackground(Color.BLACK);
			futuresTable1.setBackground(Color.BLACK);
			rtctpTable1.setBackground(Color.BLACK);
			gtctpTable1.setBackground(Color.BLACK);

			tctpTable2.setBackground(Color.DARK_GRAY);
			btctpTable2.setBackground(Color.DARK_GRAY);
			futuresTable2.setBackground(Color.DARK_GRAY);
			rtctpTable2.setBackground(Color.DARK_GRAY);
			gtctpTable2.setBackground(Color.DARK_GRAY);

			tctpTable3.setBackground(Color.BLACK);
			btctpTable3.setBackground(Color.BLACK);
			futuresTable3.setBackground(Color.BLACK);
			rtctpTable3.setBackground(Color.BLACK);
			gtctpTable3.setBackground(Color.BLACK);

			futuresTable2.clearSelection();

			detailPanel.setDetail(arb2, 2);
			chart1.setVisible(false);
			chart2.setVisible(true);
			chart3.setVisible(false);
		}
	}

	class TableSelectionListener3 implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent arg0) {
			tctpTable1.setBackground(Color.BLACK);
			btctpTable1.setBackground(Color.BLACK);
			futuresTable1.setBackground(Color.BLACK);
			rtctpTable1.setBackground(Color.BLACK);
			gtctpTable1.setBackground(Color.BLACK);

			tctpTable2.setBackground(Color.BLACK);
			btctpTable2.setBackground(Color.BLACK);
			futuresTable2.setBackground(Color.BLACK);
			rtctpTable2.setBackground(Color.BLACK);
			gtctpTable2.setBackground(Color.BLACK);

			tctpTable3.setBackground(Color.DARK_GRAY);
			btctpTable3.setBackground(Color.DARK_GRAY);
			futuresTable3.setBackground(Color.DARK_GRAY);
			rtctpTable3.setBackground(Color.DARK_GRAY);
			gtctpTable3.setBackground(Color.DARK_GRAY);

			futuresTable3.clearSelection();

			detailPanel.setDetail(arb3, 3);
			chart1.setVisible(false);
			chart2.setVisible(false);
			chart3.setVisible(true);

		}
	}*/

	class TableCellTextPaneRenderer extends JTextPane implements
			TableCellRenderer {

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;

		public TableCellTextPaneRenderer() {
			doc = new DefaultStyledDocument();
			this.setStyledDocument(doc);
			sas = new SimpleAttributeSet();
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, sas, true);
			attr = new SimpleAttributeSet();

			StyleConstants.setForeground(attr, Color.WHITE);
			setCharacterAttributes(attr, false);
			this.setFont(new Font("黑体", Font.PLAIN, 20));
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value == null ? "" : value.toString());

			return this;
		}

	}

	class BlueTableCellTextPaneRenderer extends JTextPane implements
			TableCellRenderer {

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;

		public BlueTableCellTextPaneRenderer() {
			doc = new DefaultStyledDocument();
			this.setStyledDocument(doc);
			sas = new SimpleAttributeSet();
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, sas, true);
			attr = new SimpleAttributeSet();

			StyleConstants.setForeground(attr, new Color(10, 156, 211));
			setCharacterAttributes(attr, false);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value == null ? "" : value.toString());

			return this;
		}

	}

	class RedTableCellTextPaneRenderer extends JTextPane implements
			TableCellRenderer {

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;

		public RedTableCellTextPaneRenderer() {
			doc = new DefaultStyledDocument();
			this.setStyledDocument(doc);
			sas = new SimpleAttributeSet();
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, sas, true);
			attr = new SimpleAttributeSet();

			StyleConstants.setForeground(attr, Color.RED);
			setCharacterAttributes(attr, false);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value == null ? "" : value.toString());

			return this;
		}

	}

	class GreenTableCellTextPaneRenderer extends JTextPane implements
			TableCellRenderer {

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;

		public GreenTableCellTextPaneRenderer() {
			doc = new DefaultStyledDocument();
			this.setStyledDocument(doc);
			sas = new SimpleAttributeSet();
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, sas, true);
			attr = new SimpleAttributeSet();

			StyleConstants.setForeground(attr, Color.GREEN);
			setCharacterAttributes(attr, false);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setText(value == null ? "" : value.toString());

			return this;
		}

	}

	class LinePanel extends JPanel {
		private int x1, x2, y1, y2;

		public LinePanel(int xx1, int yy1, int xx2, int yy2) {
			x1 = xx1;
			x2 = xx2;
			y1 = yy1;
			y2 = yy2;
		}

		public void paintComponets(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.WHITE);
			g2.drawLine(x1, y1, x2, y2);

		}
	}

	class FuturesPanel extends JPanel {
		private JLabel[] data = new JLabel[24];
		public JLabel[] detail = new JLabel[24];
		private JLabel title = new JLabel();
		private int width = (WIDTH / 5) * 2;
		private int height = HEIGHT - 190;

		public FuturesPanel() {

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

			this.setSize(width, height);
			this.setLayout(null);

			for (int i = 0; i < 24; i++) {
				this.add(data[i]);
				detail[i] = new JLabel();
				this.add(detail[i]);
				data[i].setForeground(Color.WHITE);
			}

			this.setBackground(Color.BLACK);

			title.setBounds(190, 10, 200, 30);
			title.setText("点击期货查看详细数据");
			this.add(title);
			title.setVisible(false);
			title.setForeground(Color.WHITE);

			for (int i = 0; i < 13; i++) {
				data[i].setBounds(30, 80 + (LABEL_HEIGHT + 10) * i,
						LABEL_WIDTH, LABEL_HEIGHT);
				detail[i].setBounds(140, 80 + (LABEL_HEIGHT + 10) * i,
						LABEL_WIDTH, LABEL_HEIGHT);

			}

			for (int i = 13; i < 24; i++) {
				data[i].setBounds(250, 140 + (LABEL_HEIGHT + 10) * (i - 13),
						LABEL_WIDTH, LABEL_HEIGHT);
				detail[i].setBounds(360, 140 + (LABEL_HEIGHT + 10) * (i - 13),
						LABEL_WIDTH, LABEL_HEIGHT);
			}
		}

		public void setDetail(Arb_detail arb, int i) {
			if (i == 1) {
				title.setText("国债1412");
			} else if (i == 2) {
				title.setText("国债1503");
			} else if (i == 3) {
				title.setText("国债1506");
			}
			title.setVisible(true);

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
		update();
		chart1.addNewPrice(arb_lists.get(0).getFormattedArb_detail()
				.getPresentPrice());
		chart2.addNewPrice(arb_lists.get(1).getFormattedArb_detail()
				.getPresentPrice());
		chart3.addNewPrice(arb_lists.get(2).getFormattedArb_detail()
				.getPresentPrice());

	}
}
