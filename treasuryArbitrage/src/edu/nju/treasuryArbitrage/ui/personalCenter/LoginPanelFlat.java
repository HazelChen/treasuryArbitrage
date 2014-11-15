package edu.nju.treasuryArbitrage.ui.personalCenter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoginPanelFlat extends LoginPanel{
	private static final long serialVersionUID = 6944414090977934692L;
	private static final String LOGO_NAI_IMAGE = "image/logo_nav_image_flat.png";
	
	public LoginPanelFlat(LoginFrame loginFrame) {
		init();
		assemble(loginFrame);
	}
	
	private void init() {
		this.setLayout(null);
	}
	
	private void assemble(LoginFrame loginFrame) {
		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon(LOGO_NAI_IMAGE));
		logoLabel.setBounds(0, 0, 550, 300);
		this.add(logoLabel);
		
		LoginInput loginInput = new LoginInputFlat(loginFrame);
		loginInput.setBounds(0, 300, 550, 75);
		this.add(loginInput);
	}
}
