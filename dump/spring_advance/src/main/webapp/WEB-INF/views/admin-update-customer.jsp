<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Update Customer</title>
<%@include file="all_css_js.jsp"%>
<script type="text/javascript" defer
	src="/spring_advance/resources/js/countryFetch.js"></script>
<script type="text/javascript" defer
	src="/spring_advance/resources/js/customerValidate.js"></script>
</head>
<body>
	<%@include file="nav-bar.jsp"%>
	<div class="container-md mt-5">
		<div class="row">
			<div class="col-12">

				<div class="card card-body">
					<form:form class="row g-3 mt-2 needs-validation"
						action="/spring_advance/admin/update?id=${param.id }"
						method="post" modelAttribute="customerDto">
						<h2>Personal Information</h2>
						<div class="col-md-6">
							<label class="form-label required"><spring:message
									code="label.gender" /></label>
							<form:select class="form-select" aria-label="gender selection"
								path="gender">
								<form:option value="male" label="male" />
								<form:option value="female" label="female" />
								<form:option value="other" label="other" />
							</form:select>
							<div class="form-text">
								<form:errors path="gender" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="validationServer02" class="form-label required"><spring:message
									code="label.firstName" /></label>
							<form:input class="form-control" id="validationServer02"
								path="firstName" />
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">length greater than 3 or Name
								Should not contain special characters!!!!</div>
							<div class="form-text text-danger fs-6">
								<form:errors path="firstName" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="validationServer03" class="form-label required"><spring:message
									code="label.lastName" /></label>
							<form:input class="form-control" id="validationServer03"
								path="lastName" />
							<div class="valid-feedback">Looks good!</div>
							<div class="invalid-feedback">length greater than 3 or last
								name should not contain special characters!!!!</div>
							<div class="form-text text-danger fs-6">
								<form:errors path="lastName" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="validationServer04" class="form-label">FullName</label>
							<input type="text" class="form-control" id="validationServer04"
								required disabled>
						</div>
						<div class="col-md-6">
							<label class="form-label"><spring:message
									code="label.nationality" /></label>
							<form:select class="form-select" aria-label="gender selection"
								path="nationality">
								<form:option value="indian" label="Indian" />
								<form:option value="nri" label="NRI" />
							</form:select>
							<div class="form-text text-danger fs-6">
								<form:errors path="gender" />
							</div>
						</div>
						<div class="col-md-6">
							<label for="dateOfBirth" class="form-label required"><spring:message
									code="label.dob" /></label>
							<!--            is-invalid-->
							<form:input class="form-select" id="dateOfBirth" type="date"
								path="dob" />
							<div class="form-text text-danger fs-6">
								<form:errors path="dob" />
							</div>
						</div>
						<h3>Address Detail</h3>
						<div class="col-md-6">
							<label class="form-label"><spring:message
									code="label.address.houseNo" /></label>
							<form:input type="number" class="form-control"
								path="address.houseNo" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.houseNo" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label"><spring:message
									code="label.address.country" /></label>
							<form:select id="country" class="form-select"
								aria-label="country select" path="address.country">

							</form:select>
							<div class="form-text text-danger fs-6">
								<form:errors path="address.country" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label"><spring:message
									code="label.address.state" /></label>
							<form:select id="state" class="form-select"
								aria-label="state select" path="address.state">
								<option value="${customerDto.address.state }">${customerDto.address.state }</option>
							</form:select>
							<div class="form-text text-danger fs-6">
								<form:errors path="address.state" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label"><spring:message
									code="label.address.city" /></label>
							<form:select id="city" class="form-select"
								aria-label="city select" path="address.city">
								<option value="${customerDto.address.city }">${customerDto.address.city }</option>
							</form:select>
							<div class="form-text text-danger fs-6">
								<form:errors path="address.city" />
							</div>
						</div>
						<div class="col-md-12">
							<label class="form-label"><spring:message
									code="label.address.address1" /></label>
							<form:input class="form-control" path="address.addressLine1" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.addressLine1" />
							</div>
						</div>
						<div class="col-md-12">
							<label class="form-label"><spring:message
									code="label.address.address2" /></label>
							<form:input class="form-control" path="address.addressLine2" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.addressLine2" />
							</div>
						</div>
						<div class="col-md-12 mb-3">
							<button type="submit" class=" form-control btn btn-primary">Add
								Customer</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



