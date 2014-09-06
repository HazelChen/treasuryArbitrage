package edu.nju.treasuryArbitrage.network;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.nju.treasuryArbitrage.news.NewsBrief;

public class DataInterfacePile implements DataInterface{


	public NewsBrief[] GetALLNewsBrief() {
		int num = 0;
	    NewsBrief[] res = null;
	    String src,author,title;
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//Сд��mm��ʾ���Ƿ���  
	    String dstr="2014/08/14";  
	    java.util.Date date = null;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        //date = sdf.parse("2014/08/14");
	    
		
		
		//--------------------��ȡ��̨�������Ÿ�Ҫ------------
		
		
		//test
	    src = "�����ڻ�";
	    author = "������";
	    title = "�Ʋֽ���ʱ1";
	    
	    /*test  
	     * 
	     * 
	     */
	    num =20;
	    
	    
	    if(num > 0){
	    	res = new NewsBrief[num];
	    	for(int i = 0;i < num; i++){
	    		res[i] = new NewsBrief(date,src,title,author);
	    	}
	    	
	    	//test
	    	res[num - 1] = new NewsBrief(new Date(),"���","һҳ�����","һ��");
	    	
	    	//--------------����----------------
	    	
	    	return res;
	    }
		else{
			res = new NewsBrief[1];
	    	res[0] = new NewsBrief();
			return null;
		}
	}

	public String GetNewsTitle(int NewsID) {
	    //test
		String str = "�Ʋֽ���ʱ11";
		
		return str;
	}

	public String GetNewsContent(int NewsID) {

	    //test
		String str = "��������    \r\n"
	+"��ծ�ڻ���������,������һ��IPO������,���г����ʽ𽫻����Ӱ��," 
	+ "��ָ���ڵĺ��̸���ծȯһ���Ļ��ᡣ������Ϊ�����ϵ������ծ���ᷢ��һ����ЧӦ,����,����ί��Ա�ƽ�Ϣ��"
	+ "׼ʱ���ѵ�,����Ԥ�ں���������Ȼƫ��,�����еױ�������ͼ,��ծ���ߴ��ڻ�������,����������������ݲ���һ"
	+ "·��ǿ,��ô��ծ���������������������ء�    \r\n"
	+ "20�գ�ũ���Ʒ�����ƣ�Ԥ�ƽ������ƣ�����Ʒ���𽺺����������"
	+ "��Ԥ�ƽ������ƣ���������ɫ�������ǿ�ƣ�Ԥ�ƽ���ƫǿ����Դ�������ƫ�������̣�Ԥ�ƽ������ƺ��̣�����ǿ��"
	+ "�˰�ƫ������̣�Ԥ�ƽ������ƣ���ָ��ծ���̣�Ԥ�ƽ��պ��̣����ڹ�ע���������ϡ����ڻ�Ͷ��QQȺ��102664812��";
		
		return str;
	}

	public NewsBrief[] searchNews(String keyword, Date fD, Date tD) {
		NewsBrief[] res = null;
		int num = 0;

		if(keyword.equals("")
		&& fD == null
		&& tD == null){    //------������Ϊ�գ�����ʾȫ������
			
			return GetALLNewsBrief();
		}
		else{//---------���򣬰���������------------------coding
			/* find keyword:   
			*  RS[i].keyword.indexof(keyword) > -1
			*  
			*  �Ƚ�����
			*  (RS[i].date.before(tD) == true ||RS[i].date.equals(tD) == true )
			*  &&  (RS[i].date.after(fD) == true ||RS[i].date.equals(fD) == true )
			*  
			*  ���������Ĵ���resTemp,num��¼�������
			*  
			*/
			//test
			num = 1;
			res = new NewsBrief[num];
	    	res[num - 1] = new NewsBrief(new Date(),"���","һҳ�����","һ��");
		}
		
		if(num > 0){
	    	
	    	return res;
	    }
		else{
			return null;
			}
	}

	@Override
	public boolean loginValidate(String username, String password) {
		if (username.equals("123") && password.equals("123")) {
			return true;
		} else {
			return false;
		}
	}

}
