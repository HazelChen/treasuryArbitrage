package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.framework.LoginedUser;
import edu.nju.treasuryArbitrage.network.DataInterface;

public class PersonalInfo extends JPanel{

	private static final long serialVersionUID = 1971202110390831814L;
	
	private JPanel mainJPanel;
	private JPanel modifyPasswdJPanel;
	private JLabel user;
	private JLabel userName;
	private JButton modifyPasswd;
	private JButton settingButton;
	private JLabel stateJLabel;
	private JButton stateButton;
	
	private JLabel oldPasswdJLabel ;
	private JLabel newPasswdJLabel ;
	private JLabel confirmJLabel ;
	private JPasswordField oldPasswdJTextField;
	private JPasswordField newPasswdJTextField;
	private JPasswordField confirmJTextField;
	private JButton confirmation;
	private JButton cancel;
	private DataInterface service = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
	
	private void initModifyPasswdJPanel(final int w,final int h){
		modifyPasswdJPanel = new JPanel();
		oldPasswdJLabel=new JLabel("请输入原密码",JLabel.CENTER);
		oldPasswdJLabel.setForeground(Color.WHITE);
		newPasswdJLabel=new JLabel("请输入新密码",JLabel.CENTER);
		newPasswdJLabel.setForeground(Color.WHITE);
		confirmJLabel=new JLabel("请确认新密码",JLabel.CENTER);
		confirmJLabel.setForeground(Color.WHITE);
		oldPasswdJTextField = new JPasswordField();
		newPasswdJTextField = new  JPasswordField();
		confirmJTextField= new  JPasswordField();
		confirmation = new JButton("确认");
		cancel = new JButton("取消");
		
		modifyPasswdJPanel.setLayout(null);
		modifyPasswdJPanel.setBackground(Color.BLACK);
		modifyPasswdJPanel.setPreferredSize(new Dimension(400, 400));
		modifyPasswdJPanel.add(confirmJLabel);
		modifyPasswdJPanel.add(confirmJTextField);
		modifyPasswdJPanel.add(confirmation);
		modifyPasswdJPanel.add(cancel);
		modifyPasswdJPanel.add(newPasswdJLabel);
		modifyPasswdJPanel.add(newPasswdJTextField);
		modifyPasswdJPanel.add(oldPasswdJLabel);
		modifyPasswdJPanel.add(oldPasswdJTextField);
		
		oldPasswdJLabel.setBounds(50*w/400, 30*h/400, 100*w/400, 35*h/400);
		newPasswdJLabel.setBounds(50*w/400, 80*h/400, 100*w/400, 35*h/400);
		confirmJLabel.setBounds(50*w/400, 130*h/400, 100*w/400, 35*h/400);
		
		oldPasswdJTextField.setBounds(200*w/400, 30*h/400, 100*w/400, 35*h/400);
		newPasswdJTextField.setBounds(200*w/400, 80*h/400, 100*w/400, 35*h/400);
		confirmJTextField.setBounds(200*w/400, 130*h/400, 100*w/400, 35*h/400);
		
		confirmation.setBounds(105*w/400, 200*h/400, 90*w/400, 40*h/400);
		cancel.setBounds(205*w/400, 200*h/400, 90*w/400, 40*h/400);
		
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(modifyPasswdJPanel);
				add(mainJPanel);
				mainJPanel.setBounds(0, 0, w, h);
				repaint();
			}
		});
		confirmation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				service.changePWD(LoginedUser.getLoginedUser(), oldPasswdJTextField.getPassword().toString(), newPasswdJTextField.getPassword().toString());
				remove(modifyPasswdJPanel);
				add(mainJPanel);
				mainJPanel.setBounds(0, 0, w, h);
				repaint();
			}
		});
		
	}
	
	public PersonalInfo(int w,int h){
		init(w,h);
		initModifyPasswdJPanel(w, h);
		repaint();
	}
	
	public PersonalInfo(){
		this(400, 400);
	}
	
	private void init(final int w,final int h){
		mainJPanel=new JPanel();
		setLayout(null);
		add(mainJPanel);
		mainJPanel.setBounds(0, 0, w, h);
		user = new JLabel("用户名",JLabel.CENTER);
		userName = new JLabel(LoginedUser.getLoginedUser(),JLabel.CENTER);
		modifyPasswd=new JButton("修改密码");
		settingButton = new JButton("设置参数");
		mainJPanel.setLayout(null);
		mainJPanel.setBackground(Color.BLACK);
		mainJPanel.setPreferredSize(new Dimension(400, 400));
		mainJPanel.add(user);
		mainJPanel.add(userName);
		mainJPanel.add(modifyPasswd);
		mainJPanel.add(settingButton);
		user.setBackground(Color.GRAY);
		user.setForeground(Color.WHITE);
		user.setFont(new  Font("Dialog",1,15));
		user.setOpaque(true);
		userName.setBackground(Color.GRAY);
		userName.setForeground(Color.WHITE);
		userName.setFont(new  Font("Dialog",1,15));
		userName.setOpaque(true);
		stateJLabel= new JLabel("当前为自动登录状态",JLabel.CENTER);
		stateJLabel.setBackground(Color.GRAY);
		stateJLabel.setForeground(Color.WHITE);
		stateJLabel.setFont(new  Font("Dialog",1,10));
		stateJLabel.setOpaque(true);
		stateButton = new JButton("取消自动登录");
		mainJPanel.add(stateButton);
		mainJPanel.add(stateJLabel);
		user.setBounds(w*50/400, h*30/400, w*148/400, h*35/400);
		userName.setBounds(w*202/400, h*30/400, w*148/400, h*35/400);
		modifyPasswd.setBounds(w*50/400, h*250/400, w*300/400, h*30/400);
		settingButton.setBounds(w*50/400, h*300/400, w*300/400, h*30/400);
		stateJLabel.setBounds(w*50/400, h*150/400, w*148/400, h*35/400);
		stateButton.setBounds(w*202/400, h*150/400, w*148/400, h*35/400);
		stateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(stateButton.getText().equalsIgnoreCase("取消自动登录")){
					new LoginStateRecorder().cancelAutoLogin();
					stateJLabel.setText("当前为正常登录状态");
					stateButton.setText("自动登录");
				}else {
					stateJLabel.setText("当前为自动登录状态");
					stateButton.setText("取消自动登录");
				}
				repaint();
			}
		});
		modifyPasswd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(mainJPanel);
				add(modifyPasswdJPanel);
				modifyPasswdJPanel.setBounds(0, 0, w, h);
				repaint();
			}
		});
		settingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame settingStopParameter = new SettingStopParameters();
				settingStopParameter.setSize(600, 400);
				settingStopParameter.setVisible(true);
			}
		});
	}
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		JPanel jPanel = new PersonalInfo(400, 400);
		jFrame.add(jPanel);
		jFrame.setSize(400, 400);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
