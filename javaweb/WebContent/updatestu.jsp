<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script  type="text/javascript">
function switchpage()
{
	sign="<%=session.getAttribute("Admin")%>";
<%-- 	document.write(sign);
	document.write(<%=(null==(session.getAttribute("Admin")))%>) --%>
	if(<%="manager".equals(session.getAttribute("Admin")) %>)
	{
		window.location.href="success.jsp";
	}
	if(<%=(null==(session.getAttribute("Admin")))%>)  
	{
		window.location.href="ordinaryuser.jsp";

	}

}

</script>
</head>
<body>
<%
   String sno1=request.getParameter("sno");
%>
<h2><%=sno1 %>请输入修改信息</h2>
<hr>
<form action="update?sno=<%=sno1 %>" method=POST >

姓名：<input type="text" name="sname" ><br>
密码：<input type="password" name="pwd"><br>
<input type="submit" value="submit" >
</form>
<button onclick="switchpage()" >返回</button>
<!-- <a href="success.jsp">返回</a> -->
</body>
</html>