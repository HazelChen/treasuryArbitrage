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

	public TakeAOrder() {
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

		tfdHoldings.addKeyListener(new HoldingsListener());

	}

	public void update(Arb_detail[] arb_details) {
		arbGroups = arb_details;
		Arb_detail arb1 = arb_details[0];
		Arb_detail arb2 = arb_details[1];
		name1.setText(arb1.getSymbol());
		name2.setText(arb2.getSymbol());
		dir1.setText("多头");
		dir2.setText("空头");
		prePrice1 = arb1.getPresentPrice();
		prePrice2 = arb2.getPresentPrice();
		price1.setText(String.valueOf(prePrice1));
		price2.setText(String.valueOf(prePrice2));
	}

	public class HoldingsListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
			holds = Integer.parseInt(tfdHoldings.getText());
			DataInterface dataInterface = DataInterfaceFactory.getInstance()
					.getDataInterfaceToServer();
			guar = (int) (dataInterface.getGuar(prePrice1, prePrice2, holds) * 1000) / 1000.0;
			mny.setText(String.valueOf(guar));
			restMoney = dataInterface.getFreeFund() - guar;
			restMny.setText(restMoney >= 0 ? String.valueOf(restMoney) : "0");
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	public class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String text = tfdHoldings.getText();
			if (text.length() == 0) {
				JOptionPane.showMessageDialog(null, "手数输入错误", "错误提示",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (Integer.parseInt(text) > 0 && restMoney >= 0) {
					JOptionPane.showMessageDialog(null, "下单成功！");
					DataInterfaceFactory.getInstance().getDataInterfaceToServer().Order(arbGroups[0].getSymbol(), 
							arbGroups[1].getSymbol(), arbGroups[0].getPresentPrice(), arbGroups[1].getPresentPrice(), 
							holds, guar);
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

	/*
	 * class ConfirmPanel extends JPanel{ private JLabel title; private JLabel
	 * name,name1,name2; private JLabel type,typeData; private JLabel
	 * direction,dir_name1,dir_name2,dir1,dir2; private JLabel
	 * price,price_name1,price_name2,price1,price2; private JLabel holdings,hld;
	 * private JLabel money,mny; private JButton confirm,cancel;
	 * 
	 * private double prePrice1,prePrice2,guar; private int holds; private
	 * String tobuy,tosell;
	 * 
	 * public ConfirmPanel(){ title=new JLabel("下单信息确认"); name=new
	 * JLabel("合约名称",JLabel.CENTER); type=new JLabel("交易类型",JLabel.CENTER);
	 * typeData=new JLabel("建仓"); direction=new JLabel("套利方向",JLabel.CENTER);
	 * price=new JLabel("合约价格",JLabel.CENTER); holdings=new
	 * JLabel("手数",JLabel.CENTER); money=new JLabel("投入保证金",JLabel.CENTER);
	 * confirm=new JButton("确认"); cancel=new JButton("取消"); name1=new JLabel();
	 * name2=new JLabel(); dir_name1=new JLabel(); dir_name2=new JLabel();
	 * dir1=new JLabel(); dir2=new JLabel(); price_name1=new JLabel();
	 * price_name2=new JLabel(); price1=new JLabel(); price2=new JLabel();
	 * hld=new JLabel(); mny=new JLabel();
	 * 
	 * this.add(confirm); this.add(cancel); this.add(money); this.add(holdings);
	 * this.add(price); this.add(direction); this.add(name); this.add(title);
	 * this.add(name1); this.add(name2); this.add(type); this.add(typeData);
	 * this.add(dir_name1); this.add(dir_name2); this.add(dir1); this.add(dir2);
	 * this.add(price_name1); this.add(price_name2); this.add(price1);
	 * this.add(price2); this.add(hld); this.add(mny);
	 * 
	 * this.setLayout(null); title.setBounds(160,5,200,30); title.setFont(new
	 * Font("serif",Font.BOLD,20)); title.setForeground(Color.BLACK);
	 * 
	 * Font font=new Font("serif",Font.BOLD,14); name.setBounds(50,50,80,30);
	 * name.setFont(font); name.setForeground(Color.BLACK);
	 * 
	 * name1.setBounds(160,50,80,30); name2.setBounds(280,50,80,30);
	 * name1.setFont(font); name1.setForeground(Color.BLACK);
	 * name2.setFont(font); name2.setForeground(Color.BLACK);
	 * 
	 * type.setBounds(50,110,80,30); type.setFont(font);
	 * type.setForeground(Color.BLACK); typeData.setBounds(160,110,80,30);
	 * typeData.setFont(font); typeData.setForeground(Color.BLACK);
	 * 
	 * direction.setBounds(50,170,80,30); direction.setFont(font);
	 * direction.setForeground(Color.BLACK); dir_name1.setBounds(160,170,80,30);
	 * dir1.setBounds(280,170,80,30); dir_name2.setBounds(160,200,80,30);
	 * dir2.setBounds(280,200,80,30); dir_name1.setFont(font);
	 * dir_name1.setForeground(Color.BLACK); dir_name2.setFont(font);
	 * dir_name2.setForeground(Color.BLACK); dir1.setFont(font);
	 * dir1.setForeground(Color.BLACK); dir2.setFont(font);
	 * dir2.setForeground(Color.BLACK);
	 * 
	 * price.setBounds(50,230,80,30); price.setFont(font);
	 * price.setForeground(Color.BLACK); price_name1.setBounds(160,230,80,30);
	 * price1.setBounds(280,230,80,30); price_name2.setBounds(160,260,80,30);
	 * price2.setBounds(280,260,80,30); price_name1.setFont(font);
	 * price_name1.setForeground(Color.BLACK); price_name2.setFont(font);
	 * price_name2.setForeground(Color.BLACK); price1.setFont(font);
	 * price1.setForeground(Color.BLACK); price2.setFont(font);
	 * price2.setForeground(Color.BLACK);
	 * 
	 * holdings.setBounds(50,290,80,30); holdings.setFont(font);
	 * holdings.setForeground(Color.BLACK); hld.setBounds(250,290,80,30);
	 * hld.setFont(font); hld.setForeground(Color.BLACK);
	 * 
	 * money.setBounds(50,350,80,30); money.setFont(font);
	 * money.setForeground(Color.BLACK); mny.setBounds(140,350,240,30);
	 * mny.setFont(font); mny.setForeground(Color.BLACK);
	 * 
	 * confirm.setBounds(110,400,100,30); cancel.setBounds(260,400,100,30);
	 * confirm.setContentAreaFilled(false); confirm.setBorderPainted(true);
	 * confirm.setFocusPainted(false); confirm.setFont(new
	 * Font("serif",Font.BOLD,20)); confirm.setForeground(Color.BLACK);
	 * cancel.setContentAreaFilled(false); cancel.setBorderPainted(true);
	 * cancel.setFocusPainted(false); cancel.setFont(new
	 * Font("serif",Font.BOLD,20)); cancel.setForeground(Color.BLACK);
	 * 
	 * confirm.addActionListener(new ConfirmDetailListener());
	 * cancel.addActionListener(new CancelListener());
	 * 
	 * }
	 * 
	 * public void setData(String tb,String ts,double p1,double p2,int h,double
	 * g){ tobuy=tb; tosell=ts; prePrice1=p1; prePrice2=p2; holds=h; guar=g;
	 * 
	 * name1.setText(tobuy); name2.setText(tosell); dir_name1.setText(tobuy);
	 * dir1.setText("多头"); dir_name2.setText(tosell); dir2.setText("空头");
	 * 
	 * price_name1.setText(tobuy); price_name2.setText(tosell);
	 * price1.setText(String.valueOf(p1)); price2.setText(String.valueOf(p2));
	 * 
	 * hld.setText(String.valueOf(holds));
	 * 
	 * mny.setText(String.valueOf(guar));
	 * 
	 * this.setVisible(true); }
	 * 
	 * 
	 * 
	 * public class ConfirmDetailListener implements ActionListener{ public void
	 * actionPerformed(ActionEvent e) {
	 * JOptionPane.showMessageDialog(null,"下单成功！"); database.Order(tobuy,
	 * tosell, prePrice1, prePrice2, holds, guar); } }
	 * 
	 * public class CancelListener implements ActionListener{ public void
	 * actionPerformed(ActionEvent e) { confirmPanel.setVisible(false); } }
	 * 
	 * }
	 */
}
