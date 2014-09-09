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
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//小写的mm表示的是分钟  
	    String dstr="2014/08/14";  
	    java.util.Date date = null;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        //date = sdf.parse("2014/08/14");
	    
		
		
		//--------------------获取后台所有新闻概要, order by date desc------------
		
		
		//test
	    src = "长江期货";
	    author = "李明宇";
	    title = "移仓进行时1";
	    
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
		String str = "移仓进行时11";
		
		return str;
	}

	public String GetNewsContent(String NewsID) {

	    //test
		String str = "操作建议    \r\n"
	+"国债期货昨日上涨,近期新一轮IPO将启动,对市场的资金将会产生影响," 
	+ "股指近期的横盘给予债券一定的机会。我们认为政策上的利多国债将会发挥一定的效应,此外,发改委官员称降息降"
	+ "准时机已到,我们预期后续政策依然偏松,体现托底保增长意图,国债中线存在机会做多,短期内如果经济数据并非一"
	+ "路走强,那么国债有望短期摸高震荡区间上沿。    \r\n"
	+ "20日，农畜产品多弱势，预计今日弱势；化工品除橡胶横盘外多弱势"
	+ "，预计今日弱势；金属除黑色弱势外多强势，预计今日偏强；能源类出铁矿偏弱外多横盘，预计今日弱势横盘；玻璃强除"
	+ "纤板偏弱外横盘，预计今日弱势；股指国债横盘，预计今日横盘；近期关注鸡蛋和塑料。（期货投资QQ群：102664812）";
		
		return str;
	}

	public NewsBrief[] searchNews(String keyword, Date fD, Date tD) {
		NewsBrief[] res = null;
		int num = 0;

		if(keyword.equals("")
		&& fD == null
		&& tD == null){    //------若条件为空，则显示全部新闻
			
			return GetALLNewsBrief();
		}
		else{//---------否则，按条件搜索------------------coding
			/* find keyword:   
			*  RS[i].keyword.indexof(keyword) > -1
			*  
			*  比较日期
			*  (RS[i].date.before(tD) == true ||RS[i].date.equals(tD) == true )
			*  &&  (RS[i].date.after(fD) == true ||RS[i].date.equals(fD) == true )
			*  
			*  符合条件的存入resTemp,num记录结果条数
			*  
			*/
			//test
			num = 1;
		}
		
		if(num > 0){

			res = new NewsBrief[num];
	    	res[num - 1] = new NewsBrief("98",new Date(),"查询","结果","一条");
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
