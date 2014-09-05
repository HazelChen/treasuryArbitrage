package model.vo;

public class Repository {
	String repo_ID;	//持仓标记……………………我感觉蛮有必要
	String time;	//交易结束时间
	int count;		//交易手数
	double guarantee;	//投入保证金
	double profit;	//收益金额……方法计算得出

	Arb_detail toSell;	//空头
	Arb_detail toBuy;	//多头
	
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
	public Arb_detail getToSell() {
		return toSell;
	}
	public void setToSell(Arb_detail toSell) {
		this.toSell = toSell;
	}
	public Arb_detail getToBuy() {
		return toBuy;
	}
	public void setToBuy(Arb_detail toBuy) {
		this.toBuy = toBuy;
	}

}
