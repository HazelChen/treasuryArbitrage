package edu.nju.treasuryArbitrage.network;

import java.util.ArrayList;
import java.util.Date;

import bizLogic.*;
import vo.*;
import edu.nju.treasuryArbitrage.news.NewsBrief;

public class DataInterfaceToServer implements DataInterface{

	UserBL userBl;
	
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

	@Override
	public boolean register(String username, String password) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public boolean loginValidate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean changePWD(String username, String oldpwd, String newpwd) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public boolean logout() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public ArrayList<Finance> getFinanceList() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public ArrayList<Finance> getRepoList() {
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
		return null;
	}

	@Override
	public void ReadMess(String MessID) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void DeleteMess(String MessID) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public ArrayList<Record> getRecordList() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public double getPara_PROF() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public double getPara_LOSS() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public double getPara_GUAR() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public boolean setPara(double PROF, double LOSS, double GUAR) {
		// TODO �Զ����ɵķ������
		return false;
	}

}
