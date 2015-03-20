package edu.nju.treasuryArbitrage.model;


public class ArbDetail {
	String symbol;			//***��ծ�ڻ�����
	String month;			//***�����·�
	
	//==========================================================
	//˵��1��ȥ��time����ԭ����day��Ϊclock;date,clock��ΪString����
	//˵��2��change��swingȡ��Ϊ��%�ŵ�String����ת��Ϊdouble����ʹ��ʱ��Ҫ ����*100�����%��ʾʵ�ʺ���
	//==========================================================
	String date;			//***����
	String clock;				//***ʱ��

	double presentPrice;	//***�ּۣ����ּۡ����ڡ��ɽ������ڡ���Լ�۸񡱣�ͳһ���ּۣ�
	double change;			//***�ǵ������Ƿ������ǵ�����ͳһ���ǵ�����
	double	priceChange;	//***����

	double bidPrice;		//***���
	double askPrice;		//***����
	int bid;				//***����
	int ask;				//***����

	//==========================================================
	//˵��3��ȥ��nvol,settlePrice,ratio,outvol,invol
	//==========================================================
 	int vol;				//***�ɽ��������ֵ��ڳɽ�����ͳһ�ɳɽ�����
	int preRepository;		//***��ֲ�
	int repository;			//***�ֲ���
	
	int dailyWarehouse;		//***������
	double preClose;		//***����
	double open;			//***���̣��񿪵��ڿ��̣�ͳһ�ɿ��̣�
	double high;			//***���
	double low;				//***���
	double fullAmount;		//***���
	double preSettlePrice;	//***ǰ����ۣ�������ǰ����ۣ�ͳһ��ǰ����ۣ�
	double swing;			//***���


	double hardenPrice;		//***��ͣ
	double limitPrice;		//***��ͣ	

	double averPrice;		//***����
	
	
	public ArbDetail(){}
	
	
	private ArbDetail cloneArb() {
		ArbDetail arbDetail = new ArbDetail();
		arbDetail.symbol = this.symbol;
		arbDetail.month = this.month;
		arbDetail.date = this.date;
		arbDetail.clock = this.clock;
		//arbDetail.time = this.time;
		arbDetail.presentPrice = this.presentPrice;
		arbDetail.change = this.change;
		arbDetail.priceChange = this.priceChange;
		arbDetail.bidPrice = this.bidPrice;
		arbDetail.askPrice = this.askPrice;
		arbDetail.bid = this.bid;
		arbDetail.ask = this.ask;
		//arbDetail.nvol = this.nvol;
		arbDetail.vol = this.vol;
		arbDetail.preRepository = this.preRepository;
		arbDetail.repository = this.repository;
		arbDetail.dailyWarehouse = this.dailyWarehouse;
		arbDetail.preClose = this.preClose;
		arbDetail.open = this.open;
		arbDetail.high = this.high;
		arbDetail.low = this.low;
		arbDetail.fullAmount = this.fullAmount;
		arbDetail.preSettlePrice = this.preSettlePrice;
		//arbDetail.settlePrice = this.settlePrice;
		arbDetail.swing = this.swing;
		//arbDetail.ratio = this.ratio;
		arbDetail.hardenPrice = this.hardenPrice;
		arbDetail.limitPrice = this.limitPrice;
		//arbDetail.outvol = this.outvol;
		//arbDetail.invol = this.invol;
		arbDetail.averPrice = this.averPrice;
		return arbDetail;
	}
	
	public ArbDetail getFormattedArb_detail() {
		ArbDetail arbDetail = cloneArb();
		
		arbDetail.presentPrice = format(presentPrice);
		arbDetail.change = format(change);
		arbDetail.bidPrice = format(bidPrice);
		arbDetail.preSettlePrice = format(preSettlePrice);
		arbDetail.high = format(high);
		arbDetail.low = format(low);
		arbDetail.swing = format(swing);
		arbDetail.fullAmount = format(fullAmount);
		
		return arbDetail;
	}
	
	private double format(double num) {
		return (int)(num * 1000) / 1000.0;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}
	
	public double getPresentPrice() {
		return presentPrice;
	}

	public void setPresentPrice(double presentPrice) {
		this.presentPrice = presentPrice;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(double priceChange) {
		this.priceChange = priceChange;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getAsk() {
		return ask;
	}

	public void setAsk(int ask) {
		this.ask = ask;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public int getPreRepository() {
		return preRepository;
	}

	public void setPreRepository(int preRepository) {
		this.preRepository = preRepository;
	}

	public int getRepository() {
		return repository;
	}

	public void setRepository(int repository) {
		this.repository = repository;
	}

	public int getDailyWarehouse() {
		return dailyWarehouse;
	}

	public void setDailyWarehouse(int dailyWarehouse) {
		this.dailyWarehouse = dailyWarehouse;
	}

	public double getPreClose() {
		return preClose;
	}

	public void setPreClose(double preClose) {
		this.preClose = preClose;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getFullAmount() {
		return fullAmount;
	}

	public void setFullAmount(double fullAmount) {
		this.fullAmount = fullAmount;
	}

	public double getPreSettlePrice() {
		return preSettlePrice;
	}

	public void setPreSettlePrice(double preSettlePrice) {
		this.preSettlePrice = preSettlePrice;
	}

	public double getSwing() {
		return swing;
	}

	public void setSwing(double swing) {
		this.swing = swing;
	}

	public double getHardenPrice() {
		return hardenPrice;
	}

	public void setHardenPrice(double hardenPrice) {
		this.hardenPrice = hardenPrice;
	}

	public double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public double getAverPrice() {
		return averPrice;
	}

	public void setAverPrice(double averPrice) {
		this.averPrice = averPrice;
	}

	public static ArbDetail nullObject() {
		ArbDetail arb_detail = new ArbDetail();
		arb_detail.symbol = "-";
		arb_detail.month = "-";
		arb_detail.date = "-";
		arb_detail.clock = "-";
		return arb_detail;
	}
}