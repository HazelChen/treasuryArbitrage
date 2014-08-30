
package edu.nju.treasuryArbitrage.news;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.nju.treasuryArbitrage.resources.NumericalResources;

public class News extends JPanel{
	private static final long serialVersionUID = -3044620398021541690L;
	  
	 JLabel jL1,jL2,jL3,hlabel;
	 static JButton b1;
	JButton b2;
	 JButton bnp,bpp;
	 static JTextField text1;
	 static JTextArea testm = new JTextArea();
	 JComboBox cB1,cB2;
	 JPanel panel1,panel2,bottomnavi,hL;
	 static String preKeyword = ""; //记录检索约束，待刷新使用
	 static String keyword = "";
	 Date pD1,pD2;
	 static Color sblue = new Color(219, 231, 243);
	 static JTable table;
	 static DefaultTableCellRenderer tcr;
	 String p1[] = { "踢足球","打篮球","打排球" };
	 String p2[] = {"踢足球","打篮球","打排球" };
	 static Object colummnames[]={"时间","来源","标题","作者"};

	 static NumericalResources Numbers;
		MyMSL listener1ms = new MyMSL();
		MyAcL listener2ac = new MyAcL();
		MyMML listener3mm = new MyMML();
		
		public News(){
	        testm.setText(new String().format("%d", Numbers.SCREEN_WIDTH));//
	        
	    	jL1 = new JLabel("关键字",JLabel.CENTER);
	 		jL2 = new JLabel("起始日期",JLabel.CENTER);
	 		jL3 = new JLabel("截止日期",JLabel.CENTER);
	 		hlabel = new JLabel(" ",JLabel.CENTER);
	 		b1 = new JButton("检索");
	 		b2 = new JButton("刷新");
	 		bnp = new JButton("下一页");
	 		bpp = new JButton("上一页");
	 		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		bnp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		bpp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
	            }//表格不允许被编辑
	    	};
	    	table.setFocusable(false);
	    	
	 	   makeFace(table);
	 	   DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	       table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 	    tableModel.addRow(colummnames);
	 	   
	 	   tableModel.addRow(new Object[]{"2014/08/16", "长江期货", "移仓进行时","李明宇"});
	 	   tableModel.addRow(new Object[]{"2014/08/16", "长江期货", "移仓进行时","李明宇"});
	 	   
	 	 
	 	text1.addActionListener(listener2ac);
	    b1.addMouseListener(listener1ms);
		 table.addMouseMotionListener(listener3mm);       
		 table.addMouseListener(listener1ms);
		 
		 GridBagLayout gridbag = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
         c.fill = GridBagConstraints.BOTH;
         c.weightx=0.0;//默认值为0.0
         c.weighty=0.0;//默认值为0.0
	 	    setLayout(gridbag);
	 	    //setSize(960,580); 
	 	    panel1.setBackground(sblue);
	 		panel1.setPreferredSize(new Dimension(Numbers.SCREEN_WIDTH, 32));
	 		panel1.add(jL1);panel1.add(text1);
	 		panel1.add(jL2);panel1.add(cB1);
	 		panel1.add(jL3);panel1.add(cB2);
	 		panel1.add(b1);panel1.add(b2);
	 		
	 		hL.add(hlabel);
	 		hL.setPreferredSize(new Dimension(Numbers.SCREEN_WIDTH, 3));
	 		hL.setBackground(Color.WHITE);
	 		hL.setBorder(null);
	 		
	 		panel1.add(hL);
	 		bottomnavi.add(bpp);
	 		bottomnavi.add(bnp);
	 		
	 		panel2.setSize(Numbers.SCREEN_WIDTH, 500);
	 		panel2.setLayout(new GridLayout(3,1));
	 		panel2.setAlignmentX(CENTER_ALIGNMENT);
	 		panel2.add(table);
	 		panel2.add(bottomnavi);
	 		panel2.add(testm);
	 		
	 		c.anchor = GridBagConstraints.NORTH;
	 		c.gridx = 0;c.gridy = 0;
	         c.gridheight=1;
	         c.gridwidth=0;
	 		 gridbag.setConstraints(panel1, c);
	 		 c.gridheight=50;c.gridy ++;
	 		 gridbag.setConstraints(panel2, c);
	 		 
	 		add(panel1);
	 		//add(hL);
	 		add(panel2);
	 		
	 		/*
	 		 * super.setBorder(new LineBorder(Color.BLUE)); // test
	 		 * panel1.setBorder(new LineBorder(Color.BLUE));
	 		 */ hL.setBorder(new LineBorder(Color.WHITE));
	 		 /* panel2.setBorder(new LineBorder(Color.RED));
	 		 */
	 		
	    }
	    
	    public static void makeFace(JTable table) {
	 	   table.setRowHeight(30);
	       table.getColumn(table.getColumnName(0)).setMinWidth(121);
	       table.getColumn(table.getColumnName(0)).setMaxWidth(121);
	       table.getColumn(table.getColumnName(1)).setMinWidth(121);
	       table.getColumn(table.getColumnName(1)).setMaxWidth(122);
	       table.getColumn(table.getColumnName(2)).setMinWidth(569);
	      // table.getColumn(table.getColumnName(2)).setMaxWidth(569);
	       table.getColumn(table.getColumnName(3)).setMinWidth(148);
	       table.getColumn(table.getColumnName(3)).setMaxWidth(148);

	 	    
	        try {
	          tcr = new DefaultTableCellRenderer() {
	            public Component getTableCellRendererComponent(JTable table,
	                Object value, boolean isSelected, boolean hasFocus,
	                int row, int column) {
	              	
	              /*if (row % 2 == 0)
	                setBackground(Color.white); //设置奇数行底色
	              else if (row % 2 == 1)
	                setBackground(new Color(206, 231, 255)); //设置偶数行底色
	                */
	              if(row == 0){
	            	  setBackground(sblue);
	              }
	              else 
	            	  setBackground(Color.white);
	              if(isSelected){
	            	  setBackground(sblue);
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
	        res="移仓进行时" + newsID;   // 
				return res;
	    } 
	    
}

class NewsDetail extends JDialog{
		 JLabel newsTitle;
		    JTextArea newsDetail;
		    JPanel panel;
		    JScrollPane panel2;
		    JButton closebtn;
		    int newsID;
		    String sNewsTitle = "移仓进行时",
		    		snewsDetail = "新闻内容"; 
		    
		    NewsDetail(int newsID){
		        //super(sNewsTitle);

		    	//GetNewsTitle(newsID);
		    	//this.setModalityType(DEFAULT_MODALITY_TYPE);
		    	this.setUndecorated(true);
		    	this.setBackground(Color.WHITE);
		    	this.setMaximumSize(new Dimension(600,400));
		    	this.setMinimumSize(new Dimension(600,400));
				this.setResizable(false);
		    	
		        Container con = this.getContentPane();
		        con.setLayout(new GridLayout(3,1));
		        this.setLocation((News.Numbers.SCREEN_WIDTH - this.getWidth())/2,
		        		(News.Numbers.SCREEN_HEIGHT - this.getHeight())/2);
		        panel = new JPanel();
		        newsTitle = new JLabel(sNewsTitle);
		        panel.add(newsTitle);
		       
		        panel2 = new JScrollPane();
		        panel2.setSize(500,500);
		        newsDetail = new JTextArea(snewsDetail,20,30);
		        panel2.add(newsDetail);
		        closebtn = new JButton("关闭窗口");
		        
		        con.setSize(600, 600);
		        con.add(panel);
		        con.add(panel2);
		        con.add(closebtn);
		        this.setVisible(true);
		        this.pack();
		    }
}

class MyMSL implements MouseListener {
			
			public void mousePressed(MouseEvent e){
				if(News.table.isRowSelected(0))
			{
					News.table.clearSelection();// 让第一行不可选
			}
		    }
		    public void mouseReleased(MouseEvent e){
		    	if(e.getSource() == News.table){
	 	    	if(News.table.isRowSelected(0))News.table.clearSelection();// 让第一行不可选
	 	    	for(int j = 1; j < News.table.getRowCount(); j++)
					{
						if(News.table.isRowSelected(j))
							{
							News.table.removeRowSelectionInterval( j, j );
								if(j > 0){
									NewsDetail myWnd = new NewsDetail(j);
								}
								News.table.clearSelection();// 让第一行不可选
							}
		            	 
					}
		    	}
		    }
		    public void mouseEntered(MouseEvent e){
		    	News.table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    }
		    public void mouseExited(MouseEvent e){
		    	News.table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    	if(e.getSource() == News.table){
		    		News.table.clearSelection();
		    	}
		    }
		    public void mouseClicked(MouseEvent e){
		    	if(e.getSource() == News.table){
		    		if(e.getClickCount() == 2 || e.getClickCount() == 1){
		    	
	 	        	for(int j = 0; j < News.table.getRowCount(); j++)
	 				{
	 					if(News.table.isRowSelected(j))
	 						{
	 						News.table.removeRowSelectionInterval( j, j );
	 							if(j > 0){
	 								NewsDetail myWnd = new NewsDetail(j);
	 							}
	 							News.table.clearSelection();// 让第一行不可选
	 						}
	 	            	 
	 				}
	 	        }
	 	        else{}
	 	    }
		    	if(e.getSource() == News.b1){
		    		News.preKeyword = News.keyword;
		    		News.keyword = News.text1.getText();
		    		News.testm.setText("pk:" + News.preKeyword + "     k:" + News.keyword);
		    	}
		    		
		    }
}

class MyMML implements MouseMotionListener {
	 	private	int index0,index1,mousey;
		    public void mouseMoved(MouseEvent e){
		    		if((mousey = e.getY()) >= 30){
		    			News.table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    		}
		    		else{
		    			News.table.clearSelection();
		    			News.table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		}
		    			
		    		index0 = mousey / 30 - 1;
		    		index1 = index0 + 1;
		    		if(index0 >= 0){
		    			News.table.setRowSelectionInterval(index0, index1);
	  	    		 for (int i = 0; i < News.table.getColumnCount(); i++) {
	  	    			News.table.getColumn(News.table.getColumnName(i)).setCellRenderer(News.tcr);
	  	             }
		    		}
		    		
		    		News.testm.setText("cur.y:" + e.getY() + 
		    				"    keyword:"+News.keyword);
		    }
		    public void mouseDragged(MouseEvent e){
		    	if(News.table.isRowSelected(0)) News.table.clearSelection();
		    }
}
		 
class MyAcL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			 if(e.getSource()==News.text1){
				 News.keyword=(News.text1.getText());
		           
		        }
		        else{}
		}
}

