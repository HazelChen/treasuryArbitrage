package edu.nju.treasuryArbitrage.view.holdings;

import java.awt.Component;
import java.awt.Cursor;

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

		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		button.setBackground(Color.white);
//		button.setPreferredSize(new Dimension(60, 25));
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalAlignment(SwingConstants.CENTER);
	}

	private void initPanel() {
		panel = new JPanel();

//		panel.setBackground(HoldingsChen.BACKGROUND_COLOR);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		button.setText(value == null ? "" : "ƽ��");

		if (row % 2 == 0)
			panel.setBackground(Holdings.BACKGROUND_COLOR); // ���������е�ɫ else
		if (row % 2 == 1)
			panel.setBackground(Holdings.TABLE_DARKER_BACKGROUND_COLOR);
		
		return panel;
	}
}
