package edu.nju.treasuryArbitrage.view.personalCenter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import edu.nju.treasuryArbitrage.view.common.TextConstants;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = -5896658657398444166L;

	private static final String LOGO_ICON_PATH = "image/logo_icon.png";
	
	public LoginFrame() {
		init();
		assemble();
	}
	
	private void init() {
		this.setTitle(TextConstants.TITLE_CHS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(550, 410);
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setIconImage(new ImageIcon(LOGO_ICON_PATH).getImage());
	}
	
	private void assemble() {
		LoginPanel loginPanel = new LoginPanelFlat(this);
		this.add(loginPanel);
	}
}
