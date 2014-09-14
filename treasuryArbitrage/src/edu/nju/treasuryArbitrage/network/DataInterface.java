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
 * ���ݽӿ�
 * �ýӿڴ������������������ĺ�����Ŀǰ���ǿ�����DataInterfacePile���׮ʵ��
 */
public interface DataInterface {
	/**
	 * 
	 * ��ȡ�������Ÿ�Ҫ�������б���ʾ�����Ž��棬����ʱ����µ�������
	 * 
	 */
	public NewsBrief[] GetALLNewsBrief();
	
	/**
	 * 
	 * ����ID��ȡ���ű��⣻��
	 * 
	 */
	public String GetNewsTitle(String newsID);
	
	/**
	 * 
	 * ����ID��ȡ������ϸ���ݣ�
	 * 
	 */
	public String GetNewsContent(String NewsID);
	
	/**
	 * 
	 * �����������������ţ����ؽ������
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
	
	public boolean Order(String More_contract,String Blank_contract,double more_price,double blank_price,int hand,double guarantee);
	public boolean cancleOrder(int Record_ID);
	public boolean Trade(int Repo_ID, double profit);//�ͻ���ˢ���������ݣ��ʽ𡢳ֲ֡���ʷ��¼��,����boolean
	
	public double getProfit(double buyprice1,double saleprice1,double buyprice2,double saleprice2,int count);
	public double getGuar(double price1, double price2, int count);
}
