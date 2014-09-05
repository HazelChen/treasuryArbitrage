package model.vo;

public class User {
	String userID;	//用户名
	String state;	//登录状态……………………暂时先留着，不提供方法
	
	double max_prof;
	double max_loss;
	double max_guar;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getMax_prof() {
		return max_prof;
	}
	public void setMax_prof(double max_prof) {
		this.max_prof = max_prof;
	}
	public double getMax_loss() {
		return max_loss;
	}
	public void setMax_loss(double max_loss) {
		this.max_loss = max_loss;
	}
	public double getMax_guar() {
		return max_guar;
	}
	public void setMax_guar(double max_guar) {
		this.max_guar = max_guar;
	}
}
