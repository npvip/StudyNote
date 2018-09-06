<%--
  Created by IntelliJ IDEA.
  User: ningpeng
  Date: 2018/9/6
  Time: 下午2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Blog</title>
</head>
<body>
<table>
    <tr>
        <td>ID</td>
        <td>标题</td>
        <td>内容</td>
    </tr>
    <c:forEach items="${allBlog}" var="blog">
        <tr>
            <td>${blog.id}</td>
            <td>${blog.title}</td>
            <td>${blog.content}</td>
        </tr>
    </c:forEach>


</table>

</body>
</html>
