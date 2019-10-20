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

import com.zcc.database.Config;
import com.zcc.po.Student;
import com.zcc.po.StudentVO;
import com.zcc.service.StudentService;
import com.zcc.service.impl.StudentServiceImpl;
import com.zcc.util.OnlineCounter;
import com.zcc.util.pageDivide;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/login")
public class loginservlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginservlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String sno = request.getParameter("sno");
		String pwd = request.getParameter("pwd");

		// 判定 是否输入了用户名与密码
		if ("".equals(sno) || "".equals(pwd))
		{
			response.sendRedirect("login.jsp");
			return;
		}

		// 判定登录结果
		StudentService studentService = new StudentServiceImpl();
		StudentVO studentVo = studentService.login(sno, pwd);
		HttpSession session = request.getSession();

		// 查询所有信息
		StudentService studentService1 = new StudentServiceImpl();
		List<Object> lists = new ArrayList<Object>();
		lists = (List<Object>) studentService1.queryAccount();

		// 分页
		List<Object> pageLists = new ArrayList<Object>();
		for (int i = 1; i <= pageDivide.everypage; i++)
		{
			pageLists.add(i - 1, lists.get(pageDivide.headpage * pageDivide.everypage + i - 1));
		}

		// System.out.print(request.getHeader("accept-language")); //???
		if (studentVo.getStudent() != null)
		{
			OnlineCounter.onLineVIP.incrementAndGet();

			// 查询个人信息
			StudentService studentService2 = new StudentServiceImpl();
			List<Object> listsone = new ArrayList<Object>();
			listsone = (List<Object>) studentService2.queryAccount(sno);
			Student student = (Student) listsone.get(0);

			if ("manager".equals(student.getAdmin()))
			{
				session.setAttribute("Admin", "manager");
				session.setAttribute("stu", studentVo.getStudent());

				session.setAttribute("lists", pageLists);
				response.sendRedirect("success.jsp");
			} else
			{
				session.setAttribute("stu", studentVo.getStudent());

				session.setAttribute("listsone", listsone);
				response.sendRedirect("ordinaryuser.jsp");
			}

			// request.getRequestDispatcher("success.jsp").forward(request,
			// response);
		} else
		{
			String msg = Config.tipsByLangues.get(studentVo.getMessage());
			session.setAttribute("message", msg);
			response.sendRedirect("login.jsp");
		}

	}
}
