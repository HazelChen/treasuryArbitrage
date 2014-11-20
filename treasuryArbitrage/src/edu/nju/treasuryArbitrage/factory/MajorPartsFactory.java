package edu.nju.treasuryArbitrage.factory;

import edu.nju.treasuryArbitrage.ui.arbitragePortfolio.ArbitragePortfolio;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.ui.futuresMarket.FuturesMarket;
import edu.nju.treasuryArbitrage.ui.holdings.Holdings;
import edu.nju.treasuryArbitrage.ui.navigater.Navigater;
import edu.nju.treasuryArbitrage.ui.navigater.SettingStopParameters;
import edu.nju.treasuryArbitrage.ui.news.NewsPanel;
import edu.nju.treasuryArbitrage.ui.personalCenter.PersonalCenter;

public class MajorPartsFactory {
	private static MajorPartsFactory self;
	
	private TreasuryFrame frame;
	private Navigater navigater;
	private FuturesMarket futuresMarket;
	private ArbitragePortfolio arbitragePortfolio;
	private Holdings holdings;
	private NewsPanel news;
	private PersonalCenter personalCenter;
	private SettingStopParameters settingStopParameters;
	
	public static MajorPartsFactory getInstance() {
		if (self == null) {
			self = new MajorPartsFactory();
		}
		return self;
	}
	
	public void init() {
		futuresMarket = new FuturesMarket();
		arbitragePortfolio = new ArbitragePortfolio();
		holdings = new Holdings();
		navigater = new Navigater();
	}
	
	public Navigater getNavigater() {
		return navigater;
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

	public NewsPanel getNews() {
		if (news == null) {
			news = new NewsPanel();
		}
		return news;
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
