package com.citihub.siteassessor;

import java.util.List;

public class Assessment {
	private String submitter;
	private String name;
	private String date;
	private String electricalStatus;
	private String mechanicalStatus;
	private String operationsStatus;
	private String sitestructureStatus;
	private String processStatus;
	private String telecomsStatus;
	private String overallStatus;
	private List<String> answer;
	private List<String> questionid;
	private List<String> comment;
	private String siteId;
	private boolean isSelected;
	
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getElectricalStatus() {
		return electricalStatus;
	}
	public void setElectricalStatus(String electricalStatus) {
		this.electricalStatus = electricalStatus;
	}
	public String getMechanicalStatus() {
		return mechanicalStatus;
	}
	public void setMechanicalStatus(String mechanicalStatus) {
		this.mechanicalStatus = mechanicalStatus;
	}
	public String getOperationsStatus() {
		return operationsStatus;
	}
	public void setOperationsStatus(String operationsStatus) {
		this.operationsStatus = operationsStatus;
	}
	public String getSitestructureStatus() {
		return sitestructureStatus;
	}
	public void setSitestructureStatus(String sitestructureStatus) {
		this.sitestructureStatus = sitestructureStatus;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getTelecomsStatus() {
		return telecomsStatus;
	}
	public void setTelecomsStatus(String telecomsStatus) {
		this.telecomsStatus = telecomsStatus;
	}
	public String getOverallStatus() {
		return overallStatus;
	}
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}
	public List<String> getAnswer() {
		return answer;
	}
	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}
	public List<String> getQuestionid() {
		return questionid;
	}
	public void setQuestionid(List<String> questionid) {
		this.questionid = questionid;
	}	
	public List<String> getComment() {
		return comment;
	}
	public void setComment(List<String> comment) {
		this.comment = comment;
	}
	
	
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	@Override
	public String toString() {
		return "Assessment [submitter=" + submitter + ", name=" + name
				+ ", date=" + date + ", electricalStatus=" + electricalStatus
				+ ", mechanicalStatus=" + mechanicalStatus
				+ ", operationsStatus=" + operationsStatus
				+ ", sitestructureStatus=" + sitestructureStatus
				+ ", processStatus=" + processStatus + ", telecomsStatus="
				+ telecomsStatus + ", overallStatus=" + overallStatus
				+ ", answer=" + answer + ", questionid=" + questionid
				+ ", comment=" + comment + ", siteId=" + siteId + "]";
	}

	
}
