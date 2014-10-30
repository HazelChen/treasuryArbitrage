package edu.nju.treasuryArbitrage.ui.personalCenter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.ui.common.LoginedUser;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;

public class LoginInputFlat extends LoginInput{
	private static final long serialVersionUID = -4223830916730143876L;
	private static final Font YAHEI_FONT = new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15);
	private static final Font TINY_YAHEI_FONT = new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13);
	/*package*/ static final Color BACKGROUND_COLOR = new Color(235,242,250);
	private static final Color FOREGROUND_COLOR = Color.BLACK;
	
	private RiskTipDialog dialog = new RiskTipDialog();
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JCheckBox readRiskWarning = new JCheckBox("“—‘ƒ∂¡∑Áœ’Ã· æ");
	private JButton okButton = new JButton("µ«¬º");
	
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
		Label userLabel = new Label("’À∫≈:");
		userLabel.setFont(YAHEI_FONT);
		userLabel.setForeground(FOREGROUND_COLOR);
		userLabel.setBounds(10, 15, 45, 25);
		userTextField.setBounds(60, 15, 105, 25);
		userTextField.setFont(YAHEI_FONT);
		this.add(userLabel);
		this.add(userTextField);
		
		Label passwordLabel = new Label("√‹¬Î:");
		passwordLabel.setFont(YAHEI_FONT);
		passwordLabel.setForeground(FOREGROUND_COLOR);
		passwordLabel.setBounds(170, 15, 45, 25);
		passwordField.setBounds(220, 15, 105, 25);
		passwordField.setFont(YAHEI_FONT);
		this.add(passwordLabel);
		this.add(passwordField);
		
		readRiskWarning.setForeground(FOREGROUND_COLOR);
		readRiskWarning.setBounds(330, 15, 120, 25);
		readRiskWarning.setFont(TINY_YAHEI_FONT);
		readRiskWarning.setBackground(BACKGROUND_COLOR);
		this.add(readRiskWarning);
		
		okButton.setBounds(455, 15, 60, 25);
		this.add(okButton);
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
					JOptionPane.showMessageDialog(null, "”√ªß√˚∫Õ√‹¬Î¥ÌŒÛ£°£°");
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
