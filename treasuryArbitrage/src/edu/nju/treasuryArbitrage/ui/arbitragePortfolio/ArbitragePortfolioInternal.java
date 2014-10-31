package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;
import edu.nju.treasuryArbitrage.ui.futuresMarket.LineChart;

public class ArbitragePortfolioInternal extends JPanel{

	private static final Color BORDER_COLOR = new Color(193,193,193);
	
	private LineChart spreadLineChart = new LineChart(ColorConstants.BRIGHT_BLUE);
	
	private ArbGroup group;
	
	public ArbitragePortfolioInternal(ArbGroup group) {
		this.setGroup(group);
		init();
	}
	
	private void init() {
		setBackground(ArbitragePortfolioChen.BACKGROUND_COLOR);
	}

	private void assemble() {
		spreadLineChart.setBounds(0, 10, ScreenSize.WIDTH, ScreenSize.HEIGHT / 2);
		spreadLineChart.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, BORDER_COLOR));
	}

	public ArbGroup getGroup() {
		return group;
	}

	public void setGroup(ArbGroup group) {
		this.group = group;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
