package edu.nju.treasuryArbitrage.network;

import java.util.ArrayList;
import java.util.Date;

import bizLogic.*;
import vo.*;
import edu.nju.treasuryArbitrage.news.NewsBrief;

public class DataInterfaceToServer implements DataInterface{

	UserBL userbl;
	MessContainerBL messbl;
	FinanceBL finanbl;
	
	public DataInterfaceToServer(){
		
		userbl = new UserBL();
		messbl = new MessContainerBL();
		finanbl = new FinanceBL();
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
		// TODO �Զ����ɵķ������
		return userbl.register(username, password);
	}

	@Override
	public boolean loginValidate(String username, String password) {
		// TODO Auto-generated method stub
		return userbl.login(username, password);
	}
	
	@Override
	public boolean changePWD(String username, String oldpwd, String newpwd) {
		// TODO �Զ����ɵķ������
		return userbl.changePWD(username, oldpwd, newpwd);
	}

	@Override
	public boolean logout() {
		// TODO �Զ����ɵķ������
		UserVO user = userbl.getUser();
		return userbl.logout(user.getUserID());
	}
	//==================================================================================================
	
	@Override
	public ArrayList<Finance> getFinanceList() {
		// TODO �Զ����ɵķ������
		UserVO user = userbl.getUser();
		return finanbl.getFinanceList(user.getUserID());
	}

	@Override
	public ArrayList<Repository> getRepoList() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public boolean Trade(String Repo_ID) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public ArrayList<Message> getMessList() {
		// TODO �Զ����ɵķ������
		return messbl.getmessages();
	}

	@Override
	public void ReadMess(int index) {
		// TODO �Զ����ɵķ������
		messbl.ReadMess(index);
	}

	@Override
	public void DeleteMess(int index) {
		// TODO �Զ����ɵķ������
		messbl.DeleteMess(index);
	}

	@Override
	public ArrayList<Record> getRecordList() {
		// TODO �Զ����ɵķ������
		return null;
	}

	
	//==================================================================================================
	@Override
	public double getPara_PROF() {
		// TODO �Զ����ɵķ������
		return userbl.getPara_PROF();
	}

	@Override
	public double getPara_LOSS() {
		// TODO �Զ����ɵķ������
		return userbl.getPara_LOSS();
	}

	@Override
	public double getPara_GUAR() {
		// TODO �Զ����ɵķ������
		return userbl.getPara_GUAR();
	}

	@Override
	public boolean setPara(double PROF, double LOSS, double GUAR) {
		// TODO �Զ����ɵķ������
		return userbl.setParams(PROF, LOSS, GUAR);
	}

	@Override
	public Arb_detail getArbDetail(String id) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
