package edu.nju.treasuryArbitrage.model;

import edu.nju.treasuryArbitrage.controller.fileIO.FileOperater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LiveData {
    private static final String FUTURES_CODE_CONFIG_FILE = "config/futures-code";

	private static LiveData self = new LiveData();

    public static LiveData getInstance() {
        return self;
    }


    private String[] futuresCodes;
    private ArrayList<ArbDetail> arbDetails = new ArrayList<>();

	private ArrayList<ArbGroup> arbGroups = new ArrayList<>();
    private ArrayList<ArbBrief> briefsTF1412;
    private ArrayList<ArbBrief> briefsTF1503;

	private ArrayList<ArbBrief> briefsTF1506;

	private boolean isReady;

	private LiveData(){
        loadFutureCodes();
        initArbDetails();
	}

    /**
     * load future codes from config file
     */
    private void loadFutureCodes() {
        String codes = FileOperater.read(FUTURES_CODE_CONFIG_FILE);
        futuresCodes = codes.split(" ");
        if (futuresCodes.length != 3) {
            throw new RuntimeException("Error in futures config");
        }
    }

    /**
     * Init arb-details with initial value.
     */
    private void initArbDetails() {
        for (int i = 0; i < futuresCodes.length; i++) {
            arbDetails.add(ArbDetail.nullObject());
        }
    }

    /**
     * Add the data to arbDetails.
     */
    public void register(ArbDetail arbDetail) {
        String symbol = arbDetail.getSymbol();

        //find the index in arb-details for this arb-detail
        int index = 0;
        for (; index < futuresCodes.length; index++) {
            if (futuresCodes[index].equals(arbDetail)) {
                break;
            }
        }

        arbDetails.set(index, arbDetail);
    }

    public String[] getFuturesCodes() {
        return futuresCodes;
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
