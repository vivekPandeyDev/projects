<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Customer's Detail Update</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class="container-md mt-5">
		<div class="row">
			<div class="col-12">

				<div class="card card-body">
					<form:form class="row g-3 mt-2 needs-validation"
						action="/final_brd/maker/customer/update/${customerDto.customerCode}"
						method="post" modelAttribute="customerDto">
						<h2>Personal Information</h2>
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
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="address.label.address1" /> </label>
							<form:input class="form-control" path="address.address1" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.address1" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="address.label.address2" /> </label>
							<form:input class="form-control" path="address.address2" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.address2" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="address.label.pinCode" /> </label>
							<form:input class="form-control" path="address.pincode" />
							<div class="form-text text-danger fs-6">
								<form:errors path="address.pincode" />
							</div>
						</div>


						<div class="col-md-12 mb-3">
							<button type="submit" class=" form-control btn btn-primary">Update
								Customer</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>



