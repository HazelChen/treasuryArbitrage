package edu.nju.treasuryArbitrage.controller.dataInterface;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.ArbDetail;
import edu.nju.treasuryArbitrage.model.Finance;
import edu.nju.treasuryArbitrage.model.Message;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;


/**
 * 锟斤拷锟捷接匡拷
 * 锟矫接口达拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥猴拷锟斤拷锟斤拷目前锟斤拷锟角匡拷锟斤拷锟斤拷DataInterfacePile锟斤拷锟阶碉拷锟�
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
	
	public ArrayList<Message> getMessList();
	public void AddUnwindMess();
	public void AddArbMess();
	public void ReadMess(int index);
	public void DeleteMess(int index);
	
	public double getPara_PROF();
	public double getPara_LOSS();
	public double getPara_GUAR();
	public boolean setPara(double PROF,double LOSS,double GUAR);
	
    /**
     * Get past price today of a futures to draw point graph.
     */
	public ArrayList<ArbBrief> getPastPriceToday(String symbol);
	
	public boolean Order(String More_contract,String Blank_contract,double more_price,double blank_price,int hand,double guarantee);
	public boolean cancleOrder(int Record_ID);
	//public boolean Trade(int Repo_ID, double profit);//锟酵伙拷锟斤拷刷锟斤拷锟斤拷锟斤拷锟斤拷锟捷ｏ拷锟绞金、持仓★拷锟斤拷史锟斤拷录锟斤拷,锟斤拷锟斤拷boolean
	public boolean Trade(int Repo_ID, double profit, double blank_price, double more_price);
	
	public double getProfit(double buyprice1,double saleprice1,double buyprice2,double saleprice2,int count);
	public double getGuar(double price1, double price2, int count);
	
	//CTP=============================================================================================================	
}
