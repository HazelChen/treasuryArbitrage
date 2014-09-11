package edu.nju.treasuryArbitrage.network;

import java.util.ArrayList;
import java.util.Date;

import vo.*;
import edu.nju.treasuryArbitrage.news.NewsBrief;


/**
 * ���ݽӿ�
 * �ýӿڴ������������������ĺ�����Ŀǰ���ǿ�����DataInterfacePile���׮ʵ��
 */
public interface DataInterface {
	/**
	 * 
	 * ��ȡ�������Ÿ�Ҫ�������б���ʾ�����Ž��棬����ʱ����µ�������
	 * 
	 */
	public NewsBrief[] GetALLNewsBrief();
	
	/**
	 * 
	 * ����ID��ȡ���ű��⣻��
	 * 
	 */
	public String GetNewsTitle(String newsID);
	
	/**
	 * 
	 * ����ID��ȡ������ϸ���ݣ�
	 * 
	 */
	public String GetNewsContent(String NewsID);
	
	/**
	 * 
	 * �����������������ţ����ؽ������
	 * 
	 */
	public NewsBrief[] searchNews(String keyword, Date fD, Date tD);
	

	public boolean register(String username,String password);
	public boolean loginValidate(String username, String password);
	public boolean changePWD(String username,String oldpwd,String newpwd);
	public boolean logout();
	
	public ArrayList<Finance> getFinanceList();
	public ArrayList<Repository> getRepoList();
	public boolean Trade(String Repo_ID);		//�ͻ���ˢ���������ݣ��ʽ𡢳ֲ֡���ʷ��¼��,����boolean
	
	public ArrayList<Message> getMessList();
	public void ReadMess(String MessID);
	public void DeleteMess(String MessID);
	
	public ArrayList<Record> getRecordList();
	
	public double getPara_PROF();
	public double getPara_LOSS();
	public double getPara_GUAR();
	public boolean setPara(double PROF,double LOSS,double GUAR);
}
