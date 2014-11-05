package edu.nju.treasuryArbitrage.model;

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