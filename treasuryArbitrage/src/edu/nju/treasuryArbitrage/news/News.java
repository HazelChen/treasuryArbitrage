
package edu.nju.treasuryArbitrage.news;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.*;

import edu.nju.treasuryArbitrage.resources.NumericalResources;

public class News extends JPanel{
	private static final long serialVersionUID = -3044620398021541690L;
	  
	 JLabel jL1,jL2,jL3,hlabel;
	 static JButton b1;
	 static JButton b2;
	 static JButton bnp,bpp;
	 static JTextField text1;
	 //JComboBox cB1,cB2;
	 //dateInTextField fDateIn,tDateIn;
	 
	 JDateChooser fromDateIn,toDateIn;
	 
	 JPanel panel1,panel2,bottomnavi,hL;
	 static String preKeyword = ""; //记录检索约束，待刷新使用
	 static String keyword = "";
	 static Date fD1;

	 static Date tD2;
	 static Color sblue = new Color(219, 231, 243);
	 static JTable table;
	 static DefaultTableCellRenderer tcr;
	 String p1[] = { "踢足球","打篮球","打排球" };
	 String p2[] = {"踢足球","打篮球","打排球" };
	 static Object colummnames[]={"时间","来源","标题","作者"};
	 static NewsDetailDg myWnd;

	 static NumericalResources Numbers;
		MyMSL listener1ms = new MyMSL();
		MyAcL listener2ac = new MyAcL();
		MyMML listener3mm = new MyMML();
		
		public News(){	       
	        //setBackground(sblue);
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
	 		
	 		//cB1 = new JComboBox(p1);
	 	    //cB2 = new JComboBox(p2);
	 	    //fDateIn = new dateInTextField();
	 	    //tDateIn = new dateInTextField();
	 		Dimension dateTextdem = new Dimension(90,20);
	 		
	 		toDateIn = new JDateChooser("yyyy/MM/dd","####/##/##",'_');
	 		fromDateIn = new JDateChooser("yyyy/MM/dd","####/##/##",'_');
	 		fromDateIn.setPreferredSize(dateTextdem);
	 		toDateIn.setPreferredSize(dateTextdem);

	 		
	 	    panel1 = new JPanel();
	 		panel2 = new JPanel();
	 		bottomnavi = new JPanel();
	 		hL = new JPanel();
	 		
	 	    table = new JTable(0,4){
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1585833408958801322L;

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
	 	   
	 	   //tableModel.removeRow(tableModel.getRowCount() - 1);
	 	 
	 	text1.addActionListener(listener2ac);
	    b1.addMouseListener(listener1ms);
		 table.addMouseMotionListener(listener3mm);       
		 table.addMouseListener(listener1ms);
		 
		/* GridBagLayout gridbag = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
         c.fill = GridBagConstraints.BOTH;
         c.weightx=0.0;//默认值为0.0
         c.weighty=0.0;//默认值为0.0
*/	 	    
		    setLayout(new BorderLayout());
	 	    //setSize(960,580); 
	 	    panel1.setBackground(sblue);
	 		panel1.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, 40));
	 		panel1.add(jL1);panel1.add(text1);
	 		panel1.add(jL2);panel1.add(fromDateIn);
	 		panel1.add(jL3);panel1.add(toDateIn);
	 		panel1.add(b1);panel1.add(b2);
	 		
	 		hL.add(hlabel);
	 		hL.setPreferredSize(new Dimension(NumericalResources.SCREEN_WIDTH, 3));
	 		hL.setBackground(Color.WHITE);
	 		hL.setBorder(new LineBorder(Color.WHITE,1));
	 		
	 		panel1.add(hL,BorderLayout.SOUTH);
	 		bottomnavi.add(bpp);
	 		bottomnavi.add(bnp);
	 		
	 		panel2.setBackground(Color.WHITE);
	 		panel2.setPreferredSize(new Dimension(50, 680));
	 		bottomnavi.setBackground(Color.WHITE);
	 		bottomnavi.setPreferredSize(new Dimension(100, 100));
	 		panel2.setLayout(new BorderLayout());
	 		panel2.setAlignmentX(CENTER_ALIGNMENT);
	 		panel2.add(table,"North");
	 		panel2.add(bottomnavi,"South");
	 		
	 		/*c.anchor = GridBagConstraints.NORTH;
	 		c.gridx = 0;c.gridy = 0;
	         c.gridheight=1;
	         c.gridwidth=0;
	 		 gridbag.setConstraints(panel1, c);
	 		 c.gridheight=50;c.gridy ++;
	 		 gridbag.setConstraints(panel2, c);*/
	 		 
	 		add(panel1,"North");
	 		//add(hL);
	 		add(panel2,"Center");
	 		
	 		/*
	 		 * super.setBorder(new LineBorder(Color.BLUE)); // test
	 		 * panel1.setBorder(new LineBorder(Color.BLUE));
	 		 */ hL.setBorder(new LineBorder(Color.WHITE));
	 		 /* panel2.setBorder(new LineBorder(Color.RED));
	 		 */
	 		myWnd = new NewsDetailDg(0);
	 		myWnd.setVisible(false);
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


		//检索
		public static void search(String keyword2, Date fD, Date tD) {
			
		} 
	    
}

class NewsDetailDg extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5893692668956428617L;
	
	private NewsDetailDg curdg = this;
		 JLabel newsTitle,inv;
		    JTextArea newsDetail;
		    JPanel panel,panel2,panelbottom,conp;
		    JButton closebtn;
		    int newsID;
		    String sNewsTitle = "移仓进行时",
		    		snewsDetail = "    操作建议 国债期货昨日上涨,近期新一轮IPO将启动,对市场的资金将会" +
		    				"产生影响,股指近期的横盘给予债券一定的机会。我们认为政策上的利多国债将会发挥一定的效应,此" +
		    				"外,发改委官员称降息降准时机已到,我们预期后续政策依然偏松,体现托底保增长意图,国债中线存在机" +
		    				"会做多,短期内如果经济数据并非一路走强,那么国债有望短期摸高震荡区间上沿。" +
		    				"    20日，农畜产品多弱势，预计今日弱势；化工品除橡胶横盘外多弱势，预计今日弱势；金属" +
		    				"除黑色弱势外多强势，预计今日偏强；能源类出铁矿偏弱外多横盘，预计今日弱势横盘；玻璃强除纤板" +
		    				"偏弱外横盘，预计今日弱势；股指国债横盘，预计今日横盘；近期关注鸡蛋和塑料。（期货投资QQ群：102664812）"; 
		    detailML dml;
		    NewsDetailDg(int newsID){
		        //super(sNewsTitle);

		    	GetNews(newsID);
		    	//this.setModalityType(DEFAULT_MODALITY_TYPE);
		    	setUndecorated(true);
		    	setBackground(Color.WHITE);
		    	setMaximumSize(new Dimension(720,510));
		    	setMinimumSize(new Dimension(750,510));
				setResizable(false);
				setModal(true);//
		    	dml = new detailML();
		        conp = new JPanel();
		        conp.setLayout(new BorderLayout());
		        this.setLocation((NumericalResources.SCREEN_WIDTH - this.getWidth())/2,
		        		(NumericalResources.SCREEN_HEIGHT - this.getHeight())/2);
		        panel = new JPanel();
		        panelbottom = new JPanel();
		        newsTitle = new JLabel(sNewsTitle);
		        Font titlef=new Font("宋体",Font.BOLD,24);
		        newsTitle.setFont(titlef);
		        inv = new JLabel("");
		        inv.setPreferredSize(new Dimension(33, 1));
		        //inv.setVisible(false);

		        panel.setLayout(new BorderLayout());
		        panel.setPreferredSize(new Dimension(750,52));
		        panel.add(newsTitle,FlowLayout.LEFT);
		        panel.add(inv,"West");
		        
		        panel2 = new JPanel();
		        panel2.setSize(682,398);
		        newsDetail = new JTextArea(snewsDetail,25,62);
		        newsDetail.setEditable(false);
		        newsDetail.setLineWrap(true);
		        newsDetail.setBorder(new LineBorder(Color.BLACK, 1));
		        newsDetail.setBackground(Color.WHITE);
		        
		        panel2.add(newsDetail);
		        closebtn = new JButton("关闭窗口");
		        closebtn.addMouseListener(dml);
		        closebtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        panelbottom.setPreferredSize(new Dimension(750,52));
		        panelbottom.add(closebtn);

		        conp.setSize(720, 510);
		        conp.setBorder(new LineBorder(Color.GRAY, 1));
		        conp.add(panel,"North");
		        conp.add(panel2,"Center");
		        conp.add(panelbottom,"South");
		        add(conp);
		        this.pack();
		    }
		    private void GetNews(int newsID) {
				// TODO 自动生成的方法存根
				
			}
			class detailML implements MouseListener{

				@Override
				public void mouseClicked(MouseEvent e) {
					curdg.setVisible(false);
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
		    	
		    }
}


//----------------------------事件响应-------------------------------//

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
										News.myWnd = new NewsDetailDg(j);
										News.myWnd.setVisible(true);
										
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
		 								News.myWnd = new NewsDetailDg(j);
										News.myWnd.setVisible(true);

		 							}
		 							News.table.clearSelection();// 让第一行不可选
		 						}
		 	            	 
		 				}
		    		}	
		    		else{}
		    	}
		    	else	if(e.getSource() == News.b1){
		    		News.preKeyword = News.keyword;
		    		News.search( News.keyword, News.fD1, News.tD2);
		    		News.keyword = News.text1.getText();
		    	}
		    	else if(e.getSource() == News.b2){
		    		News.search( News.keyword, News.fD1, News.tD2);
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
		    		
		    }
		    public void mouseDragged(MouseEvent e){
		    	if(News.table.isRowSelected(0)) News.table.clearSelection();
		    }
}
		 
class MyAcL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 if(e.getSource()==News.text1){
				 News.keyword=(News.text1.getText());
		           
		        }
		        else{}
		}
}

