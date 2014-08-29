package edu.nju.treasuryArbitrage.news;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import javax.swing.JLabel;

import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.applet.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class News extends JPanel{
	private static final long serialVersionUID = -3044620398021541690L;
	  
	JLabel jL1,jL2,jL3,hlabel;
	   JButton b1,b2;
	   JButton bnp,bpp;
	   JTextField text1;
	   JComboBox cB1,cB2;
	   JPanel panel1,panel2,bottomnavi,hL;
	   static Color sblue = new Color(219, 231, 243);
	   JTable table;
		String p1[] = { "������","������","������" };
		String p2[] = {"������","������","������" };
		static Object colummnames[]={"ʱ��","��Դ","����","����"};
		
		News(){
	 
	    	jL1 = new JLabel("�ؼ���",JLabel.CENTER);
	 		jL2 = new JLabel("��ʼ����",JLabel.CENTER);
	 		jL3 = new JLabel("��ֹ����",JLabel.CENTER);
	 		hlabel = new JLabel(" ",JLabel.CENTER);
	 		b1 = new JButton("����");
	 		b2 = new JButton("ˢ��");
	 		bnp = new JButton("��һҳ");
	 		bpp = new JButton("��һҳ");
	 		text1 = new  JTextField(20);
	 		cB1 = new JComboBox(p1);
	 	    cB2 = new JComboBox(p2);
	 	    
	 	    panel1 = new JPanel();
	 		panel2 = new JPanel();
	 		bottomnavi = new JPanel();
	 		hL = new JPanel();
	 		
	 	    table = new JTable(0,4){
	            public boolean isCellEditable(int row, int column)
	            {
	                       return false;
	            }//��������༭
	    	};
	    	
	 	   makeFace(table);
	 	   DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	 	   
	 	   tableModel.addRow(new Object[]{"2014/08/16", "�����ڻ�", "�Ʋֽ���ʱ","������"});
	 	   tableModel.addRow(new Object[]{"2014/08/16", "�����ڻ�", "�Ʋֽ���ʱ","������"});
	 	   
	 	  class MyMSL implements MouseListener {
	 	 			 		
	 		public void mousePressed(MouseEvent e){
	  				
	 	    }
	 	    public void mouseReleased(MouseEvent e){
	 	    	for(int j = 0; j < table.getRowCount(); j++)
					{
						if(table.isRowSelected(j))
							{
								table.removeRowSelectionInterval( j, j );
								if(j > 0){
									NewsDetail myWnd = new NewsDetail(j);
								}
			 	            	table.clearSelection();
							}
		            	 
					}
	 	    }
	 	    public void mouseEntered(MouseEvent e){
	 	    	if(!e.equals(table))setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	 	    	
	 	    }
	 	    public void mouseExited(MouseEvent e){
	 	    	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 	    }
	 	    public void mouseClicked(MouseEvent e){
	 	        if(e.getClickCount()==2){
	 	        	for(int j = 0; j < table.getRowCount(); j++)
	 				{
	 					if(table.isRowSelected(j))
	 						{
	 							table.removeRowSelectionInterval( j, j );
	 							if(j > 0){
	 								NewsDetail myWnd = new NewsDetail(j);
	 							}
	 		 	            	table.clearSelection();
	 						}
	 	            	 
	 				}
	 	        }
	 	        else{}
	 	    }
			
	      }

	      // �õ�һ�в���ѡ
	 	  
	 	 addMouseListener(new MyMSL());
	 	   
	 	    setLayout(new BorderLayout());
	 	    setSize(960,580); 
	 	    panel1.setBackground(sblue);
	 		panel1.add(jL1);panel1.add(text1);
	 		panel1.add(jL2);panel1.add(cB1);
	 		panel1.add(jL3);panel1.add(cB2);
	 		panel1.add(b1);panel1.add(b2);
	 		
	 		hL.add(hlabel);
	 		hL.setPreferredSize(new Dimension(960, 3));
	 		hL.setBackground(Color.WHITE);
	 		hL.setBorder(null);
	 		
	 		bottomnavi.add(bpp);
	 		bottomnavi.add(bnp);
	 		
	 		panel2.setSize(960, 500);
	 		panel2.setLayout(new BorderLayout());
	 		panel2.add(table,"Center");
	 		panel2.add(bottomnavi,"South");
	 		add(panel1,"North");
	 		add(hL,"Center");
	 		add(panel2,"South");
	    }
	    
	    public static void makeFace(JTable table) {
	 	   table.setRowHeight(30);
	 	   table.setSize(new Dimension(960,500));
	 	   table.setLocation(0, 10);
	       table.getColumn(table.getColumnName(0)).setMinWidth(121);
	       table.getColumn(table.getColumnName(0)).setMaxWidth(121);
	       table.getColumn(table.getColumnName(1)).setMinWidth(121);
	       table.getColumn(table.getColumnName(1)).setMaxWidth(122);
	       table.getColumn(table.getColumnName(2)).setMinWidth(569);
	       table.getColumn(table.getColumnName(2)).setMaxWidth(569);
	       table.getColumn(table.getColumnName(3)).setMinWidth(148);
	       table.getColumn(table.getColumnName(3)).setMaxWidth(148);
	       table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	    DefaultTableModel tableModel2 = (DefaultTableModel) table.getModel();
	 	    tableModel2.addRow(colummnames);
	        try {
	          DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
	            public Component getTableCellRendererComponent(JTable table,
	                Object value, boolean isSelected, boolean hasFocus,
	                int row, int column) {
	              	
	              /*if (row % 2 == 0)
	                setBackground(Color.white); //���������е�ɫ
	              else if (row % 2 == 1)
	                setBackground(new Color(206, 231, 255)); //����ż���е�ɫ
	                */
	              if(row == 0){
	            	  setBackground(sblue);
	              }
	              else 
	            	  setBackground(Color.white);
	              if(table.isCellSelected(0, column)){
	            	  table.clearSelection();
	              }
	              if(hasFocus){
	            	  
	              }
	              
	              setHorizontalAlignment(SwingConstants.CENTER);
	              
	              return super.getTableCellRendererComponent(table, value,
	                  isSelected, hasFocus, row, column);
	            }
	          };
	          for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
	          }
	        }
	        catch (Exception ex) {
	          ex.printStackTrace();
	        }

	   	  
	      }

	    
	    public String GetNewsTitle( int newsID ) {
            String res;
            res="�Ʋֽ���ʱ" + newsID;   // 
 			return res;
        } 
}

class NewsDetail extends JFrame{
	    JLabel newsTitle;
	    JTextField newsCon;
	    JPanel panel;
	    JButton closebtn;
	    int newsID;
	    String sNewsTitle; 
	    
	    NewsDetail(int newsID){
	        //super(sNewsTitle);

	    	//GetNewsTitle(newsID);
	        Container con = this.getContentPane();
	        con.setLayout(new GridLayout(3,1));
	        this.setSize(200,300);
	        this.setLocation(100,100);
	        panel = new JPanel();
	        newsTitle = new JLabel(sNewsTitle);
	        closebtn = new JButton("�رմ���");
	        //con.add(panel);
	        
	        con.add(closebtn);
	        this.setVisible(true);
	        this.pack();
	    }
	   
}
