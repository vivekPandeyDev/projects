<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../../jsp/allcss.jsp"%>

<script defer>
	$(document).ready(function() {
		var final_address = ""
		$('input[type=text]').each(function() {
			final_address += this.value + ", ";
		});

		$('#textbox').val(final_address)

	})
</script>
<title>Student Profile</title>
</head>
<body class="bg-light">
	<%@ include file="../../jsp/navbar.jsp"%>
	<div class="container-md card shadow p-5  rounded">
		<div class="row">
			<div class="col-6">
				<div class="row text-center">
					<div class="col-12  rounded mb-2">
						<p class="text-center fs-3 ">Welcome to Profile Page,
							${student.studentName}</p>
						<img src="img/${student.imageName}"
							alt="${student.studentName}'s photo"
							class="img-fluid text-center rounded-circle w-75">
					</div>
					<div class="col-12 mt-3">
						<table class="table table-light table-hover">
							<thead>
								<tr>
									<th scope="col">Qualification</th>
									<th scope="col">Date Of Birth</th>
									<th scope="col">Gender</th>
									<th scope="col">Email</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="col">${student.studentQualification}</th>
									<th scope="col">${student.studentDateOfBirth}</th>
									<th scope="col">${student.gender}</th>
									<th scope="col">${student.studentEmail}</th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="col-6">
				<div class="address-group row fs-4 bg-light">
					<h1 class="heading-5 text-muted">Student Address Details</h1>
					<div class="col-6 mb-3">
						<label class="form-label">House Number</label> <input type="text"
							class="form-control fs-5"
							value="${student.studentAddress.houseNo }" readonly="readonly">
					</div>
					<div class="col-6 mb-3">
						<label class="form-label">Address</label> <input type="text"
							class="form-control fs-5"
							value="${student.studentAddress.mainAddress}" readonly="readonly">
					</div>

					<div class="col-6 mb-3">
						<label class="form-label">City</label> <input type="text"
							class="form-control fs-5" value="${student.studentAddress.city}"
							readonly="readonly">

					</div>
					<div class="col-6 mb-3">
						<label class="form-label">State</label> <input type="text"
							class="form-control fs-5" value="${student.studentAddress.state}"
							readonly="readonly">
					</div>
					<div class="col-6 mb-3">
						<label class="form-label">Pin Code</label> <input type="text"
							class="form-control fs-5"
							value="${student.studentAddress.pinCode}" readonly="readonly">
					</div>
					<div class="col-6 mb-3">
						<label class="form-label">Country</label> <input type="text"
							class="form-control fs-5"
							value="${student.studentAddress.country}" readonly="readonly">
					</div>
					<div class="col-12 mb-3">
						<label class="form-label">Full Address</label>
						<textarea id="textbox" readonly="readonly"
							class="form-control fs-5" name="full_address" rows="3"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>