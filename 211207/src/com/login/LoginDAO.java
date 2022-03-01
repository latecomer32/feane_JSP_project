package com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class LoginDAO extends HttpServlet {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public String userName;
	public int grade=0;
	public LoginDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/feane";
			String dbID = "root";
			String dbPassword = "1111";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
	
		String SQL = "SELECT Password,UserName,Grade FROM member WHERE Id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					userName=rs.getString(2);
					grade=rs.getInt(3);
					return 1;}
				else
					return 0;
			}
			return -1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {if(conn!=null) {conn.close();}}catch(Exception ex) {ex.printStackTrace();}
			
			try {if(rs!=null) {rs.close();}}catch(Exception ex) {ex.printStackTrace();}
			
			try {if(pstmt!=null) {pstmt.close();}}catch(Exception ex) {ex.printStackTrace();}
		}

		return -2;
	}

}
