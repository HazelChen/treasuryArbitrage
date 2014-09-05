package edu.nju.treasuryArbitrage.network;

import java.util.Date;

import edu.nju.treasuryArbitrage.news.NewsBrief;


/**
 * 数据接口
 * 该接口存放所有与服务器交互的函数，目前我们可以在DataInterfacePile里打桩实现
 */
public interface DataInterface {
	/**
	 * 
	 * 获取所有新闻标题以用于列表显示在新闻界面
	 * 
	 */
	public NewsBrief[] GetALLNewsBrief();
	
	/**
	 * 
	 * 根据ID获取新闻标题；；
	 * 
	 */
	public String GetNewsTitle(int NewsID);
	
	/**
	 * 
	 * 根据ID获取新闻详细内容；
	 * 
	 */
	public String GetNewsContent(int NewsID);
	
	/**
	 * 
	 * 按给定条件搜索新闻，返回结果条数
	 * 
	 */
	public NewsBrief[] searchNews(String keyword, Date fD, Date tD);
	
}
