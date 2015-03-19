package edu.nju.treasuryArbitrage.view.common;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.view.futuresMarket.FuturesMarket;
import edu.nju.treasuryArbitrage.view.navigater.NavigationBar;

public class TreasuryFrame extends JFrame{
	private static final long serialVersionUID = 3299539057280891303L;
	
	private static final String LOGO_ICON_PATH = "image/logo_icon.png";
	
	private JPanel mainPage;
	
	public TreasuryFrame() {
		init();
	}

	public void setPage(JPanel panel) {
		this.remove(mainPage);
		mainPage = panel;
		this.add(mainPage, BorderLayout.CENTER);
		this.getContentPane().repaint();
		this.validate();
	}
	
	private void init() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle(TextConstants.TITLE_CHS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ScreenSize.WIDTH, ScreenSize.HEIGHT);
		this.setLocation(0, 0);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(LOGO_ICON_PATH).getImage());
		
		ViewFactory factory = ViewFactory.getInstance();
		factory.setFrame(this);
		
		enterMainPage();
	}
	
	public void enterMainPage() {
		this.getContentPane().removeAll();
		ViewFactory factory = ViewFactory.getInstance();
		NavigationBar navigationBar = factory.getNavigationBar();
		FuturesMarket market = factory.getFuturesMarket();
		mainPage = market;
		this.add(navigationBar, BorderLayout.NORTH);
		this.add(mainPage, BorderLayout.CENTER);
		this.getContentPane().repaint();
		this.validate();
	}
	
}
