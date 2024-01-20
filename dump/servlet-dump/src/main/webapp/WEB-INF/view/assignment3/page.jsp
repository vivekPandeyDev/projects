<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	HttpSession session1 = request.getSession(false);
	if (session1 == null) {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	%>

	<h1>
		You have logged In
		<%=session1.getAttribute("msg")%></h1>
</body>
</html>