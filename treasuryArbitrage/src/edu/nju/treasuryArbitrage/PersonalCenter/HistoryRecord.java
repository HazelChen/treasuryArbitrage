package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


public class HistoryRecord extends JPanel {
	private static final long serialVersionUID = -4056530132719983788L;
	Object[][] data = null;
	String[] columns ={ "套利组合信息", "交易时间", "交易手数", "投入保证金", "交易状态" };
	JTable jTableData;
	JScrollPane jScrollPane;
	
	public Object[][] getData(){
		data = new Object[4][columns.length];
		String[] strings = {"TF1409","多头","93.066","TF1409","多头","93.066"};
		data[0][0]=strings;
		data[0][1]="2014-07-18 19:32:40";
		data[0][2]="2";
		data[0][3]="20000";
		data[0][4]="成交1";
		String[] strings2 = {"TF1409","多头","93.066","TF1409","多头","93.066"};
		data[1][0]=strings2;
		data[1][1]="2014-07-18 19:32:40";
		data[1][2]="2";
		data[1][3]="20000";
		data[1][4]="成交2";
		String[] strings3 = {"TF1409","多头","93.066","TF1409","多头","93.066"};
		data[2][0]=strings3;
		data[2][1]="2014-07-18 19:32:40";
		data[2][2]="2";
		data[2][3]="20000";
		data[2][4]="成交3";
		String[] strings4 = {"TF1409","多头","93.066","TF1409","多头","93.066"};
		data[3][0]=strings4;
		data[3][1]="2014-07-18 19:32:40";
		data[3][2]="2";
		data[3][3]="20000";
		data[3][4]="成交4";
		return data;
	}

	public HistoryRecord() {
		init();
	}
	public HistoryRecord(int w,int h) {
		init();
		if(jTableData.getRowCount()*60+38<h){
			jScrollPane.setBounds(0, 0, w, jTableData.getRowCount()*60+38);
		}else{
			jScrollPane.setBounds(0, 0, w, h);
		}
	}
	
	private void init(){
		jTableData = new JTable();
		data = getData();
		AbstractTableModel modelo = new AbstractTableModel(){
			private static final long serialVersionUID = 6795312350333403806L;
			public String getColumnName(int col){
				return columns[col].toString();
			}

			public Class<?> getColumnClass(int col){
				if (getRowCount() < 1)
					return null;
				return data[0][col].getClass();
			}

			public int getRowCount(){
				return data.length;
			}

			public int getColumnCount(){
				return columns.length;
			}

			public Object getValueAt(int row, int col)	{
				return data[row][col];
			}

			public boolean isCellEditable(int row, int col)	{
				return false;
			}

			public void setValueAt(Object value, int row, int col)	{
				data[row][col] = value;
				fireTableCellUpdated(row, col);
			}
		};
		jTableData.setModel(modelo);
		DefaultTableCellRenderer jTableCellRenderer = new DefaultTableCellRenderer(){
			private static final long serialVersionUID = 4316981080467367416L;
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column){
				if (!value.getClass().isArray()){
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				    this.setHorizontalAlignment(SwingConstants.CENTER);
				    this.setBackground(Color.BLACK);
				    this.setForeground(Color.WHITE);
				    this.setPreferredSize(new Dimension(140, 60));
					return this;
				}else{
					final Object[] passed = (Object[]) value;
					table.setRowHeight(60);
					JTable jTable  =  new JTable(new AbstractTableModel(){
						private static final long serialVersionUID = 8376204668101313599L;
						public int getColumnCount()	{
							return 3;
						}
						public int getRowCount(){
							return 2;
						}
						public Object getValueAt(int rowIndex, int columnIndex)	{
							return passed[rowIndex*3+columnIndex];
						}
						public boolean isCellEditable(int row, int col)	{
							return false;
						}
					});
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
					
					jTable.setDefaultRenderer(Object.class, render);
					jTable.setRowHeight(30);
					/*TableColumn column1 = jTable.getColumnModel().getColumn(0);
					column1.setPreferredWidth(140);
					column1.setMaxWidth(140);
					column1.setMinWidth(140);
					TableColumn column2 = jTable.getColumnModel().getColumn(1);
					column2.setPreferredWidth(140);
					column2.setMaxWidth(140);
					column2.setMinWidth(140);
					TableColumn column3 = jTable.getColumnModel().getColumn(2);
					column3.setPreferredWidth(140);
					column3.setMaxWidth(140);
					column3.setMinWidth(140);*/
					return jTable;
				}
			}
		};
		jTableData.setDefaultRenderer(Object.class, jTableCellRenderer);
		jTableData.getTableHeader().setBackground(Color.BLACK);
		jTableData.getTableHeader().setFont(new Font("",Font.PLAIN,24));
		jTableData.getTableHeader().setForeground(Color.ORANGE);
		/*TableColumn column1 = jTableData.getColumnModel().getColumn(0);
		column1.setPreferredWidth(420);
		column1.setMaxWidth(420);
		column1.setMinWidth(420);
		TableColumn column2 = jTableData.getColumnModel().getColumn(0);
		column2.setPreferredWidth(140);
		column2.setMaxWidth(140);
		column2.setMinWidth(140);
		TableColumn column3 = jTableData.getColumnModel().getColumn(0);
		column3.setPreferredWidth(140);
		column3.setMaxWidth(140);
		column3.setMinWidth(140);
		TableColumn column4 = jTableData.getColumnModel().getColumn(0);
		column4.setPreferredWidth(140);
		column4.setMaxWidth(140);
		column4.setMinWidth(140);
		TableColumn column5 = jTableData.getColumnModel().getColumn(0);
		column5.setPreferredWidth(140);
		column5.setMaxWidth(140);
		column5.setMinWidth(140);*/
		setLayout(null);
		jTableData.setBackground(Color.BLACK);
		jScrollPane=new JScrollPane(jTableData);
		jScrollPane.setBackground(Color.BLACK);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		add(jScrollPane);
		if(jTableData.getRowCount()*60+38<400){
			jScrollPane.setBounds(0, 0, 980, jTableData.getRowCount()*60+38);
		}else{
			jScrollPane.setBounds(0, 0, 980, 400);
		}
		setPreferredSize(new Dimension(980, 400));
		setBackground(Color.BLACK);
	}


}


