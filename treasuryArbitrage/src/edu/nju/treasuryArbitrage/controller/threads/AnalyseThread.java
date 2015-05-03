package edu.nju.treasuryArbitrage.controller.threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.controller.dataInterface.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.controller.dataInterface.MatlabCaller;
import edu.nju.treasuryArbitrage.model.ArbBrief;
import edu.nju.treasuryArbitrage.model.LiveData;
import edu.nju.treasuryArbitrage.model.calculation.Lambda;
import edu.nju.treasuryArbitrage.model.calculation.OptimalKT;
import edu.nju.treasuryArbitrage.model.calculation.Xyz;

public class AnalyseThread implements Runnable {
	double buyprice = 0, saleprice = 0,newprice1 = -1,newprice2 = -1,newprice3 = -1,
			price1,price2;
	ArrayList<ArbBrief> hLf1=new ArrayList<ArbBrief>(),
			hLf2=new ArrayList<ArbBrief>(),
			hLf3=new ArrayList<ArbBrief>();
	ArbBrief lastArbBrief = null;
	ArrayList<Double> Lf1=new ArrayList<Double>(),
			Lf2=new ArrayList<Double>();
	String message = "DMsg",Name1="",Name2="",logFile;
	MatlabCaller dm= new MatlabCaller();
	Object[] result = null;
	int signal,stop_loss=-1,stop_profit=1,selModel=0,selGroup=0;
	Date now=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
	boolean newLogFile=false;
	String[] FuturesNames;//合约名称
	double x,y,k;//FXY 策略参数
	ArrayList<Double> newLambda=new ArrayList<Double>();//WXY 策略参数
	double T,kdj;//DJ 策略参数
	 DataInterface dataInterface = DataInterfaceFactory.getInstance()
             .getDataInterfaceToServer();
	 
	public void run() {
		x=0;y=0;k=0;
		T=0;kdj=0;
		//------------------------------------------//
		//Do not delete any comment!!!  Important!!!
		//------------------------------------------//

		// get FuturesNames
		FuturesNames=LiveData.getInstance().getFuturesCodes();
		//Get Contract(1、2、3) history price price(open -> now) from local
		hLf1=LiveData.getInstance().getHistoryPrice(FuturesNames[0]);
		hLf2=LiveData.getInstance().getHistoryPrice(FuturesNames[1]);
		hLf3=LiveData.getInstance().getHistoryPrice(FuturesNames[2]);
		
		//Modify data to make 3 hlf length be same
		/**/
		ArrayList<ArbBrief> hLf1tmp=new ArrayList<ArbBrief>(),
				hLf2tmp=new ArrayList<ArbBrief>();
		for(selGroup=1;selGroup<4;selGroup++)
		{
			switch(selGroup)
			{
			case 1:hLf1tmp = hLf1;hLf2tmp = hLf2;break;
			case 2:hLf1tmp = hLf1;hLf2tmp = hLf3;break;
			case 3:hLf1tmp = hLf2;hLf2tmp = hLf3;break;
			}
			for (int i = 0; i < hLf1tmp.size() - 1;) {
				if (hLf1tmp.get(i).getTime().substring(0, 5).equals( 
						hLf1tmp.get(i + 1).getTime().substring(0, 5))) {
					hLf1tmp.remove(i);
				} else {
					i++;
				}
			}
			for (int i = 0; i < hLf2tmp.size() - 1;) {
				if (hLf2tmp.get(i).getTime().substring(0, 5).equals(
						hLf2tmp.get(i + 1).getTime().substring(0, 5)) 
						) {
					hLf2tmp.remove(i);
				} else {
					i++;
				}
			}
			// 
			int i1 = hLf1tmp.size() - 1, i2 = hLf1tmp.size() - 1, maxdt = 2;
			// Double lf2lp=lf2.get(i2).price,lf1lp=lf1.get(i1).price;
			String dt1 = hLf1tmp.get(i1).getTime(), dt10 = hLf1tmp.get(0).getTime(), 
					dt2 = hLf2tmp.get(i2).getTime(), dt20 = hLf2tmp.get(0).getTime(),
					nt = hLf2tmp.get(i2).getTime();
			if (dt1.compareTo(dt2)>0) {
				nt = dt1;
				maxdt = 1;
			} else {
			}
			long l = minLength(nt,dt10,dt20);
			long ntMins = getMinTime(nt);
			long st = ntMins - l;
			String tmpt;
			while (ntMins >= st ) {
				if (i1 >= 0
						&& hLf1tmp.get(i1).getTime().substring(0,hLf1tmp.get(i1).getTime().lastIndexOf(':') )
						.compareTo(nt.substring(0, nt.lastIndexOf(':')))<0) {
					tmpt = hLf1tmp.get(i1).getTime();
					hLf1tmp.add(i1, hLf1tmp.get(i1));
					ArbBrief tmp = new ArbBrief(FuturesNames[0], hLf1tmp.get(i1).getTime(),
							hLf1tmp.get(i1).getPrice());
					tmp.setTime(nt);
					hLf1tmp.set(i1 + 1, tmp);
					ArbBrief tmp2 = new ArbBrief(FuturesNames[0], hLf1tmp.get(i1).getTime(),
							hLf1tmp.get(i1).getPrice());
					String tt =tmpt;
					tmp2.setTime(tt);
					hLf1tmp.set(i1, tmp2);
				} else if (i1 > 0) // if(lf1.get(i1).date.getTime()/60000>=nt.getTime()/60000)
				{
					i1--;
				}
				if (i2 >= 0
						&& hLf2tmp.get(i2).getTime().substring(0, hLf2tmp.get(i2).getTime().lastIndexOf(':'))
						.compareTo(nt.substring(0, nt.lastIndexOf(':')))<0) {
					tmpt = hLf2tmp.get(i2).getTime();
					hLf2tmp.add(i2, hLf2tmp.get(i2));
					ArbBrief tmp20 = new ArbBrief(FuturesNames[1],hLf2tmp.get(i2).getTime(),
							hLf2tmp.get(i2).getPrice());
					tmp20.setTime(nt);
					hLf2tmp.set(i2 + 1, tmp20);
					ArbBrief tmp22 = new ArbBrief(FuturesNames[1],hLf2tmp.get(i2).getTime(),
							hLf2tmp.get(i2).getPrice());
					String tt2 = tmpt;
					tmp22.setTime(tt2);
					hLf2tmp.set(i2, tmp22);
				} else if (i2 > 0) // if(lf2.get(i2).date.getTime()/60000>=nt.getTime()/60000)
				{
					i2--;
				}
				// 
				ntMins --;
				nt = String.valueOf(ntMins/60 + ":" + ntMins%60 + ":00");
				// System.out.println("i1:"+i1+" i2:"+i2+" nt:"+nt.getTime()/60000);
			}
			// fix bug
			if (maxdt == 2) {
				ArbBrief ftmp22 = new ArbBrief(FuturesNames[1], dt2,
						hLf2.get(hLf2.size() - 1).getPrice());
				hLf2.set(hLf2.size() - 1, ftmp22);
			} else if (maxdt == 1) {
				ArbBrief ftmp11 = new ArbBrief(FuturesNames[0], dt1,
						hLf1.get(hLf1.size() - 1).getPrice());
				hLf1.set(hLf1.size() - 1, ftmp11);
			}// 
			if (hLf1tmp.size() < hLf2tmp.size()) {
				for (int j = 0; j < hLf2tmp.size() - hLf1tmp.size();) {
					hLf2tmp.remove(j);
				}
			} else if (hLf1tmp.size() > hLf2.size()) {
				for (int j = 0; j < hLf1tmp.size() - hLf2tmp.size();) {
					hLf1tmp.remove(j);
				}
			}
		}
		/* modify data end*/
		
		while (true) {
			now=new Date();
			//get Contract(1、2、3)  price(now)
			//newprice1=getPrice(1);
			newprice1=LiveData.getInstance().getPresentPrice(FuturesNames[0]);
			newprice2=LiveData.getInstance().getPresentPrice(FuturesNames[1]);
			newprice3=LiveData.getInstance().getPresentPrice(FuturesNames[2]);
			//Select Model(1/2/3)
			selModel=1;
			for(selModel=1;selModel<4;selModel++)
			{
				//Select Group(1/2/3)  1/12；  2/13；  3/23
				selGroup=1;
				for(selGroup=1;selGroup<4;selGroup++)
				{
					//Set price1/2 name1/2
					switch(selGroup)
					{
					case 1:
						price1=newprice1;price2=newprice2;
						Lf1=getPrice(hLf1);Lf2=getPrice(hLf2);
						//get FuturesNames names
						Name1=FuturesNames[0];Name2=FuturesNames[1];
						break;
					case 2:
						price1=newprice1;price2=newprice3;
						Lf1=getPrice(hLf1);Lf2=getPrice(hLf3);
						//get FuturesNames names
						Name1=FuturesNames[0];Name2=FuturesNames[2];
						break;
					case 3:
						price1=newprice2;price2=newprice3;
						Lf1=getPrice(hLf2);Lf2=getPrice(hLf3);
						//get FuturesNames names
						Name1=FuturesNames[1];Name2=FuturesNames[2];
						break;
					}
					logFile="OpenCloseLogGroup" + Name1 + "" + Name2 + "Model" + selModel + ".log";
					//get Model(1/2/3)params{x,y,k}{ol1, ol2}{k,T} from server
//					double[] params=getFromServer(selGroup);
//					x = params[0];
//					y = params[1];
//					k = params[2];
					//Get WXY Strategy params
					//Lambda=getLambda();
//					kdj=getkdj(selGroup);
//					T=getT(selGroup);
					
					//open
					switch(selModel){
						case 1:
							Xyz xyz = dataInterface.getTodayXyz(selGroup);
							if(xyz != null)
							{
							x=xyz.getX();
							y=xyz.getY();
							k=xyz.getZ();
							result=dm.Open(Lf1, Lf2, price1, price2, x, y, k );break;
							}
						case 2:
							Lambda lambda = dataInterface.getTodayLambda(selGroup);
							if(lambda != null)
							{
							newLambda.add(lambda.getLambda1());
							newLambda.add(lambda.getLambda2());
							result=dm.Open1(Lf1, Lf2, price1, price2, newLambda);break;
							}
						case 3:
							OptimalKT optimalKT = dataInterface.getTodayKt(selGroup);
							if(optimalKT != null)
							{
							kdj = optimalKT.getOptimalK();
							T = optimalKT.getOptimalT();
							result=dm.Open2(Lf1, Lf2, price1, price2, kdj, T);break;
							}
					}
					if(result!=null)
					{
						signal=Integer.parseInt(String.valueOf(result[0])); 
						buyprice=Integer.parseInt(String.valueOf(result[1]));
						saleprice=Integer.parseInt(String.valueOf(result[2]));
						//out into log
						try{  
							  String str=sdf.format(now);
						      String data = str + "\t " + signal + "\t" 
						    		  	+ buyprice + "\t" + saleprice + "\r\n";
						      File file =new File(logFile);
						      //if file doesnt exists, then create it
						      if(!file.exists()){
						       file.createNewFile();
						       newLogFile=true;
						      }
						      //true = append file
						      FileWriter fileWritter = new FileWriter(file.getName(),true);
						             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
						             if(newLogFile){
						            	 String logTitle = "\tTime" + "\t\t " + "Signal" 
									    		  	+ "\t" + "BuyPrice" + " " + "SalePrice\r\n";
						            	 bufferWritter.write(logTitle);
						            	 newLogFile=false;
						             }
						             bufferWritter.write(data);
						             bufferWritter.close();

						     }catch(IOException e){e.printStackTrace();}
					}
					
					
					//close
					//get signal buyprice saleprice
					
					switch(selModel){
						case 1:
							result=dm.Close(Lf1, Lf2, price1, price2, buyprice,
									saleprice, k, signal, stop_loss, stop_profit);break;
						case 2:
							result=dm.Close1(Lf1, Lf2, price1, price2, buyprice,
									saleprice, newLambda, signal, stop_loss, stop_profit);break;
						case 3:
							result=dm.Close2(Lf1, Lf2, price1, price2, buyprice,
									saleprice, signal, kdj, T, stop_loss, stop_profit);break;
					}
					if(result!=null)
					{
						signal=Integer.parseInt(String.valueOf(result[0])); 
						buyprice=Integer.parseInt(String.valueOf(result[1]));
						saleprice=Integer.parseInt(String.valueOf(result[2]));
						//out into log
						try{  
							  String str=sdf.format(now);
						      String data = str + "\t " + signal + "\t" 
						    		  	+ buyprice + "\t" + saleprice + "\r\n";
						      File file =new File(logFile);
						      //if file doesnt exists, then create it
						      if(!file.exists()){
						       file.createNewFile();
						       newLogFile=true;
						      }
						      //true = append file
						      FileWriter fileWritter = new FileWriter(file.getName(),true);
						             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
						             if(newLogFile){
						            	 String logTitle = "\tTime" + "\t\t " + "Signal" 
									    		  	+ "\t" + "BuyPrice" + " " + "SalePrice\r\n";
						            	 bufferWritter.write(logTitle);
						            	 newLogFile=false;
						             }
						             bufferWritter.write(data);
						             bufferWritter.close();

						     }catch(IOException e){e.printStackTrace();}
					}
				}//for selGroup
			}//for selModel						
			
			//Refresh hLf1,hLf2,hLf3
			lastArbBrief=new ArbBrief(FuturesNames[0],dateFormat.format(now),newprice1);
			hLf1.add(lastArbBrief);
			lastArbBrief=new ArbBrief(FuturesNames[1],dateFormat.format(now),newprice2);
			hLf2.add(lastArbBrief);
			lastArbBrief=new ArbBrief(FuturesNames[2],dateFormat.format(now),newprice3);
			hLf3.add(lastArbBrief);
			
			try {
				Thread.sleep(5);// 0.005s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end of while(true)
	}
	
	public long getMinTime(String nt) {
		return Integer.parseInt(nt.substring(0,nt.indexOf(':')))*60
				+Integer.parseInt(
						nt.substring(nt.indexOf(':') + 1,nt.lastIndexOf(':'))
						);
	}
	private ArrayList<Double> getPrice(ArrayList<ArbBrief> hLf) {
		ArrayList<Double> result = new ArrayList<Double>();
		for(int i=0;i<hLf.size();i++)
			result.add(hLf.get(i).getPrice());
		return result;
	}
	
	public double getBuyprice() {
		return buyprice;
	}

	public double getSaleprice() {
		return saleprice;
	}

	public long minLength(String n, String a, String b) {
		int hn=Integer.parseInt(n.substring(0,n.indexOf(':'))),
			mn=Integer.parseInt(n.substring(n.indexOf(':')+1,n.lastIndexOf(':'))),
					ha=Integer.parseInt(a.substring(0,a.indexOf(':'))),
					ma=Integer.parseInt(a.substring(a.indexOf(':')+1,a.lastIndexOf(':'))),
							hb=Integer.parseInt(b.substring(0,b.indexOf(':'))),
							mb=Integer.parseInt(b.substring(b.indexOf(':')+1,b.lastIndexOf(':')));
		long l1=hn*60 + mn-(ha*60 + ma) + 1,
				l2=hn*60 + mn-(hb*60 + mb) + 1;
		
		return l1<l2?l1:l2;
	}
	
	

}
