package vo;

public class Repository {
	int repo_ID;	//持仓标记……………………我感觉蛮有必要
	long time;	//交易结束时间
	int count;		//交易手数
	double guarantee;	//投入保证金
	double profit;	//收益金额……方法计算得出

	String toSell;	//空头
	String toBuy;	//多头
	int toSell_price;
	int toBuy_price;
	
	public Repository(){
		
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

}
