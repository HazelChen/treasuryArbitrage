package edu.nju.treasuryArbitrage.PersonalCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.framework.ComponentPanel;

public class PersonalCenter extends JPanel implements ComponentPanel{
	private static final long serialVersionUID = -7820319557370987327L;
	
	private int w;
	private int h;
	
	public PersonalCenter() {
		this.setBackground(Color.BLACK);
		init();
	}
	private JLabel mJLabel;
	private JLabel pJLabel;
	private JLabel hJLabel;
	
	private JPanel messageCenter;
	private JPanel personalInfo;
	private JPanel historyRecord;
	
	
	private void init(){
		setLayout(null);
		setPreferredSize(new Dimension(1200,800));
		mJLabel = new JLabel("消息中心",JLabel.CENTER);
		pJLabel = new JLabel("设置",JLabel.CENTER);
		hJLabel = new JLabel("历史交易记录",JLabel.CENTER);
		mJLabel.setFont(new Font("微软雅黑",1,15));
		mJLabel.setForeground(Color.WHITE);
		mJLabel.setBackground(Color.RED);
		mJLabel.setOpaque(true);
		pJLabel.setFont(new Font("微软雅黑",1,15));
		pJLabel.setForeground(Color.WHITE);
		pJLabel.setBackground(Color.RED);
		pJLabel.setOpaque(true);
		hJLabel.setFont(new Font("微软雅黑",1,15));
		hJLabel.setForeground(Color.WHITE);
		hJLabel.setBackground(Color.RED);
		hJLabel.setOpaque(true);
	}
	
	public void assemble(){
		messageCenter = new MessageCenter();
		personalInfo=new PersonalInfo();
		historyRecord=new HistoryRecord();
		add(messageCenter);
		add(personalInfo);
		add(historyRecord);
		messageCenter.setBounds(0, 0, (int) (getWidth()*0.6), (int) (getHeight()*0.5));
		personalInfo.setBounds((int) (getWidth()*0.6), 0, (int) (getWidth()*0.4), (int) (getHeight()*0.5));
		historyRecord.setBounds(0, (int) (getHeight()*0.5), getWidth(), (int) (getHeight()*0.5));
	}
	public void assemble(int width,int height){
		
		w = width;
		h = height;
		
		messageCenter = new MessageCenter((int)(width*0.6), (int)(height*0.5));
		personalInfo=new PersonalInfo((int) (width*0.4), (int) (height*0.5));
		historyRecord=new HistoryRecord( width, (int) (height*0.5));
		add(messageCenter);
		add(personalInfo);
		add(historyRecord);
		add(mJLabel);
		add(pJLabel);
		add(hJLabel);
		mJLabel.setBounds(0, 0, 100, 25);
		messageCenter.setBounds(0, 25, (int)(width*0.6), (int)(height*0.5)-25);
		pJLabel.setBounds((int) (width*0.6), 0, 100, 25);
		personalInfo.setBounds((int) (width*0.6), 25, (int) (width*0.4), (int) (height*0.5)-25);
		hJLabel.setBounds(0, (int) (height*0.5), 100, 25);
		historyRecord.setBounds(0, (int) (height*0.5)+25, width, (int) (height*0.5)-25);
	}
	
	public static void main(String[] args){
		JFrame jFrame = new JFrame();
		JPanel jPanel = new PersonalCenter();
		((PersonalCenter) jPanel).assemble(1200,700);
		jFrame.add(jPanel);
		jFrame.setSize(1200,800);
		jFrame.setVisible(true);
		jFrame.repaint();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void updateRecord(){
		remove(historyRecord);
		historyRecord=new HistoryRecord( w, (int) (h*0.5));
		add(historyRecord);
		historyRecord.setBounds(0, (int) (h*0.5)+25, w, (int) (h*0.5)-25);
	}

	@Override
	public void updatePage() {
		// TODO Auto-generated method stub
		
	}

}
