package com.dkte.pizzashop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL="jdbc:mysql://localhost:3306/pizzastore_db";
	private static final String USERNAME="root";
	private static final String PASSWORD=".Shyam@123";

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
}
