package com.citihub.siteassessor.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.citihub.siteassessor.Answer;
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
	 * Delete users answers for a site from the database
	 * 
	 * @throws Exception
	 */
	public void deleteUserAnswers(String submitter, String siteId) throws Exception {
		try {
			dbconnect();

			// Result set get the result of the SQL query
			PreparedStatement stmt = connect.prepareStatement("delete from selectedsites where submitter = ? and siteid = ?");	
			connect.setAutoCommit(true);

			stmt.setString(1, submitter);
			stmt.setString(2, siteId);				

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close();
		}
	}

	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public void saveResults(List<Question> questionList, Assessment assessment) throws Exception {
		try {
			dbconnect();

			PreparedStatement stmt = connect.prepareStatement("INSERT INTO answer(submitter, sitename, submitdate, answer, questionorder, question, comment, questionid, siteId) VALUES (?,?,?,?,?,?,?,?,?)");			
					
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
				stmt.setString(8, question.getId());
				stmt.setString(9, assessment.getSiteId());				
				
				stmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close();
		}
	}

	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public Map<String, Answer> readAnswers(String submitter, String siteid) throws Exception {
		try {
			dbconnect();

			PreparedStatement stmt = connect.prepareStatement("select submitter, sitename, submitdate, answer, questionorder, question, comment, questionid, siteid from answer where siteid = ? and submitter = ?");			
					
			connect.setAutoCommit(true);

			stmt.setString(1, siteid);			
			stmt.setString(2, submitter);				

			ResultSet rs = stmt.executeQuery();
				
			return writeResultSet(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close();
		}
	}

	/**
	 * Turns the result into a list of objects representing the rows.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Map<String, Answer> writeResultSet(ResultSet resultSet)
			throws SQLException {
		// ResultSet is initially before the first data set
		HashMap<String, Answer> map = new HashMap<String,Answer>();

		while (resultSet.next()) {
			String questionid = resultSet.getString("questionid");
			String comment = resultSet.getString("comment");
			String answer = resultSet.getString("answer");			

			Answer ass = new Answer(questionid, answer, comment);
			System.out.println(ass);

			map.put(questionid, ass);
		}

		return map;
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
			e.printStackTrace();
		}
	}

}
