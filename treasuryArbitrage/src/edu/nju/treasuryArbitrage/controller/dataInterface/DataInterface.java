package edu.nju.treasuryArbitrage.controller.dataInterface;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.ArbDetail;
import edu.nju.treasuryArbitrage.model.Finance;
import edu.nju.treasuryArbitrage.model.Message;
import edu.nju.treasuryArbitrage.model.News;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;


/**
 * ���ݽӿ�
 * �ýӿڴ������������������ĺ�����Ŀǰ���ǿ�����DataInterfacePile���׮ʵ��
 */
public interface DataInterface {
	public boolean register(String username,String password);
	public boolean loginValidate(String username, String password);
	public boolean changePWD(String username,String oldpwd,String newpwd);
	public boolean logout();
	
	public ArrayList<Finance> getFinanceList();
	public double getFreeFund();
	
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
	
	public ArrayList<ArbDetail> getArbDetail();
	public ArrayList<ArbBrief> getArbBrief(String symbol);
	
	public boolean Order(String More_contract,String Blank_contract,double more_price,double blank_price,int hand,double guarantee);
	public boolean cancleOrder(int Record_ID);
	//public boolean Trade(int Repo_ID, double profit);//�ͻ���ˢ���������ݣ��ʽ𡢳ֲ֡���ʷ��¼��,����boolean
	public boolean Trade(int Repo_ID, double profit, double blank_price, double more_price);
	
	public double getProfit(double buyprice1,double saleprice1,double buyprice2,double saleprice2,int count);
	public double getGuar(double price1, double price2, int count);
	
	//CTP=============================================================================================================	
}
