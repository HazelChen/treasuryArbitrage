package edu.nju.treasuryArbitrage.model;

public class TransactionUnit {

	private Arbitrage toSell;	//ø’Õ∑
	private Arbitrage toBuy;	//∂‡Õ∑
	
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
