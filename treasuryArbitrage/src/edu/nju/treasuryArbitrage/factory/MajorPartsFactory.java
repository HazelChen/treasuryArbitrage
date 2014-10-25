package edu.nju.treasuryArbitrage.factory;

import vo.News;
import edu.nju.treasuryArbitrage.arbitragePortfolio.ArbitragePortfolio;
import edu.nju.treasuryArbitrage.futuresMarket.FuturesMarket;
import edu.nju.treasuryArbitrage.holdings.Holdings;
import edu.nju.treasuryArbitrage.navigater.Navigater;
import edu.nju.treasuryArbitrage.news.NewsPanel;
import edu.nju.treasuryArbitrage.resources.NumericalResources;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.ui.personalCenter.PersonalCenter;
import edu.nju.treasuryArbitrage.ui.personalCenter.SettingStopParameters;

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
			futuresMarket = new FuturesMarket();
		}
		return futuresMarket;
	}

	public ArbitragePortfolio getArbitragePortfolio() {
		if (arbitragePortfolio == null) {
			arbitragePortfolio = new ArbitragePortfolio();
		}
		return arbitragePortfolio;
	}

	public Holdings getHoldings() {
		if (holdings == null) {
			holdings = new Holdings();
		}
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
			personalCenter.assemble(NumericalResources.SCREEN_WIDTH, NumericalResources.SCREEN_HEIGHT);
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
