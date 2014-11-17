package edu.nju.treasuryArbitrage.ui.navigater;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;

public class SettingStopParameters extends JDialog{
	private static final long serialVersionUID = -7449799153447097837L;
	
	private JTextField stopProfitJTextField  = new JTextField();
	private JTextField stopLossJTextField = new JTextField();
	
	private JButton confirmation =new JButton("确认");
	private JButton cancelButton = new JButton("取消");
	
	public SettingStopParameters(){
		init();
		assemble();
		commonentInit();
		addListeners();
	}
	
	private void commonentInit() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		double pro = dataInterface.getPara_PROF();
		if (pro != 0) {
			stopProfitJTextField.setText(pro + "");
			stopLossJTextField.setText(dataInterface.getPara_LOSS() + "");
		} else {
			stopProfitJTextField.setText("10");
			stopLossJTextField.setText("-10");
		}
	}

	private void addListeners() {
		confirmation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
				String proString = stopProfitJTextField.getText();
				String lossString = stopLossJTextField.getText();
				
				if (proString.equals("") || lossString.equals("")) {
					SettingStopParameters.this.setVisible(false);
				} else {
					double pro = Double.parseDouble(proString);
					double loss = Double.parseDouble(lossString);
					dataInterface.setPara(pro, loss, 0);
					SettingStopParameters.this.setVisible(false);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingStopParameters.this.setVisible(false);
			}
		});
	}

	private void init() {
		this.setTitle("模型参数设置");
		this.setBackground(Navigater.BACKGROUND_COLOR);
		this.getContentPane().setBackground(Navigater.BACKGROUND_COLOR);
		this.setMaximumSize(new Dimension(450, 350));
		this.setMinimumSize(new Dimension(450, 350));
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	}
	
	private void assemble() {
		JLabel title = new JLabel("模型参数设置");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		title.setBounds(150, 20, 200, 50);
		add(title);
		
		JLabel stopProfitLaJLabel = new JLabel("止盈点");
		stopProfitLaJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		stopProfitLaJLabel.setBounds(50, 100, 150, 40);
		stopProfitJTextField.setBounds(150,100, 200, 40);
		JLabel stopPercentJLabel = new JLabel("%");
		stopPercentJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		stopPercentJLabel.setBounds(360, 100, 50, 40);
		add(stopProfitLaJLabel);
		add(stopProfitJTextField);
		add(stopPercentJLabel);
		
		JLabel stopLossJlJLabel = new JLabel("止损点");
		stopLossJlJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		stopLossJlJLabel.setBounds(50, 150, 150, 40);
		stopLossJTextField.setBounds(150, 150, 200, 40);
		JLabel percentJLabel2 = new JLabel("%");
		percentJLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		percentJLabel2.setBounds(360, 150, 50, 40);
		add(stopLossJlJLabel);
		add(stopLossJTextField);
		add(percentJLabel2);
		
		confirmation.setBounds(50,230,120,30);
		cancelButton.setBounds(280,230,120,30);
		add(confirmation);
		add(cancelButton);
		
	}

}
