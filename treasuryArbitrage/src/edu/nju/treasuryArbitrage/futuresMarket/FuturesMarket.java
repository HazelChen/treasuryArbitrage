package edu.nju.treasuryArbitrage.futuresMarket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

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

import edu.nju.treasuryArbitrage.network.DataInterfacePile;
import vo.Arb_detail;

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
	private static Arb_detail arb1,arb2,arb3;
	private static Object[][] tableInfo1,tableInfo2,tableInfo3;
	private static DefaultTableModel model1,model2,model3;
	
	
	public FuturesMarket() {
		this.setBackground(Color.BLACK);
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		arb3=new Arb_detail();
		arb3=this.getTableData("TF1503");
		arb2=new Arb_detail();
		arb2=this.getTableData("TF1412");
		arb1=new Arb_detail();
		arb1=this.getTableData("TF1409");
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
	
	public void update(ArrayList<Arb_detail> arb_list){

		arb1=arb_list.get(0);
		arb2=arb_list.get(1);
		arb3=arb_list.get(2);
		
		
		String[] columnTitle={"序号","代码","名称","持仓","增仓","日增仓","昨收","今开","最高","最低"
				,"金额","昨结","今结","振幅","量比","沉淀资金","资金流向","外盘","内盘"};
		
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
		futuresTable1.repaint();
		futuresTable2.repaint();
		futuresTable3.repaint();
		
	}
	
	public Object[][] getFuturesInfo(Arb_detail arb,int id){
		String warehouse="+0";
		if(arb.getWarehouse()>=0){
			warehouse="+"+arb.getWarehouse();
		}else{
			warehouse=String.valueOf(arb.getWarehouse());
		}
		String dailyWh="+0";
		if(arb.getDailyWarehouse()>=0){
			dailyWh="+"+arb.getDailyWarehouse();
		}else{
			dailyWh=String.valueOf(arb.getDailyWarehouse());
		}
		String swing=arb.getSwing()+"%";
		String fullAmount=arb.getFullAmount()+"亿";
		if(id==1){
			Object[][] futuresInfo={				
					new Object[]{1,"TF1409","国债1409"
							,arb.getRepository(),warehouse,dailyWh
							,arb.getPreClose(),arb.getOpen()
							,arb.getHigh(),arb.getLow(),
								fullAmount,arb.getPreSettlePrice()
								,arb.getSettlePrice(),swing
								,arb.getRatio(),"test","test"
								,arb.getOutvol(),arb.getInvol()}
			};
			return futuresInfo;
		}else if(id==2){
			Object[][] futuresInfo={				
					new Object[]{2,"TF1412","国债1412"
							,arb.getRepository(),warehouse,dailyWh
							,arb.getPreClose(),arb.getOpen()
							,arb.getHigh(),arb.getLow(),
								fullAmount,arb.getPreSettlePrice()
								,arb.getSettlePrice(),swing
								,arb.getRatio(),"xxx","yyy"
								,arb.getOutvol(),arb.getInvol()}
			};
			return futuresInfo;
		}else if(id==3){
			Object[][] futuresInfo={				
					new Object[]{3,"TF1503","国债1503"
							,arb.getRepository(),warehouse,dailyWh
							,arb.getPreClose(),arb.getOpen()
							,arb.getHigh(),arb.getLow(),
								fullAmount,arb.getPreSettlePrice()
								,arb.getSettlePrice(),swing
								,arb.getRatio(),"!!!","~~~~"
								,arb.getOutvol(),arb.getInvol()}
			};
			return futuresInfo;
		}
		return null;
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
		
		String warehouse="+0";
		if(arb1.getWarehouse()>=0){
			warehouse="+"+arb1.getWarehouse();
		}else{
			warehouse=String.valueOf(arb1.getWarehouse());
		}
		String dailyWh="+0";
		if(arb1.getDailyWarehouse()>=0){
			dailyWh="+"+arb1.getDailyWarehouse();
		}else{
			dailyWh=String.valueOf(arb1.getDailyWarehouse());
		}
		String swing=arb1.getSwing()+"%";
		String fullAmount=arb1.getFullAmount()+"亿";
		
		Object[][] futuresInfo={				
				new Object[]{1,"TF1409","国债1409"
						,arb1.getRepository(),warehouse,dailyWh
						,arb1.getPreClose(),arb1.getOpen()
						,arb1.getHigh(),arb1.getLow(),
							fullAmount,arb1.getPreSettlePrice()
							,arb1.getSettlePrice(),swing
							,arb1.getRatio(),"64.97亿","93.35万"
							,arb1.getOutvol(),arb1.getInvol()}
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
		
		String warehouse="+0";
		if(arb2.getWarehouse()>=0){
			warehouse="+"+arb2.getWarehouse();
		}else{
			warehouse=String.valueOf(arb2.getWarehouse());
		}
		String dailyWh="+0";
		if(arb2.getDailyWarehouse()>=0){
			dailyWh="+"+arb2.getDailyWarehouse();
		}else{
			dailyWh=String.valueOf(arb2.getDailyWarehouse());
		}
		String swing=arb2.getSwing()+"%";
		String fullAmount=arb2.getFullAmount()+"亿";
		
		Object[][] futuresInfo={				
				new Object[]{2,"TF1412","国债1412"
						,arb2.getRepository(),warehouse,dailyWh
						,arb2.getPreClose(),arb2.getOpen()
						,arb2.getHigh(),arb2.getLow(),
							fullAmount,arb2.getPreSettlePrice()
							,arb2.getSettlePrice(),swing
							,arb2.getRatio(),"654.97亿","93.35万"
							,arb2.getOutvol(),arb2.getInvol()}
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
		
		String warehouse="+0";
		if(arb3.getWarehouse()>=0){
			warehouse="+"+arb3.getWarehouse();
		}else{
			warehouse=String.valueOf(arb3.getWarehouse());
		}
		String dailyWh="+0";
		if(arb3.getDailyWarehouse()>=0){
			dailyWh="+"+arb3.getDailyWarehouse();
		}else{
			dailyWh=String.valueOf(arb3.getDailyWarehouse());
		}
		String swing=arb3.getSwing()+"%";
		String fullAmount=arb3.getFullAmount()+"亿";
		
		Object[][] futuresInfo={				
				new Object[]{3,"TF1503","国债1503"
						,arb3.getRepository(),warehouse,dailyWh
						,arb3.getPreClose(),arb3.getOpen()
						,arb3.getHigh(),arb3.getLow(),
							fullAmount,arb3.getPreSettlePrice()
							,arb3.getSettlePrice(),swing
							,arb3.getRatio(),"654.97亿","93.35万"
							,arb3.getOutvol(),arb3.getInvol()}
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
	
	private Arb_detail getTableData(String id){
		DataInterfacePile database=new DataInterfacePile();
		Arb_detail result=database.getArbDetail(id);
		return result;
	}
	
	public static void main(String[] args){
		JFrame frame=new JFrame();
		FuturesMarket fm=new FuturesMarket();
		JPanel test=fm.getPanel();
		frame.setSize(1200,700);
		frame.setLocationRelativeTo(null);
		frame.add(test);
		frame.setVisible(true);	
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Arb_detail> arb_list=new ArrayList<Arb_detail>();
		Arb_detail arb11;
		arb11=new Arb_detail();
		

		arb11.setName("国债TF9999");
		arb11.setRepository(6979);
		arb11.setWarehouse(0);
		arb11.setDailyWarehouse(134);
		arb11.setPreClose(92.984);
		arb11.setOpen(92.456);
		arb11.setHigh(92.984);
		arb11.setLow(92.456);
		arb11.setFullAmount(16.48);
		arb11.setPreSettlePrice(92.984);
		arb11.setSettlePrice(92.456);
		arb11.setSwing(0.33);
		arb11.setRatio(1.04);
		arb11.setOutvol(527);
		arb11.setInvol(1274);
		
		arb11.setCommitteeThan(3.70);
		arb11.setAskPrice(93.245);
		arb11.setBidPirce(98.154);
		arb11.setPresentPrice(93.370);
		arb11.setPriceChange(0.336);
		arb11.setChange(0.36);
		arb11.setNvol(1);
		arb11.setVol(1770);
		arb11.setPreRepository(6845);
		arb11.setHardenPrice(94.769);
		arb11.setAverPrice(93.125);
		arb11.setLimitPrice(91.080);
		arb_list.add(arb11);
		arb_list.add(arb11);
		arb_list.add(arb11);
		
		fm.update(arb_list);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		fm.update(arb_list);
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

			detailPanel.setDetail(arb1);
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

			detailPanel.setDetail(arb2);
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
			
			detailPanel.setDetail(arb3);
			
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
			
			title.setBounds(190,10,200,30);
			title.setText("国债TF1409");
			this.add(title);
			title.setVisible(false);
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
		
		public void setDetail(Arb_detail arb){
			title.setText(arb.getName());
			title.setVisible(true);
			String weibi;
			if(arb.getCommitteeThan()>=0){
				weibi="+"+arb.getCommitteeThan()+"%";
			}else{
				weibi=arb.getCommitteeThan()+"%";
			}
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
			String warehouse="+0";
			if(arb.getWarehouse()>=0){
				warehouse="+"+arb.getWarehouse();
			}else{
				warehouse=String.valueOf(arb.getWarehouse());
			}
			String dailyWh="+0";
			if(arb.getDailyWarehouse()>=0){
				dailyWh="+"+arb.getDailyWarehouse();
			}else{
				dailyWh=String.valueOf(arb.getDailyWarehouse());
			}
			detail[0].setText(weibi);
			detail[1].setText(String.valueOf(arb.getAskPrice()));
			detail[2].setText(String.valueOf(arb.getBidPirce()));
			detail[3].setText(String.valueOf(arb.getVol()));
			detail[4].setText(zhangdie);
			detail[5].setText(zhangfu);
			detail[6].setText(String.valueOf(arb.getSwing())+"%");
			detail[7].setText(String.valueOf(arb.getNvol()));
			detail[8].setText(String.valueOf(arb.getVol()));
			detail[9].setText(String.valueOf(arb.getRepository()));
			detail[10].setText(warehouse);
			detail[11].setText("换手");
			detail[12].setText(String.valueOf(arb.getPreRepository()));
			detail[13].setText(String.valueOf(arb.getHardenPrice()));
			detail[14].setText(String.valueOf(arb.getOutvol()));
			detail[15].setText(String.valueOf(arb.getOpen()));
			detail[16].setText(String.valueOf(arb.getPreClose()));
			detail[17].setText(String.valueOf(arb.getHigh()));
			detail[18].setText(String.valueOf(arb.getLow()));
			detail[19].setText(String.valueOf(arb.getFullAmount())+"亿");
			detail[20].setText(String.valueOf(arb.getAverPrice()));
			detail[21].setText(String.valueOf(arb.getSettlePrice()));
			detail[22].setText(String.valueOf(arb.getPreSettlePrice()));
			detail[23].setText(dailyWh);
			detail[24].setText(String.valueOf(arb.getRatio()));
			detail[25].setText(String.valueOf(arb.getLimitPrice()));
			detail[26].setText(String.valueOf(arb.getInvol()));
			
		}
	}
}
