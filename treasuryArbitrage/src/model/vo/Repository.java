package model.vo;

public class Repository {
	String repo_ID;	//持仓标记……………………我感觉蛮有必要
	String time;	//交易结束时间
	int count;		//交易手数
	double guarantee;	//投入保证金
	double profit;	//收益金额……方法计算得出

	Arbitrage toSell;	//多头
	Arbitrage toBuy;	//空头
	
	public String getRepo_ID() {
		return repo_ID;
	}
	public void setRepo_ID(String repo_ID) {
		this.repo_ID = repo_ID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
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
	public Arbitrage getToSell() {
		return toSell;
	}
	public void setToSell(Arbitrage toSell) {
		this.toSell = toSell;
	}
	public Arbitrage getToBuy() {
		return toBuy;
	}
	public void setToBuy(Arbitrage toBuy) {
		this.toBuy = toBuy;
	}
}
