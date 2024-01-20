<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>

<script>
	$(document).ready(function() {
		$('#myTable').DataTable();
		console.log("done")
	});
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class=" mt-5 container-md">
		<div class="text-center">
			<span class="fs-3  text-danger">${param.success }</span>
		</div>
		<h3 class="text-muted mb-2">Customer's Detail</h3>
		<table id="myTable" class=" table table-responsive table-striped">
			<thead>
				<tr>
					<th scope="col">FirstName</th>
					<th scope="col">LastName</th>
					<th scope="col">Nationality</th>
					<th scope="col">gender</th>
					<th scope="col">dob</th>
					<th scope="col">action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${customers}" var="customer">
					<tr>
						<td scope="col">${customer.firstName }</td>
						<td scope="col">${customer.lastName }</td>
						<td scope="col">${customer.nationality }</td>
						<td scope="col">${customer.gender }</td>
						<td scope="col">${customer.dob }</td>
						<td scope="col"><a class="btn btn-danger text-light"
							href='<c:url value="/admin/delete/${customer.customerId }" />'>delete</a>
							<a class="btn btn-info text-light"  href='<c:url value="/admin/get?id=${customer.customerId }" />'>update</a>
						</td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
		<br> <br>
		<h3 class="text-muted">Customer's Address</h3>

		<table id="myTable" class=" table table-responsive table-striped">
			<thead>
				<tr>
					<th scope="row">HouseNo</th>
					<th scope="row">country</th>
					<th scope="row">state</th>
					<th scope="row">city</th>
					<th scope="row">addressLine1</th>
					<th scope="row">addressLine2</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${customers}" var="customer">
					<tr>
						<td scope="col">${customer.address.houseNo }</td>
						<td scope="col">${customer.address.country }</td>
						<td scope="col">${customer.address.state }</td>
						<td scope="col">${customer.address.city }</td>
						<td scope="col">${customer.address.addressLine1 }</td>
						<td scope="col">${customer.address.addressLine2 }</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>



	</div>
</body>
</html>