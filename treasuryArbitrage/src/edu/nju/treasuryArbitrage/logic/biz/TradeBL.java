package edu.nju.treasuryArbitrage.logic.biz;

import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

public class TradeBL {
	private NetHelper helper;
	
	public TradeBL(NetHelper netHelper){
		this.helper = netHelper;
	}
	
	public boolean order(String username,String More_contract,String Blank_contract,double more_price,double blank_price,int hand,double guarantee){
		
		long time = new Date().getTime();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("more_contract", More_contract);
		params.put("blank_contract", Blank_contract);
		params.put("more_price", more_price+"");
		params.put("blank_price", blank_price+"");
		params.put("hand", hand+"");
		params.put("bond", guarantee+"");
		params.put("time", time+"");
		
		helper.setInitPara("order", params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if (ret == null) {
			return false;
		}
		
		if(ret.getInt("result")==1){
			return true;
		}
		
		return false;
	}
	
	public boolean cancleOrder(String username,int record_ID){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("id", record_ID+"");
		
		helper.setInitPara("cancelOrder", params);
		JSONObject ret = helper.getJSONObjectByGet();
		if (ret == null) {
			return false;
		}
		
		if(ret.getInt("result")==1){
			return true;
		}
		
		return false;
	}
	
	public boolean trade(String username,int Repo_ID,double profit){
		
		long time = new Date().getTime();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("Repo_Id", Repo_ID+"");
		params.put("profit", profit+"");
		params.put("time", time+"");
		
		helper.setInitPara("trade", params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if (ret == null) {
			return false;
		}
		
		if(ret.getInt("result")==1){
			return true;
		}
		
		return false;
	}
	
	public boolean trade(String username,int Repo_ID,double profit,double blank_price,double more_price){
		
		long time = new Date().getTime();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("Repo_Id", Repo_ID+"");
		params.put("profit", profit+"");
		params.put("time", time+"");
		params.put("blank_price", blank_price+"");
		params.put("blank_price", blank_price+"");
		
		helper.setInitPara("trade", params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if (ret == null) {
			return false;
		}
		
		if(ret.getInt("result")==1){
			return true;
		}
		
		return false;
	}
}
