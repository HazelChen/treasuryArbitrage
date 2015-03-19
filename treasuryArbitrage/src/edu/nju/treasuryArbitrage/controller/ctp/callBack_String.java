package edu.nju.treasuryArbitrage.controller.ctp;
import com.sun.jna.Callback;
import edu.nju.treasuryArbitrage.model.CTPArbDetail;

public class callBack_String implements Callback{
	
	public void setString(String val,int loc){
		//System.out.println("in java :"+val+"-->"+loc);
		CTPArbDetail.getInstance().setData(val, loc);
	}
	
}
