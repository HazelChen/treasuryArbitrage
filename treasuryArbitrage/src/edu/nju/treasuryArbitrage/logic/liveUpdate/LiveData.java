package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_detail;


public class LiveData {
	private static LiveData self = new LiveData();
	private ArrayList<Arb_detail> arb_details;
	private ArrayList<ArbGroup> arbGroups;
	
	private LiveData(){}
	
	public static LiveData getInstance() {
		return self;
	}
	
	public ArrayList<Arb_detail> getArb_details() {
		return arb_details;
	}
	
	public double getPresentPrice(String id) {
		for (Arb_detail arb_detail : arb_details) {
			if (arb_detail.getSymbol().equals(id)) {
				return arb_detail.getPresentPrice();
			}
		}
		return -1;
	}
	
	public void setArb_details(ArrayList<Arb_detail> arb_details) {
		this.arb_details = arb_details;
	}

	public ArrayList<ArbGroup> getArbGroups() {
		return arbGroups;
	}

	public void setArbGroups(ArrayList<ArbGroup> arbGroups) {
		this.arbGroups = arbGroups;
	}
}
