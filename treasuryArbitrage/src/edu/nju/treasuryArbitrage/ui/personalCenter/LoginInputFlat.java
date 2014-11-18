package edu.nju.treasuryArbitrage.ui.personalCenter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.ui.common.LoginedUser;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.ui.navigater.SettingStopParameters;

public class LoginInputFlat extends LoginInput{
	private static final long serialVersionUID = -4223830916730143876L;
	private static final Font YAHEI_FONT = new Font("微软雅黑", Font.PLAIN, 15);
//	private static final Font TINY_YAHEI_FONT = new Font("微软雅黑", Font.PLAIN, 13);
	/*package*/ static final Color BACKGROUND_COLOR = new Color(235,242,250);
	private static final Color FOREGROUND_COLOR = Color.BLACK;
	
	private RiskTipDialog dialog = new RiskTipDialog();
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JCheckBox readRiskWarning = new JCheckBox("已阅读风险提示");
	private JButton okButton = new JButton("登录");
	private JRadioButton simulationRadioButton = new JRadioButton("模拟交易");
	private JRadioButton realRadioButton = new JRadioButton("真实交易");
	private JLabel userLabel = new JLabel("账  号:");
	
	public LoginInputFlat(LoginFrame loginFrame) {
		init();
		initComponents();
		assemble();
		addListeners(loginFrame);
	}
	
	private void init() {
		this.setLayout(null);
		this.setBackground(BACKGROUND_COLOR);
	}
	
	private void initComponents() {
		LoginStateRecorder stateRecorder = new LoginStateRecorder();
		String userInfo = stateRecorder.getRememberedUser().trim();
		userTextField.setText(userInfo);
		
		if (userInfo.equals("")) {
			readRiskWarning.setSelected(false);
			okButton.setEnabled(false);
		} else {
			readRiskWarning.setSelected(true);
			okButton.setEnabled(true);
		}
	}
	
	private void assemble() {
		userLabel.setFont(YAHEI_FONT);
		userLabel.setForeground(FOREGROUND_COLOR);
		userLabel.setBounds(10, 50, 100, 25);
		userTextField.setBounds(115, 50, 135, 25);
		userTextField.setFont(YAHEI_FONT);
		this.add(userLabel);
		this.add(userTextField);
		
		Label passwordLabel = new Label("密码:");
		passwordLabel.setFont(YAHEI_FONT);
		passwordLabel.setForeground(FOREGROUND_COLOR);
		passwordLabel.setBounds(265, 50, 40, 25);
		passwordField.setBounds(310, 50, 135, 25);
		passwordField.setFont(YAHEI_FONT);
		this.add(passwordLabel);
		this.add(passwordField);
		
		readRiskWarning.setForeground(FOREGROUND_COLOR);
		readRiskWarning.setBounds(400, 15, 150, 25);
		readRiskWarning.setFont(YAHEI_FONT);
		readRiskWarning.setBackground(BACKGROUND_COLOR);
		this.add(readRiskWarning);
		
		okButton.setBounds(465, 50, 60, 25);
		this.add(okButton);
		
		simulationRadioButton.setFont(YAHEI_FONT);
		realRadioButton.setFont(YAHEI_FONT);
		simulationRadioButton.setOpaque(false);
		realRadioButton.setOpaque(false);
		simulationRadioButton.setSelected(true);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(simulationRadioButton);
		buttonGroup.add(realRadioButton);
		
		simulationRadioButton.setBounds(10, 15, 90, 25);
		realRadioButton.setBounds(110, 15, 90, 25);
		this.add(simulationRadioButton);
		this.add(realRadioButton);
	}
	
	private void addListeners(final LoginFrame loginFrame) {
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String password = new String(passwordField.getPassword());
				
				DataInterfaceFactory factory = DataInterfaceFactory.getInstance();
				DataInterface dataInterface = factory.getDataInterfaceToServer();
				boolean isRight = dataInterface.loginValidate(username, password);
				
				if (isRight) {
					LoginStateRecorder stateRecorder = new LoginStateRecorder();
					stateRecorder.rememberUsername(username);
					
					LoginedUser.setLoginedUser(username);
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					TreasuryFrame frame = new TreasuryFrame();
					loginFrame.setVisible(false);
					frame.setVisible(true);
					loginFrame.dispose();
					
					double para = dataInterface.getPara_PROF(); 
					if (para == 0) {
						SettingStopParameters settingStopParameters = MajorPartsFactory.getInstance().getSettingStopParameters();
						settingStopParameters.setVisible(true);
					}
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
		
		simulationRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userLabel.setText("账  号:");
				DataInterfaceFactory.getInstance().setIsSimulate(true);
			}
		});
		
		realRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userLabel.setText("<html>" +
						"<font color=red><b>CTP </b></font>" +
						"弘业期货:</html>");
				DataInterfaceFactory.getInstance().setIsSimulate(false);
			}
		});
		
		
	}
}
