package com.framework.utilities;

import java.sql.Connection;

import java.sql.DriverManager;

public class DBHelper {

	public static Connection connectDB() throws Exception {

		Connection con =

				DriverManager.getConnection(

						"jdbc:mysql://localhost:3306/testdb",

						"root",

						"password");

		return con;
	}
}