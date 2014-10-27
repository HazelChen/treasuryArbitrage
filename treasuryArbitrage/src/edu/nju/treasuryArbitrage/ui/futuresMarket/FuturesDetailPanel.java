package edu.nju.treasuryArbitrage.ui.futuresMarket;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.nju.treasuryArbitrage.model.Arb_detail;

public class FuturesDetailPanel extends JPanel{
	private static final long serialVersionUID = -4185005520541760560L;
	private static final Font NORMAL_FONT = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18);
	private static final int LABEL_WIDTH = 60;
	private static final int LABEL_HEIGHT = 20;
	private static final int LABEL_GAP = 3;
	private static final int LEFT_MARGIN = 3;

	private int index;
	
	private JLabel[] data = new JLabel[24];
	public JLabel[] detail = new JLabel[24];
	
	private JLabel title = new JLabel();
	private JLabel currentPriceData = new JLabel();
	private JLabel priceChangeData = new JLabel();
	private JLabel changeData = new JLabel();
	private JLabel askPriceData = new JLabel();
	private JLabel askData = new JLabel();
	
	private int width;
	private int height;

	public FuturesDetailPanel(int width, int height) {
		this.width = width;
		this.height = height;
		
		initHeader();
		initSell();
		
		data[0] = new JLabel("Âô¼Û");
		data[1] = new JLabel("Âò¼Û");
		data[2] = new JLabel("³É½»");
		data[3] = new JLabel("ÕÇµø");
		data[4] = new JLabel("ÕÇ·ù");
		data[5] = new JLabel("Õñ·ù");
		data[6] = new JLabel("ÏÖÊÖ");
		data[7] = new JLabel("×ÜÊÖ");
		data[8] = new JLabel("³Ö²Ö");
		data[9] = new JLabel("×ò³Ö²Ö");
		data[10] = new JLabel("ÕÇÍ£");
		data[11] = new JLabel("ÍâÅÌ");
		data[12] = new JLabel("¿ªÅÌ");
		data[13] = new JLabel("×òÊÕ");
		data[14] = new JLabel("×î¸ß");
		data[15] = new JLabel("×îµÍ");
		data[16] = new JLabel("½ð¶î");
		data[17] = new JLabel("¾ù¼Û");
		data[18] = new JLabel("½ñ½á");
		data[19] = new JLabel("×ò½á");
		data[20] = new JLabel("ÈÕÔö²Ö");
		data[21] = new JLabel("Á¿±È");
		data[22] = new JLabel("µøÍ£");
		data[23] = new JLabel("ÄÚÅÌ");

		this.setLayout(null);

		for (int i = 0; i < 24; i++) {
//			this.add(data[i]);
			data[i].setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
			detail[i] = new JLabel();
			detail[i].setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
			detail[i].setHorizontalAlignment(SwingConstants.RIGHT);
//			this.add(detail[i]);
			data[i].setForeground(new Color(192, 192, 192));
		}

		this.setBackground(Color.BLACK);

		

		for (int i = 0; i < 24; i++) {
			data[i].setBounds(5, 30 + (LABEL_HEIGHT) * i, LABEL_WIDTH,
					LABEL_HEIGHT);
			detail[i].setBounds(70, 30 + (LABEL_HEIGHT) * i, 120,
					LABEL_HEIGHT);
		}
	}

	public void setDetail(int index) {
		this.index = index;
	}
	
	public void update(ArrayList<Arb_detail> details) {
		update(details.get(index).getFormattedArb_detail());
	}
	
	public void update(Arb_detail[] details) {
		update(details[index].getFormattedArb_detail());
	}

	public void update(Arb_detail arb) {
		title.setText(arb.getSymbol());
		currentPriceData.setText(String.valueOf(arb.getPresentPrice()));
		
		double priceChange = arb.getPriceChange();
		String priceChangePre = priceChange >= 0 ? "+" : "-";
		priceChangeData.setText(priceChangePre + String.valueOf(priceChange));
		priceChangeData.setForeground(priceChange >= 0 ? Color.RED : Color.GREEN);
		double change = arb.getChange();
		String changePre = change >= 0 ? "+" : "-";
		changeData.setText(changePre + String.valueOf(change) + "%");
		changeData.setForeground(change >= 0 ? Color.RED : Color.GREEN);
		
		askPriceData.setText(String.valueOf(arb.getAskPrice()));
		askData.setText(String.valueOf(arb.getAsk()));
		
		String zhangdie;
		if (arb.getPriceChange() >= 0) {
			zhangdie = "+" + arb.getPriceChange() + "%";
		} else {
			zhangdie = arb.getPriceChange() + "%";
		}
		String zhangfu;
		if (arb.getChange() >= 0) {
			zhangfu = "+" + arb.getChange() + "%";
		} else {
			zhangfu = arb.getChange() + "%";
		}

		if (arb.getPresentPrice() > arb.getSettlePrice()) {
			detail[2].setForeground(Color.RED);
		} else if (arb.getPresentPrice() < arb.getSettlePrice()) {
			detail[2].setForeground(Color.GREEN);
		} else {
			detail[2].setForeground(new Color(10, 156, 211));
		}

		if (arb.getChange() > 0) {
			detail[3].setForeground(Color.RED);
		} else if (arb.getChange() < 0) {
			detail[3].setForeground(Color.GREEN);
		} else {
			detail[3].setForeground(new Color(10, 156, 211));
		}

		detail[0].setForeground(new Color(10, 156, 211));
		detail[1].setForeground(new Color(10, 156, 211));
		for (int j = 4; j < 24; j++) {
			detail[j].setForeground(new Color(10, 156, 211));
		}

		detail[0].setText(String.valueOf(arb.getAskPrice()));
		detail[1].setText(String.valueOf(arb.getBidPirce()));
		detail[2].setText(String.valueOf(arb.getPresentPrice()));
		detail[3].setText(zhangdie);
		detail[4].setText(zhangfu);
		detail[5].setText(String.valueOf(arb.getSwing()) + "%");
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
		detail[16].setText(String.valueOf(arb.getFullAmount()) + "ÒÚ");
		detail[17].setText(String.valueOf(arb.getAverPrice()));
		detail[18].setText(String.valueOf(arb.getSettlePrice()));
		detail[19].setText(String.valueOf(arb.getPreSettlePrice()));
		detail[20].setText(String.valueOf(arb.getDailyWarehouse()));
		detail[21].setText(String.valueOf(arb.getRatio()));
		detail[22].setText(String.valueOf(arb.getLimitPrice()));
		detail[23].setText(String.valueOf(arb.getInvol()));
	}
	
	private void initHeader() {
		title.setBounds(LEFT_MARGIN, 0, width, 30);
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
		this.add(title);
		
		currentPriceData.setBounds(LEFT_MARGIN, 30, width, 45);
		currentPriceData.setHorizontalAlignment(SwingConstants.LEFT);
		currentPriceData.setForeground(Color.RED);
		currentPriceData.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 40));
		currentPriceData.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(193,193,193)));
		this.add(currentPriceData);

		priceChangeData.setBounds(width - 105, 30, 100, LABEL_HEIGHT);
		priceChangeData.setHorizontalAlignment(SwingConstants.RIGHT);
		priceChangeData.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		this.add(priceChangeData);
		changeData.setBounds(width - 105, 30 + LABEL_HEIGHT + LABEL_GAP, 100, LABEL_HEIGHT);
		changeData.setHorizontalAlignment(SwingConstants.RIGHT);
		changeData.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		this.add(changeData);
	}
	
	private void initSell() {
		JLabel[] sellLabels = new JLabel[5];
		sellLabels[0] = new JLabel("ÂôÎå"); 
		sellLabels[1] = new JLabel("ÂôËÄ"); 
		sellLabels[2] = new JLabel("ÂôÈý"); 
		sellLabels[3] = new JLabel("Âô¶þ"); 
		sellLabels[4] = new JLabel("ÂôÒ»"); 
		
		JLabel[] nullLabel1s = new JLabel[4];
		JLabel[] nullLabel2s = new JLabel[4];

		for (int i = 0; i < sellLabels.length; i++) {
			sellLabels[i].setBounds(LEFT_MARGIN, 78 + (LABEL_HEIGHT + 3)* i, width, LABEL_HEIGHT);
			sellLabels[i].setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
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
}
