package edu.nju.treasuryArbitrage.framework;

import edu.nju.treasuryArbitrage.liveUpdate.UpdateThread;

public class Launch {

	public static void main(String[] args) {
		TreasuryFrame frame = new TreasuryFrame();
		frame.setVisible(true);
		
		Thread thread = new Thread(new UpdateThread());
		thread.start();
	}
}
