package edu.nju.treasuryArbitrage.liveUpdate;

import java.util.ArrayList;

import vo.Arb_detail;
import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.network.DataInterface;

public class UpdateThread implements Runnable{

	public void run() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		ArrayList<Arb_detail> arb_details = dataInterface.getArbDetail();
		LiveData.getInstance().setArb_details(arb_details);
		
		int i = 0;
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			arb_details = dataInterface.getArbDetail();
			LiveData.getInstance().setArb_details(arb_details);
			
			MajorPartsFactory.getInstance().getFuturesMarket().update(arb_details);
		}
		
		
	}
}
