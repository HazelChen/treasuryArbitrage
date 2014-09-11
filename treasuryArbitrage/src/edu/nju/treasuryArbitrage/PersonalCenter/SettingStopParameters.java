package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingStopParameters extends JFrame{

	private static final long serialVersionUID = -7449799153447097837L;
	
	private JLabel title ;
	private JLabel stopProfitLaJLabel ;
	private JLabel stopLossJlJLabel ;
	private JLabel maxInvestmentJLabel ;//最大保证金投入
	
	private JTextField stopProfitJTextField;
	private JTextField stopLossJTextField;
	private JTextField maxInvestmentJTextField;
	
	private JButton confirmation;
	
	public SettingStopParameters(){
		title = new JLabel("模型参数设置");
		stopProfitLaJLabel = new JLabel("止盈点");
		stopLossJlJLabel = new JLabel("止损点");
		maxInvestmentJLabel = new JLabel("最大保证金投入");
		stopProfitJTextField = new JTextField();
		stopLossJTextField = new JTextField();
		maxInvestmentJTextField = new JTextField();
		confirmation=new JButton("确认");
		setLayout(null);
		
		add(stopProfitLaJLabel);
		add(stopLossJlJLabel);
		add(maxInvestmentJLabel);
		add(stopProfitJTextField);
		add(stopLossJTextField);
		add(maxInvestmentJTextField);
		add(title);
		add(confirmation);
		
		setSize(600,400);
		
		
		title.setFont(new  Font("Dialog",1,30));
		title.setBounds(200, 20, 200, 50);
		confirmation.setBounds(200,300,200,50);
		
		stopProfitLaJLabel.setBounds(100, 100, 150, 40);
		stopLossJlJLabel.setBounds(100, 150, 150, 40);
		maxInvestmentJLabel.setBounds(100, 200, 150, 40);
		
		stopProfitJTextField.setBounds(300,100, 150, 40);
		stopLossJTextField.setBounds(300, 150, 150, 40);
		maxInvestmentJTextField.setBounds(300, 200, 150, 40);
		
	}
	/*public static void main(String[] args){
		JFrame jFrame = new SettingStopParameters();
		//jFrame.add(jPanel);
		jFrame.setSize(600,400);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

}
