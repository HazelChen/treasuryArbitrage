package edu.nju.treasuryArbitrage.controller.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.nju.treasuryArbitrage.model.Arbitrage;
import edu.nju.treasuryArbitrage.model.Record;


public class RecordBL {
	private NetHelper helper;
	private ArrayList<Record> record_list;
	
	public RecordBL(NetHelper helper){
		this.helper = helper;
	}
	
	public ArrayList<Record> getRecordList(String username){
		
		record_list = new ArrayList<Record>();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		helper.setInitPara("history", params);
		JSONArray ret = helper.getJSONArrayByGet();
		if (ret == null) {
			return new ArrayList<Record>();
		}
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			Record record = new Record();
			
			int id = temp.getInt("id");
//			String time = df.format((temp.getLong("time")));
			long time = temp.getLong("time");
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
			record.setToBuy(new Arbitrage(tobuy,tobuyprice));
			record.setToSell(new Arbitrage(tosell,tosellprice));
			
			String[] stateStr = {"done","unknown","cancle","over"};
			record.setState(stateStr[state]);
			
			record_list.add(record);
		}
		
		return record_list;
	}
	
	public void addRecord(){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		long time = System.currentTimeMillis();
		
		Record record = new Record();
		record.setTime(time);
		record.setState("unknown");
		record_list.add(record);
	}
	
	public void cancle(int reco_ID){
		for(Record record:record_list){
			if(record.getRepo_ID()==(reco_ID)){
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//				String time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
				
				long time = System.currentTimeMillis();
				record.setTime(time);
				record.setState("cancle");
				break;
			}
		}
	}
	
	public void done(int reco_ID){
		for(Record record:record_list){
			if(record.getRepo_ID()==(reco_ID)){
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//				String time = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
				
				long time = System.currentTimeMillis();
				record.setTime(time);
				record.setState("done");
				break;
			}
		}
	}
}
