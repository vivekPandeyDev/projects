<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PrivateResource</title>
</head>
<body>
	<%
	if (request.getCookies() == null && request.getSession(false) == null) {
		request.getRequestDispatcher("../../html/question3.html").forward(request, response);
	}
	%>

	<%
	Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					request.getSession().setAttribute("user", cookie.getName());
					cookie.setMaxAge(cookie.getMaxAge() + 1*60);
				}
			} 
		}
	%>

	<h1>This is Private Resource</h1>
</body>
</html>