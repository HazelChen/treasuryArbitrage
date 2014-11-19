package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;

public class TakeAOrder extends JPanel {
	private static final long serialVersionUID = 3595675326727853779L;

	private static final Color BACKGROUND_COLOR = new Color(13,13,13);
	private static final Color FOREGROUND_COLOR = ArbitragePortfolio.FOREGROUND_COLOR;

	private static final Font NORMAL_FONT = new Font("微软雅黑", Font.PLAIN, 18);

	private JLabel title;
	private JLabel name = new BordedLabel("合约名称", JLabel.CENTER);
	private JLabel name1 = new BordedLabel("", JLabel.CENTER);
	private JLabel name2 = new BordedLabel("", JLabel.CENTER);

	private JLabel direction = new BordedLabel("套利方向", JLabel.CENTER);
	private JLabel dir1 = new BordedLabel("", JLabel.CENTER);
	private JLabel dir2 = new BordedLabel("", JLabel.CENTER);

	private JLabel price = new BordedLabel("合约价格", JLabel.CENTER);
	private JLabel price1 = new BordedLabel("", JLabel.CENTER);
	private JLabel price2 = new BordedLabel("", JLabel.CENTER);

	private JLabel holdings = new BordedLabel("手数", JLabel.CENTER);
	private JLabel hld = new BordedLabel("手", JLabel.CENTER);

	private JLabel money = new BordedLabel("所需保证金", JLabel.CENTER);
	private JLabel mny = new BordedLabel("", JLabel.CENTER);
	private JLabel restMoneyLabel = new BordedLabel("剩余保证金", JLabel.CENTER);
	private JLabel restMny = new BordedLabel("", JLabel.CENTER);

	private JButton confirm = new JButton("下单");
	public JTextField tfdHoldings = new JTextField();
	
	private Arb_detail[] arbGroups;
	private double prePrice1, prePrice2, guar, restMoney;
	private int holds;
	private boolean isRecentBuy;

	public TakeAOrder(ArbGroup arbGroup) {
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);

		title = new JLabel("下单信息");
		title.setBounds(3, 0, 100, 30);
		title.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		title.setForeground(Color.YELLOW);
		this.add(title);

		name.setBounds(30, 50, 200, 40);
		name.setFont(NORMAL_FONT);
		name.setForeground(FOREGROUND_COLOR);
		name1.setBounds(230, 50, 200, 40);
		name2.setBounds(430, 50, 200, 40);
		name1.setFont(NORMAL_FONT);
		name1.setForeground(FOREGROUND_COLOR);
		name2.setFont(NORMAL_FONT);
		name2.setForeground(FOREGROUND_COLOR);
		this.add(name);
		this.add(name1);
		this.add(name2);

		direction.setBounds(30, 90, 200, 40);
		direction.setFont(NORMAL_FONT);
		direction.setForeground(FOREGROUND_COLOR);
		dir1.setBounds(230, 90, 200, 40);
		dir2.setBounds(430, 90, 200, 40);
		dir1.setFont(NORMAL_FONT);
		dir1.setForeground(FOREGROUND_COLOR);
		dir2.setFont(NORMAL_FONT);
		dir2.setForeground(FOREGROUND_COLOR);
		this.add(direction);
		this.add(dir1);
		this.add(dir2);

		price.setBounds(30, 130, 200, 40);
		price.setFont(NORMAL_FONT);
		price.setForeground(FOREGROUND_COLOR);
		price1.setBounds(230, 130, 200, 40);
		price2.setBounds(430, 130, 200, 40);
		price1.setFont(NORMAL_FONT);
		price1.setForeground(FOREGROUND_COLOR);
		price2.setFont(NORMAL_FONT);
		price2.setForeground(FOREGROUND_COLOR);
		this.add(price);
		this.add(price1);
		this.add(price2);

		holdings.setBounds(30, 170, 200, 40);
		holdings.setFont(NORMAL_FONT);
		holdings.setForeground(FOREGROUND_COLOR);
		hld.setBounds(230, 170, 400, 40);
		hld.setFont(NORMAL_FONT);
		hld.setForeground(FOREGROUND_COLOR);
		tfdHoldings.setBounds(240, 175, 170, 30);
		this.add(holdings);
		this.add(hld);
		this.add(tfdHoldings);

		money.setBounds(30, 210, 200, 40);
		money.setFont(NORMAL_FONT);
		money.setForeground(FOREGROUND_COLOR);
		mny.setBounds(230, 210, 400, 40);
		mny.setFont(NORMAL_FONT);
		mny.setForeground(FOREGROUND_COLOR);
		this.add(money);
		this.add(mny);

		restMoneyLabel.setBounds(30, 250, 200, 40);
		restMoneyLabel.setFont(NORMAL_FONT);
		restMoneyLabel.setForeground(FOREGROUND_COLOR);
		restMny.setBounds(230, 250, 400, 40);
		restMny.setFont(NORMAL_FONT);
		restMny.setForeground(FOREGROUND_COLOR);
		this.add(restMoneyLabel);
		this.add(restMny);

		this.add(confirm);

		confirm.setBounds(150, 300, 300, 30);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(true);
		confirm.setFocusPainted(false);
		confirm.setFont(NORMAL_FONT);
		confirm.setForeground(FOREGROUND_COLOR);
		confirm.addActionListener(new ConfirmListener());
		confirm.setEnabled(false);

		tfdHoldings.addKeyListener(new HoldingsListener());

		name1.setText(arbGroup.getRecent());
		name2.setText(arbGroup.getFar());
	}

	public void update(Arb_detail[] arbGroups, boolean isRecentBuy) {
		this.arbGroups = arbGroups;
		this.isRecentBuy = isRecentBuy;
		
		if (isRecentBuy) {
			dir1.setText("多头");
			dir2.setText("空头");
		} else {
			dir1.setText("空头");
			dir2.setText("多头");
		}
		prePrice1 = arbGroups[0].getPresentPrice();
		prePrice2 = arbGroups[1].getPresentPrice();
		price1.setText(String.valueOf(prePrice1));
		price2.setText(String.valueOf(prePrice2));
		calculateAndSetHands();
	}
	
	public class HoldingsListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
			try {
				holds = Integer.parseInt(tfdHoldings.getText());
			} catch (Exception e2) {
				holds = 0;
			}
			calculateAndSetHands();
		}

		public void keyTyped(KeyEvent e) {
		}
	}
	
	private void calculateAndSetHands() {
		if (holds == 0) {
			return;
		}
		DataInterface dataInterface = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer();
		guar = (int) (dataInterface.getGuar(prePrice1, prePrice2, holds) * 1000) / 1000.0;
		mny.setText(String.valueOf(guar));
		restMoney = (int)((dataInterface.getFreeFund() - guar) * 1000) / 1000.0;
		restMny.setText(restMoney >= 0 ? String.valueOf(restMoney) : "0");
	}

	public class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String text = tfdHoldings.getText();
			if (text.length() == 0) {
				JOptionPane.showMessageDialog(null, "手数输入错误", "错误提示",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (Integer.parseInt(text) > 0 && restMoney >= 0) {
					BuyDialog buyDialog = new BuyDialog(arbGroups, isRecentBuy, holds, guar);
					buyDialog.setVisible(true);
				} else if (restMoney < 0){
					JOptionPane.showMessageDialog(null, "剩余保证金不足", "错误提示",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "手数输入错误", "错误提示",
							JOptionPane.WARNING_MESSAGE);
					
				}
			}
		}
	}

	private class BordedLabel extends JLabel {
		private static final long serialVersionUID = -7586450516168737600L;

		public BordedLabel(String text, int location) {
			super(text, location);
			setBorder(BorderFactory.createLineBorder(new Color(193, 193, 193),
					1));
		}
	}

	public void clearActive() {
		dir1.setText("");
		dir2.setText("");
		price1.setText("");
		price2.setText("");
		confirm.setEnabled(false);
	}
	
	public void active() {
		confirm.setEnabled(true);
	}
}
