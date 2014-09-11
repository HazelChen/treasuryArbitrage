package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PersonalInfo extends JPanel{

	private static final long serialVersionUID = 1971202110390831814L;
	
	private JLabel user;
	private JLabel userName;
	private JButton modifyPasswd;
	private JButton settingButton;
	
	private JLabel stateJLabel;
	private JButton stateButton;
	
	public PersonalInfo(){
		user = new JLabel("用户名",JLabel.CENTER);
		userName = new JLabel("12345",JLabel.CENTER);
		modifyPasswd=new JButton("修改密码");
		settingButton = new JButton("设置参数");
		setLayout(null);
		setBackground(Color.BLACK);
		setSize(400, 400);
		add(user);
		add(userName);
		add(modifyPasswd);
		add(settingButton);
		
		user.setBounds(50, 30, 148, 35);
		userName.setBounds(202, 30, 148,35);
		user.setBackground(Color.GRAY);
		user.setForeground(Color.WHITE);
		user.setFont(new  Font("Dialog",1,15));
		user.setOpaque(true);
		userName.setBackground(Color.GRAY);
		userName.setForeground(Color.WHITE);
		userName.setFont(new  Font("Dialog",1,15));
		userName.setOpaque(true);
		modifyPasswd.setBounds(50, 250, 300, 30);
		settingButton.setBounds(50, 300, 300, 30);
		
		
		stateJLabel= new JLabel("当前为自动登录状态",JLabel.CENTER);
		stateJLabel.setBackground(Color.GRAY);
		stateJLabel.setForeground(Color.WHITE);
		stateJLabel.setFont(new  Font("Dialog",1,10));
		stateJLabel.setOpaque(true);
		stateButton = new JButton("取消自动登录");
		add(stateButton);
		add(stateJLabel);
		stateJLabel.setBounds(50, 150, 148, 35);
		stateButton.setBounds(202,150,148,35);
		stateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(stateButton.getText().equalsIgnoreCase("取消自动登录")){
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
				removeAll();
				JPanel modifyPasswdJPanel = new ModifyPasswd();
				add(modifyPasswdJPanel);
				modifyPasswdJPanel.setBounds(0, 0, 400, 400);
				repaint();
			}
		});
		settingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame settingStopParameter = new SettingStopParameters();
				settingStopParameter.setVisible(true);
			}
		});
	}
	/*public static void main(String[] args){
		JFrame jFrame = new JFrame();
		JPanel jPanel = new PersonalInfo();
		jFrame.add(jPanel);
		jFrame.setSize(400,400);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

}
