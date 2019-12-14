package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	public static Connection getConnection() {
		FileInputStream fileInputStream = null;
		try {
		//	fileInputStream = new FileInputStream("src/connection.properties");
			fileInputStream = new FileInputStream("C:/Users/828561/Test_Workspace/truYum - JDBC/src/connection.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("connection-url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		try {
		//	Class c = Class.forName(driver);
			DriverManager.registerDriver((Driver) Class.forName(driver).newInstance());
			return DriverManager.getConnection(url, user, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}