package bizLogic;

import vo.UserVO;

public class UserBL {
	private UserVO user;
	
	public UserVO getUser(){
		String username = "123";
		String state = "auto";
		user= new UserVO(username,"state");
		return user;
	}
	
	public boolean setParams(double PROF,double LOSS,double GUAR){
		user.setMax_prof(PROF);
		user.setMax_loss(LOSS);
		user.setMax_guar(GUAR);
		return true;
	}
}
