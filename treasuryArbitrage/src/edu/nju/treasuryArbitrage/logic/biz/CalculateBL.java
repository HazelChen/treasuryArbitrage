package edu.nju.treasuryArbitrage.logic.biz;

public class CalculateBL {
	
	private double ratio = 0.05;

	public CalculateBL(){}

	public double getProfit(double buyprice1,double saleprice1,double buyprice2,double saleprice2,int count){
		double profit = 0;
		profit = ((saleprice1+saleprice2)-(buyprice1+buyprice2))*10000*count;
		return profit;
	}
	
	public double getGuar(double price1, double price2, int count){
		double guarantee = 0;
		guarantee = (price1+price2)*count*10000*ratio;
		return guarantee;
	}
	
	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
