package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.logic.liveUpdate.LiveData;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;
import edu.nju.treasuryArbitrage.ui.futuresMarket.LineChart;

public class ArbitragePortfolioInternal extends JPanel{
	private static final long serialVersionUID = 3271440474664795135L;

	private static final Color BORDER_COLOR = new Color(193,193,193);
	
	private LineChart spreadLineChart = new LineChart(ColorConstants.BRIGHT_BLUE);
	private FuturesDetailInPortfolio detail1;
	private FuturesDetailInPortfolio detail2;
	private TakeAOrder takeAOrder;
	
	private ArbGroup group;
	
	public ArbitragePortfolioInternal(ArbGroup group) {
		this.setGroup(group);
		init();
		initComponents();
		assemble();
	}
	
	private void init() {
		setBackground(ArbitragePortfolioChen.BACKGROUND_COLOR);
		setLayout(null);
	}

	private void initComponents() {
		spreadLineChart.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, BORDER_COLOR));
		spreadLineChart.setYRange(-5.0, 5.0);
		spreadLineChart.setTitle("¼Û²î×ßÊÆ");
	}
	
	private void assemble() {
		spreadLineChart.setBounds(0, 0, ScreenSize.WIDTH - 20, ScreenSize.HEIGHT / 2 - 50);
		this.add(spreadLineChart);
		
		int underneathY = ScreenSize.HEIGHT / 2 - 50;
		int underneathHeight = ScreenSize.HEIGHT / 2 + 50;
		detail1 = new FuturesDetailInPortfolio(350, underneathHeight);
		detail1.setBounds(0, underneathY, 350, underneathHeight);
		detail1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(193, 193, 193)));
		this.add(detail1);
		
		detail2 = new FuturesDetailInPortfolio(350, underneathHeight);
		detail2.setBounds(350, underneathY, 350, underneathHeight);
		detail2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, BORDER_COLOR));
		this.add(detail2);
		
		takeAOrder = new TakeAOrder();
		takeAOrder.setBounds(700, underneathY, ScreenSize.WIDTH - 700, underneathHeight);
		this.add(takeAOrder);
	}

	public ArbGroup getGroup() {
		return group;
	}

	public void setGroup(ArbGroup group) {
		this.group = group;
	}

	public void update() {
		Arb_detail[] grpArbs = getArbDetails();
		double priceDiff = grpArbs[1].getPresentPrice() - grpArbs[0].getPresentPrice();
		spreadLineChart.addNewPrice(priceDiff);
		detail1.update(grpArbs[0]);
		detail2.update(grpArbs[1]);
		takeAOrder.update(grpArbs);
	}
	
	private Arb_detail[] getArbDetails() {
		Arb_detail[] grpArbs = new Arb_detail[2];
		ArrayList<Arb_detail> currentArbs = LiveData.getInstance().getArb_details();
		for (Arb_detail arb_detail : currentArbs) {
			if (arb_detail.getSymbol().equals(group.getTobuy())) {
				grpArbs[0] = arb_detail.getFormattedArb_detail();
			}
			if (arb_detail.getSymbol().equals(group.getTosell())) {
				grpArbs[1] = arb_detail.getFormattedArb_detail();
			}
		}
		return grpArbs;
	}
}
