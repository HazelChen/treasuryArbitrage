package edu.nju.treasuryArbitrage.network;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.Finance;
import vo.Message;
import vo.Record;
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
	    
		
		
		//--------------------��ȡ��̨�������Ÿ�Ҫ, order by date desc------------
		
		
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
	    		date.setDate(i + 1);
	    		res[i] = new NewsBrief(String.valueOf(i),date,src,title,author);
	    	}
	    	
	    	
	    	return res;
	    }
		else{
			res = new NewsBrief[1];
	    	res[0] = new NewsBrief();
			return null;
		}
	}

	public String GetNewsTitle(String NewsID) {
	    //test
		String str = "�Ʋֽ���ʱ11";
		
		return str;
	}

	public String GetNewsContent(String NewsID) {

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
		}
		
		if(num > 0){

			res = new NewsBrief[num];
	    	res[num - 1] = new NewsBrief("98",new Date(),"��ѯ","���","һ��");
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

	@Override
	public boolean register(String username, String password) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public boolean changePWD(String username, String oldpwd, String newpwd) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public boolean logout() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public ArrayList<Finance> getFinanceList() {
		// TODO �Զ����ɵķ������
		ArrayList<Finance> RES;
		RES =new ArrayList<Finance>();
		RES.add(new Finance("2014-7-15",20000,10000,10000));
		RES.add(new Finance("2014-7-18",30000,10000,20000));
		return RES;
	}

	@Override
	public ArrayList<Finance> getRepoList() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public boolean Trade(String Repo_ID) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public ArrayList<Message> getMessList() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void ReadMess(String MessID) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void DeleteMess(String MessID) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public ArrayList<Record> getRecordList() {
		ArrayList<Record> RES;
		Record r= new Record();
		
		r.setCount(3);r.setGuarantee(10000);
		r.setRepo_ID("1");r.setTime("2014-08-25");
		r.setToBuy(null);r.setToSell(null);
		RES =new ArrayList<Record>();
		
		RES.add(r);
		RES.add(r);
		return RES;
	}

	@Override
	public double getPara_PROF() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public double getPara_LOSS() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public double getPara_GUAR() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public boolean setPara(double PROF, double LOSS, double GUAR) {
		// TODO �Զ����ɵķ������
		return false;
	}

}
