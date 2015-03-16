package edu.nju.treasuryArbitrage.view.common;

public class LoginedUser {
	private static String username;
	
	public static void setLoginedUser(String name) {
		username = name;
	}
	
	public static String getLoginedUser() {
		return username;
	} 
}
