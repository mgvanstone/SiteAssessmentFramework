package com.citihub.siteassessor;

public class Answer {
	private String questionId;
	private String answer;
	private String comment;
	
	
	public Answer(String questionId, String answer, String comment) {
		super();
		this.questionId = questionId;
		this.answer = answer;
		this.comment = comment;
	}

	public String getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Answer [questionId=" + questionId + ", answer=" + answer
				+ ", comment=" + comment + "]";
	}
	
	
}
