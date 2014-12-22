package edu.nju.treasuryArbitrage.model.ctp;
import com.sun.jna.Callback;


public class callBack_Double implements Callback{
	
	public void setDouble(double val,int loc){
		System.out.println("in java :"+val+"-->"+loc);
		CThostFtdcDepthMarketDataField.getInstance().setData(val, loc);	
	}
	
}
