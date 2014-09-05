package edu.nju.treasuryArbitrage.network;

import java.util.Date;

import edu.nju.treasuryArbitrage.news.NewsBrief;


/**
 * ���ݽӿ�
 * �ýӿڴ������������������ĺ�����Ŀǰ���ǿ�����DataInterfacePile���׮ʵ��
 */
public interface DataInterface {
	/**
	 * 
	 * ��ȡ�������ű����������б���ʾ�����Ž���
	 * 
	 */
	public NewsBrief[] GetALLNewsBrief();
	
	/**
	 * 
	 * ����ID��ȡ���ű��⣻��
	 * 
	 */
	public String GetNewsTitle(int NewsID);
	
	/**
	 * 
	 * ����ID��ȡ������ϸ���ݣ�
	 * 
	 */
	public String GetNewsContent(int NewsID);
	
	/**
	 * 
	 * �����������������ţ����ؽ������
	 * 
	 */
	public NewsBrief[] searchNews(String keyword, Date fD, Date tD);
	
}
