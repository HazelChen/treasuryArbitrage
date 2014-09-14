package edu.nju.treasuryArbitrage.arbitragePortfolio;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import edu.nju.treasuryArbitrage.framework.CommentPanel;



public class ArbitragePortfolio extends JPanel implements CommentPanel{
	private static final long serialVersionUID = -2281757682879991851L;
	private static final int WIDTH=1200;
	private static final int HEIGHT=700;
	private static final int TABLE_HEIGHT=80;
	private static final int ROW_HEIGHT=40;
	private static final int HEADER_HEIGHT=40;
	private static final int BUTTON_WIDTH=80;
	private static final int BUTTON_HEIGHT=20;
	private static JButton group1,group2,group3,refresh;
	private static JTable arbitrageTable1,arbitrageTable2,arbitrageTable3;
	private static JTable arbitrageHeader;
	private static TableCellTextPaneRenderer tctpHeader;
	private static TableCellTextPaneRenderer tctpTable1;
	private static TableCellTextPaneRenderer tctpTable2;
	private static TableCellTextPaneRenderer tctpTable3;
	private static BuyPanel buyPanel;
	private static ConfirmPanel confirmPanel;
	
	public ArbitragePortfolio() {
		this.setBackground(Color.BLACK);
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		
		confirmPanel=new ConfirmPanel();
		this.add(confirmPanel);
		confirmPanel.setBounds(WIDTH/4,HEIGHT/5,WIDTH/5*2-20,HEIGHT-250);
		confirmPanel.setVisible(false);
		
		group1=new JButton("组合一");
		group2=new JButton("组合二");
		group3=new JButton("组合三");
		refresh=new JButton("刷新");
		
		this.add(group1);
		this.add(group2);
		this.add(group3);
		this.add(refresh);
		
		group1.setBounds(0, 5,BUTTON_WIDTH,BUTTON_HEIGHT);
		group2.setBounds(BUTTON_WIDTH, 5,BUTTON_WIDTH,BUTTON_HEIGHT);
		group3.setBounds(2*BUTTON_WIDTH, 5,BUTTON_WIDTH,BUTTON_HEIGHT);
		refresh.setBounds(WIDTH-BUTTON_WIDTH,5,BUTTON_WIDTH,BUTTON_HEIGHT);
		group1.setContentAreaFilled(false);
		group1.setBorderPainted(false);
		group1.setForeground(new Color(169,169,169));
		group2.setContentAreaFilled(false);
		group2.setBorderPainted(false);
		group2.setForeground(new Color(169,169,169));
		group3.setContentAreaFilled(false);
		group3.setBorderPainted(false);
		group3.setForeground(new Color(169,169,169));
		refresh.setContentAreaFilled(false);
		refresh.setBorderPainted(false);
		refresh.setForeground(new Color(135,206,250));
		
		arbitrageHeader=getHeader();
		this.add(arbitrageHeader);
		arbitrageHeader.setBounds(0,30,WIDTH-10,HEADER_HEIGHT);
		
		arbitrageTable1=getTable1();
		arbitrageTable2=getTable2();
		arbitrageTable3=getTable3();
		this.add(arbitrageTable1);
		this.add(arbitrageTable2);
		this.add(arbitrageTable3);
		arbitrageTable1.setBounds(0,30+HEADER_HEIGHT,WIDTH-10,TABLE_HEIGHT);
		arbitrageTable2.setBounds(0,30+HEADER_HEIGHT,WIDTH-10,TABLE_HEIGHT);
		arbitrageTable3.setBounds(0,30+HEADER_HEIGHT,WIDTH-10,TABLE_HEIGHT);
		arbitrageTable1.setVisible(true);
		arbitrageTable2.setVisible(false);
		arbitrageTable3.setVisible(false);
		
		group1.addActionListener(new Group1Listener());
		group2.addActionListener(new Group2Listener());
		group3.addActionListener(new Group3Listener());
		
		
		
		LinePanel line1=new LinePanel(0,30+HEADER_HEIGHT+TABLE_HEIGHT,WIDTH,30+HEADER_HEIGHT+TABLE_HEIGHT+TABLE_HEIGHT/2);
		this.add(line1);
		line1.setBounds(0,30+HEADER_HEIGHT+TABLE_HEIGHT*4/3,WIDTH,1);
		
		LinePanel line2=new LinePanel((WIDTH/5)*3,30+HEADER_HEIGHT+TABLE_HEIGHT*4/3,(WIDTH/5)*3,HEIGHT);
		this.add(line2);
		line2.setBounds((WIDTH/5)*3-10,30+HEADER_HEIGHT+TABLE_HEIGHT*4/3,1,HEIGHT);
			
		
		
		buyPanel=new BuyPanel();
		this.add(buyPanel);
		buyPanel.setBounds((WIDTH/5)*3,40+HEADER_HEIGHT+TABLE_HEIGHT*4/3,WIDTH/5*2-20,HEIGHT-230);
	}
	
	private void showInfo1(){
		buyPanel.showDetail("test~");
	}
	
	private void showInfo2(){
		buyPanel.showDetail("test~~~");
	}
	
	private void showInfo3(){
		buyPanel.showDetail("test~~~~~");
	}
	
	public JPanel getPanel(){
		return this;
	}
	
	private void getData(){
		
	}
	
	private JTable getHeader(){
		String[] header={"序号","代码","名称","现价","涨幅","涨跌","叫买","叫卖","买量","卖量"
				,"现手","总手","持仓","增仓","日增仓","昨收","今开","最高","最低","金额","昨结"
				,"今结","涨幅","量比","沉淀资金","资金流向","外盘","内盘"};
		String[][] headerInfo={				
			new String[]{"序号","代码","名称","现价","涨幅","涨跌","叫买","叫卖","买量","卖量"
					,"现手","总手","持仓","增仓","日增仓","昨收","今开","最高","最低","金额","昨结"
					,"今结","涨幅","量比","沉淀资金","资金流向","外盘","内盘"}
		};
		DefaultTableModel model = new DefaultTableModel(headerInfo,header) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
	    };
	    JTable table = new JTable(model);
        tctpHeader=new TableCellTextPaneRenderer();
        tctpHeader.setBackground(Color.BLACK);
        table.setDefaultRenderer(Object.class,tctpHeader);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(HEADER_HEIGHT);

		return table;
	}
	
	private JTable getTable1(){
		String[] header={"序号","代码","名称","现价","涨幅","涨跌","叫买","叫卖","买量","卖量"
				,"现手","总手","持仓","增仓","日增仓","昨收","今开","最高","最低","金额","昨结"
				,"今结","涨幅","量比","沉淀资金","资金流向","外盘","内盘"};
		
		Object[][] arbitrageInfo={				
				new Object[]{1,"TF1409","国债1409",6979,"+0","134",1,1,1,1,1,1,1,92.984,92.456,92.984,92.456,
							"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274,2,3},
				new Object[]{1,"TF1409","国债1409",6979,"+0","134",1,1,1,1,1,1,1,92.984,92.456,92.984,92.456,
						"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274,2,3}
						
		};
		
        DefaultTableModel model = new DefaultTableModel(arbitrageInfo,header) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model);
        tctpTable1=new TableCellTextPaneRenderer();
        tctpTable1.setBackground(Color.BLACK);
        
        table.setDefaultRenderer(Object.class,tctpTable1);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setBackground(Color.BLACK);
        table.setRowHeight(ROW_HEIGHT);

		return table;
		
	}	
	private JTable getTable2(){
		String[] header={"序号","代码","名称","现价","涨幅","涨跌","叫买","叫卖","买量","卖量"
				,"现手","总手","持仓","增仓","日增仓","昨收","今开","最高","最低","金额","昨结"
				,"今结","涨幅","量比","沉淀资金","资金流向","外盘","内盘"};
		
		Object[][] arbitrageInfo={				
				new Object[]{1,"TF1409","国债1409",6979,"+0","134",1,2,3,4,3,2,1,92.984,92.456,92.984,92.456,
							"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274,2,3},
				new Object[]{1,"TF1409","国债1409",6979,"+0","134",1,1,1,1,1,1,1,92.984,92.456,92.984,92.456,
						"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274,2,3}
						
		};
		
        DefaultTableModel model = new DefaultTableModel(arbitrageInfo,header) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model);
        tctpTable1=new TableCellTextPaneRenderer();
        tctpTable1.setBackground(Color.BLACK);
        
        table.setDefaultRenderer(Object.class,tctpTable1);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setBackground(Color.BLACK);
        table.setRowHeight(ROW_HEIGHT);

		return table;
		
	}	
	private JTable getTable3(){
		String[] header={"序号","代码","名称","现价","涨幅","涨跌","叫买","叫卖","买量","卖量"
				,"现手","总手","持仓","增仓","日增仓","昨收","今开","最高","最低","金额","昨结"
				,"今结","涨幅","量比","沉淀资金","资金流向","外盘","内盘"};
		
		Object[][] arbitrageInfo={				
				new Object[]{1,"TF1409","国债1409",6979,"+0","134",9,8,7,6,7,8,9,92.984,92.456,92.984,92.456,
							"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274,2,3},
				new Object[]{1,"TF1409","国债1409",6979,"+0","134",1,1,1,1,1,1,1,92.984,92.456,92.984,92.456,
						"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274,2,3}
						
		};
		
        DefaultTableModel model = new DefaultTableModel(arbitrageInfo,header) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model);
        tctpTable1=new TableCellTextPaneRenderer();
        tctpTable1.setBackground(Color.BLACK);
        
        table.setDefaultRenderer(Object.class,tctpTable1);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setBackground(Color.BLACK);
        table.setRowHeight(ROW_HEIGHT);

		return table;
		
	}
	
	public static void main(String[] args){
		JFrame frame=new JFrame();
		ArbitragePortfolio ap=new ArbitragePortfolio();
		JPanel test=ap.getPanel();
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.add(test);
		frame.setVisible(true);	
		
	}
	
	class TableCellTextPaneRenderer extends JTextPane implements TableCellRenderer{

		private static final long serialVersionUID = 1L;

		public TableCellTextPaneRenderer(){
			  DefaultStyledDocument doc = new DefaultStyledDocument();
			  this.setStyledDocument(doc);
			  
			  SimpleAttributeSet sas = new SimpleAttributeSet();
			  StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			  
			  	
			  
			  MutableAttributeSet attr = new SimpleAttributeSet();
			  StyleConstants.setForeground(attr,Color.WHITE);
			  
			  //StyleConstants.setFontSize(attr,12);
			  setCharacterAttributes(attr, false);
			  doc.setParagraphAttributes(0, 0, sas, true);	
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
			
	        setText(value == null ? "" : value.toString());	
	        
	        //this.setBackground(Color.BLACK);
	        
            return this;
	    }
		
	}
	
	class Group1Listener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			arbitrageTable1.setVisible(true);
			arbitrageTable2.setVisible(false);
			arbitrageTable3.setVisible(false);
			
			showInfo1();

		}
	}
	class Group2Listener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			arbitrageTable1.setVisible(false);
			arbitrageTable2.setVisible(true);
			arbitrageTable3.setVisible(false);
			
			showInfo2();
		}
	}
	class Group3Listener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			arbitrageTable1.setVisible(false);
			arbitrageTable2.setVisible(false);
			arbitrageTable3.setVisible(true);
			
			showInfo3();
		}
	}
	
	class LinePanel extends JPanel{
		private int x1,x2,y1,y2;
		public LinePanel(int xx1,int yy1,int xx2,int yy2){
			x1=xx1;
			x2=xx2;
			y1=yy1;
			y2=yy2;
		}
		
		public void paintComponets(Graphics g){
			Graphics2D g2=(Graphics2D)g; 
			g2.setColor(Color.WHITE);
		    g2.drawLine(x1,y1,x2,y2);
		    
		    
		}
	}
	
	class BuyPanel extends JPanel{
		private JLabel title;
		private JLabel name,name1,name2;
		private JLabel direction,dir1,dir2;
		private JLabel price,price1,price2;
		private JLabel holdings,hld;
		private JLabel money,mny;
		private JButton confirm;
		public JTextField tfdHoldings;
		public BuyPanel(){
			title=new JLabel("下单信息");
			name=new JLabel("合约名称",JLabel.CENTER);
			direction=new JLabel("套利方向",JLabel.CENTER);
			price=new JLabel("合约价格",JLabel.CENTER);
			holdings=new JLabel("手数",JLabel.CENTER);
			money=new JLabel("所需保证金",JLabel.CENTER);
			confirm=new JButton("下单");
			name1=new JLabel();
			name2=new JLabel();
			dir1=new JLabel();
			dir2=new JLabel();
			price1=new JLabel();
			price2=new JLabel();
			hld=new JLabel("手");
			mny=new JLabel();
			tfdHoldings=new JTextField();
			this.add(confirm);
			this.add(money);
			this.add(holdings);
			this.add(price);
			this.add(direction);
			this.add(name);
			this.add(title);
			this.add(name1);
			this.add(name2);
			this.add(dir1);
			this.add(dir2);
			this.add(price1);
			this.add(price2);
			this.add(hld);
			this.add(mny);
			this.add(tfdHoldings);
			this.setLayout(null);
			title.setBounds(20,0,200,50);
			title.setFont(new Font("serif",Font.BOLD,20));
			title.setForeground(Color.WHITE);
			this.setBackground(Color.DARK_GRAY);
			
			Font font=new Font("serif",Font.BOLD,14);
			name.setBounds(50,80,80,30);
			name.setFont(font);
			name.setForeground(Color.WHITE);
			name1.setBounds(160,80,80,30);
			name2.setBounds(280,80,80,30);
			name1.setFont(font);
			name1.setForeground(Color.WHITE);
			name2.setFont(font);
			name2.setForeground(Color.WHITE);
			
			direction.setBounds(50,140,80,30);
			direction.setFont(font);
			direction.setForeground(Color.WHITE);
			dir1.setBounds(160,140,80,30);
			dir2.setBounds(280,140,80,30);
			dir1.setFont(font);
			dir1.setForeground(Color.WHITE);
			dir2.setFont(font);
			dir2.setForeground(Color.WHITE);
			
			price.setBounds(50,200,80,30);
			price.setFont(font);
			price.setForeground(Color.WHITE);
			price1.setBounds(160,200,80,30);
			price2.setBounds(280,200,80,30);
			price1.setFont(font);
			price1.setForeground(Color.WHITE);
			price2.setFont(font);
			price2.setForeground(Color.WHITE);
			
			holdings.setBounds(50,260,80,30);
			holdings.setFont(font);
			holdings.setForeground(Color.WHITE);
			hld.setBounds(250,260,80,30);
			hld.setFont(font);
			hld.setForeground(Color.WHITE);
			tfdHoldings.setBounds(140,260,100,30);
			
			
			money.setBounds(50,320,80,30);
			money.setFont(font);
			money.setForeground(Color.WHITE);
			mny.setBounds(140,320,80,30);
			mny.setFont(font);
			mny.setForeground(Color.WHITE);
			
			confirm.setBounds(100,380,300,40);
			confirm.setContentAreaFilled(false);
			confirm.setBorderPainted(true);
			confirm.setFocusPainted(false);
			confirm.setFont(new Font("serif",Font.BOLD,20));
			confirm.setForeground(Color.WHITE);
			confirm.addActionListener(new ConfirmListener());
			
		}
		
		public void showDetail(String s){
			name1.setText(s);
			name2.setText(s);
			dir1.setText(s);
			dir2.setText(s);
			price1.setText(s);
			price2.setText(s);
		}
		
		public class ConfirmListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String text=tfdHoldings.getText();
				if(text.length()==0){
					JOptionPane.showMessageDialog(null,"手数输入错误","错误提示",JOptionPane.WARNING_MESSAGE);
				}else {
					if(Integer.parseInt(text)>0){
						confirmPanel.setVisible(true);
					}else{
					JOptionPane.showMessageDialog(null,"手数输入错误","错误提示",JOptionPane.WARNING_MESSAGE);
				}
				}
			}
		}
	}
	
	
	class ConfirmPanel extends JPanel{
		private JLabel title;
		private JLabel name,name1,name2;
		private JLabel type,typeData;
		private JLabel direction,dir_name1,dir_name2,dir1,dir2;
		private JLabel price,price_name1,price_name2,price1,price2;
		private JLabel holdings,hld;
		private JLabel money,mny;
		private JButton confirm,cancel;
		
		public ConfirmPanel(){
			title=new JLabel("下单信息确认");
			name=new JLabel("合约名称",JLabel.CENTER);
			type=new JLabel("交易类型",JLabel.CENTER);
			typeData=new JLabel();
			direction=new JLabel("套利方向",JLabel.CENTER);
			price=new JLabel("合约价格",JLabel.CENTER);
			holdings=new JLabel("手数",JLabel.CENTER);
			money=new JLabel("投入保证金",JLabel.CENTER);
			confirm=new JButton("确认");
			cancel=new JButton("取消");
			name1=new JLabel();
			name2=new JLabel();
			dir_name1=new JLabel();
			dir_name2=new JLabel();
			dir1=new JLabel();
			dir2=new JLabel();
			price_name1=new JLabel();
			price_name2=new JLabel();
			price1=new JLabel();
			price2=new JLabel();
			hld=new JLabel();
			mny=new JLabel();
			
			this.add(confirm);
			this.add(cancel);
			this.add(money);
			this.add(holdings);
			this.add(price);
			this.add(direction);
			this.add(name);
			this.add(title);
			this.add(name1);
			this.add(name2);
			this.add(type);
			this.add(typeData);
			this.add(dir_name1);
			this.add(dir_name2);
			this.add(dir1);
			this.add(dir2);
			this.add(price_name1);
			this.add(price_name2);
			this.add(price1);
			this.add(price2);
			this.add(hld);
			this.add(mny);

			this.setLayout(null);
			title.setBounds(160,5,200,30);
			title.setFont(new Font("serif",Font.BOLD,20));
			title.setForeground(Color.BLACK);
			
			Font font=new Font("serif",Font.BOLD,14);
			name.setBounds(50,50,80,30);
			name.setFont(font);
			name.setForeground(Color.BLACK);
			
			name1.setBounds(160,50,80,30);
			name2.setBounds(280,50,80,30);
			name1.setFont(font);
			name1.setForeground(Color.BLACK);
			name2.setFont(font);
			name2.setForeground(Color.BLACK);
			
			type.setBounds(50,110,80,30);
			type.setFont(font);
			type.setForeground(Color.BLACK);
			typeData.setBounds(160,110,80,30);
			typeData.setFont(font);
			typeData.setForeground(Color.BLACK);
			
			direction.setBounds(50,170,80,30);
			direction.setFont(font);
			direction.setForeground(Color.BLACK);
			dir_name1.setBounds(160,170,80,30);
			dir1.setBounds(280,170,80,30);
			dir_name2.setBounds(160,200,80,30);
			dir2.setBounds(280,200,80,30);
			dir_name1.setFont(font);
			dir_name1.setForeground(Color.BLACK);
			dir_name2.setFont(font);
			dir_name2.setForeground(Color.BLACK);
			dir1.setFont(font);
			dir1.setForeground(Color.BLACK);
			dir2.setFont(font);
			dir2.setForeground(Color.BLACK);
			
			price.setBounds(50,230,80,30);
			price.setFont(font);
			price.setForeground(Color.BLACK);
			price_name1.setBounds(160,230,80,30);
			price1.setBounds(280,230,80,30);
			price_name2.setBounds(160,230,80,30);
			price2.setBounds(280,230,80,30);
			price_name1.setFont(font);
			price_name1.setForeground(Color.BLACK);
			price_name2.setFont(font);
			price_name2.setForeground(Color.BLACK);
			price1.setFont(font);
			price1.setForeground(Color.BLACK);
			price2.setFont(font);
			price2.setForeground(Color.BLACK);
			
			holdings.setBounds(50,290,80,30);
			holdings.setFont(font);
			holdings.setForeground(Color.BLACK);
			hld.setBounds(250,290,80,30);
			hld.setFont(font);
			hld.setForeground(Color.BLACK);
			
			money.setBounds(50,350,80,30);
			money.setFont(font);
			money.setForeground(Color.BLACK);
			mny.setBounds(140,350,80,30);
			mny.setFont(font);
			mny.setForeground(Color.BLACK);
			
			confirm.setBounds(110,400,100,30);
			cancel.setBounds(260,400,100,30);
			confirm.setContentAreaFilled(false);
			confirm.setBorderPainted(true);
			confirm.setFocusPainted(false);
			confirm.setFont(new Font("serif",Font.BOLD,20));
			confirm.setForeground(Color.BLACK);
			cancel.setContentAreaFilled(false);
			cancel.setBorderPainted(true);
			cancel.setFocusPainted(false);
			cancel.setFont(new Font("serif",Font.BOLD,20));
			cancel.setForeground(Color.BLACK);
			
			confirm.addActionListener(new ConfirmDetailListener());
			cancel.addActionListener(new CancelListener());
			
		}
		
		public class ConfirmDetailListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			}	
		}
		
		public class CancelListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				confirmPanel.setVisible(false);
			}	
		}

	}


	@Override
	public void updatePage() {
		// TODO Auto-generated method stub
		
	}
}
