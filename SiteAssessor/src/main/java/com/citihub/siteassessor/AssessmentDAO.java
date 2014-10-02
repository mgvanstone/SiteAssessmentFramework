package com.citihub.siteassessor;

import java.sql.Date;
import java.sql.PreparedStatement;
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
public class AssessmentDAO extends DAO {


	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public void saveResults(Assessment assessment) throws Exception {
		try {
			dbconnect();

			PreparedStatement stmt = connect.prepareStatement("INSERT INTO assessment(submitter, site_name, submitdate) VALUES (?,?, ?)");			
					
			stmt.setString(1, assessment.getSubmitter());
			stmt.setString(2, assessment.getName());
			stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));

			stmt.executeUpdate();
			
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
