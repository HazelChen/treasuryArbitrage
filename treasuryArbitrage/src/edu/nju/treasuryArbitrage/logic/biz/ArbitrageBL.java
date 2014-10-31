package edu.nju.treasuryArbitrage.logic.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;


public class ArbitrageBL {

	private ArrayList<Arb_detail> detail_list;
	private ArrayList<ArbGroup> group_list;
	
	public ArbitrageBL(){}
	
	public ArrayList<Arb_detail> getDetailList(){
		
		detail_list = new ArrayList<Arb_detail>();
		
		HashMap<String, String> params = new HashMap<String, String>();
		NetHelper helper = new NetHelper("detail",params);
		JSONArray ret = helper.getJSONArrayByGet();
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			Arb_detail detail = new Arb_detail();
			
			String symbol = temp.getString("RT_CODE");
			int year = Integer.valueOf(symbol.substring(2, 4));
			int month = Integer.valueOf(symbol.substring(4, 6));
			String End = 20+""+year+"��"+month+"��";
			
			detail.setSymbol(symbol);
			detail.setMonth(End);
			//================================================
			int date = temp.getInt("RT_DATE");
			int day = temp.getInt("RT_TIME");
			
			detail.setDate(date);
			detail.setDay(day);
			
			int yeartemp = date/10000;
			int monthtemp = (date-yeartemp*10000)/100;
			int daytemp = (date-yeartemp*10000-monthtemp*100);
			int hourtemp = day/10000;
			int mintemp = (day-hourtemp*10000)/100;
			int secondtemp = (day-hourtemp*10000-mintemp*100);
			
			Calendar cal=Calendar.getInstance();
			cal.set(yeartemp, monthtemp-1, daytemp, hourtemp, mintemp, secondtemp);
			Date datetemp=cal.getTime();
			detail.setTime(datetemp.getTime());
			//================================================
			
			detail.setPresentPrice(temp.getDouble("RT_LAST"));
			detail.setChange(temp.getDouble("RT_PCT_CHG"));
			detail.setPriceChange(temp.getDouble("RT_CHG"));
			
			detail.setBidPirce(temp.getDouble("RT_BID1"));
			detail.setAskPrice(temp.getDouble("RT_ASK1"));
			detail.setBid(temp.getInt("RT_BSIZE1"));
			detail.setAsk(temp.getInt("RT_ASIZE1"));
			
			detail.setNvol(temp.getInt("RT_LAST_VOL"));
			detail.setVol(temp.getInt("RT_VOL"));
			detail.setPreRepository(temp.getInt("RT_PRE_OI"));
			detail.setRepository(temp.getInt("RT_OI"));
			
			detail.setDailyWarehouse(temp.getInt("RT_OI_CHG"));
			detail.setPreClose(temp.getDouble("RT_PRE_CLOSE"));
			detail.setOpen(temp.getDouble("RT_OPEN"));
			detail.setHigh(temp.getDouble("RT_HIGH"));
			detail.setLow(temp.getDouble("RT_LOW"));
			detail.setFullAmount(temp.getDouble("RT_AMT"));
			detail.setPreSettlePrice(temp.getDouble("RT_PRE_SETTLE"));
			detail.setSettlePrice(temp.getDouble("RT_SETTLE"));
			detail.setSwing(temp.getDouble("RT_SWING"));
			detail.setRatio(temp.getDouble("RT_VOL_RATIO"));
			
			detail.setHardenPrice(temp.getDouble("RT_HIGH_LIMIT"));
			detail.setLimitPrice(temp.getDouble("RT_LOW_LIMIT"));
			detail.setOutvol(temp.getInt("RT_UPWARD_VOL"));
			detail.setInvol(temp.getInt("RT_DOWNWARD_VOL"));
			detail.setAverPrice(temp.getDouble("RT_VWAP"));
			
			detail_list.add(detail);
		}
		
		return detail_list;
	}
	
//	public Arb_detail getDetail(String id){
//		Arb_detail arb = new Arb_detail();
//		
//		//���������ȡ��������������,ת��ΪJava�࣬����VO
//		
//		return arb;
//	}
	
	public ArrayList<ArbGroup> getArbGroups(){
		group_list = new ArrayList<ArbGroup>();
		
		
		
		return group_list;
	}
}