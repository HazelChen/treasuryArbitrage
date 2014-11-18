package edu.nju.treasuryArbitrage.logic.liveUpdate;

import java.util.ArrayList;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.Arb_brief;
import edu.nju.treasuryArbitrage.model.Arb_detail;


public class LiveData {
	private static LiveData self = new LiveData();
	private ArrayList<Arb_detail> arb_details;
	private ArrayList<ArbGroup> arbGroups = new ArrayList<>();
	
	private ArrayList<Arb_brief> briefsTF1412;
	private ArrayList<Arb_brief> briefsTF1503;
	private ArrayList<Arb_brief> briefsTF1506;
	
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

	public ArrayList<Arb_brief> getBriefsTF1412() {
		return briefsTF1412;
	}

	public void setBriefsTF1412(ArrayList<Arb_brief> briefsTF1412) {
		this.briefsTF1412 = briefsTF1412;
	}

	public ArrayList<Arb_brief> getBriefsTF1503() {
		return briefsTF1503;
	}

	public void setBriefsTF1503(ArrayList<Arb_brief> briefsTF1503) {
		this.briefsTF1503 = briefsTF1503;
	}

	public ArrayList<Arb_brief> getBriefsTF1506() {
		return briefsTF1506;
	}

	public void setBriefsTF1506(ArrayList<Arb_brief> briefsTF1506) {
		this.briefsTF1506 = briefsTF1506;
	}
	
	
}
