package edu.nju.treasuryArbitrage.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.nju.treasuryArbitrage.resources.NumericalResources;

import vo.Record;
import vo.Repository;

public class MyTableCellRenderer implements TableCellRenderer {
	private JPanel panel,p2;

    private JTable table;
    
    public MyTableCellRenderer(Repository repo) {
        initTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
 		tableModel.addRow(new Object[]{"TF1409","多头","93.123"});
 		tableModel.addRow(new Object[]{"TF1501","空头","95.123"});
        initPanel();
        p2 = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(p2,"North");
        p2.add(table,"North");
        p2.setBackground(Color.black);
    }

    private void initTable() {
    	table = new JTable(0,3);
    	table.setFocusable(false);
    	table.setRowHeight(38);
    	table.setForeground(Color.white);
    	table.setBackground(Color.black);

 		for(int i = 0;i<3;i++){
 			table.getColumn(table.getColumnName(i)).setMinWidth((NumericalResources.SCREEN_WIDTH - 685)/3);
 	 		table.getColumn(table.getColumnName(i)).setMaxWidth((NumericalResources.SCREEN_WIDTH - 685)/3);
 		}
    	DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
      	  table.getColumn(table.getColumnName(i)).setCellRenderer(render);
	      }
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(200,60));
        panel.setSize(200,60);
    }
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO 自动生成的方法存根
		return panel;
	}

}
