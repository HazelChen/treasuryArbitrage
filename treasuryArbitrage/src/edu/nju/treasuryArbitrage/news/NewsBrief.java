package edu.nju.treasuryArbitrage.news;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsBrief{
	String newsID;
	private Date date;
	private String sdate;
	private String src,title,author;
	//DateFormat("yyyy/MM/dd");//小写的mm表示的是分钟  
	
	
	public NewsBrief(){
		this.newsID = "";
		this.setDate(null);
		this.setSdate();
		this.setTitle(null);
		this.setSrc(null);
		this.setSrc(null);
	}
	public NewsBrief(String ID,Date date,String src,String title,String author){
		this.newsID = ID;
		this.setDate(date);
		this.setSdate();
		this.setTitle(title);
		this.setSrc(src);
		this.setAuthor(author);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
		if(this.date != null)this.sdate = sdf.format(this.date);
		else this.sdate = "";
		
		//this.sdate = sdf.format(this.getDate());
	}
};