package com.citihub.siteassessor;

import java.util.List;

/**
 * List of questions and contains any existing responses
 * @author citihubuser
 *
 */
public class Question {
	private String id;
	private int question_order;
	private String question;
	private List<Reference> referenceList;
	private String category;
	private String subcategory;
	private String helptext;
	private String answer;
	private String comment;
	private String questionType;

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
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

	public int getQuestion_order() {
		return question_order;
	}

	public void setQuestion_order(int question_order) {
		this.question_order = question_order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHelptext() {
		return helptext;
	}

	public void setHelptext(String helptext) {
		this.helptext = helptext;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Reference> getReferenceList() {
		return referenceList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public void setReferenceList(List<Reference> referenceList) {
		this.referenceList = referenceList;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question_order=" + question_order
				+ ", question=" + question + ", referenceList=" + referenceList
				+ ", category=" + category + ", subcategory=" + subcategory
				+ ", helptext=" + helptext + ", answer=" + answer
				+ ", comment=" + comment + ", questionType=" + questionType
				+ "]";
	}	
}
