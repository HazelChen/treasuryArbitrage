package edu.nju.treasuryArbitrage.network;

import java.util.Date;

import edu.nju.treasuryArbitrage.news.NewsBrief;

public class DataInterfaceToServer implements DataInterface{

	@Override
	public boolean loginValidate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NewsBrief[] GetALLNewsBrief() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetNewsTitle(int NewsID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetNewsContent(int NewsID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsBrief[] searchNews(String keyword, Date fD, Date tD) {
		// TODO Auto-generated method stub
		return null;
	}

}
