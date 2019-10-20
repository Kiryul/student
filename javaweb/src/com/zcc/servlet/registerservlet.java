package com.zcc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zcc.database.Config;
import com.zcc.service.StudentService;
import com.zcc.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class registerservlet
 */
@WebServlet("/register")
public class registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerservlet() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String pwd = request.getParameter("pwd");
		StudentService studentService = new StudentServiceImpl();
		boolean sign = studentService.register(sno, sname, pwd);
		HttpSession session = request.getSession();
		if (sign == true) {
			String msg = Config.tipsByLangues.get("e111");
			session.setAttribute("message", msg);
			response.sendRedirect("login.jsp");
		} else {
			String msg = Config.tipsByLangues.get("e222");
			session.setAttribute("message", msg);
			response.sendRedirect("register.jsp");
		}

	}

}
