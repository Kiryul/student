/*package com.zcc.servlet;

import java.io.IOException;
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

*//**
 * Servlet Filter implementation class AdminFilter
 *//*
@WebFilter(filterName="/AdminFilter",urlPatterns="/success.jsp")
public class AdminFilter implements Filter {

    *//**
     * Default constructor. 
     *//*
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see Filter#destroy()
	 *//*
	public void destroy() {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 *//*
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session=((HttpServletRequest) request).getSession();
		HttpServletRequest request1= (HttpServletRequest) request;
		HttpServletResponse response1 = (HttpServletResponse) response;

		
		if(request1.getRequestURL().indexOf("welcome.html")!=-1 ||
			request1.getRequestURL().indexOf("register.jsp")!=-1 ||
			request1.getRequestURL().indexOf("login.jsp")!=-1)
		{
			chain.doFilter(request, response);
			return ;
		}
		
		if(session.getAttribute("stu")!=null && session.getAttribute("Admin")=="1")
		{
			chain.doFilter(request, response);
		}
		else
		{
			request1.getRequestDispatcher("/ordinaryuser.jsp").forward(request1, response1);
			chain.doFilter(request1, response1);
		}
		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	*//**
	 * @see Filter#init(FilterConfig)
	 *//*
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
*/