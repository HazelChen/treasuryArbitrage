package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.Arb_detail;

public class UpdateThread implements Runnable{

	public void run() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		ArrayList<Arb_detail> arb_details = dataInterface.getArbDetail();
		LiveData.getInstance().setArb_details(arb_details);
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			arb_details = dataInterface.getArbDetail();
			LiveData.getInstance().setArb_details(arb_details);
			
			MajorPartsFactory factory = MajorPartsFactory.getInstance();
			factory.getFuturesMarket().updatePage();
			factory.getArbitragePortfolio().updatePage();
		}
		
		
	}
}
