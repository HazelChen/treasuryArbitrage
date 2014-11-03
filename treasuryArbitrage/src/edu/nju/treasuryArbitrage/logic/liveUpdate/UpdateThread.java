package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;
import java.util.Date;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.model.Repository;

public class UpdateThread implements Runnable{

	public void run() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
		ArrayList<Arb_detail> arb_details = dataInterface.getArbDetail();
		LiveData.getInstance().setArb_details(arb_details);
		
		ArbGroup arbGroup1 = new ArbGroup("TF1412", "TF1503");
		ArbGroup arbGroup2 = new ArbGroup("TF1412", "TF1506");
		ArrayList<ArbGroup> arbGroups = new ArrayList<>();
		arbGroups.add(arbGroup1);
		arbGroups.add(arbGroup2);
		LiveData.getInstance().setArbGroups(arbGroups);
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Date now = new Date(); 
			int now_hour = now.getHours(),
					now_min = now.getMinutes();
			// System.out.println(now_hour);  
			 //交易时间  9:15-11:30、13:00-15:15
			if(   	(now_hour == 9 && now_min >= 15)         //09:15-09:59
			     || (now_hour > 9 && now_hour <11)	 	 //10:00-10:59	
			     || (now_hour == 11 && now_min <= 30)	 //11:00-11:30
				 || (now_hour >= 13 && now_hour <15)	 	 //13:00-14:59
				 || (now_hour == 15 && now_min <= 15)		 //15:00-15:15
					)
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
