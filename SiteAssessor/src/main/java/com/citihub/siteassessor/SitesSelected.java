package com.citihub.siteassessor;

import java.util.Arrays;
import java.util.List;

public class SitesSelected {

	private String submitter;
	boolean isComplete;
	
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
	@Override
	public String toString() {
		return "SitesSelected [submitter=" + submitter + ", isGay=" + isComplete
				+ ", siteStatus=" + Arrays.toString(siteStatus) + "]";
	}	
}
