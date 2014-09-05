package vo;

public class ArbGroup {
	private Arb_detail tobuy;
	private Arb_detail tosell;
	
	public ArbGroup(Arb_detail tobuy,Arb_detail tosell){
		this.tobuy = tobuy;
		this.tosell = tosell;
	}
	
//	public ArbGroup(String buy_id,String sell_id){
//		
//	}
	
	public Arb_detail getTobuy() {
		return tobuy;
	}

	public void setTobuy(Arb_detail tobuy) {
		this.tobuy = tobuy;
	}

	public Arb_detail getTosell() {
		return tosell;
	}

	public void setTosell(Arb_detail tosell) {
		this.tosell = tosell;
	}
}
