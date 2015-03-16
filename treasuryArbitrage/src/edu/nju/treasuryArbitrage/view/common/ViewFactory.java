package edu.nju.treasuryArbitrage.view.common;

import edu.nju.treasuryArbitrage.view.arbitragePortfolio.ArbitragePortfolio;
import edu.nju.treasuryArbitrage.view.futuresMarket.FuturesMarket;
import edu.nju.treasuryArbitrage.view.holdings.Holdings;
import edu.nju.treasuryArbitrage.view.navigater.NavigationBar;
import edu.nju.treasuryArbitrage.view.navigater.SettingStopParameters;
import edu.nju.treasuryArbitrage.view.personalCenter.PersonalCenter;

public class ViewFactory {
	private static ViewFactory self;
	
	private TreasuryFrame frame;
	private NavigationBar navigationBar;
	private FuturesMarket futuresMarket;
	private ArbitragePortfolio arbitragePortfolio;
	private Holdings holdings;
	private PersonalCenter personalCenter;
	private SettingStopParameters settingStopParameters;
	
	public static ViewFactory getInstance() {
		if (self == null) {
			self = new ViewFactory();
		}
		return self;
	}
	
	public void init() {
		futuresMarket = new FuturesMarket();
		arbitragePortfolio = new ArbitragePortfolio();
		holdings = new Holdings();
		navigationBar = new NavigationBar();
	}
	
	public NavigationBar getNavigationBar() {
		return navigationBar;
	}

	public TreasuryFrame getFrame() {
		return frame;
	}

	public void setFrame(TreasuryFrame frame) {
		this.frame = frame;
	}

	public FuturesMarket getFuturesMarket() {
		return futuresMarket;
	}

	public ArbitragePortfolio getArbitragePortfolio() {
		return arbitragePortfolio;
	}

	public Holdings getHoldings() {
		return holdings;
	}

	public PersonalCenter getPersonalCenter() {
		if (personalCenter == null) {
			personalCenter = new PersonalCenter();
			personalCenter.assemble(ScreenSize.WIDTH, ScreenSize.HEIGHT);
		}
		return personalCenter;
	}
	
	public SettingStopParameters getSettingStopParameters() {
		if (settingStopParameters == null) {
			settingStopParameters = new SettingStopParameters();
		}
		return settingStopParameters;
	}
}
