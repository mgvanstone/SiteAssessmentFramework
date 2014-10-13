package com.citihub.siteassessor.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.citihub.siteassessor.Assessment;
import com.citihub.siteassessor.Question;
import com.citihub.siteassessor.Reference;

/**
 * DAO for the portal exchanage database
 * 
 * @author citihubuser
 * 
 */
public class AnswerDAO extends DAO {


	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public void saveResults(List<Question> questionList, Assessment assessment) throws Exception {
		try {
			dbconnect();

			PreparedStatement stmt = connect.prepareStatement("INSERT INTO answer(submitter, sitename, submitdate, answer, questionorder, question, comment) VALUES (?,?,?,?,?,?,?)");			
					
			Iterator<String> ansIt = assessment.getAnswer().iterator();
			Iterator<String> questionIt = assessment.getQuestionid().iterator();
			Iterator<Question> questionXIt = questionList.iterator();
			Iterator<String> commentIt = assessment.getComment().iterator();	
			
			// Loop over the answers
			while (ansIt.hasNext()) {				
				stmt.setString(1, assessment.getSubmitter());
				stmt.setString(2, assessment.getName());
				stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				stmt.setInt(5, Integer.parseInt(questionIt.next()));
				
				Question question = questionXIt.next();
				stmt.setString(6, question.getQuestion());				
				String answer = (String)ansIt.next();
				if (question.getReferenceList().size() == 0) {
				//	System.out.println("In there");
					stmt.setString(4, answer);
				} else {
					// Look up the reference value
					List<Reference> refList = question.getReferenceList();
				//	System.out.println("In here " + refList.size());
					if (!answer.equals("") && answer != null) {
						// Only lookup if the a value has been selected
						stmt.setString(4, refList.get(Integer.parseInt(answer)).getRequirement());
					} else {
						stmt.setString(4, "No answer");
					}
				}
				stmt.setString(7, commentIt.next());				
				
				stmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close();
		}
	}
	

	// Close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
