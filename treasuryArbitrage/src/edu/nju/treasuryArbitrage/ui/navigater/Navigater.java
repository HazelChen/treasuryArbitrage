package edu.nju.treasuryArbitrage.ui.navigater;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class Navigater extends JPanel{
	private static final long serialVersionUID = -3260728762055118620L;
	
	/*package*/ static final Color BACKGROUND_COLOR = new Color(235,242,250);
	/*package*/ static final Color MOUSE_ENTER_BACKGROUND_COLOR = new Color(247,68,97);
	/*package*/ static final Color MOUSE_ENTER_FOREGROUND_COLOR = new Color(247,68,97);
	/*package*/ static final Color SELECTED_BACKGROUND_COLOR = new Color(186,227,217);
	/*package*/ static final Color FOREGROUND_COLOR = Color.BLACK;
	/*package*/ static final Color SELECTED_FOREGROUND_COLOR = new Color(247,68,97);
	
	private NavigaterItem[] items = new NavigaterItem[3];
	
	
	public Navigater() {
		init();
		naviPanelInit();
		assemble();
	}
	
	public void init() {
		int width = ScreenSize.WIDTH;
		int height = (int) (ScreenSize.HEIGHT / 25.0);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
	}
	
	public void naviPanelInit() {
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		items[0] = new NavigaterItem(this, factory.getFuturesMarket(), "期货行情", true, true, SELECTED_BACKGROUND_COLOR, SELECTED_FOREGROUND_COLOR);
		items[1] = new NavigaterItem(this, factory.getArbitragePortfolio(), "套利组合", true, false, SELECTED_BACKGROUND_COLOR, SELECTED_FOREGROUND_COLOR);
		items[2] = new NavigaterItem(this, factory.getHoldings(), "持仓情况", true, false, SELECTED_BACKGROUND_COLOR, SELECTED_FOREGROUND_COLOR);
		//items[3] = new NavigaterItem(this, factory.getNews(), "财经要闻", false, false);
		//items[4] = new NavigaterItem(this, factory.getPersonalCenter(), "个人中心", false, false);
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
