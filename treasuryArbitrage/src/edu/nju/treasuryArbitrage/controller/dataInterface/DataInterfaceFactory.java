package edu.nju.treasuryArbitrage.controller.dataInterface;

public class DataInterfaceFactory {
	private static DataInterfaceFactory self = new DataInterfaceFactory();
	@SuppressWarnings("unused")
	private boolean isSimulate;
	
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

	public void setIsSimulate(boolean b) {
		this.isSimulate = b;
	}
}
