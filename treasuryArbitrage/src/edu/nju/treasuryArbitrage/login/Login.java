package edu.nju.treasuryArbitrage.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.resources.ColorResources;

public class Login extends JPanel{
	public static final String TITLE = "国债期货跨期交易系统";
	
	public Login() {
		init();
	}
	
	public void assemble(int width, int height) {
		int headerX = width / 5;
		int headerY = height / 5;
		Label label = new Label(TITLE);
		label.setForeground(ColorResources.TITLE_YELLOW);
		label.setFont(new Font("微软雅黑", 0, 70));
		label.setBounds(headerX, headerY, width - 2 * headerX, 100);
		this.add(label);
		
		LoginInput loginInput = new LoginInput();
		loginInput.setBounds(headerX + 100, headerY + 150, label.getWidth() - 250, 300);
		this.add(loginInput);
	}
	
	private void init() {
		this.setBackground(Color.BLACK);
		this.setLayout(null);
	}
	
}
