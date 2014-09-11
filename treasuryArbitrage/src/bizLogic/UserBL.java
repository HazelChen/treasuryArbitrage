package bizLogic;

import java.util.HashMap;

import org.json.JSONObject;

import edu.nju.treasuryArbitrage.PersonalCenter.LoginStateRecorder;
import edu.nju.treasuryArbitrage.fileIO.FileOperater;
import vo.UserVO;

/*
 * 处理用户相关的业务逻辑
 */
public class UserBL {
	private UserVO user;
	public static final String PARAM_FILE_NAME = "Parameters";

	public UserBL(){}
	
	public UserVO initUser(String username) {
		//从文件读取用户的登录状态
		if(user == null){
			LoginStateRecorder recorder = new LoginStateRecorder();
			boolean loginState = recorder.isAutoLogin();
			
			user = new UserVO(username, loginState);
			
			//从文件读取用户设置的参数
			FileOperater fileOperater = new FileOperater();
			String content = fileOperater.read(PARAM_FILE_NAME);
			String[] params = content.split("\n");
			double Max_prof = Double.valueOf(params[0]);
			double Max_loss = Double.valueOf(params[1]);
			double Max_guar = Double.valueOf(params[2]);
			user.setMax_prof(Max_prof);
			user.setMax_loss(Max_loss);
			user.setMax_guar(Max_guar);
		}
		
		return user;
	}
	
	public UserVO getUser(){
		return user;
	}

	public boolean login(String username, String pwd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", pwd);
		NetHelper helper = new NetHelper("login",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result")==1){
			initUser(username);
			return true;
		}else if(ret.getInt("result")==0){
			return false;
		}
		
		return false;
	}
	
	public boolean logout(String username){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		NetHelper helper = new NetHelper("logout",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result")==1){
			return true;
		}else if(ret.getInt("result")==0){
			return false;
		}
		
		return false;
	}

	public boolean register(String username, String pwd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", pwd);
		NetHelper helper = new NetHelper("register",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		//用户名不为空，注册成功
		if(ret.getString("username")!=null&&!ret.getString("username").equals("")){
			return true;
		}
		
		return false;
	}

	public boolean changePWD(String username, String oldpwd, String newpwd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("oldPassword", oldpwd);
		params.put("newPassword", newpwd);
		NetHelper helper = new NetHelper("changePaswd",params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if(ret.getInt("result")==1){
			return true;
		}else if(ret.getInt("result")==0){
			return false;
		}
		
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
		
		user.setMax_prof(PROF);
		user.setMax_loss(LOSS);
		user.setMax_guar(GUAR);
		
		String content = PROF+"\n"+LOSS+"\n"+GUAR;
		FileOperater fileOperater = new FileOperater();
		fileOperater.write(PARAM_FILE_NAME, content);
		
		return true;
	}
}
