package com.java2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PostService.PostService;
import com.PostVO.PostVo;
@WebServlet("/index")
public class java2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Id_ = req.getParameter("Id");
		String Title_ = req.getParameter("Title");
		String Content_ = req.getParameter("Content");
		String Files_ = req.getParameter("Files");
		String Price_ = req.getParameter("Price");
		String Kinds_ = req.getParameter("Kinds");
		
		String field_ = req.getParameter("field");
		String query_ = req.getParameter("query");
		String page_ = req.getParameter("page");
		
		String Id = "";
		if(Id_ !=null && !Id_.equals(""))
			Id = Id_;
		
		String Title = "";
		if(Title_ !=null && !Title_.equals(""))
			Title = Title_;
		
		String Content = "";
		if(Content_ !=null && !Content_.equals(""))
			Content = Content_;
		
		String Files = "";
		if(Files_ !=null && !Files_.equals(""))
			Files = Files_;
		
		int Price = 1;
		if(Price_ !=null && !Price_.equals(""))
			Price = Integer.parseInt(Price_);
		
		String Kinds = "";
		if(Kinds_ !=null && !Kinds_.equals(""))
			Kinds = Kinds_;
		
		String field = "";
		if(field_ !=null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if(query_ !=null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if(page_ !=null && !page_.equals(""))
			page = Integer.parseInt(page_);

		
		PostService service = new PostService();
		service.setPost(Id, Title, Kinds, Content, Files, Price);
		List<PostVo> getPostList = service.getPostList( field, query, page);

		
		req.setAttribute("getPostList", getPostList);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
}
