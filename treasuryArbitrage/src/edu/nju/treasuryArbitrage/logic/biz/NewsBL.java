package edu.nju.treasuryArbitrage.logic.biz;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.nju.treasuryArbitrage.model.News;


public class NewsBL {
	private NetHelper helper;
	private ArrayList<News> newslist;
	
	public NewsBL(NetHelper helper){
		this.helper = helper;
	}
	
	public ArrayList<News> getNewsList(){
		newslist = new ArrayList<News>();
		
		HashMap<String, String> params = new HashMap<String, String>();
		helper.setInitPara("news", params);
		JSONArray ret = helper.getJSONArrayByGet();
		
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			String title = temp.getString("title");
			String content = temp.getString("content");
			String source = temp.getString("source");
			long time = temp.getLong("time");
			
			News news = new News(title,content,source,time);
			newslist.add(news);
		}
		
		return newslist;
	}
}
