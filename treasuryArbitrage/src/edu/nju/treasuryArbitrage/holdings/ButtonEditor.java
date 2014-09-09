package edu.nju.treasuryArbitrage.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6546334664166791132L;

    private JPanel panel,p2;

    private JButton button;

    public ButtonEditor() {

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

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                //stopped!!!!
                fireEditingStopped();

            }
        });

    }

    private void initPanel() {
        panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(Color.black);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        
        button.setText(value == null ? "" : "Æ½²Ö");

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return "Æ½²Ö";
    }
}
