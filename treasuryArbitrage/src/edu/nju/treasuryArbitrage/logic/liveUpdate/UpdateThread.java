package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;

public class UpdateThread implements Runnable {

	public void run() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer();
		ArrayList<Arb_detail> arb_details = dataInterface.getArbDetail();
		LiveData.getInstance().setArb_details(arb_details);
		MajorPartsFactory factory = MajorPartsFactory.getInstance();

		ArbGroup arbGroup1 = new ArbGroup("TF1412", "TF1503");
		ArbGroup arbGroup2 = new ArbGroup("TF1412", "TF1506");
		ArrayList<ArbGroup> arbGroups = new ArrayList<>();
		//arbGroups.add(arbGroup1);
		//arbGroups.add(arbGroup2);
		LiveData.getInstance().setArbGroups(arbGroups);
		Date now = new Date();
		int now_hour, now_min;
		boolean runtime = true;

		while (true) {
			Calendar calendar = Calendar.getInstance();
			now = new Date();
			calendar.setTime(now);
			now_hour = calendar.get(Calendar.HOUR_OF_DAY);
			now_min = calendar.get(Calendar.MINUTE);
			// 交易时间 9:15-11:30、13:00-15:15
			if (true/*
					 * (now_hour == 9 && now_min >= 15) //09:15-09:59 ||
					 * (now_hour > 9 && now_hour <11) //10:00-10:59 || (now_hour
					 * == 11 && now_min <= 30) //11:00-11:30 || (now_hour >= 13
					 * && now_hour <15) //13:00-14:59 || (now_hour == 15 &&
					 * now_min <= 15) //15:00-15:15
					 */) {
				runtime = true;
				arb_details = dataInterface.getArbDetail();
				LiveData.getInstance().setArb_details(arb_details);

				factory.getFuturesMarket().updatePage();
				factory.getArbitragePortfolio().updatePage();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				runtime = false;
			}
			// 休市时间，线程休眠
			while (!runtime) {
				now = new Date();
				calendar.setTime(now);
				now_hour = calendar.get(Calendar.HOUR_OF_DAY);
				now_min = calendar.get(Calendar.MINUTE);
				// 稍微提前一段时间启动
				if ((now_hour == 8 && now_min >= 58) || now_hour == 9
						|| now_hour == 14 && now_min >= 43) {
					runtime = true;
				} else {
					try {
						Thread.sleep(15 * 60 * 1000);// 15分钟检查一次
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}// end of while(!runtime)
		}// end of while(true)

	}
}
