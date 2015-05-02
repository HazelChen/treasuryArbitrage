package edu.nju.treasuryArbitrage.controller.dataInterface;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.ArbDetail;
import edu.nju.treasuryArbitrage.model.Finance;
import edu.nju.treasuryArbitrage.model.Message;
import edu.nju.treasuryArbitrage.model.Record;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.model.calculation.Lambda;
import edu.nju.treasuryArbitrage.model.calculation.OptimalKT;
import edu.nju.treasuryArbitrage.model.calculation.Xyz;


/**
 * 锟斤拷锟捷接匡拷
 * 锟矫接口达拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥猴拷锟斤拷锟斤拷目前锟斤拷锟角匡拷锟斤拷锟斤拷DataInterfacePile锟斤拷锟阶碉拷锟�
 */
public interface DataInterface {
	boolean loginValidate(String username, String password);

	ArrayList<Finance> getFinanceList();
	double getFreeFund();
	
	ArrayList<Repository> getRepoList();
	ArrayList<Record> getRecordList();
	
	double getPara_PROF();
	double getPara_LOSS();
	boolean setPara(double PROF,double LOSS,double GUAR);
	
    /**
     * Get past price today of a futures to draw point graph.
     */
	ArrayList<ArbBrief> getPastPriceToday(String symbol);
	
	boolean Order(String More_contract,String Blank_contract,double more_price,double blank_price,int hand,double guarantee);
	boolean cancleOrder(int Record_ID);
	boolean Trade(int Repo_ID, double profit, double blank_price, double more_price);
	double getProfit(double buyprice1,double saleprice1,double buyprice2,double saleprice2,int count);
	double getGuar(double price1, double price2, int count);

	Xyz getTodayXyz(int group);
	Lambda getTodayLambda(int group);
	OptimalKT getTodayKt(int group);
}
