package edu.nju.treasuryArbitrage.model;


public class ArbDetail {
	String symbol;			//***国债期货代码
	String month;			//***交割月份
	String date;			//***日期
	String clock;			//***时间

	double presentPrice;	//***现价（即“成交”、“合约价格”）
	double change;			//***涨跌幅（即涨幅）
	double	priceChange;	//***跌涨

	double bidPrice;		//***买价
	double askPrice;		//***卖价
	int bid;				//***买量
	int ask;				//***卖量

 	int vol;				//***成交量（即总手）
	int preRepository;		//***昨持仓
	int repository;			//***持仓量
	
	int dailyWarehouse;		//***日增仓
	double preClose;		//***昨收
	double open;			//***开盘（即今开）
	double high;			//***最高
	double low;				//***最低
	double fullAmount;		//***金额
	double preSettlePrice;	//***前结算价（即昨结）
	double swing;			//***振幅


	double hardenPrice;		//***涨停
	double limitPrice;		//***跌停

	double averPrice;		//***均价
	
	
	public ArbDetail(){}
	
	
	private ArbDetail cloneArb() {
		ArbDetail arbDetail = new ArbDetail();
		arbDetail.symbol = this.symbol;
		arbDetail.month = this.month;
		arbDetail.date = this.date;
		arbDetail.clock = this.clock;
		arbDetail.presentPrice = this.presentPrice;
		arbDetail.change = this.change;
		arbDetail.priceChange = this.priceChange;
		arbDetail.bidPrice = this.bidPrice;
		arbDetail.askPrice = this.askPrice;
		arbDetail.bid = this.bid;
		arbDetail.ask = this.ask;
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
		arbDetail.swing = this.swing;
		arbDetail.hardenPrice = this.hardenPrice;
		arbDetail.limitPrice = this.limitPrice;
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

    public static ArbDetail nullObject(String symbol) {
        ArbDetail arb_detail = new ArbDetail();
        arb_detail.symbol = symbol;
        arb_detail.month = "-";
        arb_detail.date = "-";
        arb_detail.clock = "-";
        return arb_detail;
    }
}