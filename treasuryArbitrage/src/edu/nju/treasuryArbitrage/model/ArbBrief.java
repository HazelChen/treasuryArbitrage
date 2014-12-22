package edu.nju.treasuryArbitrage.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ArbBrief {
	String symbol;
	String time;
	double price;
	
	public ArbBrief(String symbol,String time,double price){
		this.symbol = symbol;
		this.time = time;
		this.price = price;
	}
	
	public Date getTodayDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		try {
			Date aDayDate = dateFormat.parse(time);
			Calendar aDayCalendar = Calendar.getInstance();
			aDayCalendar.setTime(aDayDate);

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,
					aDayCalendar.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, aDayCalendar.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, aDayCalendar.get(Calendar.SECOND));
			date = calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return time + " " + price;
	}
}
