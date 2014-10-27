package edu.nju.treasuryArbitrage.model;

public class ArbGroup {
	private String tobuy;
	private String tosell;
	
	public ArbGroup(String tobuy,String tosell){
		this.tobuy = tobuy;
		this.tosell = tosell;
	}
	
//	public ArbGroup(String buy_id,String sell_id){
//		
//	}
	
	public String getTobuy() {
		return tobuy;
	}

	public void setTobuy(String tobuy) {
		this.tobuy = tobuy;
	}

	public String getTosell() {
		return tosell;
	}

	public void setTosell(String tosell) {
		this.tosell = tosell;
	}
}
