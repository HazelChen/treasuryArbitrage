package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MessageCenter extends JPanel{

	private static final long serialVersionUID = 5622073443448011691L;
	
	private JLabel bxJLabel; 
	private JLabel reward ;
	private ImageIcon icon;
	private ImageIcon icon1;
	private ImageIcon icon2;
	private ImageIcon icon3;
	private JTable table;
	private JComboBox<Object> dbtype;
	
	public MessageCenter(){
		this(600, 400);
	}
	public MessageCenter(int w,int h){
		init(w,h);
		table.setBounds( 0 , 45 , w , h/2);
	}
	private void init(int w,int h){
		setBackground(Color.BLACK);
		setLayout(null);
		icon = new ImageIcon("image/icon.jpg");
		icon1 = new ImageIcon("image/icon2.jpg");
		icon2 = new ImageIcon("image/icon3.jpg");
		icon3 = new ImageIcon("image/icon4.jpg");
		bxJLabel = new JLabel(icon);
		
		dbtype = new JComboBox<>();
		dbtype.addItem("全选");
		dbtype.addItem("不选");
		dbtype.addItem("未读");
		dbtype.addItem("已读");
		dbtype.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
		add(dbtype);
		dbtype.setBounds(30, 20, 28, 25);
		add(bxJLabel);
		bxJLabel.setBounds(0, 20, 30, 25);
		setComponentZOrder(dbtype, 1);
		setComponentZOrder(bxJLabel, 0);
		
		table = setTable(w,h);
		
		add(table);
		table.setBounds(0,45, 600,200);
		
		JButton deleteButton = new JButton(new ImageIcon("image/删除.jpg"));
		add(deleteButton);
		
		deleteButton.setBounds(80, 20, 40, 25);
		setPreferredSize(new Dimension(600, 400));
		
		bxJLabel.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bxJLabel.getIcon().equals(icon)){
					bxJLabel.setIcon(icon1);
				}else {
					bxJLabel.setIcon(icon);
				}
			}
		});
		setReword(3);
	}
	
	
	
	private void setReword(int i){
		reward =  new JLabel("有"+i+"条未读信息");
		reward.setForeground(Color.WHITE);
		add(reward);
		reward.setBounds(150, 20, 100, 25);
	}
	
	private Object[][] setCellObjects(){
		Object[][] cellData = {{new ImageIcon(), "未读","编号","标题","时间"},{icon2,"否","1","有重大套利机会","2014-1-1 12:00:12"},{icon2,"否","2","有重大套利机会","2014-1-1 12:00:12"}
		,{icon2,"否","3","有重大套利机会","2014-1-1 12:00:12"}};
		return cellData;
	}
	
	
	
	private JTable setTable(int w,int h){
		Object[][] cellData = setCellObjects();
		String[] columnNames =  {"a", "未读","编号","标题","时间"};
	
		DefaultTableModel myTableModel = new MYModel(cellData, columnNames){
			private static final long serialVersionUID = 4811840115068048761L;
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
		final JTable table = new JTable(myTableModel);
		
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
		
		table.getTableHeader().setDefaultRenderer(render);
		table.setDefaultRenderer(Object.class, render);
		table.setRowHeight(30);
		table.setBackground(Color.BLACK);
		table.setGridColor(Color.BLACK);
		table.getTableHeader().setBackground(Color.BLACK);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setBorder(null);
		table.setShowGrid(false);
		TableColumn column1 = table.getColumnModel().getColumn(0);
		column1.setPreferredWidth(30*w/600);
		column1.setMaxWidth(30*w/600);
		column1.setMinWidth(30*w/600);
		TableColumn column2 = table.getColumnModel().getColumn(1);
		column2.setPreferredWidth(50*w/600);
		column2.setMaxWidth(50*w/600);
		column2.setMinWidth(50*w/600);
		TableColumn column3 = table.getColumnModel().getColumn(2);
		column3.setPreferredWidth(50*w/600);
		column3.setMaxWidth(50*w/600);
		column3.setMinWidth(50*w/600);
		TableColumn column4 = table.getColumnModel().getColumn(3);
		column4.setPreferredWidth(250*w/600);
		column4.setMaxWidth(250*w/600);
		column4.setMinWidth(250*w/600);
		TableColumn column5 = table.getColumnModel().getColumn(4);
		column5.setPreferredWidth(200*w/600);
		column5.setMaxWidth(200*w/600);
		column5.setMinWidth(200*w/600);
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				int column;
				row= table.getSelectedRow();
				column = table.getSelectedColumn();
				if(row>0){
					if(column==0){
						if(table.getValueAt(row, column).equals(icon2)){
							table.setValueAt(icon3, row, column);
						}else{
							table.setValueAt(icon2, row, column);
						}
					}else{
						
					}
				}
				
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		return table;
	}
	
	public static void main (String[] args) {
		JFrame jFrame = new JFrame();
		MessageCenter jPanel = new MessageCenter(400,300);
		jFrame.add(jPanel);
		jFrame.setSize(400,300);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	


}

class MYModel extends DefaultTableModel {
	private static final long serialVersionUID = 4752491024931735237L;
	public MYModel(Vector<?> cells,Vector<?> columnNames){
		  super(cells,columnNames);
	}

	public MYModel(Object[][] cellData, String[] columnNames) {
		super(cellData,columnNames);
	}

	@Override
	public Class<?> getColumnClass(int col) {
	    Vector<?> v=(Vector<?>)dataVector.elementAt(0);
	    if(v.elementAt(col)==null){
	    	return "".getClass();
	    }else{
	    	return v.elementAt(col).getClass();
	    }
	}
	
}
