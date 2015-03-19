package edu.nju.treasuryArbitrage.model;

public class Finance {
	long time;	//时间
	double total;	//总资金
	double guarantee;	//已投入保证金
	double idle; 	//空闲资金
	
	public Finance(long time,double total,double guarantee,double idle){
		this.time = time;
		this.total = total;
		this.guarantee = guarantee;
		this.idle = idle;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(double guarantee) {
		this.guarantee = guarantee;
	}

	public double getIdle() {
		return idle;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}
}
