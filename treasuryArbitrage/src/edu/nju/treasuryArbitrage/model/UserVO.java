package edu.nju.treasuryArbitrage.model;

public class UserVO {
	String userID;	//ÓÃ»§Ãû
	boolean isAutoLogin;
	
	double max_prof;
	double max_loss;
	double max_guar;
	
	public UserVO(String userID,boolean isAutoLogin){
		this.userID = userID;
		this.isAutoLogin = isAutoLogin;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public boolean isAutoLogin() {
		return isAutoLogin;
	}
	public void setAutoLogin(boolean isAutoLogin) {
		this.isAutoLogin = isAutoLogin;
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
