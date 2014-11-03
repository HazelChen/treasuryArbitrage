package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.Date;

import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;

public class showDiagThread implements Runnable{

	ThreadDiag d = new ThreadDiag();
	int i = 0;

	@Override
	public void run() {
		Thread t = new Thread(new disposeDiag());
		t.start();
		d.show();
	}

	
	
	
	
	class disposeDiag implements Runnable{
		public void run() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			showDiagThread.this.d.setVisible(false);
			showDiagThread.this.d.dispose();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread test = new Thread(new showDiagThread());
		
		test.start();
		
	}
}
