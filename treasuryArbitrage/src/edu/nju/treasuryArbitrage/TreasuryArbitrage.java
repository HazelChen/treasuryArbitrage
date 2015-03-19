package edu.nju.treasuryArbitrage;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.controller.threads.AnalyseThread;
import edu.nju.treasuryArbitrage.controller.threads.LiveData;
import edu.nju.treasuryArbitrage.controller.threads.ThreadDiag;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Repository;
import edu.nju.treasuryArbitrage.view.common.ViewHelper;
import edu.nju.treasuryArbitrage.view.personalCenter.LoginFrame;

public class TreasuryArbitrage {
	
	public static void main(String[] args) {
		try {
			TreasuryArbitrage treasuryArbitrage = new TreasuryArbitrage();
			treasuryArbitrage.launch();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "????????????????????");
		}
	}
	
	public void launch() {
        ViewHelper.getInstance().init();
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
		Thread analyseThread = new Thread(new AnalyseThread());
		analyseThread.start();
	}

	private void launchLoginPage() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}
	
	private void defaultArbCombinationRecommend() {
		ThreadDiag recommandDiag = new ThreadDiag("?????????????\r\n????????????????????", true);
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
		ThreadDiag recommandDiag = new ThreadDiag("???\"TF1412 TF1503\"????????!\r\n???????????", false);
		recommandDiag.setVisible(true);
	}
	
	private void defaultArbSellRemind() {
		ThreadDiag recommandDiag = new ThreadDiag("???\"TF1412 TF1503\"??????????!\r\n???????????", false);
		recommandDiag.setVisible(true);
	}
}


