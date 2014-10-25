package edu.nju.treasuryArbitrage;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.liveUpdate.UpdateThread;
import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.ui.common.LoginedUser;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.ui.personalCenter.LoginFrame;
import edu.nju.treasuryArbitrage.ui.personalCenter.LoginStateRecorder;
import edu.nju.treasuryArbitrage.ui.personalCenter.UserInfo;

public class TreasuryArbitrage {
	
	public static void main(String[] args) {
		TreasuryArbitrage treasuryArbitrage = new TreasuryArbitrage();
		treasuryArbitrage.launch();
	}
	
	public void launch() {
		useLookAndFeel();
		startDataFetch();
		
		LoginStateRecorder stateRecorder = new LoginStateRecorder();
		boolean isAutoLogin = stateRecorder.isAutoLogin();
		if (isAutoLogin) {
			UserInfo userInfo = stateRecorder.getRememberedUser();
			DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
			boolean isRememberedRight = dataInterface.loginValidate(userInfo.getUsername(), userInfo.getPassword());
			if (isRememberedRight) {
				launchMainSoftware();
				LoginedUser.setLoginedUser(userInfo.getUsername());
			} else {
				LoginStateRecorder recorder = new LoginStateRecorder();
				recorder.cancelAutoLogin();
				launchLoginPage();
			}
		} else {
			launchLoginPage();
		}
	}
	
	private void startDataFetch() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread thread = new Thread(new UpdateThread());
		thread.start();
	}

	private void launchLoginPage() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}
	
	private void launchMainSoftware() {
		Thread thread = new Thread(new UpdateThread());
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TreasuryFrame frame = new TreasuryFrame();
		frame.setVisible(true);
	}

	private void useLookAndFeel() {
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		System.setProperty("sun.awt.noerasebackground", "true");
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
