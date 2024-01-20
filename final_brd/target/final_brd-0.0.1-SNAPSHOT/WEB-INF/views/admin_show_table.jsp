<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Users detail table</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
<script>
	$(document).ready(function() {
		$('#users').DataTable();
	});
</script>


<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ripple.css' />">

</head>
<body class="bg-light">
	<%@include file="nav-bar.jsp"%>
	<div class="container-sm">
		<div class="card mt-5">
			<div class="card-header text-center h1 text-muted">User's
				Detail</div>
			<div class="card-body">
				<h5 class="card-title text-success">List Of Current Available
					User</h5>
				<c:if test="${not empty delete_msg}">
					<div class="alert alert-danger text-center fs-5" role="alert">${delete_msg}</div>
				</c:if>
				<c:if test="${not empty upload_msg}">
					<div class="alert alert-success text-center fs-5" role="alert">${upload_msg}</div>
				</c:if>
				<div class="card-text table-responsive min-vh-100">
					<table id="customers" class="table table-striped table-hover">
						<thead class="text-center text-muted">
							<tr>
								<th>userName</th>
								<th>email</th>
								<th>Roles</th>
								<th>Modify Role</th>
							</tr>
						</thead>
						<tbody class="text-center fs-5 text-dark bg-light">
							<c:forEach items="${users}" var="user">
								<tr>
									<td>${user.username}</td>
									<td>${user.email}</td>
									<td>
										<c:forEach items="${user.authorities}" var="authority">
											<span>${authority }</span>
										</c:forEach>
									</td>
									<td>
									<a class="btn btn-outline-success"
										href="/final_brd/user/admin/updateRole/${user.username}">update Role</a>
									<a class="btn btn-outline-danger"
										href="/final_brd/user/admin/delete/${user.username}">Delete User</a></td>
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



