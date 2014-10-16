package com.citihub.siteassessor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DAO 
 * @author citihubuser
 *
 */
public abstract class DAO {
	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;

	static final boolean DEBUG = false;

	static final String TEST_DB = "jdbc:mysql://localhost/siteassessor?"
			+ "user=root&password=password";
	static final String PROD_DB = "jdbc:mysql://siteassessordb.ckdislntxpum.eu-west-1.rds.amazonaws.com/sitedb?"
			+ "user=citihub&password=Pa55word";

	/**
	 * Connects to the database
	 */
	void dbconnect() throws Exception {

		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB

		String db = PROD_DB;
		if (DEBUG) {
			db = TEST_DB;
		}
		connect = DriverManager.getConnection(db);
	}
}