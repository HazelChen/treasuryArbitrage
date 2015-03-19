package edu.nju.treasuryArbitrage.view.arbitragePortfolio;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.nju.treasuryArbitrage.model.ArbDetail;

public class PortfolioDetailStyle1 extends PortfolioDetail {
	private static final long serialVersionUID = -4365806896770758444L;

	private static final Font NORMAL_FONT = new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18);
	private static final int LABEL_WIDTH = 60;
	private static final int LABEL_HEIGHT = 20;
	private static final int LABEL_GAP = 5;
	private static final int LEFT_MARGIN = 3;

	private JLabel[] title = new JLabel[2];
	private JLabel[] currentPriceData = new JLabel[2];
	private JLabel[] priceChangeData = new JLabel[2];
	private JLabel[] changeData = new JLabel[2];

	private int width;
	private int height;

	public PortfolioDetailStyle1(int width, int height) {
		this.width = width;
		this.height = height;

		this.setLayout(null);
		this.setBackground(ArbitragePortfolio.BACKGROUND_COLOR);

		init();
	}

	public void update(ArbDetail[] arbs) {
		for (int i = 0; i < arbs.length; i++) {
			ArbDetail arb = arbs[i];
			title[i].setText(arb.getSymbol());
			currentPriceData[i].setText(String.valueOf(arb.getPresentPrice()));
			double priceChange = arb.getPriceChange();
			String priceChangePre = priceChange >= 0 ? "+" : "";
			priceChangeData[i].setText(priceChangePre
					+ String.valueOf(priceChange));
			priceChangeData[i].setForeground(priceChange >= 0 ? Color.RED
					: Color.GREEN);
			double change = arb.getChange();
			String changePre = change >= 0 ? "+" : "";
			changeData[i].setText(changePre + String.valueOf(change) + "%");
			changeData[i].setForeground(change >= 0 ? Color.RED : Color.GREEN);
		}
	}

	private void init() {
		for (int i = 0; i < 2; i++) {
			title[i] = new JLabel();
			title[i].setBounds(LEFT_MARGIN, 0 + (100) * i, width, 30);
			title[i].setHorizontalAlignment(SwingConstants.LEFT);
			title[i].setForeground(Color.YELLOW);
			title[i].setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 22));
			this.add(title[i]);

			initLeft(i);
			initRight(i);
		}
	}

	private void initLeft(int index) {
		JLabel[] labels = new JLabel[2];
		labels[0] = new JLabel("锟斤拷锟�");
		labels[1] = new JLabel("锟角碉拷锟斤拷");

		for (int i = 0; i < labels.length; i++) {
			labels[i].setBounds(LEFT_MARGIN, 33 + (LABEL_HEIGHT + 3) * i + 100 * index,
					width, LABEL_HEIGHT);
			labels[i].setFont(NORMAL_FONT);
			labels[i].setForeground(Color.WHITE);
			this.add(labels[i]);
		}

		JLabel indexLabel = new JLabel(String.valueOf(index + 1));
		indexLabel.setForeground(Color.WHITE);
		indexLabel.setBounds(width / 2 - LABEL_WIDTH - 3 , 33 + 100 * index, LABEL_WIDTH, LABEL_HEIGHT);
		indexLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		indexLabel.setFont(NORMAL_FONT);
		this.add(indexLabel);

		changeData[index] = new JLabel();
		changeData[index].setBounds(width / 2 - LABEL_WIDTH - 3, 36 + LABEL_HEIGHT + 100 * index,
				LABEL_WIDTH, LABEL_HEIGHT);
		changeData[index].setHorizontalAlignment(SwingConstants.RIGHT);
		changeData[index].setFont(NORMAL_FONT);
		this.add(changeData[index]);
	}
	
	private void initRight(int index) {
		JLabel[] labels = new JLabel[2];
		labels[0] = new JLabel("锟街硷拷");
		labels[1] = new JLabel("锟角碉拷");

		for (int i = 0; i < labels.length; i++) {
			labels[i].setBounds(width / 2 + 3, 33 + (LABEL_HEIGHT + 3) * i + 100 * index,
					width, LABEL_HEIGHT);
			labels[i].setFont(new Font("微锟斤拷锟脚猴拷", Font.PLAIN, 18));
			labels[i].setForeground(Color.WHITE);
			this.add(labels[i]);
		}

		currentPriceData[index] = new JLabel();
		currentPriceData[index].setBounds(width - LABEL_WIDTH, 33 + 100 * index, LABEL_WIDTH, LABEL_HEIGHT);
		currentPriceData[index].setHorizontalAlignment(SwingConstants.RIGHT);
		currentPriceData[index].setFont(NORMAL_FONT);
		this.add(currentPriceData[index]);

		priceChangeData[index] = new JLabel();
		priceChangeData[index].setBounds(width - LABEL_WIDTH, 33 + (LABEL_HEIGHT + 3) * 1 + 100 * index,
				LABEL_WIDTH, LABEL_HEIGHT);
		priceChangeData[index].setHorizontalAlignment(SwingConstants.RIGHT);
		priceChangeData[index].setFont(NORMAL_FONT);
		this.add(priceChangeData[index]);
	}
}
