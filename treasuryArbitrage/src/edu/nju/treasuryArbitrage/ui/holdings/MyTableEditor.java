package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;


public class MyTableEditor implements TableCellEditor {
	private JPanel panel;

    private JTable table;
    
    public MyTableEditor(ArrayList<Repository> info) {
        initTable();

        initPanel();

        panel.add(this.table,"Center");
    }

    private void initTable() {
    	table = new JTable();
    	table.setFocusable(false);
    	table.setSize(200, 60);
    	
    	table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
            
    	});
    }

    private void initPanel() {
        panel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(200,60));
        panel.setSize(200,60);
    }
    
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO 自动生成的方法存根
		return panel;
	}

	@Override
    public Object getCellEditorValue() {return "";}
	public boolean isCellEditable(EventObject anEvent) {return false;}
	public boolean shouldSelectCell(EventObject anEvent) {return false;}
	public boolean stopCellEditing() {return true;	}
	public void cancelCellEditing() {}
	public void addCellEditorListener(CellEditorListener l) {}
	public void removeCellEditorListener(CellEditorListener l) {}

}
