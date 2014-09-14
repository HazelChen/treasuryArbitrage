package edu.nju.treasuryArbitrage.network;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import vo.ArbGroup;
import vo.Arb_detail;
import vo.Finance;
import vo.Message;
import vo.News;
import vo.Record;
import vo.Repository;
import edu.nju.treasuryArbitrage.news.NewsBrief;


/**
 * 数据接口
 * 该接口存放所有与服务器交互的函数，目前我们可以在DataInterfacePile里打桩实现
 */
public interface DataInterface {
	/**
	 * 
	 * 获取所有新闻概要以用于列表显示在新闻界面，根据时间从新到旧排序
	 * 
	 */
	public NewsBrief[] GetALLNewsBrief();
	
	/**
	 * 
	 * 根据ID获取新闻标题；；
	 * 
	 */
	public String GetNewsTitle(String newsID);
	
	/**
	 * 
	 * 根据ID获取新闻详细内容；
	 * 
	 */
	public String GetNewsContent(String NewsID);
	
	/**
	 * 
	 * 按给定条件搜索新闻，返回结果条数
	 * 
	 */
	public NewsBrief[] searchNews(String keyword, Date fD, Date tD);
	
	public boolean register(String username,String password);
	public boolean loginValidate(String username, String password);
	public boolean changePWD(String username,String oldpwd,String newpwd);
	public boolean logout();
	
	public ArrayList<Finance> getFinanceList();
	public ArrayList<Repository> getRepoList();
	public ArrayList<Record> getRecordList();
	public ArrayList<News> getNewsList();
	
	public ArrayList<Message> getMessList();
	public void AddUnwindMess();
	public void AddArbMess();
	public void ReadMess(int index);
	public void DeleteMess(int index);
	
	public double getPara_PROF();
	public double getPara_LOSS();
	public double getPara_GUAR();
	public boolean setPara(double PROF,double LOSS,double GUAR);
	
	public ArrayList<Arb_detail> getArbDetail();
	public ArrayList<ArbGroup> getArbGroup();
	public HashMap<Long, Double> getDateAndPricePair();
	
	public boolean Order(String username,String More_contract,String Blank_contract,double more_price,double blank_price,int hand,int guarantee);
	public boolean cancleOrder(int Record_ID);
	public boolean Trade(int Repo_ID, double profit);//客户端刷新三处数据（资金、持仓、历史记录）,返回boolean
	
	public double getProfit(double buyprice1,double saleprice1,double buyprice2,double saleprice2,int count);
	public double getGuar(double price1, double price2, int count);
}
