package newlec_homework.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newlec_homework.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String dbURL = "jdbc:mysql://localhost:3306/BBSS";
		String dbID = "root";
		String dbPassword = "1111";
		try {
		Class.forName("com.mysql.jdbc.Driver");
		String sql="SELECT * FROM NOTICE WHERE ID=?";
		Connection conn;
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1,id);
		ResultSet rs=st.executeQuery();
		rs.next();

String title = rs.getString("TITLE") ;
String writerId = rs.getString("WRITER_ID") ;
Date regdate = rs.getDate("REGDATE");
String hit = rs.getString("HIT") ;
String files = rs.getString("FILES") ;
String content = rs.getString("CONTENT");


Notice notice = new Notice(
		id,
		title,
		writerId,
		regdate,
		hit,
		files,
		content
		);


request.setAttribute("n", notice);
/*
request.setAttribute("title", title);
request.setAttribute("writerId", writerId);
request.setAttribute("regdate", regdate);
request.setAttribute("hit", hit);
request.setAttribute("files", files);
request.setAttribute("content", content);
*/
	rs.close();
    st.close();
    conn.close();
    }
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp")
		.forward(request, response);
	}
}

