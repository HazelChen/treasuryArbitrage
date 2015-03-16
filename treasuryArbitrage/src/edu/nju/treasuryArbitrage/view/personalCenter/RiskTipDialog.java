package edu.nju.treasuryArbitrage.view.personalCenter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.nju.treasuryArbitrage.controller.fileIO.FileOperater;
import edu.nju.treasuryArbitrage.view.common.ScreenSize;

public class RiskTipDialog extends JDialog {
	private static final long serialVersionUID = 5893692668956428617L;

	private JTextArea riskDetail = new JTextArea(25, 62);
	private JButton closebtn = new JButton("�رմ���");

	/*package*/ RiskTipDialog () {
		init();
		initComponents();
		assemble();
		addListeners();
	}
	
	private void addListeners() {
		closebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RiskTipDialog.this.setVisible(false);
				RiskTipDialog.this.dispose();
			}
		});
	}

	private void initComponents() {
		FileOperater fileOperater = new FileOperater();
		String riskTipString = fileOperater.read("riskTip");
		riskDetail.setText(riskTipString);
	}

	private void init() {
//		this.setUndecorated(true);
		this.setTitle("������ʾ");
		this.setBackground(LoginInputFlat.BACKGROUND_COLOR);
		this.setMaximumSize(new Dimension(720, 510));
		this.setMinimumSize(new Dimension(750, 510));
		this.setResizable(false);
		this.setModal(true);
		this.setLocation(
				(ScreenSize.WIDTH - this.getWidth()) / 2,
				(ScreenSize.HEIGHT - this.getHeight()) / 2);
	}
	
	private void assemble() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("    ������ʾ");
		titleLabel.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(titleLabel, BorderLayout.WEST);
		titlePanel.setBackground(LoginInputFlat.BACKGROUND_COLOR);
		contentPanel.add(titlePanel, BorderLayout.NORTH);
		contentPanel.setBackground(LoginInputFlat.BACKGROUND_COLOR);
		
		riskDetail.setEditable(false);
		riskDetail.setLineWrap(true);
		JPanel centerPanel = new JPanel();
		centerPanel.add(riskDetail);
		centerPanel.setBackground(LoginInputFlat.BACKGROUND_COLOR);
		contentPanel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(closebtn);
		bottomPanel.setBackground(LoginInputFlat.BACKGROUND_COLOR);
		contentPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		this.add(contentPanel);
		this.pack();
	}

}
