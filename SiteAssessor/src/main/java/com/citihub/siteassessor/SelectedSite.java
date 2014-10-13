package com.citihub.siteassessor;

public class SelectedSite {
	public String id;
	public String submitter;
	public String siteid;
	public boolean isSelected;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	@Override
	public String toString() {
		return "SelectedSite [id=" + id + ", submitter=" + submitter
				+ ", siteid=" + siteid + ", isSelected=" + isSelected + "]";
	}	
}
