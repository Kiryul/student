package com.zcc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zcc.service.StudentService;
import com.zcc.service.impl.StudentServiceImpl;
import com.zcc.util.pageDivide;

/**
 * Servlet implementation class PageSwitch
 */
@WebServlet("/Switch")
public class PageSwitch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageSwitch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		
		StudentService studentService1=new StudentServiceImpl();
		List<Object> lists=new ArrayList<Object>();
		lists= (List<Object>) studentService1.queryAccount();
		
		//分页
		String sign=request.getParameter("sign");
		//System.out.println(sign);
		int sumpage=lists.size()/pageDivide.everypage;
		if(pageDivide.headpage==0 && "up".equals(sign)) pageDivide.headpage=0;
		if(pageDivide.headpage!=0 && "up".equals(sign)) pageDivide.headpage--;
		if(pageDivide.headpage==sumpage && "down".equals(sign)) pageDivide.headpage=sumpage;
		if(pageDivide.headpage!=sumpage && "down".equals(sign)) pageDivide.headpage++;
		List<Object> pageLists=new ArrayList<Object>();
		int index=pageDivide.everypage;
		if(lists.size()<(pageDivide.headpage+1)*pageDivide.everypage) 
		{
			index=lists.size()-pageDivide.headpage*pageDivide.everypage;
			//pageDivide.headpage--;
		}
		for(int i=1;i<=index;i++)
		{
			//System.out.println(pageDivide.headpage);
			//System.out.println(pageDivide.headpage*pageDivide.everypage+i-1);
			pageLists.add(i-1, lists.get(pageDivide.headpage*pageDivide.everypage+i-1));
		}
			session.setAttribute("lists", pageLists);
			response.sendRedirect("success.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
