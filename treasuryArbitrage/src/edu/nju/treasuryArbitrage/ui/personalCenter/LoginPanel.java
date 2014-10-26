package edu.nju.treasuryArbitrage.ui.personalCenter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LoginPanel extends JPanel{
	private static final long serialVersionUID = 6944414090977934692L;
	private static final String LOGO_NAI_IMAGE = "image/logo_nav_image.png";
	
	public LoginPanel(LoginFrame loginFrame) {
		init();
		assemble(loginFrame);
	}
	
	private void init() {
		this.setLayout(null);
	}
	
	private void assemble(LoginFrame loginFrame) {
		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon(LOGO_NAI_IMAGE));
		logoLabel.setBounds(0, 0, 550, 150);
		this.add(logoLabel);
		
		LoginInput loginInput = new LoginInput(loginFrame);
		loginInput.setBounds(0, 150, 550, 300);
		this.add(loginInput);
	}

}
