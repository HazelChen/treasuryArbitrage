package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HistoryRecord extends JPanel {

	private static final long serialVersionUID = -7597398843398944418L;
	
	private JTable jTable;
	
	private JScrollPane jScrollPane;
	
	public HistoryRecord(){
		init();
	}
	
	private void init(){
		jScrollPane = new JScrollPane();
		Object[][] cells={{"套利组合信息", "交易时间","交易手数","投入保证金","交易状态"}};
		//Object[][] cellData = {  {"套利组合信息", "交易时间","交易手数","投入保证金","交易状态"}, {"套利组合信息", "交易时间","交易手数","投入保证金","交易状态"}};
		String[] columnNames =  {"套利组合信息", "交易时间","交易手数","投入保证金","交易状态"};
		
		DefaultTableModel aaa = new MYModel(cells, columnNames){
			private static final long serialVersionUID = 4811840115068048761L;
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		JTable jjJTable = new JTable(aaa);
		Object[][] cellData = {  {jjJTable, "交易时间","交易手数","投入保证金","交易状态"}, {jjJTable, "交易时间","交易手数","投入保证金","交易状态"}};
		
		DefaultTableModel myTableModel = new MYModel(cellData, columnNames){
			private static final long serialVersionUID = 4811840115068048761L;
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		jTable = new JTable(myTableModel);
		System.out.println(jjJTable.getClass());
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer(){
			private static final long serialVersionUID = -2049950775340871250L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				      boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			    setHorizontalAlignment(SwingConstants.CENTER);
			    setBackground(Color.BLACK);
			    setForeground(Color.WHITE);
			    return this;
			}
		};
		
		jTable.getTableHeader().setDefaultRenderer(render);
		jTable.setDefaultRenderer(String.class, render);
		jTable.setGridColor(Color.BLACK);
		jScrollPane.getViewport().add(jTable);
		
		setLayout(null);
		add(jScrollPane);
		jScrollPane.setBounds(0,0,1000,480);
	}
	
	

}


