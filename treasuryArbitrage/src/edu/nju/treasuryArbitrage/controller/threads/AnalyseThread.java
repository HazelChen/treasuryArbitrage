package edu.nju.treasuryArbitrage.controller.threads;

public class AnalyseThread implements Runnable {
	double buyprice = 0, saleprice = 0;
	String message = "DMsg";

	public void run() {
		while (true) {
			try {
				Thread.sleep(5);// 0.005s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end of while(true)
	}

	public double getBuyprice() {
		return buyprice;
	}

	public double getSaleprice() {
		return saleprice;
	}
}
