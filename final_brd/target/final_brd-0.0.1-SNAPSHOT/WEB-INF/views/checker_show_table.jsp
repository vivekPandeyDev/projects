<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Customers Detail Page</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
<script>
	$(document).ready(function() {
		$('#customers').DataTable();
		let url = new URL(window.location.href)
		let params = new URLSearchParams(url.search)
		if( params.get("accept_msg") !== null ||
			params.get("reject_msg") != null ||
			params.get("delete_msg") != null 
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
	<div class="container-sm">
		<div class="card mt-5">
			<div class="card-header text-center h1 text-muted">Customer's
				Detail</div>
			<div class="card-body">
				<h5 class="card-title text-success">List Of Current Available
					Customers</h5>

				<c:choose>
					<c:when test="${not empty param.accept_msg}">
						<div class="alert alert-success text-center fs-5" role="alert">${param.accept_msg}</div>
					</c:when>
					<c:when test="${not empty param.reject_msg}">
						<div class="alert alert-warning text-center fs-5" role="alert">${param.reject_msg}</div>
					</c:when>
					<c:when test="${not empty param.delete_msg}">
						<div class="alert alert-danger text-center fs-5" role="alert">${param.delete_msg }</div>
					</c:when>
				</c:choose>







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
							<c:forEach items="${unAuthorizedCustomers}" var="customer">
								<tr>
									<td>${customer.customerCode}</td>
									<td>${customer.customerName}</td>
									<td>${customer.customerEmail}</td>
									<td>${customer.recordStatus}</td>
									<td><c:choose>
											<c:when test="${customer.recordStatus == 'D'}">
												<a class="btn btn-danger"
													href="/final_brd/checker/customer/delete/${customer.customerCode}">Delete</a>
											</c:when>
											<c:otherwise>
												<a class="btn btn-outline-success"
													href="/final_brd/checker/customer/accept/${customer.customerCode}">Accept</a>
											</c:otherwise>
										</c:choose> <a class="btn btn-outline-danger"
										href="/final_brd/checker/customer/reject/${customer.customerCode}">Reject</a>
										<a class="btn btn-outline-info"
										href="/final_brd/checker/customer/detail/${customer.customerCode}?status=${customer.recordStatus}">
											Details</a></td>
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



