package edu.nju.treasuryArbitrage.framework;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.futuresMarket.FuturesMarket;
import edu.nju.treasuryArbitrage.navigater.Navigater;
import edu.nju.treasuryArbitrage.resources.NumericalResources;
import edu.nju.treasuryArbitrage.resources.TextResources;

public class TreasuryFrame extends JFrame{
	private static final long serialVersionUID = 3299539057280891303L;
	
	private JPanel mainPage;
	
	public TreasuryFrame() {
		init();
		assemble();
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
		this.setTitle(TextResources.TITLE_CHS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(NumericalResources.SCREEN_WIDTH, NumericalResources.SCREEN_WIDTH);
		this.setLocation(0, 0);
		this.setResizable(false);
		
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		factory.setFrame(this);
	}
	
	private void assemble() {
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		Navigater navigater = factory.getNavigater();
		FuturesMarket market = factory.getFuturesMarket();
		mainPage = market;
		this.add(navigater, BorderLayout.NORTH);
		this.add(mainPage, BorderLayout.CENTER);
	}
}
