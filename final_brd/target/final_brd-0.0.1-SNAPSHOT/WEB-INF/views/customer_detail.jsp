<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Customer's Detail Page</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/nav-bar.jsp"%>
	<div class="container-md mt-5">
		<div class="row">
			<div class="col-12">

				<div class="card card-body fs-5 bg-light">
					<div class="row g-3 mt-2">
						<h2>Personal Information</h2>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.customerName" /> </label> <input
								class="form-control" value="${customer.customerCode }" readonly />

						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.customerName" /> </label> <input
								class="form-control" value="${customer.customerName }" readonly />

						</div>

						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.customerEmail" /> </label> <input
								class="form-control" value="${customer.customerEmail }" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.contactNumber" /> </label> <input
								class="form-control" value="${customer.contactNumber }" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.primaryContactNumber" /> </label> <input
								class="form-control" value="${customer.primaryContactNumber }"
								name="primaryContactNumber" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.flag" /> </label> <input class="form-control"
								value="${customer.flag }" name="flag" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="customer.label.flag" /> </label> <input class="form-control"
								value="${param.status}" name="flag" readonly />
						</div>
						<h3>Address Detail</h3>
						<div class="col-md-12">
							<label class="form-label "><spring:message
									code="address.label.address1" /> </label> <input class="form-control"
								name="address.address1" value="${customer.address.address1 }"
								readonly />
						</div>
						<div class="col-md-12">
							<label class="form-label "><spring:message
									code="address.label.address2" /> </label> <input class="form-control"
								name="address.address2" value="${customer.address.address2 }"
								readonly />

						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="address.label.pinCode" /> </label> <input class="form-control"
								name="address.pincode" value="${customer.address.pincode }"
								readonly />
						</div>
						<h3>RecordDetail</h3>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="recordDetail.label.createDate" /> </label> <input
								class="form-control" name="address.pincode"
								value="${customer.recordDetail.createDate }" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="recordDetail.label.createBy" /> </label> <input
								class="form-control" name="address.pincode"
								value="${customer.recordDetail.createdBy }" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="recordDetail.label.modifiedDate" /> </label> <input
								class="form-control" name="address.pincode"
								value="${customer.recordDetail.modifiedDate }" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="recordDetail.label.modifiedBy" /> </label> <input
								class="form-control" name="address.pincode"
								value="${customer.recordDetail.modifiedBy }" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="recordDetail.label.authorizedDate" /> </label> <input
								class="form-control" name="address.pincode"
								value="${customer.recordDetail.authorizedDate }" readonly />
						</div>
						<div class="col-md-6">
							<label class="form-label "><spring:message
									code="recordDetail.label.authorizedBy" /> </label> <input
								class="form-control" name="address.pincode"
								value="${customer.recordDetail.authorizedBy }" readonly />
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>



