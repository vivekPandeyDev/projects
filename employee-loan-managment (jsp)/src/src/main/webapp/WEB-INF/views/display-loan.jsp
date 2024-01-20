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
		$('#myLoan').DataTable();
		console.log("done")
	});
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class=" mt-5 container-md">
		<h3 class="text-muted mb-2">Loan's Detail</h3>

		<table id="myTable" class=" table table-responsive table-striped">
			<thead>
				<tr>
					<th scope="row">Loan Id</th>
					<th scope="row">Product Type</th>
					<th scope="row">Product</th>
					<th scope="row">Amount</th>
					<th scope="row">Tenure</th>
					<th scope="row">Rate</th>
					<th scope="row">AgreementDate</th>
					<th scope="row">InstallmentDate</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td scope="col">${loan.loanId }</td>
					<td scope="col">${loan.productType }</td>
					<td scope="col">${loan.product}</td>
					<td scope="col">${loan.amount }</td>
					<td scope="col">${loan.tenure }</td>
					<td scope="col">${loan.rate }</td>
					<td scope="col">${loan.agreementDate }</td>
					<td scope="col">${loan.installmentDate }</td>
				</tr>
			</tbody>
		</table>
		<br>

		<h3 class="text-muted mb-2">Customer's Detail</h3>
		<table class=" table table-responsive table-striped">
			<thead>
				<tr>
					<th scope="col">FirstName</th>
					<th scope="col">LastName</th>
					<th scope="col">Nationality</th>
					<th scope="col">gender</th>
					<th scope="col">dob</th>
				</tr>
			</thead>
			<tbody>
				<tr>

					<td scope="col">${customer.firstName }</td>
					<td scope="col">${customer.lastName }</td>
					<td scope="col">${customer.nationality }</td>
					<td scope="col">${customer.gender }</td>
					<td scope="col">${customer.dob }</td>

				</tr>
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
				<tr>

					<td scope="col">${customer.address.houseNo }</td>
					<td scope="col">${customer.address.country }</td>
					<td scope="col">${customer.address.state }</td>
					<td scope="col">${customer.address.city }</td>
					<td scope="col">${customer.address.addressLine1 }</td>
					<td scope="col">${customer.address.addressLine2 }</td>

				</tr>
			</tbody>
		</table>

	</div>
</body>
</html>