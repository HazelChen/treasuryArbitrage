package edu.nju.treasuryArbitrage.logic.ctpbiz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.nju.treasuryArbitrage.model.ArbDetail;
import edu.nju.treasuryArbitrage.model.ctp.CThostFtdcDepthMarketDataField;
import edu.nju.treasuryArbitrage.model.ctp.TestJNA;
import edu.nju.treasuryArbitrage.model.ctp.getCtpData;

public class CtpDataAdapter {
	
	private ArrayList<ArbDetail> detail_list;
	
	public static void main(String[] args) {
		CtpDataAdapter adapter = new CtpDataAdapter();
		adapter.startOrder();
		ArrayList<ArbDetail> list = adapter.getDetailList();
		for(ArbDetail tem:list){
			System.out.println(tem.getSymbol());
		}
	}
	
	public void startOrder(){
		String[] codes = {"rb1505","rb1510","ag1505"};
		Thread th = new Thread(new getCtpData(codes));
		th.start();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public ArbDetail getSingleData(){
		TestJNA.INSTANCE.copyData();
		CThostFtdcDepthMarketDataField ctpdata = CThostFtdcDepthMarketDataField.getInstance();
		
		ArbDetail detail = new ArbDetail();
		
		String symbol = ctpdata.InstrumentID;
		int year = Integer.valueOf(symbol.substring(2, 4));
		int month = Integer.valueOf(symbol.substring(4, 6));
		String End = 20+""+year+"年"+month+"月";
		detail.setSymbol(symbol);
		detail.setMonth(End);
		detail.setDate(End);
		//================================================
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String clockString = twoNumberFormat(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + 
				twoNumberFormat(calendar.get(Calendar.MINUTE)) + ":" + twoNumberFormat(calendar.get(Calendar.SECOND));
		detail.setClock(clockString);
		//================================================
		detail.setPresentPrice(ctpdata.LastPrice);
		//detail.setChange(change);
		//detail.setPriceChange(temp.getDouble("RT_CHG"));//没有找到
		detail.setBidPirce(ctpdata.BidPrice1);
		detail.setBid((int)ctpdata.BidVolume1);
		detail.setAskPrice(ctpdata.AskPrice1);
		detail.setAsk((int)ctpdata.AskVolume1);
		
		detail.setVol((int)ctpdata.Volume);
		detail.setPreRepository((int)ctpdata.PreOpenInterest);
		detail.setRepository((int)ctpdata.OpenInterest);
		
		//detail.setDailyWarehouse(temp.getInt("RT_OI_CHG"));
		detail.setPreClose(ctpdata.PreClosePrice);
		detail.setOpen(ctpdata.OpenPrice);
		detail.setHigh(ctpdata.HighestPrice);
		detail.setLow(ctpdata.LowestPrice);
		detail.setFullAmount(ctpdata.Turnover);
		detail.setPreSettlePrice(ctpdata.PreSettlementPrice);
		
		//detail.setSwing(swing);
		detail.setHardenPrice(ctpdata.UpperLimitPrice);
		detail.setLimitPrice(ctpdata.LowerLimitPrice);
		detail.setAverPrice(ctpdata.AveragePrice);
		
		return detail;
	}
	
	public ArrayList<ArbDetail> getDetailList(){
		detail_list = new ArrayList<ArbDetail>();
		for(int i=0;i<6;i++){
			System.err.println("Copying->"+i);
			ArbDetail detail = getSingleData();
			boolean find = false;
			for(ArbDetail tem:detail_list){
				if(tem.getSymbol().equals(detail.getSymbol())){
					//if(i>=3) detail.setSymbol("Test"+i);
					tem = detail;
					find=true;
					break;
				}
			}
			if(!find){detail_list.add(detail);}
		}
		return detail_list;
	}
	
	private String twoNumberFormat(int i) {
		DecimalFormat df = new DecimalFormat("00");
		return df.format(i);
	}
	
}
