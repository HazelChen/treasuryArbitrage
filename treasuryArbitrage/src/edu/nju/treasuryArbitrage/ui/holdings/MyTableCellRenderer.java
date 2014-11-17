package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.nju.treasuryArbitrage.model.Arbitrage;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.model.TransactionUnit;

public class MyTableCellRenderer implements TableCellRenderer {
	private JPanel panel,p2;

    private JTable intable;
    private TransactionUnit[] infom;
    
    public MyTableCellRenderer(Repository[] info) {
        infom = new TransactionUnit[info.length];
        for (int i = 0;i < info.length; i++) {
        	Repository repository = info[i];
			Arbitrage toBuy = new Arbitrage(repository.getToBuy(), repository.gettoBuy_price());
			Arbitrage toSell = new Arbitrage(repository.getToSell(), repository.gettoSell_price());
			TransactionUnit unit = new TransactionUnit(toSell, toBuy);
			infom[i] = unit; 
		}
        init();
    }
    
    public MyTableCellRenderer(Record[] info) {
    	infom = new TransactionUnit[info.length];
    	for (int i = 0;i < info.length; i++) {
    		Record record = info[i];
    		TransactionUnit unit = new TransactionUnit(record.getToSell(), record.getToBuy());
    		infom[i] = unit; 
    	}
    	init();
    }
    
    private void init() {
    	initTable();
    	initPanel();
    	
    	p2 = new JPanel();
    	p2.setLayout(new BorderLayout());
    	
    	DefaultTableModel tableModel = (DefaultTableModel) intable.getModel();
    	tableModel.addRow(new Object[]{"","多头",""});
    	tableModel.addRow(new Object[]{"","空头",""});
    	panel.add(p2,"North");
    	p2.add(intable,"North");
    }
    
    

    private void initTable() {
    	intable = new JTable(0,3);
    	intable.setFocusable(false);
    	intable.setRowHeight(30);
    	
    	intable.setFont(new Font("微软雅黑", Font.PLAIN, 16));

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
		if (row >= infom.length) {
			return panel;
		}
		
		intable.setValueAt(infom[row].getToBuy().getId(), 0, 0);
		intable.setValueAt(infom[row].getToBuy().getValue(), 0, 2);
		intable.setValueAt(infom[row].getToSell().getId(), 1, 0);
		intable.setValueAt(infom[row].getToSell().getValue(), 1, 2);
		
		if (row % 2 == 0)
			intable.setBackground(Holdings.BACKGROUND_COLOR); // 设置奇数行底色 else
		if (row % 2 == 1)
			intable.setBackground(Holdings.TABLE_DARKER_BACKGROUND_COLOR);
		
		return panel;
	}
}
