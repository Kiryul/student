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
 * Servlet implementation class updateservlet
 */
@WebServlet("/update")
public class updateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String sname = request.getParameter("sname");
		String sno = request.getParameter("sno");
		String pwd = request.getParameter("pwd");

		// 执行修改
		StudentService studentService = new StudentServiceImpl();
		studentService.modifyAccount(sno, sname, pwd);

		// 个人信息
		StudentService studentService2 = new StudentServiceImpl();
		List<Object> listsone = new ArrayList<Object>();
		listsone = (List<Object>) studentService2.queryAccount(sno);

		// 所有信息
		StudentService studentService1 = new StudentServiceImpl();
		List<Object> lists = new ArrayList<Object>();
		lists = (List<Object>) studentService1.queryAccount();

		// 分页显示
		pageDivide.headpage = 0;
		List<Object> pageLists = new ArrayList<Object>();
		for (int i = 1; i <= pageDivide.everypage; i++) {
			pageLists.add(i - 1, lists.get(pageDivide.headpage * pageDivide.everypage + i - 1));
		}

		// 跳转管理员页面
		if ("manager".equals(session.getAttribute("Admin"))) {
			session.setAttribute("lists", pageLists);
			response.sendRedirect("success.jsp");
		} else // 跳转个人页面
		{
			session.setAttribute("listsone", listsone);
			response.sendRedirect("ordinaryuser.jsp");
		}

	}

}
