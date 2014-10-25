package edu.nju.treasuryArbitrage.ui.common;

public class LoginedUser {
	private static String username;
	
	public static void setLoginedUser(String name) {
		username = name;
	}
	
	public static String getLoginedUser() {
		return username;
	} 
}
