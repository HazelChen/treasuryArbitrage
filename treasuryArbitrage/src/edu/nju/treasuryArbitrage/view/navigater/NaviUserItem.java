package edu.nju.treasuryArbitrage.view.navigater;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.view.common.ViewFactory;
import edu.nju.treasuryArbitrage.view.common.ColorConstants;
import edu.nju.treasuryArbitrage.view.common.LoginedUser;
import edu.nju.treasuryArbitrage.view.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.view.personalCenter.LoginFrame;

public class NaviUserItem extends JPanel{
	private static final long serialVersionUID = -1940967418792821751L;
	
	private JLabel userMessageLabel;
	private JLabel setModelButton = new JLabel("��������");
	private JLabel loggoutLabel = new JLabel("��ȫ�˳�");
	
	/*package*/ NaviUserItem() {
		this.setBackground(NavigationBar.BACKGROUND_COLOR);
		assemble();
		addListeners();
	}
	
	private void addListeners() {
		setModelButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				SettingStopParameters settingStopParameters = new SettingStopParameters();
				settingStopParameters.setVisible(true);
			}
		});
		
		loggoutLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginedUser.setLoginedUser(null);
				TreasuryFrame frame = ViewFactory.getInstance().getFrame();
				LoginFrame loginFrame = new LoginFrame();
				frame.setVisible(false);
				loginFrame.setVisible(true);
				frame.dispose();
				ViewFactory.getInstance().setFrame(null);
			}
		});
	}

	private void assemble() {
		String userMessage = "���," + LoginedUser.getLoginedUser();
		userMessageLabel = new JLabel(userMessage);
		userMessageLabel.setForeground(NavigationBar.FOREGROUND_COLOR);
		userMessageLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		this.add(userMessageLabel);
		
		setModelButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		setModelButton.setForeground(ColorConstants.LINK_BLUE);
		this.add(setModelButton);
		
		JLabel gapLabel = new JLabel("��");
		gapLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));
		gapLabel.setForeground(ColorConstants.LINK_BLUE);
		this.add(gapLabel);
		
		loggoutLabel.setForeground(ColorConstants.LINK_BLUE);
		loggoutLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		this.add(loggoutLabel);
	}

	public void setUserName(String username) {
		userMessageLabel.setText("���," + username);
	}

}
