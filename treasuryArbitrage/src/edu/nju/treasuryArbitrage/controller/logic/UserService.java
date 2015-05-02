package edu.nju.treasuryArbitrage.controller.logic;

import java.util.HashMap;

import org.json.JSONObject;

import edu.nju.treasuryArbitrage.controller.fileIO.FileOperater;
import edu.nju.treasuryArbitrage.model.UserVO;
import edu.nju.treasuryArbitrage.view.personalCenter.LoginStateRecorder;

/*
 * �����û���ص�ҵ���߼�
 */
public class UserService {
	private NetHelper helper;
	private UserVO user;
	public static final String PARAM_FILE_NAME = "Parameters";

	public UserService(NetHelper helper){
		this.helper = helper;
	}
	
	@SuppressWarnings("unused")
	public UserVO initUser(String username) {
		//���ļ���ȡ�û��ĵ�¼״̬
		if(user == null){
			LoginStateRecorder recorder = new LoginStateRecorder();
//			boolean loginState = recorder.isAutoLogin();
			
			user = new UserVO(username, false);
			double Max_prof;
			double Max_loss;
			double Max_guar;
			
			//���ļ���ȡ�û����õĲ���
			FileOperater fileOperater = new FileOperater();
			String content = fileOperater.read(PARAM_FILE_NAME);
			String[] params = content.split("\n");
			if(content.equals("")||content==null){
				Max_prof = 0;
				Max_loss = 0;
				Max_guar = 0;
			}else{
				Max_prof = Double.valueOf(params[0]);
				Max_loss = Double.valueOf(params[1]);
				Max_guar = Double.valueOf(params[2]);
			}
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
		helper.setInitPara("login", params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if (ret == null) {
			return false;
		}
		
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
		helper.setInitPara("logout", params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		if (ret == null) {
			return false;
		}
		
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
		helper.setInitPara("register", params);
		JSONObject ret = helper.getJSONObjectByGet();
		
		//�û�����Ϊ�գ�ע��ɹ�
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
		helper.setInitPara("changePaswd", params);
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
