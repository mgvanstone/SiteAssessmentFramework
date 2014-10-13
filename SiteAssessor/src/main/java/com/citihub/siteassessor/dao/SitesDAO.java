package com.citihub.siteassessor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.citihub.siteassessor.Site;


/**
 * DAO for the portal exchanage database
 * 
 * @author citihubuser
 * 
 */
public class SitesDAO extends DAO {


	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public List<Site> readSites() throws Exception {
		try {
			dbconnect();

			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select id, name, demand from site order by id asc");

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
	private ArrayList<Site> writeResultSet(ResultSet resultSet)
			throws SQLException {
		// ResultSet is initially before the first data set
		ArrayList<Site> list = new ArrayList<Site>();

		while (resultSet.next()) {
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			String demand = resultSet.getString("demand");

			Site dc = new Site(id, name, demand, "Not submitted", "");
			System.out.println(dc.toString());
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
