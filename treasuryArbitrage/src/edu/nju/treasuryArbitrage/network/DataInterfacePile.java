package edu.nju.treasuryArbitrage.network;

import java.util.Date;

import edu.nju.treasuryArbitrage.news.NewsBrief;

public class DataInterfacePile implements DataInterface{

	public NewsBrief[] GetALLNewsBrief() {
		int num = 0;
	    NewsBrief[] res = null;
	    Date date = null;
	    String src,author,title;
	    //SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//Сд��mm��ʾ���Ƿ���  
        //date = sdf.parse("2014/08/14");
	    src = "�����ڻ�";
	    author = "������";
	    title = "�Ʋֽ���ʱ";
	    
	    num = 1;//test
	    
	    if(num > 0){
	    	res = new NewsBrief[num];
	    	for(int i = 0;i < num; i++){
	    		res[i] = new NewsBrief(date,src,title,author);
	    	}
	    	
	    	return res;
	    }
		else{
			return null;
		}
	}

	public String GetNewsTitle(int NewsID) {
		String str = "�Ʋֽ���ʱ";
		
		return str;
	}

	public String GetNewsContent(int NewsID) {
		String str = "��������    ��ծ�ڻ���������,������һ��IPO������,���г����ʽ𽫻����Ӱ��," 
	+ "��ָ���ڵĺ��̸���ծȯһ���Ļ��ᡣ������Ϊ�����ϵ������ծ���ᷢ��һ����ЧӦ,����,����ί��Ա�ƽ�Ϣ��"
	+ "׼ʱ���ѵ�,����Ԥ�ں���������Ȼƫ��,�����еױ�������ͼ,��ծ���ߴ��ڻ�������,����������������ݲ���һ"
	+ "·��ǿ,��ô��ծ���������������������ء�    20�գ�ũ���Ʒ�����ƣ�Ԥ�ƽ������ƣ�����Ʒ���𽺺����������"
	+ "��Ԥ�ƽ������ƣ���������ɫ�������ǿ�ƣ�Ԥ�ƽ���ƫǿ����Դ�������ƫ�������̣�Ԥ�ƽ������ƺ��̣�����ǿ��"
	+ "�˰�ƫ������̣�Ԥ�ƽ������ƣ���ָ��ծ���̣�Ԥ�ƽ��պ��̣����ڹ�ע���������ϡ����ڻ�Ͷ��QQȺ��102664812��";
		
		return str;
	}

	public NewsBrief[] searchNews(String keyword, Date fD, Date tD) {
		NewsBrief[] res = null;
		int num = 0;

		if(num > 0){
	    	
	    	return res;
	    }
		else{
			return null;
		}
	}

}
