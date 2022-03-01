package com.join_form;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class join extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.setContentType("text/html; charset=UTF-8");
		//resp.setCharacterEncoding("UTF-8");
		//req.setCharacterEncoding("UTF-8");
		
		/*
		  Class.forName() 을 이용해서 드라이버 로드
		  DriverManager.getConnection() 으로 연결 얻기
		  Connection 인스턴스를 이용해서 Statement 객체 생성
		  Statement 객체의 결과를 ResultSet 이나 int에 받기
		 */
	
		String Id_ = req.getParameter("Id");
		String Password_ = req.getParameter("Password");
		String UserName_ = req.getParameter("UserName");
		int Grade_ = Integer.parseInt(req.getParameter("flexRadioDefault"));

		/*
		if(Grade_.equals("admin")) {}
		if(Grade_.equals("customer")) {}
		*/
		int rs=0;
		
		String dbUrl = "jdbc:mysql://localhost:3306/feane";
		String dbId = "root";
		String dbPassword = "1111";
	
			try {
				Class.forName("com.mysql.jdbc.Driver");
		
			String sql = "INSERT INTO member(Id, Password, UserName, Grade, JoinDate) VALUES(?,?,?,?,now())";
			Connection con;
			
				con=DriverManager.getConnection(dbUrl,dbId,dbPassword);
		
			PreparedStatement ps = con.prepareStatement(sql);
			

			ps.setString(1,  Id_);
			ps.setString(2,  Password_);
			ps.setString(3,  UserName_);
			ps.setInt(4,  Grade_);
		

			rs = ps.executeUpdate();

			
	       	ps.close();
	        con.close();
			}
	        catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			resp.sendRedirect("index");
		
	}
}








