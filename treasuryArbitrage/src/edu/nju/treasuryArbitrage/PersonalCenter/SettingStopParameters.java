package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.resources.ColorResources;
import edu.nju.treasuryArbitrage.resources.NumericalResources;

public class SettingStopParameters extends JDialog{
	private static final long serialVersionUID = -7449799153447097837L;
	
	private JTextField stopProfitJTextField  = new JTextField();
	private JTextField stopLossJTextField = new JTextField();
	private JTextField maxInvestmentJTextField = new JTextField();
	
	private JButton confirmation =new JButton("ȷ��");
	
	public SettingStopParameters(){
		init();
		assemble();
	}
	
	private void init() {
		this.setUndecorated(true);
		this.setBackground(ColorResources.LOGIN_BORDER_GRAY);
		this.setMaximumSize(new Dimension(600, 400));
		this.setMinimumSize(new Dimension(600, 400));
		this.setResizable(false);
		this.setModal(true);
		this.setLocation(
				(NumericalResources.SCREEN_WIDTH - this.getWidth()) / 2,
				(NumericalResources.SCREEN_HEIGHT - this.getHeight()) / 2);
		this.setLayout(null);
	}
	
	private void assemble() {
		JLabel title = new JLabel("ģ�Ͳ�������");;
		JLabel stopProfitLaJLabel = new JLabel("ֹӯ��");
		JLabel stopLossJlJLabel = new JLabel("ֹ���");
		JLabel maxInvestmentJLabel = new JLabel("���֤��Ͷ��");//���֤��Ͷ��
		
		add(stopProfitLaJLabel);
		add(stopLossJlJLabel);
		add(maxInvestmentJLabel);
		add(stopProfitJTextField);
		add(stopLossJTextField);
		add(maxInvestmentJTextField);
		add(title);
		add(confirmation);
		
		stopProfitLaJLabel.setBounds(100, 100, 150, 40);
		stopLossJlJLabel.setBounds(100, 150, 150, 40);
		maxInvestmentJLabel.setBounds(100, 200, 150, 40);
		title.setFont(new  Font("Dialog",1,30));
		title.setBounds(200, 20, 200, 50);
		confirmation.setBounds(200,300,200,50);
		stopProfitJTextField.setBounds(300,100, 150, 40);
		stopLossJTextField.setBounds(300, 150, 150, 40);
		maxInvestmentJTextField.setBounds(300, 200, 150, 40);
		
	}

}
