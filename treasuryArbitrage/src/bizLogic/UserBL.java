package bizLogic;

import java.util.HashMap;

import org.json.JSONObject;

import edu.nju.treasuryArbitrage.PersonalCenter.LoginStateRecorder;
import vo.UserVO;

public class UserBL {
	private UserVO user;

	public UserBL(){}
	
	public UserVO getUser(String username) {
		LoginStateRecorder recorder = new LoginStateRecorder();
		boolean loginState = recorder.isAutoLogin();
		user = new UserVO(username, loginState);
		
		//需要获取数据——对服务器，对文件？
		user.setMax_guar(0);
		user.setMax_loss(0);
		user.setMax_prof(0);
		
		return user;
	}

	public boolean login(String username, String pwd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", pwd);
		NetHelper helper = new NetHelper("login",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getString("username")!=null){
			return true;
		}
		
		return false;
	}

	public boolean register(String username, String pwd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", pwd);
		NetHelper helper = new NetHelper("register",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getString("username")!=null){
			return true;
		}
		
		return false;
	}

	public boolean changePWD(String username, String oldpwd, String newpwd) {
		return false;
	}

	public double getPara_PROF() {
		return user.getMax_prof();
	}

	public double getPara_LOSS() {
		return user.getMax_loss();
	}

	public double getPara_GUAR() {
		return user.getMax_guar();
	}

	public boolean setParams(double PROF, double LOSS, double GUAR) {
		
		//需要写入数据——对服务器，对文件？
		user.setMax_prof(PROF);
		user.setMax_loss(LOSS);
		user.setMax_guar(GUAR);
		return true;
	}
}
