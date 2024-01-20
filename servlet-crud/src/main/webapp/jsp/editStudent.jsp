<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="./allcss.jsp"%>
<script type="text/javascript" defer src="/crud/js/validate.js"></script>
<script type="text/javascript" defer src="/crud/js/countryApi.js"></script>
<title>Student Registration</title>
</head>
<body class="bg-light">

	<%@ include file="./navbar.jsp"%>
	<div class="container-md card mt-5 shadow p-3 mb-5 bg-body rounded">
		<div class="card-body">
			<form class="row mt-3 needs-validation" method="post"
				action="/crud/editStudent?id=${param.id }"
				enctype="multipart/form-data" novalidate>
				<div class="col-6 mb-3">
					<label class="form-label">Student Name</label> <input type="text"
						class="form-control" name="student_name" id="sname"
						placeholder="enter the name" value="${student.studentName}"
						required>
					<div class="invalid-feedback">name should not contain special
						characters or numbers</div>
				</div>
				<div class="col-6 mb-3">
					<label class="form-label">Student Email</label> <input type="email"
						class="form-control" name="student_email"
						value="${student.studentEmail}" placeholder="example@gmail.com"
						required>
					<div class="invalid-feedback">Not A Valid Email</div>
				</div>
				<div class="col-6 mb-3">
					<label class="form-label">Date Of Birth</label> <input type="date"
						class="form-control" name="student_date_of_birth"
						value="${student.studentDateOfBirth}" required>
				</div>
				<div class="col-6 mb-3">
					<label class="form-label">Student Qualification</label> <select
						name="student_qualification"
						value="${student.studentQualification}" class="form-select">
						<option value="BTECH" selected>B.Tech</option>
						<option value="MTECH">M.Tech</option>
						<option value="BBA">B.B.A</option>
						<option value="MBA">M.B.A</option>
						<option value="LLB">L.L.B</option>
					</select>
				</div>
				<div class="col-6 mb-3">
					<label class="form-label">Student Gender</label> <select
						name="student_gender" value="${student.gender}"
						class="form-select">
						<option value="male" selected>male</option>
						<option value="female">female</option>
						<option value="other">other</option>
					</select>
				</div>

				<div class="col-6 mb-3">
					<label class="form-label">Upload a Photo</label>
					<div class="input-group">
						<input type="file" name="student_photo" class="form-control"
							id="inputGroupFile02"> <label
							class="input-group-text btn btn-primary" for="inputGroupFile02">Upload</label>
					</div>
				</div>




				<div class="address-group row">
					<h1 class="heading-5 text-muted">Student Address Details</h1>
					<div class="col-6 mb-3">
						<label class="form-label">House Number</label> <input
							type="number" min="0" class="form-control" name="h_no"
							value="${student.studentAddress.houseNo }" required>
					</div>
					<div class="col-6 mb-3">
						<label class="form-label">Address</label> <input type="text"
							class="form-control" name="main_address"
							value="${student.studentAddress.mainAddress}" required>
						<div class="invalid-feedback">address should not contain
							special characters or numbers</div>
					</div>

					<div class="col-6 mb-3">
						<label class="form-label">City</label> <select name="city"
							class="form-select" id="city"
							value="${student.studentAddress.city}">
							<option value="${student.studentAddress.city}">${student.studentAddress.city}</option>
						</select>
						<div class="invalid-feedback">select a city</div>
					</div>
					<div class="col-6 mb-3">
						<label class="form-label">State</label> <select name="state"
							class="form-select" id="state"
							value="${student.studentAddress.state}">
							<option value="${student.studentAddress.state}">${student.studentAddress.state}</option>
						</select>
						<div class="invalid-feedback">select a state</div>
					</div>
					<div class="col-6 mb-3">
						<label class="form-label">Pin Code</label> <input type="number"
							class="form-control" name="pincode"
							value="${student.studentAddress.pinCode}" required>
					</div>
					<div class="col-6 mb-3">
						<label class="form-label">Country</label> <select name="country"
							class="form-select" id="country"
							value="${student.studentAddress.country}">
							<option value="${student.studentAddress.country}">${student.studentAddress.country}</option>

						</select>
						<div class="invalid-feedback">select a country</div>
					</div>
				</div>

				<button type="submit" class="col-12 mx-auto btn btn-primary">submit</button>

			</form>
		</div>


	</div>

</body>
</html>