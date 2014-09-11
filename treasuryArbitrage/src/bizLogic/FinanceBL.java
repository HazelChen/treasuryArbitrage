package bizLogic;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.Finance;

public class FinanceBL {
	private ArrayList<Finance> finance_list;
	
	public FinanceBL(){}
	
	public ArrayList<Finance> getFinanceList(String username){
		
//		Finance finance = new Finance("",0,0,0);
//		finance_list.add(finance);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		NetHelper helper = new NetHelper("funds",params);
		JSONArray ret = helper.getJSONArrayByGet();
		
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			String time = temp.getString("time");
			double total = temp.getDouble("total_fund");
			double invest = temp.getDouble("invest_fund");
			double free = temp.getDouble("free_fund");
			Finance finance = new Finance(time,total,invest,free);
			finance_list.add(finance);
		}
		
		return finance_list;
	}
	
	public void addFinace(String time,double total,double guarantee,double idle){
		Finance finance = new Finance(time,total,guarantee,idle);
		finance_list.add(finance);
	}
}
