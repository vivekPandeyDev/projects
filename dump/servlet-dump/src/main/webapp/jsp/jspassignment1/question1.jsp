<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question 1</title>
</head>
<body>
	<%!int sum(int a, int b) {
		return a + b;
	}%>
	<%= "sum of two number: " + sum(3,4) %>
	<%
		System.out.println("sum of two number: " + sum(3,4));
	%>
</body>
</html>