package edu.nju.treasuryArbitrage.model.ctp;
import com.sun.jna.Callback;

public class callBack_String implements Callback{
	
	public void setString(String val,int loc){
		//System.out.println("in java :"+val+"-->"+loc);
		CThostFtdcDepthMarketDataField.getInstance().setData(val, loc);
	}
	
}
