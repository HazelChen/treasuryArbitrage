package edu.nju.treasuryArbitrage.model.ctp;

public class CThostFtdcDepthMarketDataField{
	private static CThostFtdcDepthMarketDataField self;
	
	///交易日
	public String	TradingDay;
	///合约代码
	public String	InstrumentID;
	///交易所代码
	public String	ExchangeID;
	///合约在交易所的代码
	public String	ExchangeInstID;
	///最新价
	public double	LastPrice;
	///上次结算价
	public double	PreSettlementPrice;
	///昨收盘
	public double	PreClosePrice;
	///昨持仓量
	public double	PreOpenInterest;
	///今开盘
	public double	OpenPrice;
	///最高价
	public double	HighestPrice;
	///最低价
	public double	LowestPrice;
	///数量
	public double	Volume;
	///成交金额
	public double	Turnover;
	///持仓量
	public double	OpenInterest;
	///今收盘
	public double	ClosePrice;
	///本次结算价
	public double	SettlementPrice;
	///涨停板价
	public double	UpperLimitPrice;
	///跌停板价
	public double	LowerLimitPrice;
	///昨虚实度
	public double	PreDelta;
	///今虚实度
	public double	CurrDelta;
	///最后修改时间
	public String	UpdateTime;
	///最后修改毫秒
	public double	UpdateMillisec;
	///申买价一
	public double	BidPrice1;
	///申买量一
	public double	BidVolume1;
	///申卖价一
	public double	AskPrice1;
	///申卖量一
	public double	AskVolume1;
	///申买价二
	public double	BidPrice2;
	///申买量二
	public double	BidVolume2;
	///申卖价二
	public double	AskPrice2;
	///申卖量二
	public double	AskVolume2;
	///申买价三
	public double	BidPrice3;
	///申买量三
	public double	BidVolume3;
	///申卖价三
	public double	AskPrice3;
	///申卖量三
	public double	AskVolume3;
	///申买价四
	public double	BidPrice4;
	///申买量四
	public double	BidVolume4;
	///申卖价四
	public double	AskPrice4;
	///申卖量四
	public double	AskVolume4;
	///申买价五
	public double	BidPrice5;
	///申买量五
	public double	BidVolume5;
	///申卖价五
	public double	AskPrice5;
	///申卖量五
	public double	AskVolume5;
	///当日均价
	public double	AveragePrice;
	
	public void setData(String val,int loc){
		switch (loc){
		case 1:
			self.TradingDay = val;
			break;
		case 2:
			self.InstrumentID = val;
			break;
		case 3:
			self.ExchangeID = val;
			break;
		case 4:
			self.ExchangeInstID = val;
			break;
		case 21:
			self.UpdateTime = val;
			break;
		default:
			System.err.println("Error!");
		}
	}
	
	public void setData(double val,int loc){
		switch (loc){
		case 5:
			self.LastPrice = val;
			break;
		case 6:
			self.PreSettlementPrice = val;
			break;
		case 7:
			self.PreClosePrice = val;
			break;
		case 8:
			self.PreOpenInterest = val;
			break;
		case 9:
			self.OpenPrice = val;
			break;
		case 10:
			self.HighestPrice = val;
			break;
		case 11:
			self.LowestPrice = val;
			break;
		case 12:
			self.Volume = val;
			break;
		case 13:
			self.Turnover = val;
			break;
		case 14:
			self.OpenInterest = val;
			break;
		case 15:
			self.ClosePrice = val;
			break;
		case 16:
			self.SettlementPrice = val;
			break;
		case 17:
			self.UpperLimitPrice = val;
			break;
		case 18:
			self.LowerLimitPrice = val;
			break;
		case 19:
			self.PreDelta = val;
			break;
		case 20:
			self.CurrDelta = val;
			break;
		case 22:
			self.UpdateMillisec = val;
			break;
		case 23:
			self.BidPrice1 = val;
			break;
		case 24:
			self.BidVolume1 = val;
			break;
		case 25:
			self.AskPrice1 = val;
			break;
		case 26:
			self.AskVolume1 = val;
			break;
		case 27:
			self.BidPrice2 = val;
			break;
		case 28:
			self.BidVolume2 = val;
			break;
		case 29:
			self.AskPrice2 = val;
			break;
		case 30:
			self.AskVolume2 = val;
			break;
		case 31:
			self.BidPrice3 = val;
			break;
		case 32:
			self.BidVolume3 = val;
			break;
		case 33:
			self.AskPrice3 = val;
			break;
		case 34:
			self.AskVolume3 = val;
			break;
		case 35:
			self.BidPrice4 = val;
			break;
		case 36:
			self.BidVolume4 = val;
			break;
		case 37:
			self.AskPrice4 = val;
			break;
		case 38:
			self.AskVolume4 = val;
			break;
		case 39:
			self.BidPrice5 = val;
			break;
		case 40:
			self.BidVolume5 = val;
			break;
		case 41:
			self.AskPrice5 = val;
			break;
		case 42:
			self.AskVolume5 = val;
			break;
		case 43:
			self.AveragePrice = val;
			break;
		default:
			System.err.println("Error!");
		}
	}
	
	private CThostFtdcDepthMarketDataField(){}
	
	public static CThostFtdcDepthMarketDataField getInstance(){
		if(self == null){
			self = new CThostFtdcDepthMarketDataField();
		}
		return self;
	}
	
	public snapshotOfData getSnapshot(){
		snapshotOfData data = new snapshotOfData(self);
		return data;
	}
}
