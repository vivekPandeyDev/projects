<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
	
	<style type="text/css">
		.error{
			color: red;
			font-size: 1.2rem;
		}
	</style>

<title>Hello, world!</title>
</head>
<body>
	<div class="container-md">
		<form method="get" action="../../productValidate">
			<div class="mb-3">
				<label class="form-label">Product Name</label> 
				<input type="text" class="form-control" name="productName"> 
				<span class="error">${sessionScope.msg}</span>
				<% session.removeAttribute("msg"); %>
			</div>
			<div class="mb-3">
				<label class="form-label">Product price</label> 
				<input type="text" class="form-control" name="productPrice"> 
				<span class="error" >${sessionScope.msg1}</span>
				<% session.removeAttribute("msg1"); %>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<span class="error" >${sessionScope.msg3}</span>
		<% session.removeAttribute("msg3"); %>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>