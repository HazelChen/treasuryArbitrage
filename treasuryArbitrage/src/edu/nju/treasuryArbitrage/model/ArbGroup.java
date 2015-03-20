package edu.nju.treasuryArbitrage.model;

public class ArbGroup {
	private String recent;
	private String far;
	private boolean isRecentBuy;
	
	public ArbGroup(String recent,String far, boolean isRecentBuy){
		this(recent, far);
		this.isRecentBuy = isRecentBuy;
	}

	public ArbGroup(String recent, String far) {
		this.recent = recent;
		this.far = far;
	}

	public String getRecent() {
		return recent;
	}

	public void setRecent(String recent) {
		this.recent = recent;
	}

	public String getFar() {
		return far;
	}

	public void setFar(String far) {
		this.far = far;
	}

	public boolean isRecentBuy() {
		return isRecentBuy;
	}

	public void setRecentBuy(boolean isRecentBuy) {
		this.isRecentBuy = isRecentBuy;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ArbGroup)) {
			return false;
		}
		
		ArbGroup other = (ArbGroup)o;
		if (other.recent.equals(this.recent) && other.far.equals(this.far)) {
			return true;
		} else {
			return false;
		}
	}
}
