package edu.nju.treasuryArbitrage;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import edu.nju.treasuryArbitrage.logic.liveUpdate.AnalyseThread;
import edu.nju.treasuryArbitrage.logic.liveUpdate.UpdateThread;
import edu.nju.treasuryArbitrage.ui.common.TreasuryFrame;
import edu.nju.treasuryArbitrage.ui.personalCenter.LoginFrame;

public class TreasuryArbitrage {
	
	public static void main(String[] args) {
		TreasuryArbitrage treasuryArbitrage = new TreasuryArbitrage();
		treasuryArbitrage.launch();
	}
	
	public void launch() {
//		useLookAndFeel();	
		startDataFetch();
		
		/*LoginStateRecorder stateRecorder = new LoginStateRecorder();
		boolean isAutoLogin = stateRecorder.isAutoLogin();
		if (isAutoLogin) {
			UserInfo userInfo = stateRecorder.getRememberedUser();
			DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
			boolean isRememberedRight = dataInterface.loginValidate(userInfo.getUsername(), userInfo.getPassword());
			if (isRememberedRight) {
				LoginedUser.setLoginedUser(userInfo.getUsername());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				launchMainSoftware();
			} else {
				LoginStateRecorder recorder = new LoginStateRecorder();
				recorder.cancelAutoLogin();
				launchLoginPage();
			}
		} else {*/
			launchLoginPage();
	//	}
	}
	
	private void startDataFetch() {
		Thread thread = new Thread(UpdateThread.getInstance());
		thread.start();

	}

	private void launchLoginPage() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		
	}
	
	private void launchMainSoftware() {
		Thread thread = new Thread(UpdateThread.getInstance());
		Thread thread2 = new Thread(new AnalyseThread());
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
		
		TreasuryFrame frame = new TreasuryFrame();
		frame.setVisible(true);
	}

	private void useLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/*Toolkit.getDefaultToolkit().setDynamicLayout(true);
		System.setProperty("sun.awt.noerasebackground", "true");
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
	}
}


