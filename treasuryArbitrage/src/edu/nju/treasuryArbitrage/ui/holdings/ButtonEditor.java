package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.Repository;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6546334664166791132L;

	private JPanel panel;

	private JButton button;
	private ArrayList<Repository> infom;
	private double[] sellPrices;
	private double[] buyPrices;

	private Repository repository;
	private double sellPrice;
	private double buyPrice;

	public ButtonEditor(ArrayList<Repository> info, double[] sellPrices,
			double[] buyPrices) {

		initButton();

		initPanel();
		infom = info;
		this.sellPrices = sellPrices;
		this.buyPrices = buyPrices;
		panel.add(button);
	}

	private void initButton() {
		button = new JButton();
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SellDialog sellDialog = new SellDialog(repository, buyPrice,
						sellPrice);
				sellDialog.setVisible(true);
				// stop!!!
				fireEditingStopped();
			}
		});

	}

	private void initPanel() {
		panel = new JPanel();
		panel.setBackground(Holdings.BACKGROUND_COLOR);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		button.setText(value == null ? "" : "平仓");
		repository = infom.get(row);
		sellPrice = sellPrices[row];
		buyPrice = buyPrices[row];
		return panel;
	}

	@Override
	public Object getCellEditorValue() {
		return "平仓";
	}

	class SellDg extends JDialog {
		private static final long serialVersionUID = 1997741175987123971L;
		private DataInterface di;
		private SellDg curdg = this;
		sellML listener;
		JLabel confirmL;
		JTable table;
		JPanel btnpanel, panel;
		Object data[][] = { { "合约名称:", "", "" }, { "卖出手数:", "" },
				{ "交易类型:", "平仓" }, { "套利方向:", "" }, { "合约价格:", "" },
				{ "投入保证金:", "" }, { "收益/损失:", "" }, };
		Object cnames[] = { "name", "data" };
		private JButton btnY;
		private JButton btnC;
		private DefaultTableCellRenderer tcr;

		SellDg(Repository repo) {
			di = DataInterfaceFactory.getInstance().getDataInterfaceToServer();

			data[0][1] = repo.getToBuy() + " " + repo.getToSell();
			data[1][1] = repo.getCount();
			data[2][1] = "平仓";// 交易类型
			data[3][1] = "<html><B>" + repo.getToBuy() + ": 多头<br>"
					+ repo.getToSell() + ": 空头"; // 套利方向
			data[4][1] = "<html><B>" + repo.getToBuy() + ": "
					+ repo.gettoBuy_price() + "<br>" + repo.getToSell() + ": "
					+ repo.gettoSell_price();// 合约价格
			data[5][1] = repo.getGuarantee() + " 元";
			data[6][1] = repo.getProfit() + " 元";
			listener = new sellML();
			setBackground(Holdings.BACKGROUND_COLOR);
			setMaximumSize(new Dimension(400, 350));
			setMinimumSize(new Dimension(400, 350));
			setResizable(false);
			setModal(true);//
			btnY = new JButton("确认平仓");
			btnC = new JButton("取消");
			btnY.addMouseListener(listener);
			btnC.addMouseListener(listener);
			confirmL = new JLabel("请确认信息正确后点击确认");
			confirmL.setForeground(Color.RED);
			this.setLocationRelativeTo(null);
			panel = new JPanel();
			btnpanel = new JPanel();
			btnpanel.setLayout(new BorderLayout());
			JPanel p2 = new JPanel();
			p2.setBackground(Color.white);
			p2.add(confirmL, "Center");
			btnpanel.add(p2, "North");
			JPanel p3 = new JPanel();
			JLabel inv = new JLabel("   ");
			inv.setPreferredSize(new Dimension(15, 20));
			p3.add(btnY);
			p3.add(inv);
			p3.add(btnC);
			p3.setBackground(Color.white);
			btnpanel.add(p3, "Center");
			JPanel p4 = new JPanel();
			p4.setBackground(Color.white);
			btnpanel.add(p4, "South");
			btnpanel.setPreferredSize(new Dimension(280, 76));
			btnpanel.setBackground(Color.white);
			table = new JTable(data, cnames) {
				private static final long serialVersionUID = -3991690351227862819L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}// 表格不允许被编辑
			};
			makeface(table);

			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(280, 350));
			panel.setBackground(Color.white);
			panel.add(table, BorderLayout.NORTH);
			panel.add(btnpanel, BorderLayout.SOUTH);
			add(panel);
			this.pack();
		}

		private void makeface(JTable table) {
			table.setRowHeight(30);
			table.setRowMargin(0);
			table.setRowHeight(3, 60);
			table.setRowHeight(4, 60);
			Font tablef = new Font("宋体", Font.PLAIN, 16);
			table.setFont(tablef);
			table.setGridColor(Color.white);
			table.setSelectionBackground(Color.white);
			table.setSelectionForeground(Color.black);
			table.setIntercellSpacing(new Dimension(0, 0));// 修改单元格间隔，因此也将影响网格线的粗细。
			table.setFocusable(false);
			table.setBackground(Color.white);
			tcr = new DefaultTableCellRenderer() {
				private static final long serialVersionUID = -5470081118201957800L;

				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {

					if (column == 0) {
						setHorizontalAlignment(SwingConstants.RIGHT);
					} else {
						setHorizontalAlignment(SwingConstants.LEFT);
					}
					if (column == 1 && row == 3) {
						setFont(new Font("宋体", Font.BOLD, 16));
					}
					setForeground(Color.BLACK);
					setBackground(Color.white);
					return super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column);
				}
			};
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}

		}

		class sellML implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == btnY) {
					curdg.setVisible(false);

					// -------------------卖出对应合约-----
					// repository; 更新数据库
					repository.update();
					boolean result = di.Trade(repository.getRepo_ID(),
							repository.getProfit(),
							repository.getBuyPrecentPrice(),
							repository.getSellPrecentPrice());
					if (result == true) {
						JOptionPane.showMessageDialog(null, "下单成功！");
					} else {
						JOptionPane.showMessageDialog(null, "平仓失败！");
					}
					// JOptionPane.showMessageDialog(null, "更新");
					MajorPartsFactory.getInstance().getHoldings().updatePage();
					// --------------------------------
					curdg.dispose();
				} else if (e.getSource() == btnC) {
					curdg.setVisible(false);
					curdg.dispose();
				}
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		}

	}
}
