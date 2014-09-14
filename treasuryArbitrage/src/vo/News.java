package vo;

public class News {
	String title;
	String content;
	String source;
	long time;

	public News(String title,String content,String source,long time){
		this.title = title;
		this.content = content;
		this.source = source;
		this.time = time;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
