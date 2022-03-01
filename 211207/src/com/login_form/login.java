package com.login_form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MemberVO.MemberVo;
import com.login.LoginDAO;

@WebServlet("/login")
public class login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.setContentType("text/html; charset=UTF-8");
		//resp.setCharacterEncoding("UTF-8");
		//req.setCharacterEncoding("UTF-8");
		List<String> login = new ArrayList<>();
		/*
		  Class.forName() 을 이용해서 드라이버 로드
		  DriverManager.getConnection() 으로 연결 얻기
		  Connection 인스턴스를 이용해서 Statement 객체 생성
		  Statement 객체의 결과를 ResultSet 이나 int에 받기
		 */
		HttpSession ses = req.getSession();
		String id = req.getParameter("Id");
		String password = req.getParameter("Password");

				LoginDAO loginDAO = new LoginDAO();
				
				int result = loginDAO.login(id, password);
				if (result ==1){

					login.add(id);
					login.add(loginDAO.userName);
					ses.setAttribute("login_Id", login.get(0));
					ses.setAttribute("login_info", login);
					ses.setAttribute("login_admin", loginDAO.grade);
					
					resp.sendRedirect("/index");
					//req.getRequestDispatcher("/index").forward(req, resp);
					System.out.println("로그인 성공");
				}
				else if (result ==0){
					req.getRequestDispatcher("/joinpage").forward(req, resp);
					//비번틀림
					System.out.println("비번 틀림");
				}
				
				else if (result ==-1){
					PrintWriter out = resp.getWriter();
					out.println("<out>");
					out.println("alert('존재하지 않는 아이디입니다.')");
					out.println("history.back()");
					out.println("</out>");
					System.out.println("존재하지 않는 아이디입니다.");
				}
				else if (result ==-2){
					PrintWriter out = resp.getWriter();
					out.println("<out>");
					out.println("alert('데이터베이스 오류가 발생했습니다.')");
					out.println("history.back()");
					out.println("</out>");
					System.out.println("데이터베이스 오류가 발생했습니다.");
				}	
	}
}








