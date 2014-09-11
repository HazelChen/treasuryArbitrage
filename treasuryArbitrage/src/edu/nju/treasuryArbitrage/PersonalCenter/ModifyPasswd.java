package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifyPasswd extends JPanel{

	private static final long serialVersionUID = 5897285381979442998L;
	private JLabel oldPasswdJLabel ;
	private JLabel newPasswdJLabel ;
	private JLabel confirmJLabel ;
	
	private JTextField oldPasswdJTextField;
	private JTextField newPasswdJTextField;
	private JTextField confirmJTextField;
	
	private JButton confirmation;
	private JButton cancel;
	
	public ModifyPasswd(){
		oldPasswdJLabel=new JLabel("请输入原密码",JLabel.CENTER);
		oldPasswdJLabel.setForeground(Color.WHITE);
		newPasswdJLabel=new JLabel("请输入新密码",JLabel.CENTER);
		newPasswdJLabel.setForeground(Color.WHITE);
		confirmJLabel=new JLabel("请确认新密码",JLabel.CENTER);
		confirmJLabel.setForeground(Color.WHITE);
		oldPasswdJTextField = new JTextField();
		newPasswdJTextField = new JTextField();
		confirmJTextField= new JTextField();
		confirmation = new JButton("确认");
		cancel = new JButton("取消");
		
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(400, 400));
		add(confirmJLabel);
		add(confirmJTextField);
		add(confirmation);
		add(cancel);
		add(newPasswdJLabel);
		add(newPasswdJTextField);
		add(oldPasswdJLabel);
		add(oldPasswdJTextField);
		
		oldPasswdJLabel.setBounds(50, 30, 100, 35);
		newPasswdJLabel.setBounds(50, 80, 100, 35);
		confirmJLabel.setBounds(50, 130, 100, 35);
		
		oldPasswdJTextField.setBounds(200, 30, 100, 35);
		newPasswdJTextField.setBounds(200, 80, 100, 35);
		confirmJTextField.setBounds(200, 130, 100, 35);
		
		confirmation.setBounds(105, 200, 90, 40);
		cancel.setBounds(205, 200, 90, 40);
		
	}

}
