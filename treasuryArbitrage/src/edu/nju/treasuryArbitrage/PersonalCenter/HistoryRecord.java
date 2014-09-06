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
		Object[][] cells={{"���������Ϣ", "����ʱ��","��������","Ͷ�뱣֤��","����״̬"}};
		//Object[][] cellData = {  {"���������Ϣ", "����ʱ��","��������","Ͷ�뱣֤��","����״̬"}, {"���������Ϣ", "����ʱ��","��������","Ͷ�뱣֤��","����״̬"}};
		String[] columnNames =  {"���������Ϣ", "����ʱ��","��������","Ͷ�뱣֤��","����״̬"};
		
		DefaultTableModel aaa = new MYModel(cells, columnNames){
			private static final long serialVersionUID = 4811840115068048761L;
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		JTable jjJTable = new JTable(aaa);
		Object[][] cellData = {  {jjJTable, "����ʱ��","��������","Ͷ�뱣֤��","����״̬"}, {jjJTable, "����ʱ��","��������","Ͷ�뱣֤��","����״̬"}};
		
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


