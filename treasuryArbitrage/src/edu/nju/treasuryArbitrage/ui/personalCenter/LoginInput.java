package edu.nju.treasuryArbitrage.ui.personalCenter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.LoginedUser;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;

public class LoginInput extends JPanel{
	private static final long serialVersionUID = -4223830916730143876L;
	private static final Font YAHEI_FONT = new Font("΢���ź�", Font.PLAIN, 15);
	private static final Font TINY_YAHEI_FONT = new Font("΢���ź�", Font.PLAIN, 13);
	
	private RiskTipDialog dialog = new RiskTipDialog();
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JCheckBox rememberpwdCheckBox = new JCheckBox("��ס����");
	private JCheckBox autoLoginCheckBox = new JCheckBox("�Զ���¼");
	private JCheckBox readRiskWarning = new JCheckBox("���Ķ�������ʾ");
	private JButton okButton = new JButton("��¼");
	private Label forgetPasswordLabel = new Label("�������룿");
	
	public LoginInput(LoginFrame loginFrame) {
		init();
		initComponents();
		assemble();
		addListeners(loginFrame);
	}
	
	private void init() {
		this.setLayout(null);
		this.setBackground(Color.black);
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
		Label userLabel = new Label("�˺�:");
		userLabel.setFont(YAHEI_FONT);
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(90, 20, 45, 25);
		userTextField.setBounds(140, 20, 250, 25);
		userTextField.setFont(YAHEI_FONT);
		this.add(userLabel);
		this.add(userTextField);
		
		Label passwordLabel = new Label("����:");
		passwordLabel.setFont(YAHEI_FONT);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(90, 60, 45, 20);
		passwordField.setBounds(140, 57, 250, 25);
		passwordField.setFont(YAHEI_FONT);
		this.add(passwordLabel);
		this.add(passwordField);
		
		forgetPasswordLabel.setForeground(ColorConstants.LINK_BLUE);
		forgetPasswordLabel.setBounds(400, 60, 100, 20);
		forgetPasswordLabel.setFont(TINY_YAHEI_FONT);
		this.add(forgetPasswordLabel);
		
		rememberpwdCheckBox.setBounds(140, 100, 100, 20);
		rememberpwdCheckBox.setForeground(Color.WHITE);
		rememberpwdCheckBox.setSelected(true);
		rememberpwdCheckBox.setFont(TINY_YAHEI_FONT);
		rememberpwdCheckBox.setBackground(Color.BLACK);
		this.add(rememberpwdCheckBox);
		
		autoLoginCheckBox.setForeground(Color.WHITE);
		autoLoginCheckBox.setBounds(310, 100, 100, 20);
		autoLoginCheckBox.setFont(TINY_YAHEI_FONT);
		autoLoginCheckBox.setBackground(Color.BLACK);
		this.add(autoLoginCheckBox);
		
		readRiskWarning.setForeground(Color.WHITE);
		readRiskWarning.setBounds(140, 140, 150, 20);
		readRiskWarning.setFont(TINY_YAHEI_FONT);
		readRiskWarning.setBackground(Color.BLACK);
		this.add(readRiskWarning);
		
		okButton.setBounds(140, 180, 250, 25);
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
					JOptionPane.showMessageDialog(null, "�û�����������󣡣�");
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
		
		forgetPasswordLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				forgetPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "����ϵ�ͷ����� XXX 130XXXXXXX");
			}
		});
	}
	
	
}
