package com.citihub.siteassessor;

/**
 * List of the sites selected by the user
 * @author citihubuser
 *
 */
public class SitesSelected {

	private String submitter;
	boolean isComplete;
	String[] siteId;
	Boolean checked;
	

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
		
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "SitesSelected [submitter=" + submitter + ", isComplete="
				+ isComplete + ", siteid=" + siteId  + "]";
	}
}
