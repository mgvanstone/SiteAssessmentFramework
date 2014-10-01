package com.citihub.siteassessor;


public class Site {
	private String id;
	private String name;
	private String demand;
	private String status;
	private String pricing;
	
	
	public Site(String id, String name, String demand, String status, String pricing) {
		super();
		this.id = id;
		this.name = name;
		this.demand = demand;
		this.status = status;
		this.pricing = pricing;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getPricing() {
		return pricing;
	}

	public void setPricing(String pricing) {
		this.pricing = pricing;
	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", name=" + name + ", demand=" + demand
				+ ", status=" + status + ", pricing=" + pricing + "]";
	}	

}
