<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Add Customer</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class="container-md mt-5">
		<div class="row">
			<div class="col-12">
				<div class="mb-3">
					<form
						action="/final_brd/maker/customer/uploadFile?${_csrf.parameterName}=${_csrf.token}"
						enctype="multipart/form-data" method="post">
						<div class="row">
							<div class="col-md-6">
								<label for="formFile" class="form-label">UPLOAD CUSTOMER
									DETAILS FILE</label> <input class="form-control" type="file"
									name="myFile" />
							</div>
							<div class="col-md-6">
								<label class="form-label "> File Name </label> <input
									class="form-control" name="filename" />
							</div>
						</div>

						<div class="text-center">
							<button type="submit"
								class="btn btn-primary mt-2 text-center w-50">SUBMIT</button>
						</div>
						<h3 class="text-center blockquote-footer fs-3 mt-3">OR</h3>
					</form>
				</div>
				<hr>

				<div class="card card-body fs-5 bg-light">
					<form:form class="row g-3 mt-2 needs-validation"
						action="/final_brd/maker/customer/add" method="post"
						modelAttribute="customerDto">
						<h2>Personal Information</h2>
						<div class="col-md-6">
							<label class="form-label "> <spring:message
									code="customer.label.customerCode" />
							</label>
							<form:input class="form-control" path="customerCode" />
							<div class="form-text text-danger fs-6">
								<form:errors path="customerCode" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.customerName" /> </label>
							<form:input class="form-control" path="customerName" />
							<div class="form-text text-danger fs-6">
								<form:errors path="customerName" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.customerEmail" /> </label>
							<form:input class="form-control" path="customerEmail" />
							<div class="form-text text-danger fs-6">
								<form:errors path="customerEmail" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.contactNumber" /> </label>
							<form:input class="form-control" path="contactNumber" />
							<div class="form-text text-danger fs-6">
								<form:errors path="contactNumber" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.primaryContactNumber" /> </label>
							<form:input class="form-control" path="primaryContactNumber" />
							<div class="form-text text-danger fs-6">
								<form:errors path="primaryContactNumber" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.flag" /> </label>
							<form:select class="form-select" path="flag">
								<option value="A"><spring:message htmlEscape='true'
										code="customer.label.flag.active" /></option>
								<option value="I"><spring:message htmlEscape='true'
										code="customer.label.flag.inactive" /></option>
							</form:select>
							<div class="form-text text-danger fs-6">
								<form:errors path="flag" />
							</div>
						</div>

						<h3>Address Detail</h3>
						<div class="col-md-12">
							<label class="form-label "><spring:message
									code="address.label.address1" /> </label>
							<form:textarea row="5" col="30" class="form-control"
								path="address.address1" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.address1" />
							</div>
						</div>
						<div class="col-md-12">
							<label class="form-label "><spring:message
									code="address.label.address2" /> </label>
							<form:textarea row="5" col="30" class="form-control"
								path="address.address2" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.address2" />
							</div>
						</div>
						<div class="col-md-12">
							<label class="form-label "><spring:message
									code="address.label.pinCode" /> </label>
							<form:input class="form-control" path="address.pincode" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.pincode" />
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



