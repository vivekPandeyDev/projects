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
						action="/final_brd/user/action/update" method="post"
						modelAttribute="userDto">
						<h2>User Information</h2>
						<div class="col-md-6">
							<label class="form-label ">UserName: </label>
							<form:input class="form-control" path="username" />
							<div class="form-text text-danger fs-6">
								<form:errors path="username" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label ">Password: </label>
							<form:input class="form-control" path="password" />
							<div class="form-text text-danger fs-6">
								<form:errors path="password" />
							</div>
						</div>
						<div class="col-md-12">
							<label class="form-label ">Email: </label>
							<form:input class="form-control" path="email" />
							<div class="form-text text-danger fs-6">
								<form:errors path="email" />
							</div>
						</div>
						<div class="col-md-12 mb-3">
							<button type="submit" class=" form-control btn btn-primary">Add
								User</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>



