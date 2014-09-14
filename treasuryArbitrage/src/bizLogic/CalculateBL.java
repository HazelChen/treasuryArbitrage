package bizLogic;

public class CalculateBL {
	
	private double ratio = 0.05;

	public CalculateBL(){}

	public double getProfit(){
		double profit = 0;
		
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
