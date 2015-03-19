package edu.nju.treasuryArbitrage.view.futuresMarket;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.nju.treasuryArbitrage.model.ArbDetail;

public class FuturesDetailPanel extends JPanel{
	private static final long serialVersionUID = -4185005520541760560L;
	private static final Font NORMAL_FONT = new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18);
	private static final int LABEL_WIDTH = 60;
	private static final int LABEL_HEIGHT = 20;
	private static final int LABEL_GAP = 5;
	private static final int LEFT_MARGIN = 3;

	private int index;
	
	public JLabel[] detail1Datas = new JLabel[10];
	public JLabel[] detail2Datas = new JLabel[4];
	
	private JLabel title = new JLabel();
	private JLabel currentPriceData = new JLabel();
	private JLabel priceChangeData = new JLabel();
	private JLabel changeData = new JLabel();
	private JLabel askPriceData = new JLabel();
	private JLabel askData = new JLabel();
	private JLabel bidPriceData = new JLabel();
	private JLabel bidData = new JLabel();
	
	private int width;
	private int height;

	public FuturesDetailPanel(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		
		initHeader();
		initSell();
		initBuy();
		initDetail1();
		initDetail2();
	}

	
	public void setDetail(int index) {
		this.index = index;
	}
	
	public void update(List<ArbDetail> details) {
		if (index > details.size() - 1) {
			update(ArbDetail.nullObject());
		} else {
			update(details.get(index).getFormattedArb_detail());
		}
	}
	
	public void update(ArbDetail[] details) {
		if (index > details.length) {
			update(ArbDetail.nullObject());
		} else {
			update(details[index].getFormattedArb_detail());
		}
	}

	public void update(ArbDetail arb) {
		title.setText(arb.getSymbol());
		currentPriceData.setText(String.valueOf(arb.getPresentPrice()));
		
		double priceChange = arb.getPriceChange();
		String priceChangePre = priceChange >= 0 ? "+" : "";
		priceChangeData.setText(priceChangePre + String.valueOf(priceChange));
		priceChangeData.setForeground(priceChange >= 0 ? Color.RED : Color.GREEN);
		double change = arb.getChange();
		String changePre = change >= 0 ? "+" : "";
		changeData.setText(changePre + String.valueOf(change) + "%");
		changeData.setForeground(change >= 0 ? Color.RED : Color.GREEN);
		currentPriceData.setForeground(change >= 0 ? Color.RED : Color.GREEN);
		
		askPriceData.setText(String.valueOf(arb.getAskPrice()));
		askData.setText(String.valueOf(arb.getAsk()));
		
		bidPriceData.setText(String.valueOf(arb.getBidPirce()));
		bidData.setText(String.valueOf(arb.getBid()));
		
		detail1Datas[0].setText(String.valueOf(arb.getVol()));
		detail1Datas[1].setText(String.valueOf(arb.getPreSettlePrice()));
		detail1Datas[2].setText(String.valueOf(arb.getHigh()));
		detail1Datas[3].setText(String.valueOf(arb.getAverPrice()));
		detail1Datas[4].setText(String.valueOf(arb.getHardenPrice()));
		detail1Datas[5].setText(String.valueOf(arb.getPreClose()));
		detail1Datas[6].setText(String.valueOf(arb.getOpen()));
		detail1Datas[6].setForeground(arb.getOpen() >= arb.getPreSettlePrice() ? Color.RED : Color.GREEN);
		detail1Datas[7].setText(String.valueOf(arb.getLow()));
		detail1Datas[8].setText(String.valueOf(arb.getSwing()) + "%");
		detail1Datas[8].setToolTipText(String.valueOf(arb.getSwing()) + "%");
		detail1Datas[9].setText(String.valueOf(arb.getLimitPrice()));
		
		detail2Datas[0].setText(String.valueOf(arb.getRepository()));
		detail2Datas[1].setText(String.valueOf(arb.getFullAmount()));
		detail2Datas[1].setToolTipText(String.valueOf(arb.getFullAmount()));
		
		String pre;
		Color color;
		if (arb.getDailyWarehouse() == 0) {
			pre = "";
			color = Color.WHITE;
		} else if (arb.getDailyWarehouse() > 0) {
			pre = "+";
			color = Color.RED;
		} else {
			pre = "";
			color = Color.GREEN;
		}
		detail2Datas[2].setText(pre + String.valueOf(arb.getDailyWarehouse()));
		detail2Datas[2].setForeground(color);
		
		detail2Datas[3].setText(String.valueOf(arb.getAverPrice()));
	}
	
	private void initHeader() {
		title.setBounds(LEFT_MARGIN, 0, width, 30);
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 22));
		this.add(title);
		
		currentPriceData.setBounds(LEFT_MARGIN, 30, width, 45);
		currentPriceData.setHorizontalAlignment(SwingConstants.LEFT);
		currentPriceData.setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 40));
		currentPriceData.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(193,193,193)));
		this.add(currentPriceData);

		priceChangeData.setBounds(width - 105, 30, 100, LABEL_HEIGHT);
		priceChangeData.setHorizontalAlignment(SwingConstants.RIGHT);
		priceChangeData.setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18));
		this.add(priceChangeData);
		changeData.setBounds(width - 105, 30 + LABEL_HEIGHT + LABEL_GAP, 100, LABEL_HEIGHT);
		changeData.setHorizontalAlignment(SwingConstants.RIGHT);
		changeData.setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18));
		this.add(changeData);
	}
	
	private void initBuy() {
		JLabel[] sellLabels = new JLabel[5];
		sellLabels[0] = new JLabel("锟斤拷锟斤拷"); 
		sellLabels[1] = new JLabel("锟斤拷锟斤拷"); 
		sellLabels[2] = new JLabel("锟斤拷锟斤拷"); 
		sellLabels[3] = new JLabel("锟斤拷锟斤拷"); 
		sellLabels[4] = new JLabel("锟斤拷一"); 
		
		JLabel[] nullLabel1s = new JLabel[4];
		JLabel[] nullLabel2s = new JLabel[4];

		for (int i = 0; i < sellLabels.length; i++) {
			sellLabels[i].setBounds(LEFT_MARGIN, 78 + (LABEL_HEIGHT + 3)* i, width, LABEL_HEIGHT);
			sellLabels[i].setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18));
			sellLabels[i].setForeground(Color.WHITE);
			this.add(sellLabels[i]);
			
			if (i > 3) {
				continue;
			}
			nullLabel1s[i] = new JLabel("-");
			nullLabel1s[i].setForeground(Color.WHITE);
			nullLabel1s[i].setBounds(width / 3, 78 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			nullLabel1s[i].setHorizontalAlignment(SwingConstants.RIGHT);
			this.add(nullLabel1s[i]);
			
			nullLabel2s[i] = new JLabel("-");
			nullLabel2s[i].setForeground(Color.YELLOW);
			nullLabel2s[i].setBounds(width - LABEL_WIDTH, 78 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(nullLabel2s[i]);
		}
		
		sellLabels[4].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(193,193,193)));
		
		askPriceData.setFont(NORMAL_FONT);
		askPriceData.setForeground(Color.RED);
		askPriceData.setHorizontalAlignment(SwingConstants.RIGHT);
		askPriceData.setBounds(width / 3, 78 + (LABEL_HEIGHT + 3) * 4, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(askPriceData);
		
		askData.setFont(NORMAL_FONT);
		askData.setForeground(Color.YELLOW);
		askData.setBounds(width - LABEL_WIDTH, 78 + (LABEL_HEIGHT + 3) * 4, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(askData);
	}
	
	private void initSell() {
		JLabel[] buyLabels = new JLabel[5];
		buyLabels[0] = new JLabel("锟斤拷一"); 
		buyLabels[1] = new JLabel("锟斤拷锟�"); 
		buyLabels[2] = new JLabel("锟斤拷锟斤拷"); 
		buyLabels[3] = new JLabel("锟斤拷锟斤拷"); 
		buyLabels[4] = new JLabel("锟斤拷锟斤拷"); 
		
		JLabel[] nullLabel1s = new JLabel[4];
		JLabel[] nullLabel2s = new JLabel[4];
		
		for (int i = 0; i < buyLabels.length; i++) {
			buyLabels[i].setBounds(LEFT_MARGIN, 193 + (LABEL_HEIGHT + 3)* i, width, LABEL_HEIGHT);
			buyLabels[i].setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18));
			buyLabels[i].setForeground(Color.WHITE);
			this.add(buyLabels[i]);
			
			if (i == 0) {
				continue;
			}
			i--;
			nullLabel1s[i] = new JLabel("-");
			nullLabel1s[i].setForeground(Color.WHITE);
			nullLabel1s[i].setBounds(width / 3, 216 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			nullLabel1s[i].setHorizontalAlignment(SwingConstants.RIGHT);
			this.add(nullLabel1s[i]);
			
			nullLabel2s[i] = new JLabel("-");
			nullLabel2s[i].setForeground(Color.YELLOW);
			nullLabel2s[i].setBounds(width - LABEL_WIDTH, 216 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(nullLabel2s[i]);
			i++;
		}
		
		buyLabels[4].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(193,193,193)));
		
		bidPriceData.setFont(NORMAL_FONT);
		bidPriceData.setForeground(Color.RED);
		bidPriceData.setHorizontalAlignment(SwingConstants.RIGHT);
		bidPriceData.setBounds(width / 3, 193, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(bidPriceData);
		
		bidData.setFont(NORMAL_FONT);
		bidData.setForeground(Color.YELLOW);
		bidData.setBounds(width - LABEL_WIDTH, 193, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(bidData);
	}
	
	private void initDetail1() {
		JLabel[] detail1 = new JLabel[10];
		detail1[0] = new JLabel("锟斤拷锟斤拷");
		detail1[1] = new JLabel("锟斤拷锟�");
		detail1[2] = new JLabel("锟斤拷锟�");
		detail1[3] = new JLabel("锟斤拷锟斤拷");
		detail1[4] = new JLabel("锟斤拷停");
		
		detail1[5] = new JLabel("锟斤拷锟斤拷");
		detail1[6] = new JLabel("锟斤拷锟斤拷");
		detail1[7] = new JLabel("锟斤拷锟�");
		detail1[8] = new JLabel("锟斤拷锟�");
		detail1[9] = new JLabel("锟斤拷停");
		
		for (int i = 0; i < 5; i++) {
			detail1[i].setFont(NORMAL_FONT);
			detail1[i].setBounds(LEFT_MARGIN, 311 + (LABEL_HEIGHT + 3)* i, width, LABEL_HEIGHT);
			detail1[i].setForeground(Color.WHITE);
			this.add(detail1[i]);
			
			detail1Datas[i] = new JLabel();
			detail1Datas[i].setFont(NORMAL_FONT);
			detail1Datas[i].setHorizontalAlignment(SwingConstants.RIGHT);
			detail1Datas[i].setBounds(width / 2 - LABEL_WIDTH - 3, 311 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(detail1Datas[i]);
			
			detail1[i + 5].setFont(NORMAL_FONT);
			detail1[i + 5].setBounds(width / 2 + 3, 311 + (LABEL_HEIGHT + 3)* i, width, LABEL_HEIGHT);
			detail1[i + 5].setForeground(Color.WHITE);
			this.add(detail1[i + 5]);
			
			detail1Datas[i + 5] = new JLabel();
			detail1Datas[i + 5].setFont(NORMAL_FONT);
			detail1Datas[i + 5].setHorizontalAlignment(SwingConstants.RIGHT);
			detail1Datas[i + 5].setBounds(width - LABEL_WIDTH - 7, 311 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(detail1Datas[i + 5]);
		}
		detail1[4].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(193,193,193)));
		detail1Datas[0].setForeground(Color.YELLOW);
		detail1Datas[1].setForeground(Color.WHITE);
		detail1Datas[2].setForeground(Color.RED);
		detail1Datas[3].setForeground(Color.RED);
		detail1Datas[4].setForeground(Color.RED);
		detail1Datas[5].setForeground(Color.WHITE);
		detail1Datas[7].setForeground(Color.GREEN);
		detail1Datas[8].setForeground(Color.WHITE);
		detail1Datas[9].setForeground(Color.GREEN);
	}
	
	private void initDetail2() {
		JLabel[] detail2 = new JLabel[4];
		detail2[0] = new JLabel("锟街诧拷");
		detail2[1] = new JLabel("锟斤拷锟�");
		detail2[2] = new JLabel("锟斤拷锟斤拷");
		detail2[3] = new JLabel("锟斤拷锟斤拷");
		
		for (int i = 0; i < 2; i++) {
			detail2[i].setFont(NORMAL_FONT);
			detail2[i].setBounds(LEFT_MARGIN, 426 + (LABEL_HEIGHT + 3)* i, width, LABEL_HEIGHT);
			detail2[i].setForeground(Color.WHITE);
			this.add(detail2[i]);
			
			detail2Datas[i] = new JLabel();
			detail2Datas[i].setFont(NORMAL_FONT);
			detail2Datas[i].setHorizontalAlignment(SwingConstants.RIGHT);
			detail2Datas[i].setBounds(width / 2 - LABEL_WIDTH - 3, 426 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(detail2Datas[i]);
			
			detail2[i + 2].setFont(NORMAL_FONT);
			detail2[i + 2].setBounds(width / 2 + 3, 426 + (LABEL_HEIGHT + 3)* i, width, LABEL_HEIGHT);
			detail2[i + 2].setForeground(Color.WHITE);
			this.add(detail2[i + 2]);
			
			detail2Datas[i + 2] = new JLabel();
			detail2Datas[i + 2].setFont(NORMAL_FONT);
			detail2Datas[i + 2].setHorizontalAlignment(SwingConstants.RIGHT);
			detail2Datas[i + 2].setBounds(width - LABEL_WIDTH - 7, 426 + (LABEL_HEIGHT + 3)* i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(detail2Datas[i + 2]);
		}
		detail2Datas[0].setForeground(Color.YELLOW);
		detail2Datas[1].setForeground(Color.WHITE);
		detail2Datas[2].setForeground(Color.RED);
		detail2Datas[3].setForeground(Color.WHITE);
	}

}
