package bizLogic;

import java.util.ArrayList;

import vo.*;


public class RepositoryBL {
	private ArrayList<Repository> repo_list;
	
	public RepositoryBL(){}
	
	public ArrayList<Repository> getRepoList(){
		
		Repository rep = new Repository();
		repo_list.add(rep);
		
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
