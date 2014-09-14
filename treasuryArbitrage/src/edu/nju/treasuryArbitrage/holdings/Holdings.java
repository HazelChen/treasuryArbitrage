package edu.nju.treasuryArbitrage.holdings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import vo.Finance;
import vo.Repository;
import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.framework.ComponentPanel;
import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.network.DataInterfaceToServer;
import edu.nju.treasuryArbitrage.resources.NumericalResources;

/*
 * 
 * updatePage()  ������������ҳ��
 * 
 * */
public class Holdings extends JPanel implements ComponentPanel{
	private static final long serialVersionUID = 6470810944009110491L;
	private DataInterface  dif;
	static ArrayList<Finance> fTableRec;
	static ArrayList<Repository> info;
	static Object colummnames1[]={"ʱ��","���ʽ�","��Ͷ�뱣֤��","�����ʽ�",""},
			colummnames2[]={"���������Ϣ","����ʱ��","��������","Ͷ�뱣֤��","��ʱ��ʧ/ӯ�����","����"," "},
			empOb[][] = {{"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""},
						 {"","","","","",""}};//!!!![Important]!!!!!
	JPanel con,panel1,panel2,p11,p21;
	JScrollPane jsp1,jsp2;
	JLabel l1,l2;
	JButton refreshBtn,doBtn;
	JTable fTableHeader,fTable,hTableHeader,hTable;
	Color lbg = new Color(217,122,91),
			rbtnbg = new Color(41,128,185);
	private static TableCellRenderer tcr22;

	private static DefaultTableCellRenderer tcr1;
	 int w;
	 
	public Holdings() {
		dif = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		this.setBackground(Color.DARK_GRAY);
		
		l1 = new JLabel("  �ʽ�״��  ",JLabel.CENTER);
		l1.setForeground(Color.WHITE);l1.setBackground(lbg);
		l1.setOpaque(true);//�������JLabel��͸����ֻ������Ϊ��͸�������ñ���ɫ����Ч 
		l2 = new JLabel("  �ֲ�״��  ",JLabel.CENTER);
		l2.setForeground(Color.WHITE);l2.setBackground(lbg);
		l2.setOpaque(true);//�������JLabel��͸����ֻ������Ϊ��͸�������ñ���ɫ����Ч 
		
		refreshBtn = new JButton("ˢ��");    refreshBtn.setFocusPainted(false);
		refreshBtn.setBackground(rbtnbg);
 		doBtn = new JButton("ƽ��");    doBtn.setFocusPainted(false);
 		
 		fTableHeader = new JTable(0,5){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7984075408609692730L;

			public boolean isCellEditable(int row, int column){return false;}//��������༭
    	};
    	fTableHeader.setFocusable(false); 
    	DefaultTableModel tableModel11 = (DefaultTableModel) fTableHeader.getModel();
    	fTableHeader.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	   tableModel11.addRow(colummnames1);
	          makeFace(fTableHeader);
	 	  fTable = new JTable(0,4){
				/**
			 * 
			 */
			private static final long serialVersionUID = 7225024107038367101L;

				public boolean isCellEditable(int row, int column){return false;}//��������༭
	    	};
	    	fTable.setFocusable(false); 
	    	//DefaultTableModel tableModel12 = (DefaultTableModel) fTable.getModel();
		       fTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	  // fTable.setIntercellSpacing(new Dimension(0,1));//�޸ĵ�Ԫ���������Ҳ��Ӱ�������ߵĴ�ϸ�� 
	        //  fTable.setRowMargin(0);//�����������е�Ԫ��ľ���
	          fTable.getTableHeader().setPreferredSize(new Dimension(0,0));
	          fTable.getTableHeader().setVisible(false);
	          makeFace2(fTable);
		 	   updateFTable();
	    jsp1 = new JScrollPane(fTable);
	    jsp1.getViewport().setBackground(Color.black);
	 		jsp1.getVerticalScrollBar().setPreferredSize(new Dimension(13,12));
	 		jsp1.getVerticalScrollBar().setMaximumSize(new Dimension(13,12));
			jsp1.getVerticalScrollBar().setMinimumSize(new Dimension(13,12));
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
 		
 		hTableHeader = new JTable(0,7){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4860796298385794520L;

			public boolean isCellEditable(int row, int column){return false;}//��������༭
 		};
    	hTableHeader.setFocusable(false); 
 		hTableHeader.getTableHeader().setPreferredSize(new Dimension(0,0));
 		hTableHeader.getTableHeader().setVisible(false);
 		makeFace(hTableHeader);
 		DefaultTableModel tableModel21 = (DefaultTableModel) hTableHeader.getModel();
	 	   tableModel21.addRow(colummnames2); 

		//info = di.getRepoList();//��׮��ȡ����
		info = dif.getRepoList();//�ӷ�������ȡ����
 		hTable = new JTable(new DefaultTableModel() {
 			/**
			 * 
			 */
			private static final long serialVersionUID = -335420676543481799L;
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
 		//DefaultTableModel tableModel22 = (DefaultTableModel) hTable.getModel();
        hTable.getTableHeader().setPreferredSize(new Dimension(0,0));
        hTable.getTableHeader().setVisible(false);
 		makeFace2(hTable);
		hTable.setRowHeight(80);
  		for(int i = 1;i<6;i++){
  	 		hTableHeader.getColumn(hTableHeader.getColumnName(i)).setMinWidth(137);
  	 		hTableHeader.getColumn(hTableHeader.getColumnName(i)).setMaxWidth(137);
  		}
 		for(int i = 1;i<5;i++){
 			hTable.getColumn(hTable.getColumnName(i)).setMinWidth(137);
 	 		hTable.getColumn(hTable.getColumnName(i)).setMaxWidth(137);
 		}
 		hTable.getColumn(hTable.getColumnName(5)).setMinWidth(135);
 		hTable.getColumn(hTable.getColumnName(5)).setMaxWidth(135);
    	hTable.setFocusable(false);
 		hTable.setBackground(Color.black);
 		updateRepoList();
 		hTable.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {hTable.editCellAt(e.getY()/80, 5);}
			public void mouseMoved(MouseEvent e) {hTable.editCellAt(e.getY()/80, 5);}
 		});
 		 jsp2 = new JScrollPane(hTable);
 		 jsp2.getVerticalScrollBar().setPreferredSize(new Dimension(13,13));
 		jsp2.getVerticalScrollBar().setMaximumSize(new Dimension(13,13));
 		jsp2.getVerticalScrollBar().setMinimumSize(new Dimension(13,13));
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
 		con.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, NumericalResources.SCREEN_HEIGHT-61));
		con.add(panel1,"North");
		con.add(panel2,"Center");
		
		add(con);
		 
		refreshBtn.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {updateRepoList();
	    	hTable.repaint();}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}

	public void updatePage(){
		//���³ֲ����ҳ����ʾ
		updateFTable();
		updateRepoList();
	}
	
	public void updateRepoList() {
		info = dif.getRepoList();//�ӷ�������ȡ����
 		//info = di.getRepoList();//��׮��ȡ����
	    	if(info.size() > 0){
	    		for(int j = 0;j < info.size();j ++){
	    			Date dt = new Date(info.get(j).getTime());
		 	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 	    	String time = df.format(dt);
		 	    	hTable.setValueAt(time, j, 1);
		 	    	hTable.setValueAt(info.get(j).getCount(), j, 2);
		 	    	hTable.setValueAt(info.get(j).getGuarantee(), j, 3);
		 	    	hTable.setValueAt(info.get(j).getProfit(), j, 4);
	 	    	} 
	 	 		hTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(info));
	 	 		hTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonCellRenderer());
	 	 		hTable.getColumnModel().getColumn(0).setCellEditor(new MyTableEditor(info));
	 	 		hTable.getColumnModel().getColumn(0).setCellRenderer(new MyTableCellRenderer(info));//
	 	 		if(info.size() > 4){
	 	  	 		hTableHeader.getColumn(hTableHeader.getColumnName(6)).setMinWidth(13);
	 	  	 		hTableHeader.getColumn(hTableHeader.getColumnName(6)).setMaxWidth(13);
	 	 		}
	 	 		else{
	 	 			hTableHeader.getColumn(hTableHeader.getColumnName(6)).setMinWidth(0);
	 	  	 		hTableHeader.getColumn(hTableHeader.getColumnName(6)).setMaxWidth(0);
	 	 		}
	    	}
	    	hTable.repaint();
	    	//JOptionPane.showMessageDialog(null, "�ֲ�״����¼������" + info.size());
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
		//�����ʽ�״���б�
		
        fTableRec = dif.getFinanceList();//�����ѽ�����ʱ���ź�˳��Ľ��
        
		DefaultTableModel tableModel = (DefaultTableModel) fTable.getModel();
	    	for(int i = 1;i < fTable.getRowCount();){
	    		tableModel.removeRow(i);
	    	} 
	    	if(fTableRec.size() > 0){
	    		for(int j = 0;j < fTableRec.size();j ++){
	    			Date dt = new Date(fTableRec.get(j).getTime());
		 	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 	    	String time = df.format(dt);
		 	    	tableModel.addRow(new Object[]{
		 	    			time,
		 	    			fTableRec.get(j).getTotal(),
		 	    			fTableRec.get(j).getGuarantee(),
		 	    			fTableRec.get(j).getIdle()});
	 	    	} 
	    		if(fTableRec.size() > 8){
	 	  	 		fTableHeader.getColumn(fTableHeader.getColumnName(4)).setMinWidth(13);
	 	  	 		fTableHeader.getColumn(fTableHeader.getColumnName(4)).setMaxWidth(13);
	 	 		}
	 	 		else{
	 	 			fTableHeader.getColumn(fTableHeader.getColumnName(4)).setMinWidth(0);
	 	  	 		fTableHeader.getColumn(fTableHeader.getColumnName(4)).setMaxWidth(0);
	 	 		}
	    	}
	    	else{
	    	}
	    	//JOptionPane.showMessageDialog(null, "�ʽ�״����¼������" + fTableRec.size());
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
	                setBackground(Color.white); //���������е�ɫ
	              else if (row % 2 == 1)
	                setBackground(new Color(206, 231, 255)); //����ż���е�ɫ
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
