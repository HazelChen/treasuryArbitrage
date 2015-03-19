package edu.nju.treasuryArbitrage.controller.ctp;

import com.sun.jna.Callback;
import edu.nju.treasuryArbitrage.controller.threads.LiveData;
import edu.nju.treasuryArbitrage.model.CTPArbDetail;
import edu.nju.treasuryArbitrage.view.common.ViewHelper;


public class callBack_Double implements Callback{
	
	public void setDouble(double val,int loc){
		CTPArbDetail.getInstance().setData(val, loc);
		if(loc == 43){
			System.out.println("Start Show");
			show();
		}
	}
	
	private void show(){
		CTPArbDetail ctpArbDetal = CTPArbDetail.getInstance();
        LiveData.getInstance().register(ctpArbDetal.getArbDetail());
        ViewHelper.getInstance().updateViews();
	}
	
}
