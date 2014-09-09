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
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean loginValidate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean changePWD(String username, String oldpwd, String newpwd) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean logout() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public ArrayList<Finance> getFinanceList() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<Finance> getRepoList() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean Trade(String Repo_ID) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public ArrayList<Message> getMessList() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void ReadMess(String MessID) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void DeleteMess(String MessID) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public ArrayList<Record> getRecordList() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public double getPara_PROF() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public double getPara_LOSS() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public double getPara_GUAR() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public boolean setPara(double PROF, double LOSS, double GUAR) {
		// TODO 自动生成的方法存根
		return false;
	}

}
