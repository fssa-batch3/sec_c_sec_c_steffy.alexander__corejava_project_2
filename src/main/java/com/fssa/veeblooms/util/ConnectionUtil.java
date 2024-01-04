package com.fssa.veeblooms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.fssa.veeblooms.exception.DAOException;

public class ConnectionUtil {

	public static Connection getConnection() throws DAOException {

		Connection con = null;

		String url;
		String userName;
		String passWord;

//		    String url = "jdbc:mysql://localhost:3306/veeblooms";
//	        String userName = "root";
//	        String passWord = "123456";

		// Check if the "CI" environment variable is set
		String environment = System.getenv("CI");
//
//		if (environment != null && environment.equalsIgnoreCase("true")) {
//			// Use cloud database credentials
//			url = System.getenv("DATABASE_HOST");
//			userName = System.getenv("DATABASE_USERNAME");
//			passWord = System.getenv("DATABASE_PASSWORD");
//		} else {
//			// Use local database credentials
//			url = System.getenv("LOCAL_DATABASE_HOST");
//			userName = System.getenv("LOCAL_DATABASE_USERNAME");
//			passWord = System.getenv("LOCAL_DATABASE_PASSWORD");
//		}
		
		url = System.getenv("LOCAL_DATABASE_HOST");
		userName = System.getenv("LOCAL_DATABASE_USERNAME");
		passWord = System.getenv("LOCAL_DATABASE_PASSWORD");

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			System.out.println("connection");

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Unable to connect to the database " + e.getMessage());
		}
		return con;

	}

}