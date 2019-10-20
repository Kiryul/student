<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zcc.util.OnlineCounter"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>zcc login system</title>
</head>
<body>
<h1> ZCC的登录系统</h1>
<hr>
    <form  action="login" method=POST>
    用户名：<input type="text" name="sno" ><br>
    密    码：<input type="password" name="pwd"><br>
    <input type="submit" value="Login">
    </form>
 <hr>  
    当前游客人数：<%  out.println(OnlineCounter.onLineVisitor.get());%>
<%--     当前登陆人数：<%  out.println(OnlineCounter.onLineVIP.get());%> --%>
    <br>
    <% 
    String message = (String) session.getAttribute("message");
    if(null != message)  
        out.print("<h2> 提示："+message); 
    %>
    
    <a href="register.jsp">注册</a>
</body>
</html>