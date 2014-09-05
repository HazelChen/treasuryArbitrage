package edu.nju.treasuryArbitrage.news;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsBrief{
	int newsID;
	private Date date;
	private String sdate;
	private String src,title,author;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//小写的mm表示的是分钟  
	
	public NewsBrief(){
		this.setDate(null);
		this.setSdate();
		this.setTitle(null);
		this.setSrc(null);
		this.setSrc(null);
	}
	public NewsBrief(Date date,String src,String title,String author){
		this.setDate(date);
		this.setSdate();
		this.setTitle(title);
		this.setSrc(author);
		this.setSrc(src);
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
		//this.sdate = sdf.format(this.getDate());
	}
};