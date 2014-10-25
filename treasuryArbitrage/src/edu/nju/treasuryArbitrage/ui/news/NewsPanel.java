
package edu.nju.treasuryArbitrage.ui.news;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import vo.News;

import com.toedter.calendar.JDateChooser;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.ui.common.ComponentPanel;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class NewsPanel extends JPanel implements ComponentPanel{
	private static final long serialVersionUID = -3044620398021541690L;
	
	private DataInterface di;
	static int NewsNum,MaxNumPerpage,pageNum,curPageNo;
	static ArrayList<News> newsTable;
	 JLabel jL1,jL2,jL3,hlabel,inv,inv2;
	 static JButton b1;
	 static JButton b2,btnAllnews;
	 static JButton bnp,bpp;
	 static JTextField text1;
	 //JComboBox cB1,cB2;
	 //dateInTextField fDateIn,tDateIn;
	 
	 static JDateChooser fromDateIn;

	static JDateChooser toDateIn;
	 
	 JPanel panel1,panel2;

	static JPanel bottomnavi;

	JPanel hL;
	 static String preKeyword = ""; //记录检索约束，待刷新使用
	 static String keyword = "";
	 static Date fD1;
	 static Date tD2;
	 static Color sblue = new Color(219, 231, 243);
	 static JTable table;
	 static DefaultTableCellRenderer tcr;
	 static Object colummnames[]={"ID","时间","来源","标题","作者"};//ID列被隐藏
	 static NewsDetailDg myWnd;

		MyMSL listener1ms = new MyMSL();
		MyAcL listener2ac = new MyAcL();
		MyMML listener3mm = new MyMML();
		
		public NewsPanel(){	      
			di = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
			NewsNum = 0;
			MaxNumPerpage=0;
			pageNum=0;curPageNo=0;
			
			MaxNumPerpage = (int) (ScreenSize.HEIGHT - 180)/31;
	    	jL1 = new JLabel("关键字",JLabel.CENTER);jL1.setForeground(Color.WHITE);
	 		jL2 = new JLabel("        起始日期",JLabel.CENTER);jL2.setForeground(Color.WHITE);
	 		jL3 = new JLabel("        截止日期",JLabel.CENTER);jL3.setForeground(Color.WHITE);
	 		inv = new JLabel("    ");//占位
	 		inv2 = new JLabel("    ");//占位
	 		hlabel = new JLabel(" ",JLabel.CENTER);
	 		b1 = new JButton("搜索");    b1.setFocusPainted(false);
	 		b2 = new JButton("刷新");    b2.setFocusPainted(false);
	 		btnAllnews = new JButton("所有新闻");    btnAllnews.setFocusPainted(false);
	 		bnp = new JButton("下一页");  bnp.setFocusPainted(false);
	 		bpp = new JButton("上一页");  bpp.setFocusPainted(false);
	 		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		btnAllnews.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		bnp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		bpp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 		text1 = new  JTextField(40);
	 		
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

	 	    table = new JTable(0,5){
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
	 	   table.setIntercellSpacing(new Dimension(0,1));//修改单元格间隔，因此也将影响网格线的粗细。 
	          table.setRowMargin(0);//设置相邻两行单元格的距离
	 	   //获取所有新闻标题等内容显示
	 	   /*
	 	   */
	 	    newsTable = di.getNewsList();//接收已将按照时间排好顺序的结果
	 	   updateTable(newsTable);
	 	   //tableModel.addRow(new Object[]{"2014/08/16", "长江期货", "移仓进行时","李明宇"});
	 	   
	 	   //tableModel.removeRow(tableModel.getRowCount() - 1);
	 	   
	 	text1.addActionListener(listener2ac);
	    b1.addMouseListener(listener1ms);
	    b2.addMouseListener(listener1ms);
	    btnAllnews.addMouseListener(listener1ms);
		 table.addMouseMotionListener(listener3mm);       
		 table.addMouseListener(listener1ms);
		 bpp.addMouseListener(listener1ms);
		 bnp.addMouseListener(listener1ms);
		/* GridBagLayout gridbag = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
         c.fill = GridBagConstraints.BOTH;
         c.weightx=0.0;//默认值为0.0
         c.weighty=0.0;//默认值为0.0
*/	 	    
		    setLayout(new BorderLayout());
	 	    //setSize(960,580); 
	 	    panel1.setBackground(Color.DARK_GRAY);
	 		panel1.setPreferredSize(new Dimension(ScreenSize.WIDTH, 40));
	 		panel1.add(jL1);panel1.add(text1);
	 		panel1.add(jL2);panel1.add(fromDateIn);
	 		panel1.add(jL3);panel1.add(toDateIn);
	        inv.setPreferredSize(new Dimension(33, 1));
	 		panel1.add(inv);
	 		panel1.add(b1);
	        inv2.setPreferredSize(new Dimension(16, 1));
	 		//panel1.add(inv2);
	        panel1.add(b2);
	        //panel1.add(btnAllnews);
	 		
	 		hL.add(hlabel);
	 		hL.setPreferredSize(new Dimension(ScreenSize.WIDTH, 1));
	 		hL.setBackground(Color.darkGray);
	 		hL.setBorder(new LineBorder(Color.darkGray,1));
	 		hL.setVisible(false);
	 		panel1.add(hL,BorderLayout.SOUTH);
	 		bottomnavi.add(bpp);
	 		bottomnavi.add(bnp);
	 		
	 		panel2.setBackground(Color.BLACK);
	 		panel2.setPreferredSize(new Dimension(50, 680));
	 		bottomnavi.setBackground(Color.BLACK);
	 		bottomnavi.setPreferredSize(new Dimension(100, 100));
	 		panel2.setLayout(new BorderLayout());
	 		panel2.setAlignmentX(CENTER_ALIGNMENT);
	 		panel2.add(table,"North");
	 		panel2.add(bottomnavi,"South");
	 		if(pageNum > 1) bottomnavi.setVisible(true);
	 		else bottomnavi.setVisible(false);
	 		/*c.anchor = GridBagConstraints.NORTH;
	 		c.gridx = 0;c.gridy = 0;
	         c.gridheight=1;
	         c.gridwidth=0;
	 		 gridbag.setConstraints(panel1, c);
	 		 c.gridheight=50;c.gridy ++;
	 		 gridbag.setConstraints(panel2, c);*/
	 		setForeground(Color.WHITE);
	 		setBackground(Color.BLACK);
	 		add(panel1,"North");
	 		//add(hL);
	 		add(panel2,"Center");
	 		
	 		/*
	 		 * super.setBorder(new LineBorder(Color.BLUE)); // test
	 		 * panel1.setBorder(new LineBorder(Color.BLUE));
	 		 */ hL.setBorder(new LineBorder(Color.WHITE));
	 		 /* panel2.setBorder(new LineBorder(Color.RED));
	 		 */
	    }
		
	    
	    public static void makeFace(JTable table) {
	 	   table.setRowHeight(30);
	 	   table.getColumn(table.getColumnName(0)).setMinWidth(0);
	       table.getColumn(table.getColumnName(0)).setMaxWidth(0);
	       table.getColumn(table.getColumnName(0)).setPreferredWidth(0);
	       table.getColumn(table.getColumnName(0)).setResizable(false);//隐藏第一列
	       table.getColumn(table.getColumnName(1)).setMinWidth(121);
	       table.getColumn(table.getColumnName(1)).setMaxWidth(121);
	       table.getColumn(table.getColumnName(2)).setMinWidth(121);
	       table.getColumn(table.getColumnName(2)).setMaxWidth(122);
	       table.getColumn(table.getColumnName(3)).setMinWidth(569);
	      // table.getColumn(table.getColumnName(2)).setMaxWidth(569);
	       table.getColumn(table.getColumnName(4)).setMinWidth(148);
	       table.getColumn(table.getColumnName(4)).setMaxWidth(148);
	       table.setSelectionBackground(Color.DARK_GRAY);
	       table.setSelectionForeground(Color.white);
	 	    
	          tcr = new DefaultTableCellRenderer(){
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
		              if(column == 1){setForeground(Color.blue);}
		              else{      	  setForeground(Color.gray);}
			          if(row == 0){setForeground(Color.white);}
	              setHorizontalAlignment(SwingConstants.CENTER);

	              return super.getTableCellRendererComponent(table, value,
	                  isSelected, hasFocus, row, column);
	            }
	          };
	          for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
	          }
	             	  
	      }

		public static Date setFromDate() {
			return fromDateIn.getDate();
		}


		public static Date setToDate() {
			return toDateIn.getDate();
		} 
		
		public static void updateTable(ArrayList<News> newsTable2){
			if(newsTable2 == null){
		 		   NewsNum = 0;
		 	   }
		 	   else {
		 	    	NewsNum = newsTable2.size();
		 	   }
			 DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	 	    	for(int i = 1;i < table.getRowCount();){
	 	    		tableModel.removeRow(i);
	 	    	} 
	 	    	
		 	    if(NewsNum == 0){
		 	    	tableModel.addRow(new Object[]{"","", "", "暂无新闻！",""}); 	
		 	    	bottomnavi.setVisible(false);
		 	    }
		 	    else{
		 	    	pageNum = NewsNum / (MaxNumPerpage) + 1;
		 	    	if(pageNum > 1) curPageNo = 1;
		 	    	for(int j = 0;j < NewsNum && j < MaxNumPerpage;j ++){
		 	    		Date dt = new Date(newsTable2.get(j).getTime());
			 	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 	    	String time = df.format(dt);
			 	    	tableModel.addRow(new Object[]{
			 	    			newsTable2.get(j).getSource(),
			 	    			time,
			 	    			newsTable2.get(j).getSource(),
			 	    			newsTable2.get(j).getTitle(),
			 	    			newsTable2.get(j).getSource()});
		 	    	} 
		 	    	if(pageNum > 1) bottomnavi.setVisible(true);
			 		else bottomnavi.setVisible(false);
		 	    }
		}
		
		public static String getNewsID(int Rsel){
			return (String) table.getValueAt(Rsel, 0);
		}
		
		

		@Override
		public void updatePage() {
			// TODO Auto-generated method stub
			newsTable = di.getNewsList();//接收已将按照时间排好顺序的结果
		 	   updateTable(newsTable);
		}
		
}//end of News



class NewsDetailDg extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5893692668956428617L;
	
	private DataInterface di;
	private NewsDetailDg curdg = this;
		 JLabel newsTitle,inv;
		    JTextArea newsDetail;
		    JPanel panel,panel2,panelbottom,conp;
		    JButton closebtn;
		    int RowSel;
		    String sNewsTitle,newsID,
		    		snewsDetail; 
		    detailML dml;
		    NewsDetailDg(int Rsel){
		    	di = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		    	RowSel = Rsel;
		    	sNewsTitle = di.getNewsList().get(Rsel-1).getTitle()/* test  String
		    			+ " ID=" + String.valueOf(Rsel)
		    			+ "  "+ NewsPanel.table.getValueAt(Rsel, 1).toString() */;
		    	snewsDetail = di.getNewsList().get(Rsel-1).getContent();
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
		        this.setLocation((ScreenSize.WIDTH - this.getWidth())/2,
		        		(ScreenSize.HEIGHT - this.getHeight())/2);
		        panel = new JPanel();
		        panelbottom = new JPanel();
		        newsTitle = new JLabel(sNewsTitle);
		        Font titlef=new Font("宋体",Font.BOLD,24);
		        newsTitle.setFont(titlef);
		        newsTitle.setForeground(Color.WHITE);
		        inv = new JLabel("");
		        inv.setPreferredSize(new Dimension(33, 1));
		        //inv.setVisible(false);

		        panel.setLayout(new BorderLayout());
		        panel.setPreferredSize(new Dimension(750,52));
		        panel.setBackground(Color.DARK_GRAY);
		        panel.add(newsTitle,FlowLayout.LEFT);
		        panel.add(inv,"West");
		        
		        panel2 = new JPanel();
		        panel2.setSize(682,398);
		        panel2.setBackground(Color.DARK_GRAY);
		        newsDetail = new JTextArea(snewsDetail,25,62);
		        newsDetail.setEditable(false);
		        newsDetail.setLineWrap(true);
		        newsDetail.setBorder(new LineBorder(Color.gray, 1));
		        newsDetail.setBackground(Color.BLACK);
		        newsDetail.setForeground(Color.WHITE);

		        JScrollPane jsp = new JScrollPane();
		        jsp.setViewportView(newsDetail);
		        jsp.getVerticalScrollBar().setPreferredSize(new Dimension(13,13));
		        jsp.setBorder(new LineBorder(Color.black, 0));
		        
		        panel2.add(jsp);
		        closebtn = new JButton("关闭窗口");
		        closebtn.setFocusPainted(false);
		        closebtn.addMouseListener(dml);
		        closebtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        panelbottom.setPreferredSize(new Dimension(750,52));
		        panelbottom.add(closebtn);
		        panelbottom.setBackground(Color.DARK_GRAY);

		        
		        conp.setSize(720, 510);
		        conp.setBorder(new LineBorder(Color.GRAY, 1));
		        conp.add(panel,"North");
		        conp.add(panel2,"Center");
		        conp.add(panelbottom,"South");
		        conp.setBackground(Color.DARK_GRAY);
		        add(conp);
		        this.pack();
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
	  private DataInterface di;
		
	  public void mousePressed(MouseEvent e){
				if(NewsPanel.table.isRowSelected(0))
			{
					NewsPanel.table.clearSelection();// 让第一行不可选
			}
		    }
		    public void mouseReleased(MouseEvent e){
		    	if(e.getSource() == NewsPanel.table){
		 	    	if(NewsPanel.table.isRowSelected(0))NewsPanel.table.clearSelection();// 让第一行不可选
		 	    	/*for(int j = 1; j < News.table.getRowCount(); j++)
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
						*/
		    	}
		    }
		    public void mouseEntered(MouseEvent e){
		    
		    }
		    public void mouseExited(MouseEvent e){
		    	if(e.getSource() == NewsPanel.table){
		    		NewsPanel.table.clearSelection();
		    	}
		    }
		    public void mouseClicked(MouseEvent e){
		    	di = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		    	if(e.getSource() == NewsPanel.table){
		    		if(e.getClickCount() == 2 || e.getClickCount() == 1){
		    	
		 	        	for(int j = 0; j < NewsPanel.table.getRowCount(); j++)
		 				{
		 					if(NewsPanel.table.isRowSelected(j))
		 						{
		 						NewsPanel.table.removeRowSelectionInterval( j, j );
		 							if(j > 0){
		 								NewsPanel.myWnd = new NewsDetailDg(j);
										NewsPanel.myWnd.setVisible(true);

		 							}
		 							NewsPanel.table.clearSelection();// 
		 						}
		 	            	 
		 				}
		    		}	
		    		else{NewsPanel.table.clearSelection();// 
		    		}
		    	}
		    	else if(e.getSource() == NewsPanel.b1){
		    		NewsPanel.preKeyword = NewsPanel.keyword;
		    		NewsPanel.keyword = NewsPanel.text1.getText();
		    		
		    		NewsPanel.fD1 = NewsPanel.setFromDate();
		    		NewsPanel.tD2 = NewsPanel.setToDate();
		    		ArrayList<News> NewsPanelTable = di.searchNews( NewsPanel.keyword, NewsPanel.fD1, NewsPanel.tD2);
		 	 	   NewsPanel.updateTable(NewsPanelTable);
		    		NewsPanel.newsTable = NewsPanelTable;
		    	}
		    	else if(e.getSource() == NewsPanel.b2){
			    		ArrayList<News> NewsPanelTable = di.getNewsList();
			    		NewsPanel.updateTable(NewsPanelTable);
			    		NewsPanel.newsTable = NewsPanelTable;
		    	}else if(e.getSource() == NewsPanel.btnAllnews){
		    		ArrayList<News> NewsPanelTable = di.getNewsList();
		    		NewsPanel.updateTable(NewsPanelTable);
		    		//NewsPanel.NewsPanelTable = NewsPanelTable;
		    	}
		    	
		    		
		    }
}

class MyMML implements MouseMotionListener {
	 	private	int index0,index1,mousey;
		    public void mouseMoved(MouseEvent e){
		    		if((mousey = e.getY()) >= 30 && NewsPanel.NewsNum > 0){
		    			NewsPanel.table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    		}
		    		else{
		    			NewsPanel.table.clearSelection();
		    			NewsPanel.table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		}
		    			
		    		index0 = mousey / 30 - 1;
		    		index1 = index0 + 1;
		    		if(index0 >= 0 && NewsPanel.NewsNum > 0){
		    			NewsPanel.table.setRowSelectionInterval(index0, index1);
		    		}
		    		
		    }
		    public void mouseDragged(MouseEvent e){
		    	if(NewsPanel.table.isRowSelected(0)) NewsPanel.table.clearSelection();
		    }
}
		 
class MyAcL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 if(e.getSource()==NewsPanel.text1){
				 NewsPanel.keyword=(NewsPanel.text1.getText());
		           
		        }
		        else{}
		}
}

