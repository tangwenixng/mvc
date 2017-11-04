<%--
  Created by IntelliJ IDEA.
  User: twx
  Date: 2017/11/1
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    hello,${message}
    <c:out value="${message}" />
    <br>
    <c:forEach var="str" items="${arr}">
        <p>${str}</p>
    </c:forEach>
</body>
</html>
