package edu.nju.treasuryArbitrage.model.ctp;

import com.sun.jna.Callback;

import edu.nju.treasuryArbitrage.view.common.ViewHelper;


public class callBack_Double implements Callback{
	
	public void setDouble(double val,int loc){
		//System.out.println("in java :"+val+"-->"+loc);
		CThostFtdcDepthMarketDataField.getInstance().setData(val, loc);	
		if(loc == 43){
			System.out.println("Start Show");
			//System.out.println(count++);
			show();
		}
	}
	
	private void show(){
		CThostFtdcDepthMarketDataField tem = CThostFtdcDepthMarketDataField.getInstance();
		System.err.println(tem.InstrumentID);
		
		ViewHelper.getInstance().updateViews();
	}
	
}
