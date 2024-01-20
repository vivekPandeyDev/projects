<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>number</td>
			<td>fact</td>
		</tr>

		<%
		int n = Integer.parseInt(request.getParameter("fact"));
		int fact = 1;
		int i;
		for (i = 1; i <= n; i++) {
			System.out.println(i);
			fact *= i;
		%>

		<tr>
			<td><%= i%></td>
			<td><%=fact%></td>
		</tr>

		<%
		}
		%>
	</table>
</body>
</html>