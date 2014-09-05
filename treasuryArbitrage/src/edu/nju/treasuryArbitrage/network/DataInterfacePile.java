package edu.nju.treasuryArbitrage.network;

public class DataInterfacePile implements DataInterface{

	@Override
	public boolean loginValidate(String username, String password) {
		if (username.equals("123") && password.equals("123")) {
			return true;
		} else {
			return false;
		}
	}

}
