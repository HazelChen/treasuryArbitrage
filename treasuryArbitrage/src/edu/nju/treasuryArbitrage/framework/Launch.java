package edu.nju.treasuryArbitrage.framework;

import edu.nju.treasuryArbitrage.futuresMarket.LineChart;
import edu.nju.treasuryArbitrage.liveUpdate.UpdateThread;

public class Launch {

	public static void main(String[] args) {
		LineChart lineChart = new LineChart();
		Thread thread = new Thread(new UpdateThread());
		thread.start();
		
		TreasuryFrame frame = new TreasuryFrame();
		frame.setVisible(true);
	}
}
