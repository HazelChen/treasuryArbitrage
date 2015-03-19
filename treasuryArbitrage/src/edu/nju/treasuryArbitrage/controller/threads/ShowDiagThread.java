package edu.nju.treasuryArbitrage.controller.threads;

public class ShowDiagThread implements Runnable{
	private String message;
	private boolean isOpen;
	private ThreadDiag dialog;
	
	public ShowDiagThread(String massage, boolean isOpen) {
		this.message = massage;
		this.isOpen = isOpen;
	}

	@Override
	public void run() {
		Thread t = new Thread(new disposeDiag());
		t.start();
		dialog = new ThreadDiag(message, isOpen);
		dialog.setVisible(true);
	}
	
	class disposeDiag implements Runnable{
		int i = 0;
		public void run() {
			while(i<10){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
			if (dialog != null) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		}
	}
}
