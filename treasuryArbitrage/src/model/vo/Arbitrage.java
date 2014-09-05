package model.vo;

//持有静态信息即可
public class Arbitrage {
	String id;
	double value;	//此处数值需要即时得出
	
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
