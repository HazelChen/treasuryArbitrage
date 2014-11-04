package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class ButtonCellRenderer implements TableCellRenderer {
	private JPanel panel;

	private JButton button;

	public ButtonCellRenderer() {
		initButton();

		initPanel();

		panel.add(button);
	}

	private void initButton() {
		button = new JButton();
		button.setFocusable(false);
//		button.setBackground(Color.white);
//		button.setPreferredSize(new Dimension(60, 25));
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalAlignment(SwingConstants.CENTER);
	}

	private void initPanel() {
		panel = new JPanel();

		panel.setBackground(HoldingsChen.BACKGROUND_COLOR);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		button.setText(value == null ? "" : "Æ½²Ö");

		return panel;
	}
}
