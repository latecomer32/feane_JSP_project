package com.MemberList;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MemberService.MemberService;
import com.MemberVO.MemberVo;

@WebServlet("/member")
public class MemberList extends HttpServlet{
@Override
public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//List<MemberVo> list = new ArrayList<>();

	String field_ = req.getParameter("f");
	String query_ = req.getParameter("q");
	String page_ = req.getParameter("p");
	String rowNum_ = req.getParameter("r");
	String order_ = req.getParameter("order");
	String desc_ = req.getParameter("desc");

	String order = "JoinDate";
	if(order_ !=null && !order_.equals(""))
		order = order_;
	
	String desc = "DESC";
	if(desc_ !=null && !desc_.equals(""))
		desc = desc_;
	
	String field = "UserName";
	if(field_ !=null && !field_.equals(""))
		field = field_;
	
	String query = "";
	if(query_ !=null && !query_.equals(""))
		query = query_;
	
	int page = 1;
	if(page_ !=null && !page_.equals(""))
		page = Integer.parseInt(page_);
	
	int rowNum = 15;
	if(rowNum_ !=null && !rowNum_.equals(""))
		rowNum = Integer.parseInt(rowNum_);

	System.out.println("/member ½ÇÇà");
	
	MemberService service = new MemberService();
	List<MemberVo> list = service.getMemberList(field,query,page,rowNum,order,desc);
	int count = service.getMemberCount(field, query);

	System.out.println("count:"+count);
	req.setAttribute("list", list);
	req.setAttribute("count", count);
	
	System.out.println("getMemberList:"+list);
	req.getRequestDispatcher("/member_list.jsp").forward(req, resp);
}
}
