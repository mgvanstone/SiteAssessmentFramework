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
public class AnswerDAO extends DAO {


	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public void saveResults(Assessment assessment) throws Exception {
		try {
			dbconnect();

			//PreparedStatement stmt = connect.prepareStatement("INSERT INTO assessment(site_name, overall, electrical, mechanical, telecoms,	sitestructure, operations,	process, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO assessment(submitter, site_name) VALUES (?,?)");			
					
			stmt.setString(1, assessment.getSubmitter());
			stmt.setString(2, assessment.getName());
//			stmt.setString(3, assessment.getElectricalStatus());
//			stmt.setString(4, assessment.getMechanicalStatus());
//			stmt.setString(5, assessment.getTelecomsStatus());
//			stmt.setString(6, assessment.getSitestructureStatus());
//			stmt.setString(7, assessment.getOperationsStatus());
//			stmt.setString(8, assessment.getProcessStatus());		
//			stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));

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
