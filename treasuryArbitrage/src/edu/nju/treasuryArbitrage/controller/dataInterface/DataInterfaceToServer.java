package edu.nju.treasuryArbitrage.controller.dataInterface;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.controller.logic.ArbitrageBL;
import edu.nju.treasuryArbitrage.controller.logic.CalculateBL;
import edu.nju.treasuryArbitrage.controller.logic.FinanceBL;
import edu.nju.treasuryArbitrage.controller.logic.MessContainerBL;
import edu.nju.treasuryArbitrage.controller.logic.NetHelper;
import edu.nju.treasuryArbitrage.controller.logic.RecordBL;
import edu.nju.treasuryArbitrage.controller.logic.RepositoryBL;
import edu.nju.treasuryArbitrage.controller.logic.TradeBL;
import edu.nju.treasuryArbitrage.controller.logic.UserBL;
import edu.nju.treasuryArbitrage.controller.logic.CtpDataAdapter;
import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.ArbDetail;
import edu.nju.treasuryArbitrage.model.Finance;
import edu.nju.treasuryArbitrage.model.Message;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.model.UserVO;

public class DataInterfaceToServer implements DataInterface {

	UserBL userbl;
	MessContainerBL messbl;
	FinanceBL finanbl;
	RepositoryBL repobl;
	RecordBL recordbl;

	ArbitrageBL arbtbl;
	TradeBL tradebl;

	CalculateBL calcbl;
	
	CtpDataAdapter ctpAdapter;

	public DataInterfaceToServer() {
		NetHelper netHelper = new NetHelper();
		userbl = new UserBL(netHelper);
		finanbl = new FinanceBL(netHelper);
		repobl = new RepositoryBL(netHelper);
		recordbl = new RecordBL(netHelper);
		messbl = new MessContainerBL();
		arbtbl = new ArbitrageBL(netHelper);
		tradebl = new TradeBL(netHelper);
		calcbl = new CalculateBL();
		ctpAdapter = new CtpDataAdapter();
		ctpAdapter.startOrder();
		
	}

	// ==================================================================================================
	@Override
	public boolean register(String username, String password) {
		return userbl.register(username, password);
	}

	@Override
	public boolean loginValidate(String username, String password) {
		return userbl.login(username, password);
	}

	@Override
	public boolean changePWD(String username, String oldpwd, String newpwd) {
		return userbl.changePWD(username, oldpwd, newpwd);
	}

	@Override
	public boolean logout() {
		UserVO user = userbl.getUser();
		return userbl.logout(user.getUserID());
	}

	// ==================================================================================================

	@Override
	public ArrayList<Finance> getFinanceList() {
		UserVO user = userbl.getUser();
		if (user == null) {
			return new ArrayList<>();
		}
		return finanbl.getFinanceList(user.getUserID());
	}

	@Override
	public ArrayList<Repository> getRepoList() {
		UserVO user = userbl.getUser();
		if (user == null) {
			return new ArrayList<>();
		}
		return repobl.getRepoList(user.getUserID());
	}

	@Override
	public ArrayList<Record> getRecordList() {
		UserVO user = userbl.getUser();
		if (user == null) {
			return new ArrayList<>();
		}
		return recordbl.getRecordList(user.getUserID());
	}

	// ==================================================================================================

	@Override
	public ArrayList<Message> getMessList() {
		return messbl.getmessages();
	}

	@Override
	public void AddUnwindMess() {
		messbl.AddUnwind();
	}

	@Override
	public void AddArbMess() {
		messbl.AddArb();
	}

	@Override
	public void ReadMess(int index) {
		messbl.ReadMess(index);
	}

	@Override
	public void DeleteMess(int index) {
		messbl.DeleteMess(index);
	}

	// ==================================================================================================

	@Override
	public double getPara_PROF() {
		return userbl.getPara_PROF();
	}

	@Override
	public double getPara_LOSS() {
		return userbl.getPara_LOSS();
	}

	@Override
	public double getPara_GUAR() {
		return userbl.getPara_GUAR();
	}

	@Override
	public boolean setPara(double PROF, double LOSS, double GUAR) {
		return userbl.setParams(PROF, LOSS, GUAR);
	}

	// ==================================================================================================

	@Override
	public ArrayList<ArbDetail> getArbDetail() {
		return arbtbl.getDetailList();
		//return ctpAdapter.getDetailList();
	}

	@Override
	public ArrayList<ArbBrief> getPastPriceToday(String symbol){
		ArrayList<ArbBrief> pastPrices = arbtbl.getBriefList(symbol);
		
		if (pastPrices == null) {
			return new ArrayList<ArbBrief>();
		}
		
		return pastPrices;
	}
	
	@Override
	public boolean Order(String More_contract,
			String Blank_contract, double more_price, double blank_price,
			int hand,double guarantee) {
		UserVO user = userbl.getUser();
		return tradebl.order(user.getUserID(), More_contract, Blank_contract,
				more_price, blank_price, hand, guarantee);
	}

	@Override
	public boolean cancleOrder(int record_ID) {
		UserVO user = userbl.getUser();
		return tradebl.cancleOrder(user.getUserID(), record_ID);
	}

//	@Override
//	public boolean Trade(int Repo_ID, double profit) {
//		UserVO user = userbl.getUser();
//		return tradebl.trade(user.getUserID(), Repo_ID, profit);
//	}

	@Override
	public boolean Trade(int Repo_ID, double profit, double blank_price, double more_price) {
		UserVO user = userbl.getUser();
		return tradebl.trade(user.getUserID(), Repo_ID, profit, blank_price, more_price);
	}

	@Override
	public double getProfit(double buyprice1, double saleprice1,
			double buyprice2, double saleprice2, int count) {
		return calcbl.getProfit(buyprice1, saleprice1, buyprice2, saleprice2,
				count);
	}

	@Override
	public double getGuar(double price1, double price2, int count) {
		return calcbl.getGuar(price1, price2, count);
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

}
