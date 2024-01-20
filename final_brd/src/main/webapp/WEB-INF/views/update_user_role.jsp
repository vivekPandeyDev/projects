<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Update User Info</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class="container-md mt-5">
		<div class="row">
			<div class="col-12">

				<div class="card card-body">
					<form:form class="row g-3 mt-2 needs-validation"
						action="/final_brd/user/admin/assignRole/${adminDto.username}" method="post"
						modelAttribute="adminDto">
						<h2>User Information</h2>
						<div class="col-md-12">
							<label class="form-label ">UserName: </label>
							<form:input class="form-control text-center fs-3" path="username"
								readonly="true" />
						</div>
						<div class="col-md-12">
							<label class="form-label">Roles: </label>
							<div class="row fs-5 justify-content-center">
								<div class=" col-3  form-check form-switch">
									<form:checkbox class="form-check-input" path="roles"
										value="ROLE_MAKER" />
									<label class="form-check-label">MAKER</label>
								</div>
								<div class="col-3 form-check form-switch">
									<form:checkbox class="form-check-input" path="roles"
										value="ROLE_CHECKER" />
									<label class="form-check-label">CHECKER</label>
								</div>
								<div class="col-3 form-check form-switch">
									<form:checkbox class="form-check-input" path="roles"
										value="ROLE_ADMIN" />
									<label class="form-check-label">ADMIN</label>
								</div>
							</div>

						</div>
						<div class="col-md-12 mb-3">
							<button type="submit" class=" form-control btn btn-primary">Update
								Role</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>



