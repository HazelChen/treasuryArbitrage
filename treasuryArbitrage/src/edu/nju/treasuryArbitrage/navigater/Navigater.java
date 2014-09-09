package edu.nju.treasuryArbitrage.navigater;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.resources.ColorResources;
import edu.nju.treasuryArbitrage.resources.NumericalResources;

public class Navigater extends JPanel{
	private static final long serialVersionUID = -3260728762055118620L;
	
	private NavigaterItem[] items = new NavigaterItem[5];
	
	public Navigater() {
		init();
		naviPanelInit();
		assemble();
	}
	
	public void init() {
		int width = NumericalResources.SCREEN_WIDTH;
		int height = (int) (NumericalResources.SCREEN_HEIGHT / 25.0);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(ColorResources.NAVIGATER_GRAY);
		this.setLayout(new BorderLayout());
	}
	
	public void naviPanelInit() {
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		items[0] = new NavigaterItem(this, factory.getFuturesMarket(), "期货行情", true);
		items[1] = new NavigaterItem(this, factory.getArbitragePortfolio(), "套利组合", true);
		items[2] = new NavigaterItem(this, factory.getHoldings(), "持仓情况", true);
		items[3] = new NavigaterItem(this, factory.getNews(), "财经要闻", false);
		items[4] = new NavigaterItem(this, factory.getPersonalCenter(), "个人中心", false);
//		items[4] = new NavigaterItem(this, new JPanel(), "个人中心", false);
	}
	
	public void assemble() {
		JPanel left = new JPanel();
		BoxLayout boxLayout = new BoxLayout(left, BoxLayout.X_AXIS);
		left.setLayout(boxLayout);
		for (int i = 0; i < items.length; i++) {
			left.add(items[i]);
		}
		this.add(left, BorderLayout.WEST);
		
		NaviUserItem naviUserItem = new NaviUserItem();
		this.add(naviUserItem, BorderLayout.EAST);
	}

	public void initAllItem() {
		for (int i = 0; i < items.length; i++) {
			items[i].initialState();
		}
	}

	public void clearSelected() {
		for (int i = 0; i < items.length; i++) {
			items[i].setSelected(false);
		}
	}

}
