package com.citihub.siteassessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * DAO for the portal exchanage database
 * 
 * @author citihubuser
 * 
 */
public class QuestionDAO extends DAO {


	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public List<Question> readQuestions() throws Exception {
		try {
			dbconnect();

			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select id,question_order,question, reference, category, subcategory, helptext from question order by category, question_order");

			return writeResultSet(resultSet);
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
	private ArrayList<Question> writeResultSet(ResultSet resultSet)
			throws SQLException {
		// ResultSet is initially before the first data set
		ArrayList<Question> list = new ArrayList<Question>();

		while (resultSet.next()) {
			String id = resultSet.getString("id");
			int question_order = resultSet.getInt("question_order");
			String question = resultSet.getString("question");
			String reference = resultSet.getString("reference");
			String category = resultSet.getString("category");
			String subcategory = resultSet.getString("subcategory");
			String helptext = resultSet.getString("helptext");

			Question dc = new Question();
			dc.setQuestion(question);
			
			List<Reference> refList = new ArrayList<Reference>();
			StringTokenizer tok = new StringTokenizer(reference, ",");

			int tier = 0;
			
			Reference ref = new Reference();
			
			while (tok.hasMoreTokens()) {

				String val = tok.nextToken();
				if (tier == 0 && val.equals("yn")) {
					ref = new Reference();
					ref.setRequirement("Yes");
					refList.add(ref);
					ref = new Reference();
					ref.setRequirement("No");
					refList.add(ref);
					break;
				} else {
					ref = new Reference();					
					tier++;				
					ref.setTier(tier);
					//String dd = "Tier " + tier + " | " + val;
					String dd = val;					
					if (dd.length() > 40) {
						ref.setRequirement(dd.substring(0, 40));
					} else {
						ref.setRequirement(dd);
					}
			//		helptext.append("Tier " + tier + ": " + val + "<br/>");
					refList.add(ref);					
				}
				
			}
			
			dc.setId(id);
			dc.setReferenceList(refList);
			dc.setCategory(category);
			dc.setSubcategory(subcategory);
			dc.setHelptext(helptext);
			dc.setQuestion_order(question_order);
//			if (DEBUG) {
//				System.out.println(dc);
//			}
			list.add(dc);
		}

		return list;
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
