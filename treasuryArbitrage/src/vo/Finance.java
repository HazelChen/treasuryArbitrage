package vo;

public class Finance {
	String time;	//ʱ��
	double total;	//���ʽ�
	double guarantee;	//��Ͷ�뱣֤��
	double idle; 	//�����ʽ�
	
	public Finance(String time,double total,double guarantee,double idle){
		this.time = time;
		this.total = total;
		this.guarantee = guarantee;
		this.idle = idle;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(double guarantee) {
		this.guarantee = guarantee;
	}

	public double getIdle() {
		return idle;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}
}
