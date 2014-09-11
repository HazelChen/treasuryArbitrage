package edu.nju.treasuryArbitrage.futuresMarket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class FuturesMarket extends JPanel{
	private static final long serialVersionUID = 4293989421427626065L;
	private static final int WIDTH=1200;
	private static final int HEIGHT=700;
	private static final int TABLE_HEIGHT=40;
	private static final int ROW_HEIGHT=40;
	private static final int HEADER_HEIGHT=40;
	private static final int LABEL_WIDTH=60;
	private static final int LABEL_HEIGHT=20;
	private static JTable futuresTable1;
	private static JTable futuresTable2;
	private static JTable futuresTable3;
	private static JTable futuresHeader;
	private static FuturesPanel detailPanel;
	private static TableCellTextPaneRenderer tctpHeader;
	private static TableCellTextPaneRenderer tctpTable1;
	private static TableCellTextPaneRenderer tctpTable2;
	private static TableCellTextPaneRenderer tctpTable3;
	
	
	
	public FuturesMarket() {
		this.setBackground(Color.BLACK);
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		
		futuresHeader=getHeader();
		futuresTable1=getTable1();
		futuresTable2=getTable2();
		futuresTable3=getTable3();
		this.add(futuresHeader);
		this.add(futuresTable1);
		this.add(futuresTable2);
		this.add(futuresTable3);
		futuresHeader.setBounds(0,10,WIDTH-20,HEADER_HEIGHT);
		futuresTable1.setBounds(0,10+HEADER_HEIGHT,WIDTH-20,TABLE_HEIGHT);
		futuresTable2.setBounds(0,10+HEADER_HEIGHT+TABLE_HEIGHT,WIDTH-20,TABLE_HEIGHT);
		futuresTable3.setBounds(0,10+HEADER_HEIGHT+2*TABLE_HEIGHT,WIDTH-20,TABLE_HEIGHT);

		LinePanel line1=new LinePanel(0,10+HEADER_HEIGHT+2*TABLE_HEIGHT+TABLE_HEIGHT,WIDTH,10+HEADER_HEIGHT+2*TABLE_HEIGHT+TABLE_HEIGHT);
		this.add(line1);
		line1.setBounds(0,10+HEADER_HEIGHT+3*TABLE_HEIGHT+20,WIDTH,1);
		
		detailPanel=new FuturesPanel();
		this.add(detailPanel);
		detailPanel.setBounds(0,10+HEADER_HEIGHT+3*TABLE_HEIGHT+20,WIDTH/5*2,HEIGHT-190);
		
		LinePanel line2=new LinePanel((WIDTH/5)*2,10+HEADER_HEIGHT+2*TABLE_HEIGHT+TABLE_HEIGHT,(WIDTH/5)*2,HEIGHT);
		this.add(line2);
		line2.setBounds((WIDTH/5)*2,10+HEADER_HEIGHT+3*TABLE_HEIGHT+20,1,HEIGHT-190);
	
	}
	
	public JPanel getPanel(){
		return this;
	}
	
	private JTable getHeader(){
		String[] header={"序号","代码","名称","持仓","增仓","日增仓","昨收","今开","最高","最低"
				,"金额","昨结","今结","振幅","量比","沉淀资金","资金流向","外盘","内盘"};
		String[][] headerInfo={				
			new String[]{"序号","代码","名称","持仓","增仓","日增仓","昨收","今开","最高","最低"
						,"金额","昨结","今结","振幅","量比","沉淀资金","资金流向","外盘","内盘"}
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
		String[] columnTitle={"序号","代码","名称","持仓","增仓","日增仓","昨收","今开","最高","最低"
				,"金额","昨结","今结","振幅","量比","沉淀资金","资金流向","外盘","内盘"};
		
		Object[][] futuresInfo={				
				new Object[]{1,"TF1409","国债1409",6979,"+0","134",92.984,92.456,92.984,92.456,
							"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274}
		};
		
        DefaultTableModel model = new DefaultTableModel(futuresInfo,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model);
        tctpTable1=new TableCellTextPaneRenderer();
        tctpTable1.setBackground(Color.BLACK);
        
        table.setDefaultRenderer(Object.class,tctpTable1);
        //table.setGridColor(Color.BLACK);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setBackground(Color.BLACK);
        table.setRowHeight(ROW_HEIGHT);
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener1());
        

		return table;
  
		
	}
	
	private JTable getTable2(){
		String[] columnTitle={"序号","代码","名称","持仓","增仓","日增仓","昨收","今开","最高","最低"
				,"金额","昨结","今结","振幅","量比","沉淀资金","资金流向","外盘","内盘"};
		
		Object[][] futuresInfo={ 
				new Object[]{2,"TF1412","国债1412",6979,"+0","134",92.984,92.456,92.984,92.456,
						"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274}};

		
        DefaultTableModel model = new DefaultTableModel(futuresInfo,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model);
        tctpTable2=new TableCellTextPaneRenderer();
        tctpTable2.setBackground(Color.BLACK);
        
        table.setDefaultRenderer(Object.class,tctpTable2);
        //table.setGridColor(Color.BLACK);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setBackground(Color.BLACK);
        table.setRowHeight(ROW_HEIGHT);
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener2());
        

		return table;
  
		
	}
	
	private JTable getTable3(){
		String[] columnTitle={"序号","代码","名称","持仓","增仓","日增仓","昨收","今开","最高","最低"
				,"金额","昨结","今结","振幅","量比","沉淀资金","资金流向","外盘","内盘"};
		
		Object[][] futuresInfo={				
				new Object[]{3,"TF1433","国债1433",6979,"+0","134",92.984,92.456,92.984,92.456,
						"16.48亿",92.984,92.456,"0.33%",1.04,"64.97亿","93.35万",527,1274}
		};
		
        DefaultTableModel model = new DefaultTableModel(futuresInfo,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model);
        tctpTable3=new TableCellTextPaneRenderer();
        tctpTable3.setBackground(Color.BLACK);
        
        table.setDefaultRenderer(Object.class,tctpTable3);
        //table.setGridColor(Color.BLACK);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setBackground(Color.BLACK);
        table.setRowHeight(ROW_HEIGHT);
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener3());
        

		return table;
  
		
	}
	
	private void getTableData(){
				
	}
	
	public static void main(String[] args){
		JFrame frame=new JFrame();
		FuturesMarket fm=new FuturesMarket();
		JPanel test=fm.getPanel();
		frame.setSize(1200,700);
		frame.setLocationRelativeTo(null);
		frame.add(test);
		frame.setVisible(true);	
		
	}
	
	class TableSelectionListener1 implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent arg0) {
			tctpTable1.setBackground(Color.DARK_GRAY);
			futuresTable1.setBackground(Color.DARK_GRAY);
			tctpTable2.setBackground(Color.BLACK);
			futuresTable2.setBackground(Color.BLACK);
			tctpTable3.setBackground(Color.BLACK);
			futuresTable3.setBackground(Color.BLACK);
			
			futuresTable1.clearSelection();

			detailPanel.setDetail("test~");
		}
	}
	class TableSelectionListener2 implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent arg0) {	
			tctpTable1.setBackground(Color.BLACK);
			futuresTable1.setBackground(Color.BLACK);
			tctpTable2.setBackground(Color.DARK_GRAY);
			futuresTable2.setBackground(Color.DARK_GRAY);
			tctpTable3.setBackground(Color.BLACK);
			futuresTable3.setBackground(Color.BLACK);
			
			futuresTable2.clearSelection();

			detailPanel.setDetail("test~~~");
		}
	}
	class TableSelectionListener3 implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent arg0) {
			tctpTable1.setBackground(Color.BLACK);
			futuresTable1.setBackground(Color.BLACK);
			tctpTable2.setBackground(Color.BLACK);
			futuresTable2.setBackground(Color.BLACK);
			tctpTable3.setBackground(Color.DARK_GRAY);
			futuresTable3.setBackground(Color.DARK_GRAY);
			
			futuresTable3.clearSelection();
			
			detailPanel.setDetail("test~~~~~~");
			
		}
	}
	
	class TableCellTextPaneRenderer extends JTextPane implements TableCellRenderer{

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;
		public TableCellTextPaneRenderer(){
			  doc = new DefaultStyledDocument();
			  this.setStyledDocument(doc);
			  sas = new SimpleAttributeSet();
			  StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			  doc.setParagraphAttributes(0, 0, sas, true);		  
			  attr = new SimpleAttributeSet();	 
			  StyleConstants.setForeground(attr,Color.WHITE);
			  setCharacterAttributes(attr, false);
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {		
	        setText(value == null ? "" : value.toString());	        	        
            return this;
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
	
	class FuturesPanel extends JPanel{
		private JLabel[] data=new JLabel[28];
		public JLabel[] detail=new JLabel[28];
		private JLabel title=new JLabel();
		private int width=(WIDTH/5)*2;
		private int height=HEIGHT-190;
		
		public FuturesPanel(){
			data[0]=new JLabel("委比");
			data[1]=new JLabel("卖价");
			data[2]=new JLabel("买价");
			data[3]=new JLabel("成交");
			data[4]=new JLabel("涨跌");
			data[5]=new JLabel("涨幅");
			data[6]=new JLabel("振幅");
			data[7]=new JLabel("现手");
			data[8]=new JLabel("总手");
			data[9]=new JLabel("持仓");
			data[10]=new JLabel("增仓");
			data[11]=new JLabel("开平仓");
			data[12]=new JLabel("昨持仓");
			data[13]=new JLabel("涨停");
			data[14]=new JLabel("外盘");
			data[15]=new JLabel("开盘");
			data[16]=new JLabel("昨收");
			data[17]=new JLabel("最高");
			data[18]=new JLabel("最低");
			data[19]=new JLabel("金额");
			data[20]=new JLabel("均价");
			data[21]=new JLabel("今结");
			data[22]=new JLabel("昨结");
			data[23]=new JLabel("日增仓");
			data[24]=new JLabel("量比");
			data[25]=new JLabel("跌停");	
			data[26]=new JLabel("内盘");
			
			this.setSize(width,height);
			this.setLayout(null);
			
			for(int i=0;i<27;i++){
				this.add(data[i]);
				detail[i]=new JLabel();
				this.add(detail[i]);
				data[i].setForeground(Color.WHITE);
				detail[i].setForeground(Color.WHITE);
			}
			
			this.setBackground(Color.BLACK);
			
			title.setBounds(155,10,200,30);
			title.setText("国债TF102");
			this.add(title);
			title.setForeground(Color.WHITE);
			
			for(int i=0;i<15;i++){
				data[i].setBounds(30, 40+(LABEL_HEIGHT+7)*i, LABEL_WIDTH, LABEL_HEIGHT);
				detail[i].setBounds(140, 40+(LABEL_HEIGHT+7)*i, LABEL_WIDTH, LABEL_HEIGHT);
			}
			
			for(int i=15;i<27;i++){
				data[i].setBounds(250,121+(LABEL_HEIGHT+7)*(i-15),LABEL_WIDTH,LABEL_HEIGHT);
				detail[i].setBounds(360, 121+(LABEL_HEIGHT+7)*(i-15), LABEL_WIDTH, LABEL_HEIGHT);
			}
		}
		
		public void setDetail(String s){
			for(int i=0;i<27;i++){
				detail[i].setText(s);
			}
		}
	}
}
