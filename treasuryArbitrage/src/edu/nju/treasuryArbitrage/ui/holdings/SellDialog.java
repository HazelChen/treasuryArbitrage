package edu.nju.treasuryArbitrage.ui.holdings;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.Repository;

public class SellDialog extends JDialog {
	private static final long serialVersionUID = 3595675326727853779L;

	private static final Color BACKGROUND_COLOR = Holdings.BACKGROUND_COLOR;
	private static final Color FOREGROUND_COLOR = Color.BLACK;

	private static final Font NORMAL_FONT = new Font("微软雅黑", Font.PLAIN, 18);

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

	private JLabel money = new BordedLabel("投入保证金", JLabel.CENTER);
	private JLabel mny = new BordedLabel("", JLabel.CENTER);
	private JLabel restMoneyLabel = new BordedLabel("收益/损失", JLabel.CENTER);
	private JLabel restMny = new BordedLabel("", JLabel.CENTER);

	private JButton confirm = new JButton("确认卖出");
	
	private double prePrice1, prePrice2;
	private Repository repository;

	public SellDialog(Repository repository, 
			double buyPrice, double sellPrice) {
		this.prePrice1 = (int)(buyPrice * 1000) / 1000.0;
		this.prePrice2 = (int)(sellPrice * 1000) / 1000.0;
		
		this.repository = repository;
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);
		this.setSize(700, 380);
		this.setResizable(false);
		this.setModal(true);
		this.setTitle("平仓");
		this.setLocationRelativeTo(null);
		
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
		this.add(holdings);
		this.add(hld);

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

		confirm.setBounds(180, 300, 300, 30);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(true);
		confirm.setFocusPainted(false);
		confirm.setFont(NORMAL_FONT);
		confirm.setForeground(FOREGROUND_COLOR);
		confirm.addActionListener(new ConfirmListener());
		update();

	}

	public void update() {
		name1.setText(repository.getToBuy());
		name2.setText(repository.getToSell());
		dir1.setText("多头");
		dir2.setText("空头");
		price1.setText(String.valueOf(prePrice1));
		price2.setText(String.valueOf(prePrice2));
		hld.setText(repository.getCount() + "手");
		mny.setText(repository.getGuarantee() + "元");
		restMny.setText(repository.getProfit() + "元");
	}

	public class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SellDialog.this.setVisible(false);
			DataInterface database = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
			boolean result = database.Trade(repository.getRepo_ID(), repository.getProfit());
			//TODO
			boolean cancelOrderResult = database.cancleOrder(repository.getRepo_ID());
			if (cancelOrderResult && result) {
				JOptionPane.showMessageDialog(null, "平仓成功！");
			} else {
				JOptionPane.showMessageDialog(null, "平仓失败！");
			}
			MajorPartsFactory.getInstance().getHoldings().updatePage();
			SellDialog.this.dispose();
		}
	}

	private class BordedLabel extends JLabel {
		private static final long serialVersionUID = -7586450516168737600L;

		public BordedLabel(String text, int location) {
			super(text, location);
			setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
		}
	}

}
