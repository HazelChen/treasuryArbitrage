package edu.nju.treasuryArbitrage.futuresMarket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import vo.Arb_detail;
import edu.nju.treasuryArbitrage.framework.ComponentPanel;
import edu.nju.treasuryArbitrage.liveUpdate.LiveData;

public class FuturesMarket extends JPanel implements ComponentPanel{
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
	private static BlueTableCellTextPaneRenderer btctpTable1,btctpTable2,btctpTable3;
	private static RedTableCellTextPaneRenderer rtctpTable1,rtctpTable2,rtctpTable3;
	private static GreenTableCellTextPaneRenderer gtctpTable1,gtctpTable2,gtctpTable3;
	private static Arb_detail arb1,arb2,arb3;
	private static Object[][] tableInfo1,tableInfo2,tableInfo3;
	private static DefaultTableModel model1,model2,model3;
	private static LineChart chart1,chart2,chart3;
	
	
	public FuturesMarket() {
		this.setBackground(Color.BLACK);
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		
		
		arb1=new Arb_detail();
		arb1=this.getTableData("TF1412");
		arb2=new Arb_detail();
		arb2=this.getTableData("TF1503");
		arb3=new Arb_detail();
		arb3=this.getTableData("TF1506");
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
	
		chart1=new LineChart(0);
		chart2=new LineChart(1);
		chart3=new LineChart(2);
		this.add(chart1);
		this.add(chart2);
		this.add(chart3);
		chart1.setBounds((WIDTH/5)*2,10+HEADER_HEIGHT+3*TABLE_HEIGHT+20,(WIDTH/5)*3,HEIGHT-190);
		chart2.setBounds((WIDTH/5)*2,10+HEADER_HEIGHT+3*TABLE_HEIGHT+20,(WIDTH/5)*3,HEIGHT-190);
		chart3.setBounds((WIDTH/5)*2,10+HEADER_HEIGHT+3*TABLE_HEIGHT+20,(WIDTH/5)*3,HEIGHT-190);
		chart1.setVisible(true);
		chart2.setVisible(false);
		chart3.setVisible(false);
		
		detailPanel.setDetail(arb1,1);
	}
	
	public JPanel getPanel(){
		return this;
	}
	
	
	public void update(ArrayList<Arb_detail> arb_list){

		arb1=arb_list.get(0);
		arb2=arb_list.get(1);
		arb3=arb_list.get(2);
		
		
		String[] columnTitle={"代码","交割月份","现价","涨跌","涨跌幅"
				,"买量","买价","卖价","卖量","成交量","持仓量","日增仓"
				,"前结算价","今开","最高","最低","时间"};
		
		tableInfo1=this.getFuturesInfo(arb1, 1);
		tableInfo2=this.getFuturesInfo(arb2, 2);
		tableInfo3=this.getFuturesInfo(arb3, 3);
		model1 = new DefaultTableModel(tableInfo1,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };
        model2 = new DefaultTableModel(tableInfo2,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };
        model3 = new DefaultTableModel(tableInfo3,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };
        
		futuresTable1.setModel(model1);		
		futuresTable2.setModel(model2);	
		futuresTable3.setModel(model3);
		
		futuresTable1.getColumn(columnTitle[0]).setCellRenderer(tctpTable1);
		futuresTable1.getColumn(columnTitle[1]).setCellRenderer(tctpTable1);      
		futuresTable1.getColumn(columnTitle[3]).setCellRenderer(btctpTable1); 
		futuresTable2.getColumn(columnTitle[0]).setCellRenderer(tctpTable2);
		futuresTable2.getColumn(columnTitle[1]).setCellRenderer(tctpTable2);      
		futuresTable2.getColumn(columnTitle[3]).setCellRenderer(btctpTable2); 
		futuresTable3.getColumn(columnTitle[0]).setCellRenderer(tctpTable3);
		futuresTable3.getColumn(columnTitle[1]).setCellRenderer(tctpTable3);      
		futuresTable3.getColumn(columnTitle[3]).setCellRenderer(btctpTable3); 
	    if(arb1.getPresentPrice()>arb1.getPreSettlePrice()){
	    	futuresTable1.getColumn(columnTitle[2]).setCellRenderer(rtctpTable1);         
	    }else if(arb1.getPresentPrice()<arb1.getPreSettlePrice()){
	    	futuresTable1.getColumn(columnTitle[2]).setCellRenderer(gtctpTable1);
	    }else{
	    	futuresTable1.getColumn(columnTitle[2]).setCellRenderer(btctpTable1);
	    }      
	    if(arb1.getChange()>0){
	    	futuresTable1.getColumn(columnTitle[4]).setCellRenderer(rtctpTable1);         
	    }else if(arb1.getChange()<0){
	    	futuresTable1.getColumn(columnTitle[4]).setCellRenderer(gtctpTable1);
	    }else{
	    	futuresTable1.getColumn(columnTitle[4]).setCellRenderer(btctpTable1);
	    }
	    if(arb2.getPresentPrice()>arb2.getPreSettlePrice()){
	    	futuresTable2.getColumn(columnTitle[2]).setCellRenderer(rtctpTable2);         
        }else if(arb2.getPresentPrice()<arb2.getPreSettlePrice()){
        	futuresTable2.getColumn(columnTitle[2]).setCellRenderer(gtctpTable2);
        }else{
        	futuresTable2.getColumn(columnTitle[2]).setCellRenderer(btctpTable2);
        }      
        if(arb2.getChange()>0){
        	futuresTable2.getColumn(columnTitle[4]).setCellRenderer(rtctpTable2);         
        }else if(arb2.getChange()<0){
        	futuresTable2.getColumn(columnTitle[4]).setCellRenderer(gtctpTable2);
        }else{
        	futuresTable2.getColumn(columnTitle[4]).setCellRenderer(btctpTable2);
        }
        
        if(arb3.getPresentPrice()>arb3.getPreSettlePrice()){
        	futuresTable3.getColumn(columnTitle[2]).setCellRenderer(rtctpTable3);         
        }else if(arb3.getPresentPrice()<arb3.getPreSettlePrice()){
        	futuresTable3.getColumn(columnTitle[2]).setCellRenderer(gtctpTable3);
        }else{
        	futuresTable3.getColumn(columnTitle[2]).setCellRenderer(btctpTable3);
        }      
        if(arb3.getChange()>0){
        	futuresTable3.getColumn(columnTitle[4]).setCellRenderer(rtctpTable3);         
        }else if(arb3.getChange()<0){
        	futuresTable3.getColumn(columnTitle[4]).setCellRenderer(gtctpTable3);
        }else{
        	futuresTable3.getColumn(columnTitle[4]).setCellRenderer(btctpTable3);
        }
	        
	    for(int i=5;i<16;i++){
	    	futuresTable1.getColumn(columnTitle[i]).setCellRenderer(btctpTable1);
	    	futuresTable2.getColumn(columnTitle[i]).setCellRenderer(btctpTable2);
	    	futuresTable3.getColumn(columnTitle[i]).setCellRenderer(btctpTable3);
		    
	    }
	    futuresTable1.getColumn(columnTitle[16]).setCellRenderer(tctpTable1);
	    futuresTable2.getColumn(columnTitle[16]).setCellRenderer(tctpTable2);
	    futuresTable3.getColumn(columnTitle[16]).setCellRenderer(tctpTable3);
		futuresTable1.repaint();
		futuresTable2.repaint();
		futuresTable3.repaint();
		
	}
	
	public Object[][] getFuturesInfo(Arb_detail arb,int id){
		
		Date date = new Date(arb.getTime());
		int hour=date.getHours();
		int min=date.getMinutes();
		if(id==1){
			Object[][] futuresInfo={				
					new Object[]{"TF1412","2014年12月"				
							,arb.getPresentPrice(),arb.getPriceChange()
							,arb.getChange(),arb.getBid(),arb.getBidPirce()
							,arb.getAskPrice(),arb.getAsk(),arb.getVol()
							,arb.getRepository(),arb.getDailyWarehouse(),arb.getPreSettlePrice()
							,arb.getOpen(),arb.getHigh(),arb.getLow()
							,hour+":"+min}
			};
			return futuresInfo;
		}else if(id==2){
			Object[][] futuresInfo={				
					new Object[]{"TF1503","2015年03月"				
							,arb.getPresentPrice(),arb.getPriceChange()
							,arb.getChange(),arb.getBid(),arb.getBidPirce()
							,arb.getAskPrice(),arb.getAsk(),arb.getVol()
							,arb.getRepository(),arb.getDailyWarehouse(),arb.getPreSettlePrice()
							,arb.getOpen(),arb.getHigh(),arb.getLow()
							,hour+":"+min}
			};
			return futuresInfo;
		}else if(id==3){
			Object[][] futuresInfo={				
					new Object[]{"TF1506","2015年06月"				
							,arb.getPresentPrice(),arb.getPriceChange()
							,arb.getChange(),arb.getBid(),arb.getBidPirce()
							,arb.getAskPrice(),arb.getAsk(),arb.getVol()
							,arb.getRepository(),arb.getDailyWarehouse(),arb.getPreSettlePrice()
							,arb.getOpen(),arb.getHigh(),arb.getLow()
							,hour+":"+min}
			};
			return futuresInfo;
		}
		return null;
	}
	private JTable getHeader(){
		String[] header={"代码","交割月份","现价","涨跌","涨跌幅"
				,"买量","买价","卖价","卖量","成交量","持仓量","日增仓"
				,"前结算价","今开","最高","最低","时间"};
		String[][] headerInfo={				
			new String[]{"代码","交割月份","现价","涨跌","涨跌幅"
					,"买量","买价","卖价","卖量","成交量","持仓量","日增仓"
					,"前结算价","今开","最高","最低","时间"}
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
		
		String[] columnTitle={"代码","交割月份","现价","涨跌","涨跌幅"
				,"买量","买价","卖价","卖量","成交量","持仓量","日增仓"
				,"前结算价","今开","最高","最低","时间"};
		
		Date date = new Date(arb1.getTime());
		int hour=date.getHours();
		int min=date.getMinutes();
		
		
		Object[][] futuresInfo={				
				new Object[]{"TF1412","2014年12月"				
						,arb1.getPresentPrice(),arb1.getPriceChange()
						,arb1.getChange(),arb1.getBid(),arb1.getBidPirce()
						,arb1.getAskPrice(),arb1.getAsk(),arb1.getVol()
						,arb1.getRepository(),arb1.getDailyWarehouse(),arb1.getPreSettlePrice()
						,arb1.getOpen(),arb1.getHigh(),arb1.getLow()
						,hour+":"+min
							}
				};
		tableInfo1=futuresInfo;
        model1 = new DefaultTableModel(tableInfo1,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
        };

        
        JTable table = new JTable(model1);
        tctpTable1=new TableCellTextPaneRenderer();
        tctpTable1.setBackground(Color.BLACK);
        btctpTable1=new BlueTableCellTextPaneRenderer();
        btctpTable1.setBackground(Color.BLACK);
        rtctpTable1=new RedTableCellTextPaneRenderer();
        rtctpTable1.setBackground(Color.BLACK);
        gtctpTable1=new GreenTableCellTextPaneRenderer();
        gtctpTable1.setBackground(Color.BLACK);
        
        table.getColumn(columnTitle[0]).setCellRenderer(tctpTable1);
        table.getColumn(columnTitle[1]).setCellRenderer(tctpTable1);      
        table.getColumn(columnTitle[3]).setCellRenderer(btctpTable1);              
        if(arb1.getPresentPrice()>arb1.getPreSettlePrice()){
        	table.getColumn(columnTitle[2]).setCellRenderer(rtctpTable1);         
        }else if(arb1.getPresentPrice()<arb1.getPreSettlePrice()){
        	table.getColumn(columnTitle[2]).setCellRenderer(gtctpTable1);
        }else{
        	table.getColumn(columnTitle[2]).setCellRenderer(btctpTable1);
        }      
        if(arb1.getChange()>0){
        	table.getColumn(columnTitle[4]).setCellRenderer(rtctpTable1);         
        }else if(arb1.getChange()<0){
        	table.getColumn(columnTitle[4]).setCellRenderer(gtctpTable1);
        }else{
        	table.getColumn(columnTitle[4]).setCellRenderer(btctpTable1);
        }
        
        for(int i=5;i<16;i++){
        	table.getColumn(columnTitle[i]).setCellRenderer(btctpTable1);
        }
        table.getColumn(columnTitle[16]).setCellRenderer(tctpTable1);
        
        //table.setDefaultRenderer(Object.class,tctpTable1);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setBackground(Color.BLACK);
        table.setRowHeight(ROW_HEIGHT);
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener1());
        

		return table;
  
		
	}
	
	private JTable getTable2(){
		String[] columnTitle={"代码","交割月份","现价","涨跌","涨跌幅"
				,"买量","买价","卖价","卖量","成交量","持仓量","日增仓"
				,"前结算价","今开","最高","最低","时间"};
		
		Date date = new Date(arb2.getTime());
		int hour=date.getHours();
		int min=date.getMinutes();
		Object[][] futuresInfo={				
				new Object[]{"TF1503","2015年03月"				
						,arb2.getPresentPrice(),arb2.getPriceChange()
						,arb2.getChange(),arb2.getBid(),arb2.getBidPirce()
						,arb2.getAskPrice(),arb2.getAsk(),arb2.getVol()
						,arb2.getRepository(),arb2.getDailyWarehouse(),arb2.getPreSettlePrice()
						,arb2.getOpen(),arb2.getHigh(),arb2.getLow()
						,hour+":"+min}
		};
		tableInfo2=futuresInfo;
        model2 = new DefaultTableModel(tableInfo2,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model2);
        tctpTable2=new TableCellTextPaneRenderer();
        tctpTable2.setBackground(Color.BLACK);
        btctpTable2=new BlueTableCellTextPaneRenderer();
        btctpTable2.setBackground(Color.BLACK);
        rtctpTable2=new RedTableCellTextPaneRenderer();
        rtctpTable2.setBackground(Color.BLACK);
        gtctpTable2=new GreenTableCellTextPaneRenderer();
        gtctpTable2.setBackground(Color.BLACK);
        
        table.getColumn(columnTitle[0]).setCellRenderer(tctpTable2);
        table.getColumn(columnTitle[1]).setCellRenderer(tctpTable2);      
        table.getColumn(columnTitle[3]).setCellRenderer(btctpTable2);              
        if(arb2.getPresentPrice()>arb2.getPreSettlePrice()){
        	table.getColumn(columnTitle[2]).setCellRenderer(rtctpTable2);         
        }else if(arb2.getPresentPrice()<arb2.getPreSettlePrice()){
        	table.getColumn(columnTitle[2]).setCellRenderer(gtctpTable2);
        }else{
        	table.getColumn(columnTitle[2]).setCellRenderer(btctpTable2);
        }      
        if(arb2.getChange()>0){
        	table.getColumn(columnTitle[4]).setCellRenderer(rtctpTable2);         
        }else if(arb2.getChange()<0){
        	table.getColumn(columnTitle[4]).setCellRenderer(gtctpTable2);
        }else{
        	table.getColumn(columnTitle[4]).setCellRenderer(btctpTable2);
        }
        
        for(int i=5;i<16;i++){
        	table.getColumn(columnTitle[i]).setCellRenderer(btctpTable2);
        }
        table.getColumn(columnTitle[16]).setCellRenderer(tctpTable2);
        
       
        //table.setDefaultRenderer(Object.class,tctpTable2);
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
		String[] columnTitle={"代码","交割月份","现价","涨跌","涨跌幅"
				,"买量","买价","卖价","卖量","成交量","持仓量","日增仓"
				,"前结算价","今开","最高","最低","时间"};
		
		Date date = new Date(arb3.getTime());
		int hour=date.getHours();
		int min=date.getMinutes();
		
		Object[][] futuresInfo={				
				new Object[]{"TF1506","2015年06月"				
						,arb3.getPresentPrice(),arb3.getPriceChange()
						,arb3.getChange(),arb3.getBid(),arb3.getBidPirce()
						,arb3.getAskPrice(),arb3.getAsk(),arb3.getVol()
						,arb3.getRepository(),arb3.getDailyWarehouse(),arb3.getPreSettlePrice()
						,arb3.getOpen(),arb3.getHigh(),arb3.getLow()
						,hour+":"+min}
		};
		tableInfo3=futuresInfo;
        model3 = new DefaultTableModel(tableInfo3,columnTitle) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        
        JTable table = new JTable(model3);
        tctpTable3=new TableCellTextPaneRenderer();
        tctpTable3.setBackground(Color.BLACK);
        btctpTable3=new BlueTableCellTextPaneRenderer();
        btctpTable3.setBackground(Color.BLACK);
        rtctpTable3=new RedTableCellTextPaneRenderer();
        rtctpTable3.setBackground(Color.BLACK);
        gtctpTable3=new GreenTableCellTextPaneRenderer();
        gtctpTable3.setBackground(Color.BLACK);
        
        table.getColumn(columnTitle[0]).setCellRenderer(tctpTable3);
        table.getColumn(columnTitle[1]).setCellRenderer(tctpTable3);      
        table.getColumn(columnTitle[3]).setCellRenderer(btctpTable3);              
        if(arb3.getPresentPrice()>arb3.getPreSettlePrice()){
        	table.getColumn(columnTitle[2]).setCellRenderer(rtctpTable3);         
        }else if(arb3.getPresentPrice()<arb3.getPreSettlePrice()){
        	table.getColumn(columnTitle[2]).setCellRenderer(gtctpTable3);
        }else{
        	table.getColumn(columnTitle[2]).setCellRenderer(btctpTable3);
        }      
        if(arb3.getChange()>0){
        	table.getColumn(columnTitle[4]).setCellRenderer(rtctpTable3);         
        }else if(arb3.getChange()<0){
        	table.getColumn(columnTitle[4]).setCellRenderer(gtctpTable3);
        }else{
        	table.getColumn(columnTitle[4]).setCellRenderer(btctpTable3);
        }
        
        for(int i=5;i<16;i++){
        	table.getColumn(columnTitle[i]).setCellRenderer(btctpTable3);
        }
        table.getColumn(columnTitle[16]).setCellRenderer(tctpTable3);
        
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
	
	private Arb_detail getTableData(String symbol){		
		ArrayList<Arb_detail> result=LiveData.getInstance().getArb_details();
		Arb_detail detail=new Arb_detail();

			for(int i=0;i<3;i++){
				if(result.get(i).getSymbol().equals(symbol)){
					detail=result.get(i);
					break;
				}		
			}
		
		return detail;
	}
	

	
	class TableSelectionListener1 implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent arg0) {
			tctpTable1.setBackground(Color.DARK_GRAY);
			btctpTable1.setBackground(Color.DARK_GRAY);
			futuresTable1.setBackground(Color.DARK_GRAY);
			rtctpTable1.setBackground(Color.DARK_GRAY);
			gtctpTable1.setBackground(Color.DARK_GRAY);
			
			tctpTable2.setBackground(Color.BLACK);
			btctpTable2.setBackground(Color.BLACK);
			futuresTable2.setBackground(Color.BLACK);
			rtctpTable2.setBackground(Color.BLACK);
			gtctpTable2.setBackground(Color.BLACK);
			
			tctpTable3.setBackground(Color.BLACK);
			btctpTable3.setBackground(Color.BLACK);
			futuresTable3.setBackground(Color.BLACK);
			rtctpTable3.setBackground(Color.BLACK);
			gtctpTable3.setBackground(Color.BLACK);
			
			futuresTable1.clearSelection();

			detailPanel.setDetail(arb1,1);
			chart1.setVisible(true);
			chart2.setVisible(false);
			chart3.setVisible(false);
		}
	}
	class TableSelectionListener2 implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent arg0) {	
			tctpTable1.setBackground(Color.BLACK);
			btctpTable1.setBackground(Color.BLACK);
			futuresTable1.setBackground(Color.BLACK);
			rtctpTable1.setBackground(Color.BLACK);
			gtctpTable1.setBackground(Color.BLACK);
			
			tctpTable2.setBackground(Color.DARK_GRAY);
			btctpTable2.setBackground(Color.DARK_GRAY);
			futuresTable2.setBackground(Color.DARK_GRAY);
			rtctpTable2.setBackground(Color.DARK_GRAY);
			gtctpTable2.setBackground(Color.DARK_GRAY);
			
			tctpTable3.setBackground(Color.BLACK);
			btctpTable3.setBackground(Color.BLACK);
			futuresTable3.setBackground(Color.BLACK);
			rtctpTable3.setBackground(Color.BLACK);
			gtctpTable3.setBackground(Color.BLACK);
			
			futuresTable2.clearSelection();

			detailPanel.setDetail(arb2,2);
			chart1.setVisible(false);
			chart2.setVisible(true);
			chart3.setVisible(false);
		}
	}
	class TableSelectionListener3 implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent arg0) {
			tctpTable1.setBackground(Color.BLACK);
			btctpTable1.setBackground(Color.BLACK);
			futuresTable1.setBackground(Color.BLACK);
			rtctpTable1.setBackground(Color.BLACK);
			gtctpTable1.setBackground(Color.BLACK);
			
			tctpTable2.setBackground(Color.BLACK);
			btctpTable2.setBackground(Color.BLACK);
			futuresTable2.setBackground(Color.BLACK);
			rtctpTable2.setBackground(Color.BLACK);
			gtctpTable2.setBackground(Color.BLACK);
			
			tctpTable3.setBackground(Color.DARK_GRAY);
			btctpTable3.setBackground(Color.DARK_GRAY);
			futuresTable3.setBackground(Color.DARK_GRAY);
			rtctpTable3.setBackground(Color.DARK_GRAY);
			gtctpTable3.setBackground(Color.DARK_GRAY);
			
			futuresTable3.clearSelection();
			
			detailPanel.setDetail(arb3,3);
			chart1.setVisible(false);
			chart2.setVisible(false);
			chart3.setVisible(true);
			
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
	
	class BlueTableCellTextPaneRenderer extends JTextPane implements TableCellRenderer{

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;
		public BlueTableCellTextPaneRenderer(){
			doc = new DefaultStyledDocument();
			this.setStyledDocument(doc);
			sas = new SimpleAttributeSet();
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, sas, true);		  
			attr = new SimpleAttributeSet();	 
			  
			StyleConstants.setForeground(attr,new Color(10,156,211));
			setCharacterAttributes(attr, false);
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {		
	        setText(value == null ? "" : value.toString());	
	        
            return this;
	    }
		
	}
	
	class RedTableCellTextPaneRenderer extends JTextPane implements TableCellRenderer{

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;
		public RedTableCellTextPaneRenderer(){
			doc = new DefaultStyledDocument();
			this.setStyledDocument(doc);
			sas = new SimpleAttributeSet();
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, sas, true);		  
			attr = new SimpleAttributeSet();	 
			  
			StyleConstants.setForeground(attr,Color.RED);
			setCharacterAttributes(attr, false);
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {		
	        setText(value == null ? "" : value.toString());	
	        
            return this;
	    }
		
	}
	
	class GreenTableCellTextPaneRenderer extends JTextPane implements TableCellRenderer{

		private static final long serialVersionUID = 1L;
		DefaultStyledDocument doc;
		MutableAttributeSet attr;
		SimpleAttributeSet sas;
		public GreenTableCellTextPaneRenderer(){
			doc = new DefaultStyledDocument();
			this.setStyledDocument(doc);
			sas = new SimpleAttributeSet();
			StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, 0, sas, true);		  
			attr = new SimpleAttributeSet();	 
			  
			StyleConstants.setForeground(attr,Color.GREEN);
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
		private JLabel[] data=new JLabel[24];
		public JLabel[] detail=new JLabel[24];
		private JLabel title=new JLabel();
		private int width=(WIDTH/5)*2;
		private int height=HEIGHT-190;
		
		public FuturesPanel(){

			data[0]=new JLabel("卖价");
			data[1]=new JLabel("买价");
			data[2]=new JLabel("成交");
			data[3]=new JLabel("涨跌");
			data[4]=new JLabel("涨幅");
			data[5]=new JLabel("振幅");
			data[6]=new JLabel("现手");
			data[7]=new JLabel("总手");
			data[8]=new JLabel("持仓");
			data[9]=new JLabel("昨持仓");
			data[10]=new JLabel("涨停");
			data[11]=new JLabel("外盘");		
			data[12]=new JLabel("开盘");
			data[13]=new JLabel("昨收");
			data[14]=new JLabel("最高");
			data[15]=new JLabel("最低");
			data[16]=new JLabel("金额");
			data[17]=new JLabel("均价");
			data[18]=new JLabel("今结");
			data[19]=new JLabel("昨结");
			data[20]=new JLabel("日增仓");
			data[21]=new JLabel("量比");
			data[22]=new JLabel("跌停");	
			data[23]=new JLabel("内盘");
			
			this.setSize(width,height);
			this.setLayout(null);
			
			for(int i=0;i<24;i++){
				this.add(data[i]);
				detail[i]=new JLabel();
				this.add(detail[i]);
				data[i].setForeground(Color.WHITE);
			}
			
			this.setBackground(Color.BLACK);
			
			title.setBounds(190,10,200,30);
			title.setText("点击期货查看详细数据");
			this.add(title);
			title.setVisible(false);
			title.setForeground(Color.WHITE);
			
			for(int i=0;i<13;i++){
				data[i].setBounds(30, 80+(LABEL_HEIGHT+10)*i, LABEL_WIDTH, LABEL_HEIGHT);
				detail[i].setBounds(140, 80+(LABEL_HEIGHT+10)*i, LABEL_WIDTH, LABEL_HEIGHT);
			
			}
			
			for(int i=13;i<24;i++){
				data[i].setBounds(250,140+(LABEL_HEIGHT+10)*(i-13),LABEL_WIDTH,LABEL_HEIGHT);
				detail[i].setBounds(360, 140+(LABEL_HEIGHT+10)*(i-13), LABEL_WIDTH, LABEL_HEIGHT);
			}
		}
		
		public void setDetail(Arb_detail arb,int i){
			if(i==1){
				title.setText("国债1412");
			}else if(i==2){
				title.setText("国债1503");
			}else if(i==3){
				title.setText("国债1506");
			}
			title.setVisible(true);

			String zhangdie;
			if(arb.getPriceChange()>=0){
				zhangdie="+"+arb.getPriceChange()+"%";
			}else{
				zhangdie=arb.getPriceChange()+"%";
			}
			String zhangfu;
			if(arb.getChange()>=0){
				zhangfu="+"+arb.getChange()+"%";
			}else{
				zhangfu=arb.getChange()+"%";
			}
			
			if(arb.getPresentPrice()>arb.getSettlePrice()){
				detail[2].setForeground(Color.RED);
			}else if(arb.getPresentPrice()<arb.getSettlePrice()){
				detail[2].setForeground(Color.GREEN);
			}else{
				detail[2].setForeground(new Color(10,156,211));
			}
			
			if(arb.getChange()>0){
				detail[3].setForeground(Color.RED);			
			}else if(arb.getChange()<0){
				detail[3].setForeground(Color.GREEN);	
			}else{
				detail[3].setForeground(new Color(10,156,211));	
			}
			
			detail[0].setForeground(new Color(10,156,211));
			detail[1].setForeground(new Color(10,156,211));
			for(int j=4;j<24;j++){
				detail[j].setForeground(new Color(10,156,211));
			}

			detail[0].setText(String.valueOf(arb.getAskPrice()));
			detail[1].setText(String.valueOf(arb.getBidPirce()));
			detail[2].setText(String.valueOf(arb.getPresentPrice()));
			detail[3].setText(zhangdie);
			detail[4].setText(zhangfu);
			detail[5].setText(String.valueOf(arb.getSwing())+"%");
			detail[6].setText(String.valueOf(arb.getNvol()));
			detail[7].setText(String.valueOf(arb.getVol()));
			detail[8].setText(String.valueOf(arb.getRepository()));
			detail[9].setText(String.valueOf(arb.getPreRepository()));
			detail[10].setText(String.valueOf(arb.getHardenPrice()));
			detail[11].setText(String.valueOf(arb.getOutvol()));
			detail[12].setText(String.valueOf(arb.getOpen()));
			detail[13].setText(String.valueOf(arb.getPreClose()));
			detail[14].setText(String.valueOf(arb.getHigh()));
			detail[15].setText(String.valueOf(arb.getLow()));
			detail[16].setText(String.valueOf(arb.getFullAmount())+"亿");
			detail[17].setText(String.valueOf(arb.getAverPrice()));
			detail[18].setText(String.valueOf(arb.getSettlePrice()));
			detail[19].setText(String.valueOf(arb.getPreSettlePrice()));
			detail[20].setText(String.valueOf(arb.getDailyWarehouse()));
			detail[21].setText(String.valueOf(arb.getRatio()));
			detail[22].setText(String.valueOf(arb.getLimitPrice()));
			detail[23].setText(String.valueOf(arb.getInvol()));
			
		}
	}

	@Override
	public void updatePage() {

		ArrayList<Arb_detail> arb_lists = LiveData.getInstance().getArb_details();
		update(arb_lists);
		chart1.addNewPrice(arb_lists.get(0).getPresentPrice());
		chart2.addNewPrice(arb_lists.get(1).getPresentPrice());
		chart3.addNewPrice(arb_lists.get(2).getPresentPrice());

	}
}
