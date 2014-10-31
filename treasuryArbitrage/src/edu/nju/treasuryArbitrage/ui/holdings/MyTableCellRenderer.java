package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

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
 		//Repository coding
        tableModel.addRow(new Object[]{"","��ͷ",""});
 		tableModel.addRow(new Object[]{"","��ͷ",""});
        panel.add(p2,"North");
        p2.add(intable,"North");
        //p2.setBackground(Color.blue);
        //panel.setBackground(Color.red);
    }

    private void initTable() {
    	intable = new JTable(0,3);
    	intable.setFocusable(false);
    	intable.setRowHeight(40);
    	intable.setForeground(Color.white);
    	intable.setBackground(Color.black);

 		for(int i = 0;i<intable.getColumnCount();i++){
 			intable.getColumn(intable.getColumnName(i)).setMinWidth((ScreenSize.WIDTH - 685)/3);
 	 		intable.getColumn(intable.getColumnName(i)).setMaxWidth((ScreenSize.WIDTH - 685)/3);
 		}
    	DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < intable.getColumnCount(); i++) {
      	  intable.getColumn(intable.getColumnName(i)).setCellRenderer(render);
	      }
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setBackground(Color.black);
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