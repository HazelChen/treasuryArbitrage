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
		
		finance_list = new ArrayList<Finance>();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		NetHelper helper = new NetHelper("funds",params);
		JSONArray ret = helper.getJSONArrayByGet();
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			
//			String time = df.format((temp.getLong("time")));
			long time = temp.getLong("time");
			double total = temp.getDouble("total_fund");
			double invest = temp.getDouble("invest_fund");
			double free = temp.getDouble("free_fund");
			Finance finance = new Finance(time,total,invest,free);
			
			finance_list.add(finance);
		}
		
		return finance_list;
	}
	
	public void addFinace(long time,double total,double guarantee,double idle){
		Finance finance = new Finance(time,total,guarantee,idle);
		finance_list.add(finance);
	}
}
