package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import edu.nju.treasuryArbitrage.model.Repository;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
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

		button.setText(value == null ? "" : "Æ½²Ö");
		repository = infom.get(row);
		sellPrice = sellPrices[row];
		buyPrice = buyPrices[row];
		return panel;
	}

	@Override
	public Object getCellEditorValue() {
		return "Æ½²Ö";
	}
}
