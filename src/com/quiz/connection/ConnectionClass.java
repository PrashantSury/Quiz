package com.quiz.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

	public Connection getConnectionDetails() throws ClassNotFoundException, SQLException {

		// 1. Loading Driver Class
		Class.forName("com.mysql.jdbc.Driver");

		// 2. Connection URL
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","Root@123#");

		return conn;
	}
}
