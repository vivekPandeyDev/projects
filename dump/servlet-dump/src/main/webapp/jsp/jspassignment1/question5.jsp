<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question 5</title>
</head>
<body>
	<%
		int val = Integer.parseInt(request.getParameter("value"));
	
		if(val==1){
			request.getRequestDispatcher("../jspassignment1/aboutUs.jsp").forward(request, response);
		}else if(val == 2){
			request.getRequestDispatcher("../jspassignment1/contactUs.jsp").forward(request, response);
			
		}else{
			request.getRequestDispatcher("../jspassignment1/error.jsp").forward(request, response);
			
		}
	
	%>
</body>
</html>