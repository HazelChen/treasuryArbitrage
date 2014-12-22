package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;
import java.util.Calendar;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.factory.MajorPartsFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.ArbDetail;

public class UpdateThread implements Runnable {
	private static UpdateThread self;
	private boolean canUpdateHoldings;
	private boolean canUpdate;
	
	private UpdateThread(){}
	
	public static UpdateThread getInstance() {
		if (self == null) {
			self = new UpdateThread();
		}
		return self;
	}
	
	@SuppressWarnings("unused")
	public void run() {
		DataInterface dataInterface = DataInterfaceFactory.getInstance()
				.getDataInterfaceToServer();
		ArrayList<ArbDetail> arb_details = dataInterface.getArbDetail();
		if (arb_details == null) {
			arb_details = new ArrayList<ArbDetail>();
		}
		
		MajorPartsFactory factory = MajorPartsFactory.getInstance();
		factory.init();
		
		LiveData.getInstance().setArb_details(arb_details);
		LiveData.getInstance().setBriefsTF1412(dataInterface.getArbBrief("TF1412"));
		LiveData.getInstance().setBriefsTF1503(dataInterface.getArbBrief("TF1503"));
		LiveData.getInstance().setBriefsTF1506(dataInterface.getArbBrief("TF1506"));
		
		factory.getFuturesMarket().initPoint();
		factory.getArbitragePortfolio().initPoint();
		
		LiveData.getInstance().ready();
		System.out.println("ready!");
		
		int now_hour, now_min;
		boolean runtime = true;
		while (true) {
			Calendar calendar = Calendar.getInstance();
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
				if (arb_details == null) {
					continue;
				}
				LiveData.getInstance().setArb_details(arb_details);

				if (canUpdate) {
					factory.getFuturesMarket().updatePage();
					factory.getArbitragePortfolio().updatePage();
					if (canUpdateHoldings) {
						factory.getHoldings().liveUpdate();
					}
				}
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
				calendar = Calendar.getInstance();
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

	public void startUpdateHoldings() {
		canUpdateHoldings = true;
	}
	
	public void startUpdate() {
		canUpdate = true;
	}
}
