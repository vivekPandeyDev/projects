<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="true" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Error</title>
</head>
<body>
	<ul>
		<li>Exception: ${requestScope['javax.servlet.error.exception']}</li>
		<li>Exception type:
			${requestScope['javax.servlet.error.exception_type']}</li>
		<li>Exception message:
			${requestScope['javax.servlet.error.message']}</li>
		<li>Request URI:
			${requestScope['javax.servlet.error.request_uri']}</li>
		<li>Servlet name:
			${requestScope['javax.servlet.error.servlet_name']}</li>
		<li>Status code:
			${requestScope['javax.servlet.error.status_code']}</li>
	</ul>
</body>
</html>