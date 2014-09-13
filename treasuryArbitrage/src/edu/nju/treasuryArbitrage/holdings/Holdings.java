package edu.nju.treasuryArbitrage.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import vo.Finance;
import vo.Record;
import vo.Repository;
import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.network.DataInterfacePile;
import edu.nju.treasuryArbitrage.resources.NumericalResources;

public class Holdings extends JPanel{
	private static final long serialVersionUID = 6470810944009110491L;
	private DataInterface di;
	static ArrayList<Finance> fTableRec;
	static ArrayList<Repository> info;
	static Object colummnames1[]={"时间","总资金","已投入保证金","空闲资金"},
			colummnames2[]={"套利组合信息","交易时间","交易手数","投入保证金","即时损失/盈利金额","操作"},
			empOb[][] = {{"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""}};//
	JPanel con,panel1,panel2,p11,p21;
	JScrollPane jsp1,jsp2;
	JLabel l1,l2;
	JButton refreshBtn,doBtn;
	JTable fTableHeader,fTable,hTableHeader,hTable;
	Color lbg = new Color(217,122,91),
			rbtnbg = new Color(41,128,185);
	private static TableCellRenderer tcr22;

	private static DefaultTableCellRenderer tcr1;
	
	public Holdings() {
		di = new DataInterfacePile();
		this.setBackground(Color.DARK_GRAY);
		
		l1 = new JLabel("  资金状况  ",JLabel.CENTER);
		l1.setForeground(Color.WHITE);l1.setBackground(lbg);
		l1.setOpaque(true);//设置组件JLabel不透明，只有设置为不透明，设置背景色才有效 
		l2 = new JLabel("  持仓状况  ",JLabel.CENTER);
		l2.setForeground(Color.WHITE);l2.setBackground(lbg);
		l2.setOpaque(true);//设置组件JLabel不透明，只有设置为不透明，设置背景色才有效 
		
		refreshBtn = new JButton("刷新");    refreshBtn.setFocusPainted(false);
		refreshBtn.setBackground(rbtnbg); 
		refreshBtn.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {updateRepoList();}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {updateRepoList();}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
 		doBtn = new JButton("平仓");    doBtn.setFocusPainted(false);
 		
 		fTableHeader = new JTable(0,4){
			public boolean isCellEditable(int row, int column){return false;}//表格不允许被编辑
    	};
    	fTableHeader.setFocusable(false); 
    	DefaultTableModel tableModel11 = (DefaultTableModel) fTableHeader.getModel();
    	fTableHeader.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	   tableModel11.addRow(colummnames1);
	          makeFace(fTableHeader);
	 	  fTable = new JTable(0,4){
				public boolean isCellEditable(int row, int column){return false;}//表格不允许被编辑
	    	};
	    	fTable.setFocusable(false); 
	    	DefaultTableModel tableModel12 = (DefaultTableModel) fTable.getModel();
		       fTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	  // fTable.setIntercellSpacing(new Dimension(0,1));//修改单元格间隔，因此也将影响网格线的粗细。 
	        //  fTable.setRowMargin(0);//设置相邻两行单元格的距离
	          fTable.getTableHeader().setPreferredSize(new Dimension(0,0));
	          fTable.getTableHeader().setVisible(false);
	          makeFace2(fTable);
		 	   updateFTable();
	    jsp1 = new JScrollPane(fTable);
	    jsp1.getViewport().setBackground(Color.black);
 		p11 = new JPanel();
 		p11.setLayout(new BorderLayout());
 		p11.setBackground(Color.DARK_GRAY);
 		p11.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, 50));
 		p11.add(l1,"West");
 		p11.add(fTableHeader,"South");
 		panel1 = new JPanel();
 		panel1.setBackground(Color.black);
 		panel1.setLayout(new BorderLayout());
 		panel1.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, 310));
 		panel1.add(p11,"North");
 		panel1.add(jsp1,"Center");
 		
 		hTableHeader = new JTable(0,6){
			public boolean isCellEditable(int row, int column){return false;}//表格不允许被编辑
 		};
    	hTableHeader.setFocusable(false); 
 		hTableHeader.getTableHeader().setPreferredSize(new Dimension(0,0));
 		hTableHeader.getTableHeader().setVisible(false);
 		makeFace(hTableHeader);
 		for(int i = 1;i<6;i++){
 	 		hTableHeader.getColumn(hTableHeader.getColumnName(i)).setMinWidth(137);
 	 		hTableHeader.getColumn(hTableHeader.getColumnName(i)).setMaxWidth(137);
 		}
 		DefaultTableModel tableModel21 = (DefaultTableModel) hTableHeader.getModel();
	 	   tableModel21.addRow(colummnames2); 

	 	info = di.getRepoList();//从服务器获取数据
 		hTable = new JTable(new DefaultTableModel() {
 			@Override
            public Object getValueAt(int row, int column) {
            return empOb[row][column];
 			}
            @Override
            public void setValueAt(Object aValue, int row, int column){
            	empOb[row][column] = aValue;
                fireTableCellUpdated(row, column);
            }
            @Override
            public int getRowCount() {
                return info.size();
            }
            @Override
            public int getColumnCount() {
                return 6;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        });
 		DefaultTableModel tableModel22 = (DefaultTableModel) hTable.getModel();
        hTable.getTableHeader().setPreferredSize(new Dimension(0,0));
        hTable.getTableHeader().setVisible(false);
 		makeFace2(hTable);
 		updateRepoList();
		hTable.setRowHeight(80);
 		for(int i = 1;i<5;i++){
 			hTable.getColumn(hTable.getColumnName(i)).setMinWidth(
 					hTableHeader.getColumn(hTableHeader.getColumnName(i)).getMinWidth());
 	 		hTable.getColumn(hTable.getColumnName(i)).setMaxWidth(
 					hTableHeader.getColumn(hTableHeader.getColumnName(i)).getMaxWidth());
 		}
 		hTable.getColumn(hTable.getColumnName(5)).setMinWidth(135);
 		hTable.getColumn(hTable.getColumnName(5)).setMaxWidth(135);
    	hTable.setFocusable(false); 
 		hTable.setBackground(Color.black);
 		
 		 jsp2 = new JScrollPane(hTable);
 	    jsp2.getViewport().setBackground(Color.black);
 		jsp2.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, 350));
 		p21 = new JPanel();
 		p21.setLayout(new BorderLayout());
 		p21.setBackground(Color.DARK_GRAY);
 		p21.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, 50));
 		p21.add(l2,"West");
 		p21.add(refreshBtn,"East");
 		p21.add(hTableHeader,"South");
 		
 		panel2 = new JPanel();
 		panel2.setBackground(Color.black);
 		panel2.setLayout(new BorderLayout());
 		panel2.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, 410));
 		panel2.add(p21,"North");
 		panel2.add(jsp2,"Center");
		con = new JPanel();
 		con.setLayout(new BorderLayout());
 		con.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, NumericalResources.SCREEN_WIDTH));
		con.add(panel1,"North");
		con.add(panel2,"Center");
		
		add(con);
	}

	public void updateRepoList() {
 		info = di.getRepoList();//从服务器获取数据
	    	
	    	if(info.size() > 0){
	    		for(int j = 0;j < info.size();j ++){
		 	    	hTable.setValueAt(info.get(j).getTime(), j, 1);
		 	    	hTable.setValueAt(info.get(j).getCount(), j, 2);
		 	    	hTable.setValueAt(info.get(j).getGuarantee(), j, 3);
		 	    	hTable.setValueAt(info.get(j).getProfit(), j, 4);
	 	    	} 
	 	 		hTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(info));
	 	 		hTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonCellRenderer());
	 	 		hTable.getColumnModel().getColumn(0).setCellEditor(new MyTableEditor(info));
	 	 		hTable.getColumnModel().getColumn(0).setCellRenderer(new MyTableCellRenderer(info));//
	    	}
	    	hTable.repaint();
	}

	private void makeFace2(JTable table) {
		table.setRowHeight(30);
	       table.setSelectionBackground(Color.black);
	       table.setSelectionForeground(Color.white);
		tcr22 = new DefaultTableCellRenderer(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 2220633049102091416L;

			public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {          	
              
				if(column == 5){
					
				}
				else{}
				  setForeground(Color.white);
            	  setBackground(Color.black);
              setHorizontalAlignment(SwingConstants.CENTER);
              return super.getTableCellRendererComponent(table, value,
                  isSelected, hasFocus, row, column);
            }
          };
          for (int i = 0; i < table.getColumnCount(); i++) {
        	  table.getColumn(table.getColumnName(i)).setCellRenderer(tcr22);
	          }
        
	}

	public void updateFTable() {
		int recnum = 0;

        fTableRec = di.getFinanceList();//接收已将按照时间排好顺序的结果
        
		DefaultTableModel tableModel = (DefaultTableModel) fTable.getModel();
	    	for(int i = 1;i < fTable.getRowCount();){
	    		tableModel.removeRow(i);
	    	} 
	    	if((recnum = fTableRec.size()) > 0){
	    		for(int j = 0;j < recnum;j ++){
		 	    	tableModel.addRow(new Object[]{
		 	    			fTableRec.get(j).getTime(),
		 	    			fTableRec.get(j).getTotal(),
		 	    			fTableRec.get(j).getGuarantee(),
		 	    			fTableRec.get(j).getIdle()});
	 	    	} 
	    	}
	    	else{
	    	}
	}

	private void makeFace(JTable table) {
		table.setRowHeight(30);
	       table.setSelectionBackground(Color.black);
	       table.setSelectionForeground(Color.blue);
	 	    
	          tcr1 = new DefaultTableCellRenderer(){
	            /**
				 * 
				 */
				private static final long serialVersionUID = 2220633049102091416L;

				public Component getTableCellRendererComponent(JTable table,
	                Object value, boolean isSelected, boolean hasFocus,
	                int row, int column) {          	
	              /*if (row % 2 == 0)
	                setBackground(Color.white); //设置奇数行底色
	              else if (row % 2 == 1)
	                setBackground(new Color(206, 231, 255)); //设置偶数行底色
	                */    
	            	  setBackground(Color.black);
			          setForeground(Color.blue);
			          if(isSelected && row == 0){setForeground(Color.blue);}
	              setHorizontalAlignment(SwingConstants.CENTER);
	              return super.getTableCellRendererComponent(table, value,
	                  isSelected, hasFocus, row, column);
	            }
	          };
	          for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumn(table.getColumnName(i)).setCellRenderer(tcr1);
	          }
		
	}

}
