package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.nju.treasuryArbitrage.model.Arb_detail;

public class PortfolioDetailStyle2 extends PortfolioDetail{
	private static final long serialVersionUID = 6883083029440568354L;
	
	private static final Font NORMAL_FONT = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18);
	private static final int LABEL_WIDTH = 100;
	private static final int LABEL_HEIGHT = 20;
	private static final int ARB_SHOW_HEIGHT = 180;
	private static final int LEFT_MARGIN = 3;

	private JLabel[] title = new JLabel[2];
	private JLabel[] currentPriceData = new JLabel[2];
	private JLabel[] priceChangeData = new JLabel[2];
	private JLabel[] changeData = new JLabel[2];

	private int width;
	private int height;

	public PortfolioDetailStyle2(int width, int height) {
		this.width = width;
		this.height = height;

		this.setLayout(null);
		this.setBackground(ArbitragePortfolioChen.BACKGROUND_COLOR);

		init();
	}

	@Override
	public void update(Arb_detail[] arbs) {
		for (int i = 0; i < arbs.length; i++) {
			Arb_detail arb = arbs[i];
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
			initInternal(i);
		}
	}

	private void initInternal(int index) {
		title[index] = new JLabel();
		title[index].setBounds(LEFT_MARGIN, ARB_SHOW_HEIGHT * index, width, 30);
		title[index].setHorizontalAlignment(SwingConstants.LEFT);
		title[index].setForeground(Color.YELLOW);
		title[index].setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		this.add(title[index]);
		
		JLabel[] labels = new JLabel[3];
		labels[0] = new JLabel("ÐòºÅ");
		labels[1] = new JLabel("ÕÇµø·ù");
		labels[2] = new JLabel("ÕÇµø");
		
		for (int i = 0; i < labels.length; i++) {
			labels[i].setBounds(LEFT_MARGIN, 78 + (LABEL_HEIGHT + 3) * i + ARB_SHOW_HEIGHT * index,
					width, LABEL_HEIGHT);
			labels[i].setFont(NORMAL_FONT);
			labels[i].setForeground(Color.WHITE);
			this.add(labels[i]);
		}
		
		currentPriceData[index] = new JLabel();
		currentPriceData[index].setBounds(LEFT_MARGIN, 30 + ARB_SHOW_HEIGHT * index, width, 45);
		currentPriceData[index].setHorizontalAlignment(SwingConstants.LEFT);
		currentPriceData[index].setForeground(Color.RED);
		currentPriceData[index].setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 40));
		currentPriceData[index].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(193,193,193)));
		this.add(currentPriceData[index]);

		JLabel indexLabel = new JLabel(String.valueOf(index + 1));
		indexLabel.setForeground(Color.WHITE);
		indexLabel.setBounds(width - LABEL_WIDTH - 10, 78 + ARB_SHOW_HEIGHT * index, LABEL_WIDTH, LABEL_HEIGHT);
		indexLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		indexLabel.setFont(NORMAL_FONT);
		this.add(indexLabel);

		priceChangeData[index] = new JLabel();
		priceChangeData[index].setBounds(width - LABEL_WIDTH - 10, 81 + LABEL_HEIGHT + ARB_SHOW_HEIGHT * index,
				LABEL_WIDTH, LABEL_HEIGHT);
		priceChangeData[index].setHorizontalAlignment(SwingConstants.RIGHT);
		priceChangeData[index].setFont(NORMAL_FONT);
		this.add(priceChangeData[index]);
		
		changeData[index] = new JLabel();
		changeData[index].setBounds(width - LABEL_WIDTH - 10, 78 + (LABEL_HEIGHT + 3) * 2 + ARB_SHOW_HEIGHT * index,
				LABEL_WIDTH, LABEL_HEIGHT);
		changeData[index].setHorizontalAlignment(SwingConstants.RIGHT);
		changeData[index].setFont(NORMAL_FONT);
		this.add(changeData[index]);
	}
}
