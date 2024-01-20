<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Customers Detail Page</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#customers').DataTable();
	})
</script>
</head>
<body class="bg-light">
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class="container-fluid my-5">
		<table id="customers" class="table table-hover">
			<thead class="text-center text-muted">
				<tr>
					<th>Code</th>
					<th>Name</th>
					<th>Address1</th>
					<th>PinCode</th>
					<th>Email</th>
					<th>RecordStatus</th>
					<th>createDate</th>
					<!-- 					<th>flag</th>
					<th>createdBy</th> -->
					<th>message</th>
				</tr>
			</thead>

			<tbody class="mt-5 fs-5 text-light">
				<c:forEach items="${customers}" var="customer">
					<c:choose>
						<c:when test="${not customer.isAccepted}">
							<tr class="bg-danger">
						</c:when>
						<c:otherwise>
							<tr style="background-color: #2b9765!important">
						</c:otherwise>
					</c:choose>


					<td>${customer.customerCode}</td>
					<td>${customer.customerName}</td>
					<td>${customer.address1}</td>
					<td>${customer.pinCode}</td>
					<td>${customer.email}</td>
					<td>${customer.status}</td>
					<td>${customer.createDate}</td>
					<%-- 					<td>${customer.flag}</td>
					<td>${customer.createdBy}</td> --%>
					<td>${customer.message}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>



