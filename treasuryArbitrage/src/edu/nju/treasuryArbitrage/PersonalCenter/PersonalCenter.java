package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PersonalCenter extends JPanel{
	private static final long serialVersionUID = -7820319557370987327L;
	
	public PersonalCenter() {
		this.setBackground(Color.PINK);
		init();
	}
	
	private JPanel messageCenter;
	private JPanel personalInfo;
	private JPanel historyRecord;
	
	
	private void init(){
		setLayout(null);
		setSize(980, 800);
		messageCenter = new MessageCenter();
		personalInfo=new PersonalInfo();
		historyRecord=new HistoryRecord();
		add(messageCenter);
		add(personalInfo);
		add(historyRecord);
		messageCenter.setBounds(0, 0, 580, 400);
		personalInfo.setBounds(580, 0, 400, 400);
		historyRecord.setBounds(0, 400, 980, 400);
	}
	
	public static void main(String[] args){
		JFrame jFrame = new JFrame();
		JPanel jPanel = new PersonalCenter();
		jFrame.add(jPanel);
		jFrame.setSize(980,800);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
