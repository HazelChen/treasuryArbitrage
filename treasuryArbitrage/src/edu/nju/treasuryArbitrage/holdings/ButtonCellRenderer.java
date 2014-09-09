package edu.nju.treasuryArbitrage.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class ButtonCellRenderer implements TableCellRenderer {
	private JPanel panel,p2;

    private JButton button;
    
    
    public ButtonCellRenderer() {
        initButton();

        initPanel();
        
        p2 = new JPanel();
        panel.add(p2);

        p2.setBounds(20,20, 
        		100, 30);
        p2.setBackground(Color.black);
        p2.add(button,"Center");
    }

    private void initButton() {
        button = new JButton();
        button.setFocusable(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        
    }

    private void initPanel() {
        panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(Color.black);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        button.setText(value == null ? "" : "Æ½²Ö");

        return panel;
    }
}
