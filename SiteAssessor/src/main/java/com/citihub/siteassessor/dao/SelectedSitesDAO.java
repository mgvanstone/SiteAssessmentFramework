package com.citihub.siteassessor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.citihub.siteassessor.SelectedSite;

/**
 * DAO for the portal exchanage database
 * 
 * @author citihubuser
 * 
 */
public class SelectedSitesDAO extends DAO {

	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public Map<String, SelectedSite> readSelectedSite(String siteid, String submitter) throws Exception {
		try {
			dbconnect();

			// Result set get the result of the SQL query
			PreparedStatement stmt = connect.prepareStatement("select siteid, submitter, isSelected from selectedsites where submitter = ? and siteid = ?");			

			stmt.setString(1, submitter);
			stmt.setString(2, siteid);				

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
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public Map<String, SelectedSite> readSelectedSites(String submitter) throws Exception {
		try {
			dbconnect();

			// Result set get the result of the SQL query
			PreparedStatement stmt = connect.prepareStatement("select siteid, submitter, isSelected from selectedsites where submitter = ?");			

			stmt.setString(1, submitter);	

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
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public void deleteSelectedSites(String submitter) throws Exception {
		try {
			dbconnect();

			// Result set get the result of the SQL query
			PreparedStatement stmt = connect.prepareStatement("delete from selectedsites where submitter = ?");	
			connect.setAutoCommit(true);

			stmt.setString(1, submitter);	

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
	 * Turns the result into a list of objects representing the rows.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Map<String, SelectedSite> writeResultSet(ResultSet resultSet)
			throws SQLException {
		// ResultSet is initially before the first data set
		HashMap<String, SelectedSite> map = new HashMap<String, SelectedSite>();

		while (resultSet.next()) {
			String submitter = resultSet.getString("submitter");
			String siteId = resultSet.getString("siteid");
			String isSelected = resultSet.getString("isSelected");			

			SelectedSite selectedSite = new SelectedSite();
			selectedSite.setSubmitter(submitter);
			selectedSite.setSiteid(siteId);
			if (isSelected.equals("true")) {
				selectedSite.setSelected(true);
			} else {
				selectedSite.setSelected(false);
			}

			map.put(siteId, selectedSite);
		}

		return map;
	}
	
	public void updateResults(String submitter, String siteId, boolean isSelected) throws Exception {
		try {
			dbconnect();

			PreparedStatement stmt = connect.prepareStatement("update isselected = ? from selectedsites where submitter = ? and siteid = ?");			

			stmt.setString(2, submitter);			
			stmt.setString(3, siteId);
			if (isSelected) 
				stmt.setString(1, "true");
			else
				stmt.setString(1, "false");

			stmt.executeUpdate();
			
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
	public void saveResults(String submitter, String siteId, boolean isSelected) throws Exception {
		try {
			dbconnect();

			PreparedStatement stmt = connect.prepareStatement("INSERT INTO selectedsites(submitter, siteid, isselected) VALUES (?,?,?)");
								
			stmt.setString(1, submitter);
			stmt.setString(2, siteId);
			if (isSelected) 
				stmt.setString(3, "true");
			else
				stmt.setString(3, "false");
		//	stmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));

			stmt.executeUpdate();
			stmt.close();
			
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
			e.printStackTrace();
		}
	}

}
