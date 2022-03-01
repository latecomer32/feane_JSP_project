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
				//static ����̶� ���� �� ������ ..�ϴ� j++; ����
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
		System.out.println("���� destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("���� ����");
		HttpSession ses = ((HttpServletRequest) request).getSession();

		url = ((HttpServletRequest) request).getServletPath();
		System.out.println("url_grade : " + authmap.get(url));

		if (!url.equals("/error_page.jsp")) {
			//System.out.println("���� ������ ���� �������� �ƴ�.");
			
			Integer user_grade = (Integer)ses.getAttribute("login_admin");
			if (user_grade != null) {
				System.out.println("�α��� Ȯ�� ��");
				System.out.println("user_grade : " + ses.getAttribute("login_admin"));
				System.out.println("Id : " + ses.getAttribute("login_Id"));
				
				Id=(String)ses.getAttribute("login_Id");
				
				try {
					if ( authmap.get(url) > user_grade) {
						((HttpServletResponse) response).sendRedirect("/error_page.jsp");
						System.out.println("������ ����");
						return; // return ���������� �ݺ� ����Ǽ� 500������
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
		//System.out.println("���� �� ���� üũ �Ϸ�");
		chain.doFilter(request, response);
		//System.out.println("���� ���� �� ���ͷ� �ǵ��� ��.");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("���� �ʱ�ȭ");
	}

}
