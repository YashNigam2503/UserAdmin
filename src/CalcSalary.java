
public class CalcSalary {
	
	private int hra;
	private int transport;
	private int entertainment;
	private int dearness;
	private int provident ;
	private int mediclaim;
	private int tds;
	
	public int gSalary(int basic,int general) {
		
			int sum=basic+hra+transport+dearness+general;
		
		return sum;
		
	 }
	
	public int total(int basic,int general) {
		 int tot=gSalary(basic,general)*12;
		 
		 return tot;
	 }
	
	
	public int netSalary(int basic,int general) {
		 int totalsalary=total(basic,general);
		 int med=mediclaim*12;
		 int prov=provident*12;
		 
		 int net =totalsalary-(prov+med+tds);
		 return net;
	}
	
	public int monthly(int basic,int general) {
		int net=netSalary(basic,general);
		int m=net/12;
		return m;
	}
	
	public int getHra() {
		return hra;
	}
	public void setHra(int basic) {
		this.hra = basic/2 ;
	}
	
	/*------------------*/
	
	public int getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(int basic) {
		this.entertainment = basic/2;
	}
	
	/*------------------*/
	
	public int getTransport() {
		return transport;
	}
	public void setTransport(int basic) {
		if(basic>=10000 && basic <=15000) {
			this.transport=5000;
		}else if(basic>15000 && basic<=20000) {
			this.transport=8000;
		}else if(basic>20000) {
			this.transport=12000;
		}else {
			this.transport=0;
		}
		
	}
	
	/*------------------*/
	
	public int getDearness() {
		return dearness;
	}
	public void setDearness() {
		this.dearness = 8000;
	}
	
	/*------------------*/
	
	public int getProvident() {
		return provident;
	}
	public void setProvident(int basic) {
		this.provident = basic/10;
	}
	
	/*------------------*/
	
	public int getMediclaim() {
		return mediclaim;
	}
	public void setMediclaim(int basic) {
		if(basic>=10000 && basic<=20000) {
			 this.mediclaim=500;
		 }else if(basic>20000) {
			this.mediclaim=800;
		 }
	}
	
	/*------------------*/
	
	public int getTds() {
		return tds;
	}
	public void setTds(int basic,int general) {
         int totalsalary=total(basic,general);
		 if(totalsalary>=500000 && totalsalary<=1000000) {
			 tds=totalsalary/20;
		 }else if(totalsalary>1000000) {
			 tds=totalsalary/10;
		 }
	}


	

}

