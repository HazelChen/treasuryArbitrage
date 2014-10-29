package edu.nju.treasuryArbitrage.ui.navigater;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class Navigater extends JPanel{
	private static final long serialVersionUID = -3260728762055118620L;
	
	private NavigaterItem[] items = new NavigaterItem[4];
	
	public Navigater() {
		init();
		naviPanelInit();
		assemble();
	}
	
	public void init() {
		int width = ScreenSize.WIDTH;
		int height = (int) (ScreenSize.HEIGHT / 25.0);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(ColorConstants.NAVIGATER_GRAY);
		this.setLayout(new BorderLayout());
	}
	
	public void naviPanelInit() {
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		items[0] = new NavigaterItem(this, factory.getFuturesMarket(), "�ڻ�����", true, true);
		items[1] = new NavigaterItem(this, factory.getArbitragePortfolio(), "�������", true, false);
		items[2] = new NavigaterItem(this, factory.getHoldings(), "�ֲ����", true, false);
		items[3] = new NavigaterItem(this, factory.getNews(), "�ƾ�Ҫ��", false, false);
		//items[4] = new NavigaterItem(this, factory.getPersonalCenter(), "��������", false, false);
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
