package edu.nju.treasuryArbitrage.view.personalCenter;

public class UserInfo {
	String username;
	String password;
	
	UserInfo() {
		username = "";
		password = "";
	}
	
	UserInfo(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
