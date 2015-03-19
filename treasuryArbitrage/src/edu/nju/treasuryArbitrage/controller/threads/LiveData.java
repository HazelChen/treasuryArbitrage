package edu.nju.treasuryArbitrage.controller.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.nju.treasuryArbitrage.model.ArbGroup;
import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.ArbDetail;


public class LiveData {
	private static LiveData self = new LiveData();

    private Map<String, Integer> arbNameToIndex = new HashMap<>();

	private ArrayList<ArbDetail> arbDetails = new ArrayList<>();
	private ArrayList<ArbGroup> arbGroups = new ArrayList<>();
	
	private ArrayList<ArbBrief> briefsTF1412;
	private ArrayList<ArbBrief> briefsTF1503;
	private ArrayList<ArbBrief> briefsTF1506;
	
	private boolean isReady;
	
	private LiveData(){}
	
	public static LiveData getInstance() {
		return self;
	}

    /**
     * When a arb data come, judge whether it's a new arb.
     * If it's a new arb, give a index to it and put to map.
     * Add the data to arbDetails.
     */
    public void register(ArbDetail arbDetail) {
        String symbol = arbDetail.getSymbol();
        if (arbNameToIndex.size() < 3 &&
                !arbNameToIndex.containsKey(symbol)) {
            arbNameToIndex.put(symbol, arbNameToIndex.size());
        }

        arbDetails.set(arbNameToIndex.get(symbol), arbDetail);
    }
	
	public boolean isReady() {
		return isReady;
	}
	
	public ArrayList<ArbDetail> getArbDetails() {
		return arbDetails;
	}
	
	public double getPresentPrice(String id) {
		for (ArbDetail arb_detail : arbDetails) {
			if (arb_detail.getSymbol().equals(id)) {
				return arb_detail.getPresentPrice();
			}
		}
		return -1;
	}
	
	public ArrayList<ArbGroup> getArbGroups() {
		return arbGroups;
	}

	public void setArbGroups(ArrayList<ArbGroup> arbGroups) {
		this.arbGroups = arbGroups;
	}

	public ArrayList<ArbBrief> getBriefsTF1412() {
		return briefsTF1412;
	}

	public void setBriefsTF1412(ArrayList<ArbBrief> briefsTF1412) {
		this.briefsTF1412 = briefsTF1412;
	}

	public ArrayList<ArbBrief> getBriefsTF1503() {
		return briefsTF1503;
	}

	public void setBriefsTF1503(ArrayList<ArbBrief> briefsTF1503) {
		this.briefsTF1503 = briefsTF1503;
	}

	public ArrayList<ArbBrief> getBriefsTF1506() {
		return briefsTF1506;
	}

	public void setBriefsTF1506(ArrayList<ArbBrief> briefsTF1506) {
		this.briefsTF1506 = briefsTF1506;
	}

	public void ready() {
		this.isReady = true;
	}
	
	
}
