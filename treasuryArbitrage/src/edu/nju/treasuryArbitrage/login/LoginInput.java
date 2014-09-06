package edu.nju.treasuryArbitrage.login;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.framework.TreasuryFrame;
import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.resources.ColorResources;

public class LoginInput extends JPanel{
	private static final long serialVersionUID = -4223830916730143876L;
	
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton okButton = new JButton("登录");
	
	public LoginInput() {
		init();
		assemble();
		addListeners();
	}
	
	private void init() {
		this.setBackground(ColorResources.LOGIN_GRAY);
		this.setBorder(BorderFactory.createLineBorder(ColorResources.LOGIN_BORDER_GRAY));
		this.setLayout(null);
	}
	
	private void assemble() {
		Label userLabel = new Label("用户名:");
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(50, 50, 80, 20);
		userTextField.setBounds(150, 50, 200, 20);
		this.add(userLabel);
		this.add(userTextField);
		
		Label newUserLabel = new Label("新用户注册");
		newUserLabel.setForeground(ColorResources.LINK_BLUE);
		newUserLabel.setBounds(380, 50, 100, 20);
		this.add(newUserLabel);
		
		Label passwordLabel = new Label("密  码:");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(50, 100, 80, 20);
		passwordField.setBounds(150, 100, 200, 20);
		this.add(passwordLabel);
		this.add(passwordField);
		
		Label forgetPasswordLabel = new Label("忘记密码？");
		forgetPasswordLabel.setForeground(ColorResources.LINK_BLUE);
		forgetPasswordLabel.setBounds(380, 100, 100, 20);
		this.add(forgetPasswordLabel);
		
		okButton.setBounds(50, 200, 400, 25);
		this.add(okButton);
	}
	
	private void addListeners() {
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataInterfaceFactory factory = DataInterfaceFactory.getInstance();
				DataInterface dataInterface = factory.getDataInterface();
				
				String username = userTextField.getText();
				String password = new String(passwordField.getPassword());
				boolean isRight = dataInterface.loginValidate(username, password);
				
				if (isRight) {
					TreasuryFrame frame = MajorPartsFactory.getInstance().getFrame();
					frame.enterMainPage();
				} else {
					JOptionPane.showMessageDialog(null, "用户名和密码错误！！");
				}
			}
		});
	}
}
