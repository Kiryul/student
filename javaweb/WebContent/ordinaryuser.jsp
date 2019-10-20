<%@page import="com.zcc.po.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function updatestu(sno)
{
	window.location.href="updatestu.jsp?sno="+sno+"";
	}

</script>
</head>
<body>
<%
   Student stu = (Student) session.getAttribute("stu");
   String sname = "";
   if(stu != null)
       sname = stu.getSname();
%>
<h1>欢迎           <%=sname%>    登录ZCC的系统！</h1>

<%
   List<Object> listsone=new ArrayList<Object>();
   listsone=(List<Object>) session.getAttribute("listsone"); 
   if(listsone==null) return ;
%>

<hr>
<table align="center" border="1">
<tr><th>学号</th><th>姓名</th><th>密码</th><th>操作</th></tr>
<%
   for(int i=0;i<listsone.size();i++)
   {
	   Student student =(Student)listsone.get(i);
	   %>
	   <tr>
	   <td><%=student.getSno()%></td>
	   <td><%=student.getSname() %></td>
	   <td><%=student.getPwd() %></td>
	   <td>
<%-- 	   <a href="delete?sno=<%=student.getSno() %>">删除</a> --%>
	   <button onclick="updatestu('<%=student.getSno() %>')" >修改</button>
	   </td>
	   </tr>
 <% } %> 

</table>
<hr>
 <p align="right"><a href="welcome.html" >退出</a></p>
</body>
</html>