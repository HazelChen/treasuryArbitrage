package edu.nju.treasuryArbitrage.factory;

import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.network.DataInterfacePile;

public class DataInterfaceFactory {
	private DataInterfacePile pile;
	
	private DataInterfaceFactory() {}
	
	public DataInterface getDataInterface() {
		if (pile == null) {
			pile = new DataInterfacePile();
		}
		return pile;
	}
}
