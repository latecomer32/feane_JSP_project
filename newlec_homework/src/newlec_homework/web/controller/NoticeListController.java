package newlec_homework.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newlec_homework.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Notice> list= new ArrayList<Notice>();
		
	String dbURL = "jdbc:mysql://localhost:3306/BBSS";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	
	String sql="SELECT * FROM NOTICE";
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	Statement st = conn.createStatement();
	ResultSet rs=st.executeQuery(sql);

	while(rs.next()){
		int id = rs.getInt("ID") ;
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
		list.add(notice);
	} 

		rs.close();
        st.close();
        conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	request.setAttribute("list", list);
	
	request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
	.forward(request, response);

}
}
