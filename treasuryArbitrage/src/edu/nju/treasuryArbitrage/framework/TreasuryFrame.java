package edu.nju.treasuryArbitrage.framework;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.nju.treasuryArbitrage.PersonalCenter.Login;
import edu.nju.treasuryArbitrage.PersonalCenter.LoginStateRecorder;
import edu.nju.treasuryArbitrage.PersonalCenter.UserInfo;
import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.futuresMarket.FuturesMarket;
import edu.nju.treasuryArbitrage.navigater.Navigater;
import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.resources.NumericalResources;
import edu.nju.treasuryArbitrage.resources.TextResources;

public class TreasuryFrame extends JFrame{
	private static final long serialVersionUID = 3299539057280891303L;
	
	private JPanel mainPage;
	
	public TreasuryFrame() {
		init();
	}

	public void setPage(JPanel panel) {
		this.remove(mainPage);
		mainPage = panel;
		this.add(mainPage, BorderLayout.CENTER);
		this.getContentPane().repaint();
		this.validate();
	}
	
	private void init() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle(TextResources.TITLE_CHS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(NumericalResources.SCREEN_WIDTH, NumericalResources.SCREEN_HEIGHT);
		this.setLocation(0, 0);
		this.setResizable(false);
		
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		factory.setFrame(this);
		
		LoginStateRecorder stateRecorder = new LoginStateRecorder();
		boolean isAutoLogin = stateRecorder.isAutoLogin();
		if (isAutoLogin) {
			UserInfo userInfo = stateRecorder.getRememberedUser();
			DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
			boolean isRememberedRight = dataInterface.loginValidate(userInfo.getUsername(), userInfo.getPassword());
			if (isRememberedRight) {
				enterMainPage();
				LoginedUser.setLoginedUser(userInfo.getUsername());
			} else {
				LoginStateRecorder recorder = new LoginStateRecorder();
				recorder.cancelAutoLogin();
				enterLogin();
			}
		} else {
			enterLogin();
		}
	}
	
	public void enterLogin() {
		this.getContentPane().removeAll();
		Login login = new Login();
		this.add(login);
		login.assemble(NumericalResources.SCREEN_WIDTH, NumericalResources.SCREEN_HEIGHT);
		this.getContentPane().repaint();
		this.validate();
	}
	
	public void enterMainPage() {
		this.getContentPane().removeAll();
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		Navigater navigater = factory.getNavigater();
		FuturesMarket market = factory.getFuturesMarket();
		mainPage = market;
		this.add(navigater, BorderLayout.NORTH);
		this.add(mainPage, BorderLayout.CENTER);
		this.getContentPane().repaint();
		this.validate();
	}
}
