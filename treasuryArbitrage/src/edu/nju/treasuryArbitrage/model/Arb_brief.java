package edu.nju.treasuryArbitrage.model;

public class Arb_brief {
	String symbol;
	String time;
	double price;
	
	public Arb_brief(String symbol,String time,double price){
		this.symbol = symbol;
		this.time = time;
		this.price = price;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
