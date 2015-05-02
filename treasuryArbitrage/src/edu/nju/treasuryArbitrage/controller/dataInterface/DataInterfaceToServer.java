package edu.nju.treasuryArbitrage.controller.dataInterface;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.controller.logic.ArbitrageService;
import edu.nju.treasuryArbitrage.controller.logic.CalculationService;
import edu.nju.treasuryArbitrage.controller.logic.FinanceService;
import edu.nju.treasuryArbitrage.controller.logic.NetHelper;
import edu.nju.treasuryArbitrage.controller.logic.RecordService;
import edu.nju.treasuryArbitrage.controller.logic.RepositoryService;
import edu.nju.treasuryArbitrage.controller.logic.TradeService;
import edu.nju.treasuryArbitrage.controller.logic.UserService;
import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.Finance;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.model.UserVO;
import edu.nju.treasuryArbitrage.model.calculation.Lambda;
import edu.nju.treasuryArbitrage.model.calculation.OptimalKT;
import edu.nju.treasuryArbitrage.model.calculation.Xyz;

public class DataInterfaceToServer implements DataInterface {

	private UserService userService;
	private FinanceService financeService;
	private RepositoryService repositoryService;
	private RecordService recordService;

	private ArbitrageService arbitrageService;
	private TradeService tradeService;

	private CalculationService calculationService;
	
	public DataInterfaceToServer() {
		NetHelper netHelper = new NetHelper();
		userService = new UserService(netHelper);
		financeService = new FinanceService(netHelper);
		repositoryService = new RepositoryService(netHelper);
		recordService = new RecordService(netHelper);
		arbitrageService = new ArbitrageService(netHelper);
		tradeService = new TradeService(netHelper);
		calculationService = new CalculationService(netHelper);
	}

	/**
	 * User part
	 */
	@Override
	public boolean loginValidate(String username, String password) {
		return userService.login(username, password);
	}

	/**
	 * Finance part
	 */
	@Override
	public ArrayList<Finance> getFinanceList() {
		UserVO user = userService.getUser();
		if (user == null) {
			return new ArrayList<>();
		}
		return financeService.getFinanceList(user.getUserID());
	}

	@Override
	public double getFreeFund() {
		ArrayList<Finance> finances = getFinanceList();
		Finance latestFinance = null;
		long latestTime = 0;
		for (Finance finance : finances) {
			if (finance.getTime() > latestTime) {
				latestFinance = finance;
			}
		}
		return latestFinance.getIdle();
	}

	/**
	 * The treasury futures a user had
	 */
	@Override
	public ArrayList<Repository> getRepoList() {
		UserVO user = userService.getUser();
		if (user == null) {
			return new ArrayList<>();
		}
		return repositoryService.getRepoList(user.getUserID());
	}

	/**
	 * Transaction records
	 */
	@Override
	public ArrayList<Record> getRecordList() {
		UserVO user = userService.getUser();
		if (user == null) {
			return new ArrayList<>();
		}
		return recordService.getRecordList(user.getUserID());
	}


	/**
	 * User parameters, including profit point and loss point
	 */
	@Override
	public double getPara_PROF() {
		return userService.getPara_PROF();
	}

	@Override
	public double getPara_LOSS() {
		return userService.getPara_LOSS();
	}

	@Override
	public boolean setPara(double PROF, double LOSS, double GUAR) {
		return userService.setParams(PROF, LOSS, GUAR);
	}

	/**
	 * Futures price
	 */
	@Override
	public ArrayList<ArbBrief> getPastPriceToday(String symbol){
		ArrayList<ArbBrief> pastPrices = arbitrageService.getPastPrice(symbol);
		
		if (pastPrices == null) {
			return new ArrayList<ArbBrief>();
		}
		
		return pastPrices;
	}

	/**
	 * Order part
	 */
	@Override
	public boolean Order(String More_contract,
			String Blank_contract, double more_price, double blank_price,
			int hand,double guarantee) {
		UserVO user = userService.getUser();
		return tradeService.order(user.getUserID(), More_contract, Blank_contract,
				more_price, blank_price, hand, guarantee);
	}

	@Override
	public boolean cancleOrder(int record_ID) {
		UserVO user = userService.getUser();
		return tradeService.cancleOrder(user.getUserID(), record_ID);
	}


	@Override
	public boolean Trade(int Repo_ID, double profit, double blank_price, double more_price) {
		UserVO user = userService.getUser();
		return tradeService.trade(user.getUserID(), Repo_ID, profit, blank_price, more_price);
	}

	@Override
	public double getProfit(double buyprice1, double saleprice1,
			double buyprice2, double saleprice2, int count) {
		return tradeService.getProfit(buyprice1, saleprice1, buyprice2, saleprice2,
				count);
	}

	@Override
	public double getGuar(double price1, double price2, int count) {
		return tradeService.getGuar(price1, price2, count);
	}

	/**
	 * Calculation part
	 */
	@Override
	public Xyz getTodayXyz(int group) {
		return calculationService.getTodayXyz(group);
	}

	@Override
	public Lambda getTodayLambda(int group) {
		return calculationService.getTodayLambda(group);
	}

	@Override
	public OptimalKT getTodayKt(int group) {
		return calculationService.getTodayOptimalKT(group);
	}

}
