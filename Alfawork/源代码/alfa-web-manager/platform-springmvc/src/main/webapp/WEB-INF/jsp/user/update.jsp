<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/23 0023
  Time: 下午 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 此时没有写action,直接提交会提交给/add -->
<sf:form method="post" modelAttribute="user">
    Username:<sf:input path="username"/><sf:errors path="username"/><br/>
    Password:<sf:password path="password"/><sf:errors path="password"/><br/>
    Nickname:<sf:input path="nickname"/><br/>
    Email:<sf:input path="email"/><sf:errors path="email"/><br/>
    <input type="submit" value="修改用户"/>
</sf:form>
</body>
</html>
