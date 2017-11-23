<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23 0023
  Time: 下午 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" type="text/css">
</head>
<body>
<a href="add">添加</a>-->${loginUser.nickname }
<br/>
<c:forEach items="${users }" var="um">
    ${um.value.username }
    ----<a href="${um.value.username }">${um.value.nickname }</a>
    ----${um.value.password }
    ----${um.value.email }--<a href="${um.value.username }/update">修改</a>
    <a href="${um.value.username }/delete">删除</a><br/>
</c:forEach>
</body>
</html>
