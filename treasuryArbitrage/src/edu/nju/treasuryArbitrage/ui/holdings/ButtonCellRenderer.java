package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
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
        		100, 40);
        p2.setBackground(Color.black);
        p2.add(button,"Center");
    }

    private void initButton() {
        button = new JButton();
        button.setFocusable(false);
        button.setBackground(Color.white);
        button.setPreferredSize(new Dimension(60,25));
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
