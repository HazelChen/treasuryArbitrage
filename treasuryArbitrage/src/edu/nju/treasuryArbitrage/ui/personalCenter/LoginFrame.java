package edu.nju.treasuryArbitrage.ui.personalCenter;

import javax.swing.JFrame;

import edu.nju.treasuryArbitrage.ui.common.TextConstants;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = -5896658657398444166L;

	public LoginFrame() {
		init();
		assemble();
	}
	
	private void init() {
		this.setTitle("µÇÂ¼µ½" + TextConstants.TITLE_CHS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(550, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
	}
	
	private void assemble() {
		LoginPanel loginPanel = new LoginPanel(this);
		this.add(loginPanel);
	}
}
