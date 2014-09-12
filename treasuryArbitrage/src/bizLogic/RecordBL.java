package bizLogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.Record;

public class RecordBL {
	private ArrayList<Record> record_list;
	
	public RecordBL(){}
	
	public ArrayList<Record> getRecordList(String username){
		
//		Record record = new Record();
//		record_list.add(record);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		NetHelper helper = new NetHelper("history",params);
		JSONArray ret = helper.getJSONArrayByGet();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			Record record = new Record();
			
			String id = temp.getString("id");
			String time = df.format((temp.getLong("time")));
			int count = temp.getInt("hand");
			int guarantee = temp.getInt("bond");
			int state = temp.getInt("state");
			
			String tobuy = temp.getString("more_contract");
			double tobuyprice = temp.getDouble("more_price");
			String tosell = temp.getString("blank_contract");
			double tosellprice = temp.getDouble("blank_price");
			
			record.setRepo_ID(id);
			record.setTime(time);
			record.setCount(count);
			record.setGuarantee(guarantee);
			record.setToBuy(record.new Arbitrage(tobuy,tobuyprice));
			record.setToSell(record.new Arbitrage(tosell,tosellprice));
			
			String[] stateStr = {"done","unknown","cancle","over"};
			record.setState(stateStr[state]);
		}
		
		return record_list;
	}
	
	public void addRecord(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		
		Record record = new Record();
		record.setTime(time);
		record.setState("unknown");
		record_list.add(record);
	}
	
	public void cancle(String reco_ID){
		for(Record record:record_list){
			if(record.getRepo_ID().equals(reco_ID)){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String time = df.format(new Date());// new Date()为获取当前系统时间
				
				record.setTime(time);
				record.setState("cancle");
				break;
			}
		}
	}
	
	public void done(String reco_ID){
		for(Record record:record_list){
			if(record.getRepo_ID().equals(reco_ID)){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String time = df.format(new Date());// new Date()为获取当前系统时间
				
				record.setTime(time);
				record.setState("done");
				break;
			}
		}
	}
}
