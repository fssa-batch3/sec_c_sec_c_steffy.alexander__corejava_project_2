package com.fssa.veeblooms.dao;

import java.sql.Connection;
import com.fssa.veeblooms.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ConnectionUtil {

	public static Connection getConnection() throws CustomException {

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/veeblooms";
		String userName = "root";
		String passWord = "123456";
		try {
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Unable to connect to the database");
		}
		return con;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// No need re throw the exception.
		}
	}
}