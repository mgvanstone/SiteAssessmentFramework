package com.citihub.siteassessor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.citihub.siteassessor.User;


/**
 * DAO for the portal exchanage database
 * 
 * @author citihubuser
 * 
 */
public class UsersDAO extends DAO {


	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public List<User> readUser() throws Exception {
		try {
			dbconnect();

			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select id, user, password, status from users");

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
	private ArrayList<User> writeResultSet(ResultSet resultSet)
			throws SQLException {
		// ResultSet is initially before the first data set
		ArrayList<User> list = new ArrayList<User>();

		while (resultSet.next()) {
			String id = resultSet.getString("id");
			String user = resultSet.getString("user");
			String password = resultSet.getString("password");
			String status = resultSet.getString("status");			

			User dc = new User(id, user, password, status);
			//System.out.println(dc.toString());
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
