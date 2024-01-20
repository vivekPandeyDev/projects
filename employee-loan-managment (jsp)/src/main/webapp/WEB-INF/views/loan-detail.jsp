<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Add New Loan</title>
<%@include file="all_css_js.jsp"%>
<script type="text/javascript" defer
	src="/spring_advance/resources/js/loan.js"></script>
</head>
<body>
	<%@include file="nav-bar.jsp"%>
	<div class="container-md mt-5">
		<div class="row">
			<div class="col-12">
				<div class="card card-body">
					<form method="post" action="/spring_advance/loan/add"
						class="row g-3 mt-2">
						<div class="col-md-6">
							<label class="form-label required"> Customer Id</label> <input
								type="text" class="form-control" required name="customerId">
						</div>
						<div class="col-md-6">
							<label for="validationServer06" class="form-label required">Loan
								Application No</label> <input type="text" class="form-control"
								id="validationServer06" required name="loanId">
						</div>
						<div class="col-md-6">
							<label class="form-label required">Product Type</label> <select
								id="loanType" class="form-select" aria-label="product type" name="productType">
								<option selected value="none">select product type</option>
							</select>
						</div>
						<div class="col-md-6">
							<label class="form-label required">Product</label> <select
								id="product" class="form-select" aria-label="product" name="product">
								<option selected value="none">select product</option>
							</select>
						</div>

						<div class="col-md-6">
							<label class="form-label required">Loan Amount Request</label> <input
								type="number" min="0" class="form-control" required name="amount">
						</div>
						<div class="col-md-6">
							<label class="form-label required">Tenure(months)</label> <input
								type="number" min="0" max="999" class="form-control" required name="tenure">
						</div>
						<div class="col-md-6">
							<label class="form-label required">rate</label> <input
								type="number" min="0" max="99" class="form-control" required name="rate">
						</div>
						<div class="col-md-6">
							<label for="agreementDate" class="form-label">Agreement
								Date</label>
							<!--            is-invalid-->
							<input class="form-select" id="agreementDate" type="date"
								name="agreementDate" placeholder="dd-mm-yyyy" required >
						</div>
						<div class="col-md-6">
							<label for="installmentDate" class="form-label required">Installment
								Due Date</label>
							<!--            is-invalid-->
							<input class="form-select" id="installmentDate" type="date"
								name="installmentDate" placeholder="dd-mm-yyyy" required>
						</div>
						<div class="col-md-12 mb-3">
							<button type="submit" class=" form-control btn btn-primary">Add
								Loan</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



