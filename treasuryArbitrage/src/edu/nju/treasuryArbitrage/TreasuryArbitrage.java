package edu.nju.treasuryArbitrage;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.logic.liveUpdate.AnalyseThread;
import edu.nju.treasuryArbitrage.logic.liveUpdate.LiveData;
import edu.nju.treasuryArbitrage.logic.liveUpdate.ThreadDiag;
import edu.nju.treasuryArbitrage.logic.liveUpdate.UpdateThread;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.ui.personalCenter.LoginFrame;

public class TreasuryArbitrage {
	
	public static void main(String[] args) {
		TreasuryArbitrage treasuryArbitrage = new TreasuryArbitrage();
		treasuryArbitrage.launch();
	}
	
	public void launch() {
//		useLookAndFeel();	
		startDataFetch();
		setOverallHotKey();
		launchLoginPage();
	}
	
	private void setOverallHotKey() {
		Toolkit toolkit = Toolkit.getDefaultToolkit(); 
		toolkit.addAWTEventListener(new AWTEventListener() {
			
			@Override
			public void eventDispatched(AWTEvent event) {
				if (!(event instanceof KeyEvent)) {
					return;
				}
				KeyEvent keyEvent = (KeyEvent)event;
				if (keyEvent.getKeyCode() == KeyEvent.VK_F1) {
					defaultArbCombinationRecommend();
				} else if(keyEvent.getKeyCode() == KeyEvent.VK_F2) {
					defaultArbSellRemind();
				} else if(keyEvent.getKeyCode() == KeyEvent.VK_F3) {
					defaultArbSell();
				}
			}
		}, AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK| AWTEvent.WINDOW_EVENT_MASK); 
	}

	private void startDataFetch() {
		Thread thread = new Thread(UpdateThread.getInstance());
		thread.start();

		Thread analyseThread = new Thread(new AnalyseThread());
		analyseThread.start();
	}

	private void launchLoginPage() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}
	
	/*private void useLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		System.setProperty("sun.awt.noerasebackground", "true");
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/
	
	private void defaultArbCombinationRecommend() {
		ThreadDiag recommandDiag = new ThreadDiag("���ش��������ᣡ\r\n����ǰ���������ҳ��鿴��", true);
		recommandDiag.setVisible(true);
		ArbGroup group = new ArbGroup("TF1412", "TF1503", true);
		ArrayList<ArbGroup> groups = new ArrayList<>();
		groups.add(group);
		LiveData.getInstance().setArbGroups(groups);
	}
	
	private void defaultArbSell() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		ArrayList<Repository> holdings = dataInterface.getRepoList();
		if (holdings == null || holdings.isEmpty()) {
			return;
		}
		
		Repository repository = holdings.get(0);
		repository.update();
		dataInterface.Trade(repository.getRepo_ID(), repository.getProfit(), repository.getBuyPrecentPrice(), repository.getSellPrecentPrice());
		ThreadDiag recommandDiag = new ThreadDiag("���\"TF1412 TF1503\"���Զ�ƽ��!\r\n����ǰ���鿴��", false);
		recommandDiag.setVisible(true);
	}
	
	private void defaultArbSellRemind() {
		ThreadDiag recommandDiag = new ThreadDiag("���\"TF1412 TF1503\"����ƽ�ֻ���!\r\n����ǰ���鿴��", false);
		recommandDiag.setVisible(true);
	}
}


