package edu.nju.treasuryArbitrage.model;

import edu.nju.treasuryArbitrage.model.tool.FormatTool;

import java.util.Calendar;
import java.util.Date;

public class CTPArbDetail {
    private static CTPArbDetail self;

    //交易日
    public String tradingDay;
    //合约代码
    public String instrumentID;
    //交易所代码
    public String exchangeID;
    //合约在交易所的代码
    public String exchangeInstID;
    //最新价
    public double lastPrice;
    //上次结算价
    public double preSettlementPrice;
    //昨收盘
    public double preClosePrice;
    //昨持仓量
    public double preOpenInterest;
    //今开盘
    public double openPrice;
    //最高价
    public double highestPrice;
    //最低价
    public double lowestPrice;
    //数量
    public double volume;
    //成交金额
    public double turnover;
    //持仓量
    public double openInterest;
    //今收盘
    public double closePrice;
    //本次结算价
    public double settlementPrice;
    //涨停板价
    public double upperLimitPrice;
    //跌停板价
    public double lowerLimitPrice;
    //昨虚实度
    public double preDelta;
    //今虚实度
    public double currDelta;
    //最后修改时间
    public String updateTime;
    //最后修改毫秒
    public double updateMillisec;
    //申买价一
    public double bidPrice1;
    //申买量一
    public double bidVolume1;
    //申卖价一
    public double askPrice1;
    //申卖量一
    public double askVolume1;
    //申买价一
    public double bidPrice2;
    //申买量一
    public double bidVolume2;
    //申卖价二
    public double askPrice2;
    //申卖量二
    public double askVolume2;
    //申买价三
    public double bidPrice3;
    //申买量三
    public double bidVolume3;
    //申卖价三
    public double askPrice3;
    //申卖量三
    public double askVolume3;
    //申买价四
    public double bidPrice4;
    //申买量四
    public double bidVolume4;
    //申卖价四
    public double askPrice4;
    //申卖量四
    public double askVolume4;
    //申买价五
    public double bidPrice5;
    //申买量五
    public double bidVolume5;
    //申卖价五
    public double askPrice5;
    //申卖量五
    public double askVolume5;
    //当日均价
    public double averagePrice;

    public void setData(String val, int loc) {
        switch (loc) {
            case 1:
                self.tradingDay = val;
                break;
            case 2:
                self.instrumentID = val;
                break;
            case 3:
                self.exchangeID = val;
                break;
            case 4:
                self.exchangeInstID = val;
                break;
            case 21:
                self.updateTime = val;
                break;
            default:
                System.err.println("Error!");
        }
    }

    public void setData(double val, int loc) {
        switch (loc) {
            case 5:
                self.lastPrice = val;
                break;
            case 6:
                self.preSettlementPrice = val;
                break;
            case 7:
                self.preClosePrice = val;
                break;
            case 8:
                self.preOpenInterest = val;
                break;
            case 9:
                self.openPrice = val;
                break;
            case 10:
                self.highestPrice = val;
                break;
            case 11:
                self.lowestPrice = val;
                break;
            case 12:
                self.volume = val;
                break;
            case 13:
                self.turnover = val;
                break;
            case 14:
                self.openInterest = val;
                break;
            case 15:
                self.closePrice = val;
                break;
            case 16:
                self.settlementPrice = val;
                break;
            case 17:
                self.upperLimitPrice = val;
                break;
            case 18:
                self.lowerLimitPrice = val;
                break;
            case 19:
                self.preDelta = val;
                break;
            case 20:
                self.currDelta = val;
                break;
            case 22:
                self.updateMillisec = val;
                break;
            case 23:
                self.bidPrice1 = val;
                break;
            case 24:
                self.bidVolume1 = val;
                break;
            case 25:
                self.askPrice1 = val;
                break;
            case 26:
                self.askVolume1 = val;
                break;
            case 27:
                self.bidPrice2 = val;
                break;
            case 28:
                self.bidVolume2 = val;
                break;
            case 29:
                self.askPrice2 = val;
                break;
            case 30:
                self.askVolume2 = val;
                break;
            case 31:
                self.bidPrice3 = val;
                break;
            case 32:
                self.bidVolume3 = val;
                break;
            case 33:
                self.askPrice3 = val;
                break;
            case 34:
                self.askVolume3 = val;
                break;
            case 35:
                self.bidPrice4 = val;
                break;
            case 36:
                self.bidVolume4 = val;
                break;
            case 37:
                self.askPrice4 = val;
                break;
            case 38:
                self.askVolume4 = val;
                break;
            case 39:
                self.bidPrice5 = val;
                break;
            case 40:
                self.bidVolume5 = val;
                break;
            case 41:
                self.askPrice5 = val;
                break;
            case 42:
                self.askVolume5 = val;
                break;
            case 43:
                self.averagePrice = val;
                break;
            default:
                System.err.println("Error!");
        }
    }

    private CTPArbDetail() {
    }

    public static CTPArbDetail getInstance() {
        if (self == null) {
            self = new CTPArbDetail();
        }
        return self;
    }

    public ArbDetail getArbDetail() {
        ArbDetail detail = new ArbDetail();

        String symbol = instrumentID;
        int year = Integer.valueOf(symbol.substring(2, 4));
        int month = Integer.valueOf(symbol.substring(4, 6));
        String monthAndDateString = 20 + "" + year + "年" + month + "月";
        detail.setSymbol(symbol);
        detail.setMonth(monthAndDateString);
        detail.setDate(monthAndDateString);
        //================================================
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String clockString = FormatTool.twoNumberFormat(calendar.get(Calendar.HOUR_OF_DAY)) + ":" +
                FormatTool.twoNumberFormat(calendar.get(Calendar.MINUTE)) + ":" +
                FormatTool.twoNumberFormat(calendar.get(Calendar.SECOND));
        detail.setClock(clockString);
        //================================================
        detail.setPresentPrice(lastPrice);
        //detail.setChange(change);
        //detail.setPriceChange(temp.getDouble("RT_CHG"));//û���ҵ�
        detail.setBidPirce(bidPrice1);
        detail.setBid((int) bidVolume1);
        detail.setAskPrice(askPrice1);
        detail.setAsk((int) askVolume1);

        detail.setVol((int) volume);
        detail.setPreRepository((int) preOpenInterest);
        detail.setRepository((int) openInterest);

        //detail.setDailyWarehouse(temp.getInt("RT_OI_CHG"));
        detail.setPreClose(preClosePrice);
        detail.setOpen(openPrice);
        detail.setHigh(highestPrice);
        detail.setLow(lowestPrice);
        detail.setFullAmount(turnover);
        detail.setPreSettlePrice(preSettlementPrice);

        //detail.setSwing(swing);
        detail.setHardenPrice(upperLimitPrice);
        detail.setLimitPrice(lowerLimitPrice);
        detail.setAverPrice(averagePrice);

        return detail;
    }
}
