<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question 4</title>
</head>
<body>
	<%
	String message = "";
	int n = Integer.parseInt(request.getParameter("age"));
	String userName = request.getParameter("name");
	if (n > 62) {
		message = "Price ticket is 7";
	} else if (n < 10) {
		message = "Price ticket is 5";
	} else {
		message = "Price ticket is 9.50";
	}
	%>name
	<%=userName%><br>age
	<%=n%><br>message
	<%=message%>
</body>
</html>