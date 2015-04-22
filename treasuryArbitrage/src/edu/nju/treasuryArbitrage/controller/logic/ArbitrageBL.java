package edu.nju.treasuryArbitrage.controller.logic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.ArbDetail;


public class ArbitrageBL {
	
	private ArrayList<ArbBrief> brief_list;
	
	private NetHelper helper;
	
	public ArbitrageBL(NetHelper netHelper){
		this.helper = netHelper;
	}
	
	public ArrayList<ArbBrief> getBriefList(String symbol){
		brief_list = new ArrayList<ArbBrief>();
		
		HashMap<String, String> params = new HashMap<String, String>();
		try {
			params.put("CODE", URLEncoder.encode(symbol, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		helper.setInitPara("olddetail", params);
		JSONArray ret = helper.getJSONArrayByGet();
		
		if (ret == null) {
			return new ArrayList<ArbBrief>();
		}
		
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			String time = temp.getString("UpdataTime");
			double price = temp.getDouble("LastPrice");
			ArbBrief brief = new ArbBrief(symbol,time,price);
			brief_list.add(brief);
		}
		
		return brief_list;
	}
	
}
