package com.citihub.siteassessor;

import java.util.Arrays;
import java.util.List;

public class SitesSelected {

	private String submitter;
	boolean isComplete;
	String[] siteId;
	boolean checked;
	

	public String[] getSiteId() {
		return siteId;
	}
	public void setSiteId(String[] siteId) {
		this.siteId = siteId;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	String [] siteStatus;
		
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	
	public String[] getSiteStatus() {
		return siteStatus;
	}
	public void setSiteStatus(String[] siteStatus) {
		this.siteStatus = siteStatus;
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "SitesSelected [submitter=" + submitter + ", isComplete="
				+ isComplete + ", siteid=" + siteId + ", siteStatus="
				+ Arrays.toString(siteStatus) + "]";
	}
}
