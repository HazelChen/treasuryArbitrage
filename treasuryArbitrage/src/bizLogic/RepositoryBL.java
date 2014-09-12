package bizLogic;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.*;


public class RepositoryBL {
	private ArrayList<Repository> repo_list;
	
	public RepositoryBL(){}
	
	public ArrayList<Repository> getRepoList(String username){
		
//		Repository rep = new Repository();
//		repo_list.add(rep);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		NetHelper helper = new NetHelper("repository",params);
		JSONArray ret = helper.getJSONArrayByGet();
		
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			Repository rep = new Repository();
			
			//这里未完工！！！！！！
			JSONArray a_temp = temp.getJSONArray("arbitrage");
			for(int j=0;j<a_temp.length();j++){
				String orientation = a_temp.getJSONObject(j).getString("orientation");
				if(orientation.equals("")){
					
				}else if(orientation.equals("")){
					
				}
			}
			
			rep.setRepo_ID(temp.getInt("repo_ID")+"");
			rep.setCount(temp.getInt("count"));
			rep.setGuarantee(temp.getDouble("confirm"));
			rep.setProfit(temp.getDouble("profit"));
			
			
			
			repo_list.add(rep);
		}
		
		return repo_list;
	}
	
	public void addRepo(){
		
		Repository rep = new Repository();
		repo_list.add(rep);
	}
	
	public boolean Trade(String Repo_ID){
		int index = -1;
		for(Repository rep:repo_list){
			index++;
			if(rep.getRepo_ID().equals(Repo_ID)){
				break;
			}
		}
		repo_list.remove(index);
		
		//向服务器发送修改数据请求
		
		return true;
	}	
}
