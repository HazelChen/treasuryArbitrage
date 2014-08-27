package edu.nju.treasuryArbitrage.navigater;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	}
	
	public void naviPanelInit() {
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		items[0] = new NavigaterItem(factory.getFuturesMarket());
		items[1] = new NavigaterItem(factory.getArbitragePortfolio());
		items[2] = new NavigaterItem(factory.getHoldings());
		items[3] = new NavigaterItem(factory.getNews());
		items[4] = new NavigaterItem(factory.getPersonalCenter());
	}
	
	public void assemble() {
		JPanel left = new JPanel();
		for (int i = 0; i < items.length; i++) {
			left.add(items[i]);
		}
		this.add(left);
	}

}
