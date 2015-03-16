package edu.nju.treasuryArbitrage.view.personalCenter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.Record;


public class HistoryRecord extends JPanel {
	private static final long serialVersionUID = -4056530132719983788L;
	Object[][] data = null;
	String[] columns ={ "锟斤拷锟斤拷锟斤拷锟斤拷锟较�", "锟斤拷锟斤拷时锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷", "投锟诫保证锟斤拷", "锟斤拷锟斤拷状态" };
	JTable jTableData;
	JScrollPane jScrollPane;
	private DataInterface service = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
	
	public void getData(){
		ArrayList<Record> records = service.getRecordList();
		data = new Object[records.size()][columns.length];
		for(int i = 0 ; i < records.size() ; i++){
			String[] strings = {records.get(i).getToBuy().getId(),"锟斤拷头",Double.toString(records.get(i).getToBuy().getValue()),
					records.get(i).getToSell().getId(),"锟斤拷头",Double.toString(records.get(i).getToSell().getValue())};
			data[i][0]=strings;
			Date date = new Date(records.get(i).getTime());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   
			data[i][1]=sdf.format(date);
			data[i][2]=String.valueOf(records.get(i).getCount());
			data[i][3]=String.valueOf(records.get(i).getGuarantee());
			data[i][4]=records.get(i).getState();
		}
	}

	public HistoryRecord() {
		this(980, 400);
	}
	public HistoryRecord(int w,int h) {
		init(w,h);
		
	}
	
	private void init(int w,int h){
		jTableData = new JTable();
		getData();
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
					return jTable;
				}
			}
		};
		jTableData.setDefaultRenderer(Object.class, jTableCellRenderer);
		jTableData.getTableHeader().setBackground(Color.BLACK);
		jTableData.getTableHeader().setFont(new Font("",Font.PLAIN,24));
		jTableData.getTableHeader().setForeground(Color.ORANGE);
		setLayout(null);
		jTableData.setBackground(Color.BLACK);
		jTableData.getTableHeader().setReorderingAllowed(false);
		jScrollPane=new JScrollPane(jTableData);
		jScrollPane.setBackground(Color.BLACK);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		add(jScrollPane);
		if(jTableData.getRowCount()*60+38<h){
			jScrollPane.setBounds(0, 0, w, jTableData.getRowCount()*60+38);
		}else{
			jScrollPane.setBounds(0, 0, w, h);
		}
		setBackground(Color.BLACK);
	}
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		JPanel jPanel = new HistoryRecord(980, 400);
		jFrame.add(jPanel);
		jFrame.setSize(980, 400);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}


