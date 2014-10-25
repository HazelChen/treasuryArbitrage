package edu.nju.treasuryArbitrage.model;

public class Record {
	int reco_ID;	//记录标记……………………我感觉蛮有必要
	long time;	//交易结束时间
	int count;		//交易手数
	double guarantee;	//投入保证金
	String state;	//交易状态

	Arbitrage toSell;	//空头
	Arbitrage toBuy;	//多头
	
	public Record(){}
	
	public int getRepo_ID() {
		return reco_ID;
	}
	public void setRepo_ID(int reco_ID) {
		this.reco_ID = reco_ID;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	
	//持有静态信息即可,内部类
	public class Arbitrage {
		String id;
		double value;
		
		public Arbitrage(String id,double value){
			this.id = id;
			this.value = value;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
	}
}
