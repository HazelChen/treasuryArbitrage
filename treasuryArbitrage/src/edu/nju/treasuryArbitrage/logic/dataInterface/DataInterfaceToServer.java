package edu.nju.treasuryArbitrage.logic.dataInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.nju.treasuryArbitrage.logic.biz.ArbitrageBL;
import edu.nju.treasuryArbitrage.logic.biz.CalculateBL;
import edu.nju.treasuryArbitrage.logic.biz.FinanceBL;
import edu.nju.treasuryArbitrage.logic.biz.MessContainerBL;
import edu.nju.treasuryArbitrage.logic.biz.NewsBL;
import edu.nju.treasuryArbitrage.logic.biz.RecordBL;
import edu.nju.treasuryArbitrage.logic.biz.RepositoryBL;
import edu.nju.treasuryArbitrage.logic.biz.TradeBL;
import edu.nju.treasuryArbitrage.logic.biz.UserBL;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.model.Finance;
import edu.nju.treasuryArbitrage.model.Message;
import edu.nju.treasuryArbitrage.model.News;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.model.UserVO;
import edu.nju.treasuryArbitrage.ui.news.NewsBrief;

public class DataInterfaceToServer implements DataInterface {

	UserBL userbl;
	MessContainerBL messbl;
	FinanceBL finanbl;
	RepositoryBL repobl;
	RecordBL recordbl;
	NewsBL newsbl;

	ArbitrageBL arbtbl;
	TradeBL tradebl;

	CalculateBL calcbl;

	public DataInterfaceToServer() {

		userbl = new UserBL();
		finanbl = new FinanceBL();
		repobl = new RepositoryBL();
		recordbl = new RecordBL();
		messbl = new MessContainerBL();
		arbtbl = new ArbitrageBL();
		tradebl = new TradeBL();
		calcbl = new CalculateBL();
		newsbl = new NewsBL();
		
	}

	@Override
	public NewsBrief[] GetALLNewsBrief() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetNewsTitle(String NewsID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetNewsContent(String NewsID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<News> searchNews(String keyword, Date fD, Date tD) {
		// TODO Auto-generated method stub
		return null;
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
		return finanbl.getFinanceList(user.getUserID());
	}

	@Override
	public ArrayList<Repository> getRepoList() {
		UserVO user = userbl.getUser();
		return repobl.getRepoList(user.getUserID());
	}

	@Override
	public ArrayList<Record> getRecordList() {
		UserVO user = userbl.getUser();
		return recordbl.getRecordList(user.getUserID());
	}

	@Override
	public ArrayList<News> getNewsList() {
		return newsbl.getNewsList();
	}

	// ==================================================================================================

	@Override
	public ArrayList<Message> getMessList() {
		return messbl.getmessages();
	}

	@Override
	public void AddUnwindMess() {
		// TODO �Զ����ɵķ������
		messbl.AddUnwind();
	}

	@Override
	public void AddArbMess() {
		// TODO �Զ����ɵķ������
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
	public ArrayList<Arb_detail> getArbDetail() {
		return arbtbl.getDetailList();
	}

	@Override
	public ArrayList<ArbGroup> getArbGroup() {

		ArbGroup arbGroup=new ArbGroup("TF1412","TF1503");
		ArrayList<ArbGroup> list=new ArrayList<ArbGroup>();

		list.add(arbGroup);
		return list;
	}

	@Override
	public boolean Order(String More_contract,
			String Blank_contract, double more_price, double blank_price,
			int hand,double guarantee) {
		// TODO �Զ����ɵķ������
		UserVO user = userbl.getUser();
		return tradebl.order(user.getUserID(), More_contract, Blank_contract,
				more_price, blank_price, hand, guarantee);
	}

	@Override
	public boolean cancleOrder(int record_ID) {
		// TODO �Զ����ɵķ������
		UserVO user = userbl.getUser();
		return tradebl.cancleOrder(user.getUserID(), record_ID);
	}

	@Override
	public boolean Trade(int Repo_ID, double profit) {
		// TODO �Զ����ɵķ������
		UserVO user = userbl.getUser();
		return tradebl.trade(user.getUserID(), Repo_ID, profit);
	}

	@Override
	public HashMap<Long, Double> getDateAndPricePair() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getProfit(double buyprice1, double saleprice1,
			double buyprice2, double saleprice2, int count) {
		// TODO �Զ����ɵķ������
		return calcbl.getProfit(buyprice1, saleprice1, buyprice2, saleprice2,
				count);
	}

	@Override
	public double getGuar(double price1, double price2, int count) {
		// TODO �Զ����ɵķ������
		return calcbl.getGuar(price1, price2, count);
	}

}
