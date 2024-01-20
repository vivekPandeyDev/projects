<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class="container-md mt-4">
		<div class="row">
			<form action="/spring_advance/loan/search" class="col-8 d-flex">
				<input class="form-control me-2 " type="search"
					placeholder="Enter loan id....." aria-label="Search" name="id">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>


			<form action="/spring_advance/changeLanguage" class=" col-4 d-flex">
				<select class="form-control me-2 " aria-label="language" name="lang">
					<option selected value="en_US">English</option>
					<option value="hi">Hindi</option>
					<option value="fr">French</option>
				</select>
				<button class="btn btn-outline-success" type="submit">Change
					Language</button>
			</form>
		</div>
	</div>



</body>
</html>