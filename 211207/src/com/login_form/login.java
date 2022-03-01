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
		  Class.forName() �� �̿��ؼ� ����̹� �ε�
		  DriverManager.getConnection() ���� ���� ���
		  Connection �ν��Ͻ��� �̿��ؼ� Statement ��ü ����
		  Statement ��ü�� ����� ResultSet �̳� int�� �ޱ�
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
					System.out.println("�α��� ����");
				}
				else if (result ==0){
					req.getRequestDispatcher("/joinpage").forward(req, resp);
					//���Ʋ��
					System.out.println("��� Ʋ��");
				}
				
				else if (result ==-1){
					PrintWriter out = resp.getWriter();
					out.println("<out>");
					out.println("alert('�������� �ʴ� ���̵��Դϴ�.')");
					out.println("history.back()");
					out.println("</out>");
					System.out.println("�������� �ʴ� ���̵��Դϴ�.");
				}
				else if (result ==-2){
					PrintWriter out = resp.getWriter();
					out.println("<out>");
					out.println("alert('�����ͺ��̽� ������ �߻��߽��ϴ�.')");
					out.println("history.back()");
					out.println("</out>");
					System.out.println("�����ͺ��̽� ������ �߻��߽��ϴ�.");
				}	
	}
}








