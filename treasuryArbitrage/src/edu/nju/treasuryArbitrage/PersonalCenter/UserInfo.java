package edu.nju.treasuryArbitrage.PersonalCenter;

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
}
