<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 欢迎来到注册页面，请按提示输入信息</h1>
<hr>
<form action="register" method="post">
<br>请输入学号：<input type="text" name="sno" >
<br>请输入姓名：<input type="text" name="sname">
<br>请输入密码：<input type="password" name="pwd">
<br><input type="submit" value="register">
</form>

    <% 
    String message = (String) session.getAttribute("message");
    if(null != message)  
        out.print("<h2> 提示："+message); 
    %>
    
        <a href="login.jsp">登录</a>
</body>
</html>