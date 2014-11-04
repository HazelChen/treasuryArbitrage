package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.nju.treasuryArbitrage.model.Repository;

public class MyTableCellRenderer implements TableCellRenderer {
	private JPanel panel,p2;

    private JTable intable;
    private int rowN;
    private ArrayList<Repository> infom;
    
    public MyTableCellRenderer(ArrayList<Repository> info) {
        initTable();
        initPanel();
        infom = new ArrayList<Repository>();
        infom = info;
        p2 = new JPanel();
        p2.setLayout(new BorderLayout());

        DefaultTableModel tableModel = (DefaultTableModel) intable.getModel();
        tableModel.addRow(new Object[]{"","∂‡Õ∑",""});
 		tableModel.addRow(new Object[]{"","ø’Õ∑",""});
        panel.add(p2,"North");
        p2.add(intable,"North");
    }

    private void initTable() {
    	intable = new JTable(0,3);
    	intable.setFocusable(false);
    	intable.setRowHeight(30);
    	intable.setBackground(HoldingsChen.BACKGROUND_COLOR);
    	
    	intable.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 16));

    	DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < intable.getColumnCount(); i++) {
      	  intable.getColumn(intable.getColumnName(i)).setCellRenderer(render);
	    }
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(200,80));
        panel.setSize(200,80);
    }
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		rowN = row;
		intable.setValueAt(infom.get(rowN).getToBuy(), 0, 0);
		intable.setValueAt(infom.get(rowN).gettoBuy_price(), 0, 2);
		intable.setValueAt(infom.get(rowN).getToSell(), 1, 0);
		intable.setValueAt(infom.get(rowN).gettoSell_price(), 1, 2);
		return panel;
	}

}
