package edu.nju.treasuryArbitrage.ui.personalCenter;

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
import edu.nju.treasuryArbitrage.ui.common.ColorConstants;
import edu.nju.treasuryArbitrage.ui.common.ScreenSize;

public class SettingStopParameters extends JDialog{
	private static final long serialVersionUID = -7449799153447097837L;
	
	private JTextField stopProfitJTextField  = new JTextField();
	private JTextField stopLossJTextField = new JTextField();
	private JTextField maxInvestmentJTextField = new JTextField();
	
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
			maxInvestmentJTextField.setText(dataInterface.getPara_GUAR() + "");
		}
	}

	private void addListeners() {
		confirmation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
				String proString = stopProfitJTextField.getText();
				String lossString = stopLossJTextField.getText();
				String maxString = maxInvestmentJTextField.getText();
				
				if (proString.equals("") || lossString.equals("") || maxString.equals("")) {
					SettingStopParameters.this.setVisible(false);
				} else {
					double pro = Double.parseDouble(proString);
					double loss = Double.parseDouble(lossString);
					double max = Double.parseDouble(maxString);
					dataInterface.setPara(pro, loss, max);
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
		this.setUndecorated(true);
		this.setBackground(ColorConstants.LOGIN_BORDER_GRAY);
		this.setMaximumSize(new Dimension(500, 400));
		this.setMinimumSize(new Dimension(500, 400));
		this.setResizable(false);
		this.setModal(true);
		this.setLocation(
				(ScreenSize.WIDTH - this.getWidth()) / 2,
				(ScreenSize.HEIGHT - this.getHeight()) / 2);
		this.setLayout(null);
	}
	
	private void assemble() {
		JLabel title = new JLabel("模型参数设置");;
		JLabel stopProfitLaJLabel = new JLabel("止盈点");
		JLabel stopLossJlJLabel = new JLabel("止损点");
		JLabel maxInvestmentJLabel = new JLabel("最大保证金投入");//最大保证金投入
		
		add(stopProfitLaJLabel);
		add(stopLossJlJLabel);
		add(maxInvestmentJLabel);
		add(stopProfitJTextField);
		add(stopLossJTextField);
		add(maxInvestmentJTextField);
		add(title);
		add(confirmation);
		add(cancelButton);
		
		stopProfitLaJLabel.setBounds(80, 100, 150, 40);
		stopLossJlJLabel.setBounds(80, 150, 150, 40);
		maxInvestmentJLabel.setBounds(80, 200, 150, 40);
		title.setFont(new  Font("Dialog",1,30));
		title.setBounds(150, 20, 200, 50);
		confirmation.setBounds(80,300,150,30);
		cancelButton.setBounds(280,300,150,30);
		stopProfitJTextField.setBounds(280,100, 150, 40);
		stopLossJTextField.setBounds(280, 150, 150, 40);
		maxInvestmentJTextField.setBounds(280, 200, 150, 40);
	}

}
