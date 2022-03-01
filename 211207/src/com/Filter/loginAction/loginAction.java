package com.Filter.loginAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class loginAction implements Filter {

	static Map<String, Integer> authmap = new HashMap<String, Integer>();
	static String url;
	String Id="";
	
	String user_grade="";
	static {
		authmap.put("/member", 2);
		authmap.put("/about.jsp", 1);
		authmap.put("/menu.jsp", 1);
		authmap.put("/book.jsp", 1);
		authmap.put("/index", 1);
		authmap.put("/index.jsp", 1);
		authmap.put("/error_page.jsp", 1);
		authmap.put("/login", 1);
		authmap.put("/logout", 1);
		authmap.put("/PostMenu", 1);
		
	}
	/*int j=0;
	static {
		high_grade_urls.add("/member");
		
		for (int i = 0; i < high_grade_urls.size(); i++) {
			if (!url.equals(high_grade_urls)) {
				authmap.put(url, 1);
			} else {
				//static 블록이라 변수 못 넣으니 ..일단 j++; 포기
				authmap.put(high_grade_urls.get(0), 2);
			}
		}
	}
	*/
	

	public loginAction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("필터 destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 시작");
		HttpSession ses = ((HttpServletRequest) request).getSession();

		url = ((HttpServletRequest) request).getServletPath();
		System.out.println("url_grade : " + authmap.get(url));

		if (!url.equals("/error_page.jsp")) {
			//System.out.println("현재 페이지 에러 페이지가 아님.");
			
			Integer user_grade = (Integer)ses.getAttribute("login_admin");
			if (user_grade != null) {
				System.out.println("로그인 확인 됨");
				System.out.println("user_grade : " + ses.getAttribute("login_admin"));
				System.out.println("Id : " + ses.getAttribute("login_Id"));
				
				Id=(String)ses.getAttribute("login_Id");
				
				try {
					if ( authmap.get(url) > user_grade) {
						((HttpServletResponse) response).sendRedirect("/error_page.jsp");
						System.out.println("권한이 낮음");
						return; // return 하지않으면 반복 수행되서 500에러뜸
					}
					else {

						ses.setAttribute("user_grade", user_grade);
						ses.setAttribute("Id", Id);
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
				
			}

		}
		//System.out.println("필터 전 권한 체크 완료");
		chain.doFilter(request, response);
		//System.out.println("서블릿 실행 후 필터로 되돌아 옴.");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("필터 초기화");
	}

}
