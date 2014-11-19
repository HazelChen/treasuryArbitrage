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

		button.setText(value == null ? "" : "ƽ��");
		repository = infom.get(row);
		sellPrice = sellPrices[row];
		buyPrice = buyPrices[row];
		return panel;
	}

	@Override
	public Object getCellEditorValue() {
		return "ƽ��";
	}

	class SellDg extends JDialog {
		private static final long serialVersionUID = 1997741175987123971L;
		private DataInterface di;
		private SellDg curdg = this;
		sellML listener;
		JLabel confirmL;
		JTable table;
		JPanel btnpanel, panel;
		Object data[][] = { { "��Լ����:", "", "" }, { "��������:", "" },
				{ "��������:", "ƽ��" }, { "��������:", "" }, { "��Լ�۸�:", "" },
				{ "Ͷ�뱣֤��:", "" }, { "����/��ʧ:", "" }, };
		Object cnames[] = { "name", "data" };
		private JButton btnY;
		private JButton btnC;
		private DefaultTableCellRenderer tcr;

		SellDg(Repository repo) {
			di = DataInterfaceFactory.getInstance().getDataInterfaceToServer();

			data[0][1] = repo.getToBuy() + " " + repo.getToSell();
			data[1][1] = repo.getCount();
			data[2][1] = "ƽ��";// ��������
			data[3][1] = "<html><B>" + repo.getToBuy() + ": ��ͷ<br>"
					+ repo.getToSell() + ": ��ͷ"; // ��������
			data[4][1] = "<html><B>" + repo.getToBuy() + ": "
					+ repo.gettoBuy_price() + "<br>" + repo.getToSell() + ": "
					+ repo.gettoSell_price();// ��Լ�۸�
			data[5][1] = repo.getGuarantee() + " Ԫ";
			data[6][1] = repo.getProfit() + " Ԫ";
			listener = new sellML();
			setBackground(Holdings.BACKGROUND_COLOR);
			setMaximumSize(new Dimension(400, 350));
			setMinimumSize(new Dimension(400, 350));
			setResizable(false);
			setModal(true);//
			btnY = new JButton("ȷ��ƽ��");
			btnC = new JButton("ȡ��");
			btnY.addMouseListener(listener);
			btnC.addMouseListener(listener);
			confirmL = new JLabel("��ȷ����Ϣ��ȷ����ȷ��");
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
				}// ��������༭
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
			Font tablef = new Font("����", Font.PLAIN, 16);
			table.setFont(tablef);
			table.setGridColor(Color.white);
			table.setSelectionBackground(Color.white);
			table.setSelectionForeground(Color.black);
			table.setIntercellSpacing(new Dimension(0, 0));// �޸ĵ�Ԫ���������Ҳ��Ӱ�������ߵĴ�ϸ��
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
						setFont(new Font("����", Font.BOLD, 16));
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

					// -------------------������Ӧ��Լ-----
					// repository; �������ݿ�
					repository.update();
					boolean result = di.Trade(repository.getRepo_ID(),
							repository.getProfit(),
							repository.getBuyPrecentPrice(),
							repository.getSellPrecentPrice());
					if (result == true) {
						JOptionPane.showMessageDialog(null, "�µ��ɹ���");
					} else {
						JOptionPane.showMessageDialog(null, "ƽ��ʧ�ܣ�");
					}
					// JOptionPane.showMessageDialog(null, "����");
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
