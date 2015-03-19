package edu.nju.treasuryArbitrage.model.ctp;

public class snapshotOfData {
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
		
		public snapshotOfData(CThostFtdcDepthMarketDataField data){
			this.AskPrice1 = data.AskPrice1;
			this.AskPrice2 = data.AskPrice2;
			this.AskPrice3 = data.AskPrice3;
			this.AskPrice4 = data.AskPrice4;
			this.AskPrice5 = data.AskPrice5;
			this.AskVolume1 = data.AskVolume1;
			this.AskVolume2 = data.AskVolume2;
			this.AskVolume3 = data.AskVolume3;
			this.AskVolume4 = data.AskVolume4;
			this.AskVolume5 = data.AskVolume5;
			this.AveragePrice = data.AveragePrice;
			this.BidPrice1 = data.BidPrice1;
			this.BidPrice2 = data.BidPrice2;
			this.BidPrice3 = data.BidPrice3;
			this.BidPrice4 = data.BidPrice4;
			this.BidPrice5 = data.BidPrice5;
			this.BidVolume1 = data.BidVolume1;
			this.BidVolume2 = data.BidVolume2;
			this.BidVolume3 = data.BidVolume3;
			this.BidVolume4 = data.BidVolume4;
			this.BidVolume5 = data.BidVolume5;
			this.ClosePrice = data.ClosePrice;
			this.CurrDelta = data.CurrDelta;
			this.ExchangeID = data.ExchangeID;
			this.ExchangeInstID = data.ExchangeInstID;
			this.HighestPrice = data.HighestPrice;
			this.InstrumentID = data.InstrumentID;
			this.LastPrice = data.LastPrice;
			this.LowerLimitPrice = data.LowerLimitPrice;
			this.LowestPrice = data.LowestPrice;
			this.OpenInterest = data.OpenInterest;
			this.OpenPrice = data.OpenPrice;
			this.PreClosePrice = data.PreClosePrice;
			this.PreDelta = data.PreDelta;
			this.PreOpenInterest = data.PreOpenInterest;
			this.PreSettlementPrice = data.PreSettlementPrice;
			this.SettlementPrice = data.SettlementPrice;
			this.TradingDay = data.TradingDay;
			this.Turnover = data.Turnover;
			this.UpdateMillisec = data.UpdateMillisec;
			this.UpdateTime = data.UpdateTime;
			this.UpperLimitPrice = data.UpperLimitPrice;
			this.Volume = data.Volume;
		}
}
