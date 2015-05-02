package edu.nju.treasuryArbitrage.view.personalCenter;

import edu.nju.treasuryArbitrage.controller.fileIO.FileOperater;

public class LoginStateRecorder {
	private static final String REMEMBER_PWD_FILE_NAME = "rememberUser";

	public void rememberUsername(String username) {
		String content = username;
		
		FileOperater fileOperater = new FileOperater();
		fileOperater.write(REMEMBER_PWD_FILE_NAME, content);
	}
	
	
	public String getRememberedUser() {
		FileOperater fileOperater = new FileOperater();
		String content = fileOperater.read(REMEMBER_PWD_FILE_NAME);
		return content;
	}
}
