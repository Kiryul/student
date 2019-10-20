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
 * Servlet implementation class setmanager
 */
@WebServlet("/setmanager")
public class setmanager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setmanager() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String sno = request.getParameter("sno");
		StudentService studentService = new StudentServiceImpl();
		studentService.setManager(sno);

		HttpSession session = request.getSession();

		// 查询所有信息
		StudentService studentService1 = new StudentServiceImpl();
		List<Object> lists = new ArrayList<Object>();
		lists = (List<Object>) studentService1.queryAccount();

		// 分页
		List<Object> pageLists = new ArrayList<Object>();
		for (int i = 1; i <= pageDivide.everypage; i++) {
			pageLists.add(i - 1, lists.get(pageDivide.headpage * pageDivide.everypage + i - 1));
		}

		session.setAttribute("lists", pageLists);
		response.sendRedirect("success.jsp");
	}

}
