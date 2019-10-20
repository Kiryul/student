<%@page import="com.zcc.util.pageDivide"%>
<%@page import="com.zcc.po.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login successful</title>

<script type="text/javascript">
	function deletestu(sno) {
		if (!confirm("确定删除" + sno + "的信息？"))
			return;
		window.location.href = "delete?sno=" + sno + "";
	}

	function updatestu(sno) {
		window.location.href = "updatestu.jsp?sno=" + sno + "";
	}
</script>
</head>
<body>
	<%
		Student stu = (Student) session.getAttribute("stu");
		String sname = "";
		if (stu != null)
			sname = stu.getSname();
	%>
	<h1>
		欢迎
		<%=sname%>
		登录ZCC的系统！
	</h1>

	<%
		List<Object> lists = new ArrayList<Object>();
		lists = (List<Object>) session.getAttribute("lists");
		//  out.print(lists);
		if (lists == null)
			return;
	%>

	<hr>
	<table align="center" border="1">
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>操作</th>
		</tr>
		<%
			for (int i = 0; i < lists.size(); i++) {
				Student student = (Student) lists.get(i);
		%>
		<tr>
			<td><%=student.getSno()%></td>
			<td><%=student.getSname()%></td>
			<td>
				<button onclick="deletestu('<%=student.getSno()%>')">删除</button>
				<button onclick="updatestu('<%=student.getSno()%>')">修改</button>
			</td>
		</tr>
		<%
			}
		%>

	</table>
	<p align="center">
		<a href="Switch?sign=up">上一页</a>
	</p>
	<p align="center">
		第<%=pageDivide.headpage + 1%>页
	</p>
	<p align="center">
		<a href="Switch?sign=down">下一页</a>
	</p>
	<br>
	<br>
	<p align="right">
		<a href="setmanager.jsp">设置管理员</a>
	</p>
	<hr>
	<p align="right">
		<a href="welcome.html">退出</a>
	</p>
</body>
</html>