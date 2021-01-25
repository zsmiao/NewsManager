<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/25
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: center">
<%
    String name ="";
    String password ="";
    String save ="";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                name = cookie.getValue();
                save="checked";
            }
            if ("password".equals(cookie.getName())) {
                password = cookie.getValue();
            }
        }
    }
%>
<form action="/cookieDemo" method="post" >
    username:<input type="text" name="username" value="<%=name%>"/><br/>
    password:<input type="password" name="password" value="<%=password%>"/><br/>
    <input type="checkbox" name="save" <%=save%>>记住密码<br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
