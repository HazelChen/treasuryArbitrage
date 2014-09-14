package edu.nju.treasuryArbitrage.factory;

import edu.nju.treasuryArbitrage.network.DataInterface;
import edu.nju.treasuryArbitrage.network.DataInterfacePile;
import edu.nju.treasuryArbitrage.network.DataInterfaceToServer;

public class DataInterfaceFactory {
	private static DataInterfaceFactory self = new DataInterfaceFactory();
	
	private DataInterfacePile pile;
	private DataInterfaceToServer dataInterfaceToServer;
	
	private DataInterfaceFactory() {}
	
	public DataInterface getDataInterfacePile() {
		if (pile == null) {
			pile = new DataInterfacePile();
		}
		return pile;
	}
	
	public DataInterface getDataInterfaceToServer() {
		if (dataInterfaceToServer == null) {
			dataInterfaceToServer = new DataInterfaceToServer();
		}
		return dataInterfaceToServer;
	}
	
	public static DataInterfaceFactory getInstance() {
		return self;
	}
}
