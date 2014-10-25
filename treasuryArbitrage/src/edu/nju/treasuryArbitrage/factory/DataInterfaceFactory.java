package edu.nju.treasuryArbitrage.factory;

import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterfaceToServer;

public class DataInterfaceFactory {
	private static DataInterfaceFactory self = new DataInterfaceFactory();
	
	private DataInterfaceToServer dataInterfaceToServer;
	
	private DataInterfaceFactory() {}
	
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
