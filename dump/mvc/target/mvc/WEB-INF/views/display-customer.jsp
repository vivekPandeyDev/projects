<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display</title>
</head>
<body>
    <c:forEach items="${names}" var="name">
	    <h1>
	    Customer Name:  <a href="getSingleCustomer/${name}">${name}</a>
        </h1>
    </c:forEach>
</body>
</html>