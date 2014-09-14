package edu.nju.treasuryArbitrage.network;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import vo.ArbGroup;
import vo.Arb_detail;
import vo.Finance;
import vo.Message;
import vo.News;
import vo.Record;
import vo.Repository;
import vo.UserVO;
import bizLogic.ArbitrageBL;
import bizLogic.FinanceBL;
import bizLogic.MessContainerBL;
import bizLogic.NewsBL;
import bizLogic.RecordBL;
import bizLogic.RepositoryBL;
import bizLogic.TradeBL;
import bizLogic.UserBL;
import edu.nju.treasuryArbitrage.news.NewsBrief;

public class DataInterfaceToServer implements DataInterface{

	UserBL userbl;
	MessContainerBL messbl;
	FinanceBL finanbl;
	RepositoryBL repobl;
	RecordBL recordbl;
	NewsBL newsbl;
	
	ArbitrageBL arbtbl;
	TradeBL tradebl;
	
	public DataInterfaceToServer(){
		
		userbl = new UserBL();
		finanbl = new FinanceBL();
		repobl = new RepositoryBL();
		recordbl = new RecordBL();
		messbl = new MessContainerBL();
		arbtbl = new ArbitrageBL();
		tradebl = new TradeBL();
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
	public NewsBrief[] searchNews(String keyword, Date fD, Date tD) {
		// TODO Auto-generated method stub
		return null;
	}

	//==================================================================================================
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
	
	//==================================================================================================
	
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
		// TODO 自动生成的方法存根
		return newsbl.getNewsList();
	}
	
	//==================================================================================================
	
	@Override
	public ArrayList<Message> getMessList() {
		return messbl.getmessages();
	}

	@Override
	public void ReadMess(int index) {
		messbl.ReadMess(index);
	}

	@Override
	public void DeleteMess(int index) {
		messbl.DeleteMess(index);
	}

	//==================================================================================================
	
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

	//==================================================================================================
	
	@Override
	public ArrayList<Arb_detail> getArbDetail() {
		return arbtbl.getDetailList();
	}

	@Override
	public ArrayList<ArbGroup> getArbGroup() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean Order(String username,String More_contract,String Blank_contract,double more_price,double blank_price,int hand,int guarantee) {
		// TODO 自动生成的方法存根
		UserVO user = userbl.getUser();
		return tradebl.order(user.getUserID(), More_contract, Blank_contract, more_price, blank_price, hand, guarantee);
	}

	@Override
	public boolean cancleOrder(int record_ID) {
		// TODO 自动生成的方法存根
		UserVO user = userbl.getUser();
		return tradebl.cancleOrder(user.getUserID(), record_ID);
	}

	@Override
	public boolean Trade(int Repo_ID, int profit) {
		// TODO 自动生成的方法存根
		UserVO user = userbl.getUser();
		return tradebl.trade(user.getUserID(), Repo_ID, profit);
	}

	@Override
	public HashMap<Long, Double> getDateAndPricePair() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
