package com.DelMember;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MemberService.MemberService;
import com.MemberVO.MemberVo;



@WebServlet("/DelMember")
public class DelMember extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] del_member_ = req.getParameterValues("del_member");

		

		for(String del_member:del_member_) {
			if(del_member_ !=null && !del_member_.equals("")) {
				MemberService service = new MemberService();
				service.getDelMember(del_member);
				
		}
		}

		resp.sendRedirect("/member");
	}
	}

