package edu.nju.treasuryArbitrage.model;

public class TransactionUnit {

	private Arbitrage toSell;	//��ͷ
	private Arbitrage toBuy;	//��ͷ
	
	public TransactionUnit(Arbitrage toSell, Arbitrage toBuy) {
		this.setToSell(toSell);
		this.setToBuy(toBuy);
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
