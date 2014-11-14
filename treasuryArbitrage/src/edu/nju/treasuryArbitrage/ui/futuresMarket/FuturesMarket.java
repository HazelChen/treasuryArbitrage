package edu.nju.treasuryArbitrage.ui.futuresMarket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
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

public class FuturesMarket extends JPanel implements ComponentPanel {
	private static final long serialVersionUID = 4293989421427626065L;
	private static final int TABLE_HEIGHT = 40;
	private JTable futuersTable;
	private FuturesDetailPanel detailPanel;
	private String[] headerData = { "代码", "交割月份", "现价", "涨跌", "涨跌幅", "买量",
			"买价", "卖价", "卖量", "成交量", "持仓量", "日增仓", "前结算价", "今开", "最高", "最低",
			"时间" };
	private Arb_detail[] arb_details = new Arb_detail[3];
	private DefaultTableModel model;
	private LineChart[] charts = new LineChart[3];
	private boolean[] isPresentPriceRed = new boolean[3];
	private double[] preSettlePrices = new double[3];

	public FuturesMarket() {
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
		
		for (int i = 0;i < arb_details.length; i++) {
			arb_details[i] = Arb_detail.nullObject();
		}
		
		ArrayList<Arb_detail> result = LiveData.getInstance().getArb_details();
		if (result == null) {
			return;
		}
		for (int i = 0; i < Math.min(result.size(), arb_details.length); i++) {
			arb_details[i] = result.get(i);
		}
	}

	private void updateTable() {

		Object[][] futuresInfo = new Object[arb_details.length][headerData.length];
		for (int i = 0; i < arb_details.length; i++) {
			Arb_detail arb = arb_details[i].getFormattedArb_detail();

			futuresInfo[i] = new Object[] { arb.getSymbol(), arb.getMonth(),
					arb.getPresentPrice(), arb.getPriceChange(),
					arb.getChange() + "%", arb.getBid(), arb.getBidPirce(),
					arb.getAskPrice(), arb.getAsk(), arb.getVol(),
					arb.getRepository(), arb.getDailyWarehouse(),
					arb.getPreSettlePrice(), arb.getOpen(), arb.getHigh(),
					arb.getLow(), arb.getClock()};
			preSettlePrices[i] = arb.getPreSettlePrice();
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

		setColomnColor(0, Color.WHITE);
		setColomnColorWithIsRed(2);
		setColomnColorWithPositiveAndNegative(3);
		DefaultTableCellRenderer renderer4 = setColomnColorWithPositiveAndNegative(4);
		renderer4.setBackground(ColorConstants.DARK_FOCUS_BLUE);
		setColomnColor(5, Color.YELLOW);
		setColomnColor(6, Color.RED);
		setColomnColor(7, Color.RED);
		setColomnColor(8, Color.YELLOW);
		setColomnColor(9, Color.YELLOW);
		setColomnColor(10, Color.YELLOW);
		setColomnColor(11, Color.YELLOW);
		setColomnColor(12, Color.WHITE);
		setColomnCompareWithPreSettle(13);
		setColomnCompareWithPreSettle(14);
		setColomnCompareWithPreSettle(15);
	}

	private void setColomnCompareWithPreSettle(int i) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {   
			private static final long serialVersionUID = 3126419784517343739L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				if (value instanceof Double) {
					double valueDouble = ((Double) value).doubleValue();   
					setForeground(valueDouble >= preSettlePrices[row] ? Color.RED : Color.GREEN); 
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
        };   
		futuersTable.getColumn(headerData[i]).setCellRenderer(renderer);
	}

	private DefaultTableCellRenderer setColomnColor(int index, Color color) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setForeground(color);
		futuersTable.getColumn(headerData[index]).setCellRenderer(renderer);
		return renderer;
	}
	
	private DefaultTableCellRenderer setColomnColorWithIsRed(int index) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {   
			private static final long serialVersionUID = 3126419784517343739L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				setForeground((isPresentPriceRed[row]) ? Color.RED : Color.GREEN); 
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
        };   
		futuersTable.getColumn(headerData[index]).setCellRenderer(renderer);
		return renderer;
	}
	
	private DefaultTableCellRenderer setColomnColorWithPositiveAndNegative(int index) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {   
			private static final long serialVersionUID = 1L;
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				double valueDouble = 0.0;
				if (value instanceof String) {
					String valueString = (String)value;
					String formattedValueString = valueString.substring(0, valueString.length() - 1);
					valueDouble = Double.parseDouble(formattedValueString);
					setText((value == null) ? "" : value.toString() + "%"); 
				} else if (value instanceof Double) {
					valueDouble = ((Double) value).doubleValue();   
					setText((value == null) ? "" : value.toString());   
				}
				setForeground((valueDouble  >= 0) ? Color.RED : Color.GREEN); 
				isPresentPriceRed[row] = (valueDouble >= 0);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
        };   
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

	@Override
	public void updatePage() {
		ArrayList<Arb_detail> arb_lists = LiveData.getInstance()
				.getArb_details();
		for (int i = 0; i < Math.min(arb_lists.size(), arb_details.length); i++) {
			Arb_detail arb_detail = arb_lists.get(i);
			arb_details[i] = arb_detail;
			charts[i].addNewPrice(arb_detail.getPresentPrice());
		}
		detailPanel.update(arb_lists);
		updateTable();
	}
}
