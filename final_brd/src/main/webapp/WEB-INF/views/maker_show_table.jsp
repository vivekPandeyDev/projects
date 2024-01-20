<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Add Customer</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
<script>
	$(document).ready(function() {
		$('#customers').DataTable();
		let url = new URL(window.location.href)
		let params = new URLSearchParams(url.search)
		if( params.get("delete_msg") !== null ||
			params.get("upload_msg") != null 
		){
			setTimeout(() => {
				window.location = window.location.href.split("?")[0];
			}, 2000);
			
		}
	});
	
</script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ripple.css' />">

</head>
<body class="bg-light">
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class="container-md">
		<div class="card mt-5">
			<div class="card-header text-center h1 text-muted">Customer's
				Detail</div>
			<div class="card-body">
				<h5 class="card-title text-success">List Of Current Available
					Customers</h5>
				<c:if test="${not empty param.delete_msg}">
					<div class="alert alert-danger text-center fs-5" role="alert">${param.delete_msg}</div>
				</c:if>
				<c:if test="${not empty param.upload_msg}">
					<div class="alert alert-success text-center fs-5" role="alert">${param.upload_msg}</div>
				</c:if>
				<c:remove var="param.upload_msg" />
				<c:remove var="param.delete_msg" />
				<div class="card-text table-responsive min-vh-100">
					<table id="customers" class="table table-striped table-hover">
						<thead class="text-center text-muted">
							<tr>
								<th>customerCode</th>
								<th>customerName</th>
								<th>customerEmail</th>
								<th>recordStatus</th>
								<th>action</th>
							</tr>
						</thead>
						<tbody class="text-center fs-5 text-dark bg-light">
							<c:forEach items="${unAuthorizedCustomers}"
								var="unAuthorizedCustomer">
								<tr>
									<td>${unAuthorizedCustomer.customerCode}</td>
									<td>${unAuthorizedCustomer.customerName}</td>
									<td>${unAuthorizedCustomer.customerEmail}</td>
									<td>${unAuthorizedCustomer.recordStatus}</td>
									<td><a class="btn btn-outline-warning"
										href="/final_brd/maker/customer/update/${unAuthorizedCustomer.customerCode}">Modify</a>
										<a class="btn btn-outline-danger"
										href="/final_brd/maker/customer/remove/${unAuthorizedCustomer.customerCode}">Delete</a>
										<a class="btn btn-outline-info"
										href="/final_brd/maker/customer/detail/${unAuthorizedCustomer.customerCode}?status=${unAuthorizedCustomer.recordStatus}">Details</a></td>
								</tr>
							</c:forEach>
							<c:forEach items="${authorizedCustomers}"
								var="authorizedCustomer">
								<tr>
									<td>${authorizedCustomer.customerCode}</td>
									<td>${authorizedCustomer.customerName}</td>
									<td>${authorizedCustomer.customerEmail}</td>
									<td>A</td>
									<td><a class="btn btn-outline-warning"
										href="/final_brd/maker/customer/update/${authorizedCustomer.customerCode}">Modify</a>

										<a class="btn btn-outline-danger"
										href="/final_brd/maker/customer/remove/${authorizedCustomer.customerCode}">Delete</a>
										<a class="btn btn-outline-info"
										href="/final_brd/maker/customer/detail/${authorizedCustomer.customerCode}?status=A">Details</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="text-center">
						<%@include file="/public/js/effect.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>



