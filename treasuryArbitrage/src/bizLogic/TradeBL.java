package bizLogic;

import java.util.HashMap;

import org.json.JSONObject;

public class TradeBL {
	
	public TradeBL(){}
	
	public boolean order(String username,String More_contract,String Blank_contract,int hand){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("More_contract", More_contract);
		params.put("Blank_contract", Blank_contract);
		params.put("hand", hand+"");//++++++++++++++++++++++++++++++++++++++++++++++
		
		NetHelper helper = new NetHelper("order",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result ")==1){
			return true;
		}
		
		return false;
	}
	
	public boolean cancleOrder(String username,int record_ID){
		
		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("username", username);
		params.put("id", record_ID+"");//++++++++++++++++++++++++++++++++++++++++++++++
		
		NetHelper helper = new NetHelper("cancelOrder",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result ")==1){
			return true;
		}
		
		return false;
	}
	
	public boolean trade(String username,int Repo_ID){
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("Repo_ID", Repo_ID+"");//++++++++++++++++++++++++++++++++++++++++++++++
		
		NetHelper helper = new NetHelper("trade",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result ")==1){
			return true;
		}
		
		return false;
	}
	
}
