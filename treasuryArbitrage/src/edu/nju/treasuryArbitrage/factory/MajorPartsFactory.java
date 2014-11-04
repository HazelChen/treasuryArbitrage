package edu.nju.treasuryArbitrage.factory;

import edu.nju.treasuryArbitrage.ui.arbitragePortfolio.ArbitragePortfolio;
import edu.nju.treasuryArbitrage.ui.arbitragePortfolio.ArbitragePortfolioChen;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.ui.futuresMarket.FuturesMarket;
import edu.nju.treasuryArbitrage.ui.futuresMarket.FuturesMarketChen;
import edu.nju.treasuryArbitrage.ui.holdings.Holdings;
import edu.nju.treasuryArbitrage.ui.holdings.HoldingsChen;
import edu.nju.treasuryArbitrage.ui.navigater.Navigater;
import edu.nju.treasuryArbitrage.ui.navigater.SettingStopParameters;
import edu.nju.treasuryArbitrage.ui.news.NewsPanel;

public class MajorPartsFactory {
	private static MajorPartsFactory self;
	
	private TreasuryFrame frame;
	private Navigater navigater;
	private FuturesMarket futuresMarket;
	private ArbitragePortfolio arbitragePortfolio;
	private Holdings holdings;
	private NewsPanel news;
	//private PersonalCenter personalCenter;
	private SettingStopParameters settingStopParameters;
	
	private MajorPartsFactory(){}
	
	public static MajorPartsFactory getInstance() {
		if (self == null) {
			self = new MajorPartsFactory();
		}
		return self;
	}
	
	public Navigater getNavigater() {
		if (navigater == null) {
			navigater = new Navigater();
		}
		return navigater;
	}

	public TreasuryFrame getFrame() {
		return frame;
	}

	public void setFrame(TreasuryFrame frame) {
		this.frame = frame;
	}

	public FuturesMarket getFuturesMarket() {
		if (futuresMarket == null) {
			futuresMarket = new FuturesMarketChen();
		}
		return futuresMarket;
	}

	public ArbitragePortfolio getArbitragePortfolio() {
		if (arbitragePortfolio == null) {
			arbitragePortfolio = new ArbitragePortfolioChen();
		}
		return arbitragePortfolio;
	}

	public Holdings getHoldings() {
		if (holdings == null) {
			holdings = new HoldingsChen();
		}
		return holdings;
	}

	public NewsPanel getNews() {
		if (news == null) {
			news = new NewsPanel();
		}
		return news;
	}

	/*public PersonalCenter getPersonalCenter() {
		if (personalCenter == null) {
			personalCenter = new PersonalCenter();
			personalCenter.assemble(ScreenSize.WIDTH, ScreenSize.HEIGHT);
		}
		return personalCenter;
	}*/
	
	public SettingStopParameters getSettingStopParameters() {
		if (settingStopParameters == null) {
			settingStopParameters = new SettingStopParameters();
		}
		return settingStopParameters;
	}
}
