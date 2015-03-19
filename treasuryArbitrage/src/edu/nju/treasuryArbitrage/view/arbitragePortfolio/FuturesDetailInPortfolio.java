package edu.nju.treasuryArbitrage.view.arbitragePortfolio;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.nju.treasuryArbitrage.model.ArbDetail;

public class FuturesDetailInPortfolio extends JPanel {
	private static final long serialVersionUID = -1068475248299847947L;
	private static final Font NORMAL_FONT = new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18);
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 20;
	private static final int LEFT_MARGIN = 3;

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

	public FuturesDetailInPortfolio(int width, int height) {
		this.width = width;
		this.height = height;

		this.setLayout(null);
		this.setBackground(Color.BLACK);

		initHeader();
		initSell();
		initBuy();
		initDetail1();
	}

	public void update(ArbDetail arb) {
		title.setText(arb.getSymbol());
		currentPriceData.setText(String.valueOf(arb.getPresentPrice()));

		double priceChange = arb.getPriceChange();
		String priceChangePre = priceChange >= 0 ? "+" : "";
		priceChangeData.setText(priceChangePre + String.valueOf(priceChange));
		priceChangeData.setForeground(priceChange >= 0 ? Color.RED
				: Color.GREEN);
		double change = arb.getChange();
		String changePre = change >= 0 ? "+" : "";
		changeData.setText(changePre + String.valueOf(change) + "%");
		changeData.setForeground(change >= 0 ? Color.RED : Color.GREEN);

		askPriceData.setText(String.valueOf(arb.getAskPrice()));
		askData.setText(String.valueOf(arb.getAsk()));

		bidPriceData.setText(String.valueOf(arb.getBidPirce()));
		bidData.setText(String.valueOf(arb.getBid()));
	}

	private void initHeader() {
		title.setBounds(LEFT_MARGIN, 0, width, 30);
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 22));
		this.add(title);

		currentPriceData.setBounds(LEFT_MARGIN, 30, width, 45);
		currentPriceData.setHorizontalAlignment(SwingConstants.LEFT);
		currentPriceData.setForeground(Color.RED);
		currentPriceData.setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 40));
		currentPriceData.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				new Color(193, 193, 193)));
		this.add(currentPriceData);
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
			sellLabels[i].setBounds(LEFT_MARGIN, 78 + (LABEL_HEIGHT + 3) * i,
					width, LABEL_HEIGHT);
			sellLabels[i].setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18));
			sellLabels[i].setForeground(Color.WHITE);
			this.add(sellLabels[i]);

			if (i > 3) {
				continue;
			}
			nullLabel1s[i] = new JLabel("-");
			nullLabel1s[i].setForeground(Color.WHITE);
			nullLabel1s[i].setBounds(width / 3, 78 + (LABEL_HEIGHT + 3) * i,
					LABEL_WIDTH, LABEL_HEIGHT);
			nullLabel1s[i].setHorizontalAlignment(SwingConstants.RIGHT);
			this.add(nullLabel1s[i]);

			nullLabel2s[i] = new JLabel("-");
			nullLabel2s[i].setForeground(Color.YELLOW);
			nullLabel2s[i].setBounds(width - LABEL_WIDTH / 2, 78
					+ (LABEL_HEIGHT + 3) * i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(nullLabel2s[i]);
		}

//		sellLabels[4].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,new Color(193, 193, 193)));

		askPriceData.setFont(NORMAL_FONT);
		askPriceData.setForeground(Color.RED);
		askPriceData.setHorizontalAlignment(SwingConstants.RIGHT);
		askPriceData.setBounds(width / 3, 78 + (LABEL_HEIGHT + 3) * 4,
				LABEL_WIDTH, LABEL_HEIGHT);
		this.add(askPriceData);

		askData.setFont(NORMAL_FONT);
		askData.setForeground(Color.YELLOW);
		askData.setBounds(width - LABEL_WIDTH / 2, 78 + (LABEL_HEIGHT + 3) * 4,
				LABEL_WIDTH, LABEL_HEIGHT);
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
			buyLabels[i].setBounds(LEFT_MARGIN, 193 + (LABEL_HEIGHT + 3) * i,
					width, LABEL_HEIGHT);
			buyLabels[i].setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18));
			buyLabels[i].setForeground(Color.WHITE);
			this.add(buyLabels[i]);

			if (i == 0) {
				continue;
			}
			i--;
			nullLabel1s[i] = new JLabel("-");
			nullLabel1s[i].setForeground(Color.WHITE);
			nullLabel1s[i].setBounds(width / 3, 216 + (LABEL_HEIGHT + 3) * i,
					LABEL_WIDTH, LABEL_HEIGHT);
			nullLabel1s[i].setHorizontalAlignment(SwingConstants.RIGHT);
			this.add(nullLabel1s[i]);

			nullLabel2s[i] = new JLabel("-");
			nullLabel2s[i].setForeground(Color.YELLOW);
			nullLabel2s[i].setBounds(width - LABEL_WIDTH / 2, 216
					+ (LABEL_HEIGHT + 3) * i, LABEL_WIDTH, LABEL_HEIGHT);
			this.add(nullLabel2s[i]);
			i++;
		}

//		buyLabels[4].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(193, 193, 193)));

		bidPriceData.setFont(NORMAL_FONT);
		bidPriceData.setForeground(Color.RED);
		bidPriceData.setHorizontalAlignment(SwingConstants.RIGHT);
		bidPriceData.setBounds(width / 3, 193, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(bidPriceData);

		bidData.setFont(NORMAL_FONT);
		bidData.setForeground(Color.YELLOW);
		bidData.setBounds(width - LABEL_WIDTH / 2, 193, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(bidData);
	}

	private void initDetail1() {
		JLabel[] detail1 = new JLabel[2];
		detail1[0] = new JLabel("锟角碉拷");
		detail1[1] = new JLabel("锟角碉拷锟斤拷");

		for (int i = 0; i < 1; i++) {
			detail1[i].setFont(NORMAL_FONT);
			detail1[i].setBounds(LEFT_MARGIN, 311 + (LABEL_HEIGHT + 3) * i,
					width, LABEL_HEIGHT);
			detail1[i].setForeground(Color.WHITE);
			this.add(detail1[i]);

			detail1[i + 1].setFont(NORMAL_FONT);
			detail1[i + 1].setBounds(width / 2 + 3, 311 + (LABEL_HEIGHT + 3)
					* i, width, LABEL_HEIGHT);
			detail1[i + 1].setForeground(Color.WHITE);
			this.add(detail1[i + 1]);
		}

		priceChangeData = new JLabel();
		priceChangeData.setFont(NORMAL_FONT);
		priceChangeData.setHorizontalAlignment(SwingConstants.RIGHT);
		priceChangeData.setBounds(width / 2 - LABEL_WIDTH - 3, 311, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(priceChangeData);

		changeData = new JLabel();
		changeData.setFont(NORMAL_FONT);
		changeData.setHorizontalAlignment(SwingConstants.RIGHT);
		changeData.setBounds(width - LABEL_WIDTH - 7, 311, LABEL_WIDTH, LABEL_HEIGHT);
		this.add(changeData);
	}

}
