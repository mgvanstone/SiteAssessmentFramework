package com.citihub.siteassessor;

public class Reference {
	private int tier;
	private String requirement;
	public int getTier() {
		return tier;
	}
	public void setTier(int tier) {
		this.tier = tier;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	@Override
	public String toString() {
		return "Reference [tier=" + tier + ", requirement=" + requirement + "]";
	}
}
