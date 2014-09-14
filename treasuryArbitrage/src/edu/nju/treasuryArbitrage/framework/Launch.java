package edu.nju.treasuryArbitrage.framework;

import edu.nju.treasuryArbitrage.liveUpdate.UpdateThread;


public class Launch {

	public static void main(String[] args) {
		Thread thread = new Thread(new UpdateThread());
		thread.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TreasuryFrame frame = new TreasuryFrame();
		frame.setVisible(true);
	}
}
