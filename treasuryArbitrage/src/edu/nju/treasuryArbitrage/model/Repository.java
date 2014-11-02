package edu.nju.treasuryArbitrage.model;

public class Repository {
	int repo_ID;	//持仓标记……………………我感觉蛮有必要
	long time;	//交易结束时间
	int count;		//交易手数
	double guarantee;	//投入保证金
	double profit;	//收益金额……方法计算得出
	
	int signal;     //交易状态       signal=1，正向套利建仓；signal=-1，反向套利建仓；signal=0，不交易。signal=0，不交易；signal=2，止盈平仓；signal=-2,止损平仓,signal=3，正向套利平仓；signal=-3，反向套利平仓；
	
	String toSell;	//空头
	String toBuy;	//多头
	double toSell_price;
	double toBuy_price;
	
	public Repository(){
		
	}
	public int getSignal() {
		return signal;
	}
	public int getRepo_ID() {
		return repo_ID;
	}
	public void setRepo_ID(int repo_ID) {
		this.repo_ID = repo_ID;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(double guarantee) {
		this.guarantee = guarantee;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public String getToSell() {
		return toSell;
	}
	public void setToSell(String toSell) {
		this.toSell = toSell;
	}
	public String getToBuy() {
		return toBuy;
	}
	public void setToBuy(String toBuy) {
		this.toBuy = toBuy;
	}
	public double gettoBuy_price() {
		return toBuy_price;
	}
	public void settoBuy_price(double toBuy_price) {
		this.toBuy_price = toBuy_price;
	}
	public double gettoSell_price() {
		return toSell_price;
	}
	public void settoSell_price(double d) {
		this.toSell_price = d;
	}
}
