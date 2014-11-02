package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;
import java.util.Date;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.logic.dataInterface2Matlab.DataInterface2Matlab;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.model.Repository;

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

			Date now = new Date(); 
			int now_hour = now.getHours(),
					now_sec = now.getSeconds();
			 System.out.println(now_hour);  
			if((now_hour >= 9 && now_hour < 11) || (now_hour >= 13 && now_hour < 15))
			{
				arb_details = dataInterface.getArbDetail();
				LiveData.getInstance().setArb_details(arb_details);
				
				MajorPartsFactory factory = MajorPartsFactory.getInstance();
				factory.getFuturesMarket().updatePage();
				factory.getArbitragePortfolio().updatePage();
				
			}
			else {
			}
		}
		
		
	}
}
