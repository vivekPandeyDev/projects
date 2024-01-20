<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In and Sign Up</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/login.css' />">
</head>
<body>

	<div class="main">

		<c:if test="${not empty msg}">
			<div
				class="alert alert-danger position-absolute main text-center text-danger">${msg }</div>
		</c:if>

		<input type="checkbox" id="chk" aria-hidden="true">
		<div class="signup">
			<form:form action="/final_brd/user/signUp" method="post"
				modelAttribute="userDto">
				<label for="chk" aria-hidden="true">Sign up</label>
				<form:input type="text" path="username" placeholder="User name"
					required="true" />
				<div class=" text-center form-text text-danger fs-6">
					<form:errors path="username" />
				</div>
				<form:input type="email" path="email" placeholder="Email"
					required="true" />
				<div class="text-center form-text text-danger fs-6">
					<form:errors path="email" />
				</div>
				<form:input type="password" path="password" placeholder="Password"
					required="true" />
				<div class="text-center form-text text-danger fs-6">
					<form:errors path="password" />
				</div>

				<button>Sign up</button>
			</form:form>
		</div>
		<div class="login">
			<form:form action="login" method="post">
				<label for="chk" aria-hidden="true">Login</label>
				<input type="text" name="username" placeholder="user name" />
				<input type="password" name="password" placeholder="Password" />
				<button>Login</button>
			</form:form>

		</div>

	</div>
</body>
</html>