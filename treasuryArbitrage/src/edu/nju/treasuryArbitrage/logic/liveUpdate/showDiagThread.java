package edu.nju.treasuryArbitrage.logic.liveUpdate;

public class showDiagThread implements Runnable{

	ThreadDiag d = new ThreadDiag(AnalyseThread.getInstance().getMsg());
	int i = 0;

	@Override
	public void run() {
		Thread t = new Thread(new disposeDiag());
		t.start();
		d.show();
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
				//System.out.println(i);
			}
			try{
			showDiagThread.this.d.setVisible(false);
			showDiagThread.this.d.dispose();
			}finally{}
		}
		
	}

	
}
