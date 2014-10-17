package com.citihub.siteassessor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.citihub.siteassessor.User;


/**
 * DAO for the users
 * 
 * @author citihubuser
 * 
 */
public class UsersDAO extends DAO {

	private static final String QUERY = "select id, user, password, status from users";
	private static final String QUERY_USER = "select id, user, password, status from users where user = ?";	
	
	/**
	 * Read the database
	 * 
	 * @throws Exception
	 */
	public User readUser(String user) throws Exception {
		try {
			dbconnect();

			PreparedStatement stmt = connect.prepareStatement(QUERY_USER);			

			stmt.setString(1, user);				

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
	private User writeResultSet(ResultSet resultSet)
			throws SQLException {
		// ResultSet is initially before the first data set
		User user = null;

		while (resultSet.next()) {
			String id = resultSet.getString("id");
			String username = resultSet.getString("user");
			String password = resultSet.getString("password");
			String status = resultSet.getString("status");			

			user = new User(id, username, password, status);
		}

		return user;
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
