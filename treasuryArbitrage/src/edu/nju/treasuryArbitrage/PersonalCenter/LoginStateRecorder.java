package edu.nju.treasuryArbitrage.PersonalCenter;

import edu.nju.treasuryArbitrage.fileIO.FileOperater;

public class LoginStateRecorder {
	private static final String AUTO_LOGIN_FILE_NAME = "isAutoLogin";
	private static final String REMEMBER_PWD_FILE_NAME = "rememberPwd";

	public void rememberPwd(String username, String password) {
		String content = username + " " + password;
		
		FileOperater fileOperater = new FileOperater();
		fileOperater.write(REMEMBER_PWD_FILE_NAME, content);
	}
	
	
	public UserInfo getRememberedUser() {
		FileOperater fileOperater = new FileOperater();
		String content = fileOperater.read(REMEMBER_PWD_FILE_NAME);
		
		if (content.equals("")) {
			return new UserInfo();
		}
		
		String[] userAndPwd = content.split(" ");
		String username = userAndPwd[0];
		String pwd = userAndPwd[1];
		UserInfo userInfo = new UserInfo(username, pwd);
		return userInfo;
	}
	
	public void cancelRemember() {
		FileOperater fileOperater = new FileOperater();
		fileOperater.write(REMEMBER_PWD_FILE_NAME, "");
	}
	
	public void autoLogin(String username, String pwd) {
		FileOperater fileOperater = new FileOperater();
		fileOperater.write(AUTO_LOGIN_FILE_NAME, "1");
		rememberPwd(username, pwd);
	}
	
	public void cancelAutoLogin() {
		FileOperater fileOperater = new FileOperater();
		fileOperater.write(AUTO_LOGIN_FILE_NAME, "0");
	}
	
	public boolean isAutoLogin() {
		FileOperater fileOperater = new FileOperater();
		String isAutoString = fileOperater.read(AUTO_LOGIN_FILE_NAME).trim();
		if (isAutoString.equals("0")) {
			return false;
		} else {
			return true;
		}
	}
}
