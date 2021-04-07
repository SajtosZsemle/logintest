package com.logintest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



public class DBHandler {

	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/userslogin";
	private static final String DB_USER = "root";
	private static final String DB_PWD = "root";
	private Connection conn = null;
	
	
	public DBHandler() throws SQLException {
		/** Create Connection */
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
	}
	
	public boolean addUser(final String email, final String pwd)	throws SQLException, AddressException, MessagingException {

		boolean result = true;

		PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
		// email = "'" + email + "'";
		// pwd = "'" + pwd + "'";

		stmt2.setString(1, email);

		ResultSet rs = stmt2.executeQuery();

		if(rs.next() == true) {
			result = false;
			System.out.println("Létezik ilyen regisztráció");
		}
		
		
	/*	while (rs.next()) {

			result = false;
			System.out.println("Létezik ilyen regisztráció");
			// break;
		}
*/
		rs.close();
		stmt2.close();

		if (result == true) {
			System.out.println("Regisztráció sikeres");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUE (null, ?, ?);");

			// email = "'" + email + "'";
			// pwd = "'" + pwd + "'";
			stmt.setString(1, email);
			stmt.setString(2, pwd);
			stmt.executeUpdate();


			stmt.close();
		}
		return result;
	}

	public boolean loginUser(String email, String pwd) throws SQLException {
		boolean result = false;
		
		System.out.println("Elintult a loginUser method");
		
		
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND pw = ?");
		//	email = "'" + email + "'";
		//	pwd = "'" + pwd + "'";
			
			stmt.setString(1, email);
			stmt.setString(2, pwd);
		
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {

			result = true;
			System.out.println("Létezik ilyen login");
			//	break;
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
