package com.PostMenu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MemberService.MemberService;
import com.MemberVO.MemberVo;

@WebServlet("/PostMenuWrite")
public class WritePostMenu extends HttpServlet{
@Override
public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.getRequestDispatcher("/post_menu_write.jsp").forward(req, resp);
}
}
