package edu.nju.treasuryArbitrage.logic.ctpbiz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.model.ctp.CThostFtdcDepthMarketDataField;
import edu.nju.treasuryArbitrage.model.ctp.getCtpData;

public class ctpdataAdapter {
	
	private ArrayList<Arb_detail> detail_list;
	
	public static void main(String[] args) {
		new ctpdataAdapter().getSingleData("rb1505");
	}
	
	@SuppressWarnings("deprecation")
	public Arb_detail getSingleData(String code){
		
		Thread th = new Thread(new getCtpData(code));
		th.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		th.stop();
		th.interrupt();
		if(th.isAlive()){System.out.println("WTF");}
		CThostFtdcDepthMarketDataField ctpdata = CThostFtdcDepthMarketDataField.getInstance();
		
		Arb_detail detail = new Arb_detail();
		
		String symbol = ctpdata.InstrumentID;
		int year = Integer.valueOf(symbol.substring(2, 4));
		int month = Integer.valueOf(symbol.substring(4, 6));
		String End = 20+""+year+"年"+month+"月";
		detail.setSymbol(symbol);
		detail.setMonth(End);
		detail.setDate(End);
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
	
	public ArrayList<Arb_detail> getDetailList(){
		detail_list = new ArrayList<Arb_detail>();
		for(int i=0;i<3;i++){
			detail_list.add(getSingleData("rb1505"));
		}
		return detail_list;
	}
	
	private String twoNumberFormat(int i) {
		DecimalFormat df = new DecimalFormat("00");
		return df.format(i);
	}
	
}
