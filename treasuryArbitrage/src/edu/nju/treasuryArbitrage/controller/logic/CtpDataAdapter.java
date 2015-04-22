package edu.nju.treasuryArbitrage.controller.logic;

import edu.nju.treasuryArbitrage.controller.ctp.*;
import edu.nju.treasuryArbitrage.model.LiveData;

public class CtpDataAdapter {
	
	public static void main(String[] args) {
		CtpDataAdapter adapter = new CtpDataAdapter();
		adapter.startOrder();
	}
	
	public void startOrder(){
		String[] codes = LiveData.getInstance().getFuturesCodes();
		Thread th = new Thread(new GetCtpData(codes));
		th.start();
	}
	
}
