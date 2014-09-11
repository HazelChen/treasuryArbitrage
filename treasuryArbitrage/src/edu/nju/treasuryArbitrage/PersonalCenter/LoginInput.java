package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.framework.LoginedUser;
import edu.nju.treasuryArbitrage.framework.TreasuryFrame;
import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.resources.ColorResources;

public class LoginInput extends JPanel{
	private static final long serialVersionUID = -4223830916730143876L;
	
	private RiskTipDialog dialog = new RiskTipDialog();
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JCheckBox rememberpwdCheckBox = new JCheckBox("记住密码");
	private JCheckBox autoLoginCheckBox = new JCheckBox("自动登录");
	private JCheckBox readRiskWarning = new JCheckBox("已阅读风险提示");
	private JButton okButton = new JButton("登录");
	
	public LoginInput() {
		init();
		initComponents();
		assemble();
		addListeners();
	}
	
	private void init() {
		this.setBackground(ColorResources.LOGIN_GRAY);
		this.setBorder(BorderFactory.createLineBorder(ColorResources.LOGIN_BORDER_GRAY));
		this.setLayout(null);
	}
	
	private void initComponents() {
		LoginStateRecorder stateRecorder = new LoginStateRecorder();
		UserInfo userInfo = stateRecorder.getRememberedUser();
		userTextField.setText(userInfo.username);
		passwordField.setText(userInfo.password);
		
		if (userInfo.username.equals("")) {
			readRiskWarning.setSelected(false);
			okButton.setEnabled(false);
		} else {
			readRiskWarning.setSelected(true);
			okButton.setEnabled(true);
		}
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
		
		rememberpwdCheckBox.setBounds(70, 150, 80, 20);
		rememberpwdCheckBox.setBackground(ColorResources.LOGIN_GRAY);
		rememberpwdCheckBox.setForeground(Color.WHITE);
		rememberpwdCheckBox.setSelected(true);
		this.add(rememberpwdCheckBox);
		
		autoLoginCheckBox.setBackground(ColorResources.LOGIN_GRAY);
		autoLoginCheckBox.setForeground(Color.WHITE);
		autoLoginCheckBox.setBounds(200, 150, 80, 20);
		this.add(autoLoginCheckBox);
		
		readRiskWarning.setBackground(ColorResources.LOGIN_GRAY);
		readRiskWarning.setForeground(Color.WHITE);
		readRiskWarning.setBounds(70, 200, 150, 20);
		this.add(readRiskWarning);
		
		okButton.setBounds(50, 250, 400, 25);
		this.add(okButton);
	}
	
	private void addListeners() {
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String password = new String(passwordField.getPassword());
				
				DataInterfaceFactory factory = DataInterfaceFactory.getInstance();
				DataInterface dataInterface = factory.getDataInterface();
				boolean isRight = dataInterface.loginValidate(username, password);
				
				if (isRight) {
					LoginStateRecorder stateRecorder = new LoginStateRecorder();
					if (rememberpwdCheckBox.isSelected()) {
						stateRecorder.rememberPwd(username, password);
					} else {
						stateRecorder.cancelRemember();
					}
					
					if (autoLoginCheckBox.isSelected()) {
						stateRecorder.autoLogin(username, password);
					} else {
						stateRecorder.cancelAutoLogin();
					}
					
					LoginedUser.setLoginedUser(username);
					
					TreasuryFrame frame = MajorPartsFactory.getInstance().getFrame();
					frame.enterMainPage();
				} else {
					JOptionPane.showMessageDialog(null, "用户名和密码错误！！");
				}
			}
		});
		
		readRiskWarning.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (readRiskWarning.isSelected()) {
					
					dialog.setVisible(true);
					okButton.setEnabled(true);
				} else {
					okButton.setEnabled(false);
				}
			}
		});
	}
	
	
}
