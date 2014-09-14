package bizLogic;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.Arb_detail;

public class ArbitrageBL {

	private ArrayList<Arb_detail> detail_list;
	
	public ArbitrageBL(){}
	
	public ArrayList<Arb_detail> getDetailList(String username){
		
		detail_list = new ArrayList<Arb_detail>();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		NetHelper helper = new NetHelper("detail",params);
		JSONArray ret = helper.getJSONArrayByGet();
		
		for(int i=0;i<ret.length();i++){
			JSONObject temp = ret.getJSONObject(i);
			Arb_detail detail = new Arb_detail();
			
			detail.setSymbol(temp.getString("RT_CODE"));
			
			detail.setDate(temp.getInt("RT_DATE"));
			detail.setDay(temp.getInt("RT_TIME"));
			
			detail.setPresentPrice(temp.getDouble("RT_LAST"));
			detail.setChange(temp.getDouble("RT_PCT_CHG"));
			detail.setPriceChange(temp.getDouble("RT_CHG"));
			
			detail.setBidPirce(temp.getDouble("RT_BID1"));
			detail.setAskPrice(temp.getDouble("RT_ASK1"));
			detail.setBid(temp.getInt("RT_BSIZE1"));
			detail.setAsk(temp.getInt("RT_ASIZE1"));
			
			detail.setNvol(temp.getInt("RT_LAST_VOL"));
			detail.setVol(temp.getInt("RT_VOL"));
			
			detail.setPreClose(temp.getDouble("RT_PRE_CLOSE"));
			detail.setOpen(temp.getDouble("RT_OPEN"));
			detail.setHigh(temp.getDouble("RT_HIGH"));
			detail.setLow(temp.getDouble("RT_LOW"));
			detail.setFullAmount(temp.getDouble("RT_AMT"));
			detail.setPreSettlePrice(temp.getDouble("RT_PRE_SETTLE"));
			detail.setSettlePrice(temp.getDouble("RT_SETTLE"));
			detail.setSwing(temp.getDouble("RT_SWING"));
			detail.setRatio(temp.getDouble("RT_VOL_RATIO"));
			
			detail.setHardenPrice(temp.getDouble("RT_HIGH_LIMIT"));
			detail.setLimitPrice(temp.getDouble("RT_LOW_LIMIT"));
			detail.setOutvol(temp.getInt("RT_UPWARD_VOL"));
			detail.setInvol(temp.getInt("RT_DOWNWARD_VOL"));
			detail.setAverPrice(temp.getDouble("RT_VWAP"));
			
			detail_list.add(detail);
		}
		
		return detail_list;
	}
	
//	public Arb_detail getDetail(String id){
//		Arb_detail arb = new Arb_detail();
//		
//		//向服务器读取参数并进行设置,转换为Java类，完善VO
//		
//		return arb;
//	}
}
