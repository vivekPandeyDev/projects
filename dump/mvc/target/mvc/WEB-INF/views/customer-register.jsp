<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>CustomerForm</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.1/css/fontawesome.min.css"
	integrity="sha384-QYIZto+st3yW+o8+5OHfT6S482Zsvz2WfOzpFSXMF9zqeLcFV0/wlZpMtyFcZALm"
	crossorigin="anonymous">
<style>
#form-header {
	transition: all 1s;
}

#form-header:hover {
	opacity: 0.5;
}

.input {
	transition: all 0.5s;
}

.input:hover {
	background-color: var(- -bs-gray-200);
}
</style>
</head>
<body class="bg-light">


	<div class="container-md">

		<div class="row">
			<div class="col-12">
				<div class="card shadow-lg">
					<div id="form-header"
						class="card-header fs-1 text-center bg-dark text-light p-2 ">
						CustomerForm</div>
					<div class="card-body">
						<form id="customerForm" action="customerRegister" method="post">
							<div class="row">
								<p class="fs-1">Customer Details</p>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Customer Name:</label> <input
										type="text" class="input form-control" name="customerName"
										placeholder="enter your name" required>
								</div>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Profession:</label> <input type="text"
										class="input form-control" name="profession"
										placeholder="enter your profession" required>
								</div>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Designation:</label> <input type="text"
										class="input form-control" name="designation"
										placeholder="enter your designation" required>
								</div>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Company Name:</label> <input
										type="text" class="input form-control" name="companyName"
										placeholder="enter your companyName" required>
								</div>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Monthly Income:</label> <input
										type="number" min="0" class="input form-control"
										name="monthlyIncome" placeholder="enter your monthlyIncome"
										required>
								</div>
																<div class="col-6 form-group">
                                									<label class="p-1 fs-5">Monthly Income:</label> <input
                                										type="date"  class="input form-control"
                                										name="dob" placeholder="enter your monthlyIncome"
                                										required>
                                								</div>

								<div class="btn-group mt-3 gap-4 ">
									<button name="add" type="submit"
										class="btn btn-default btn-success">Add Customer</button>
																			<button name="update" type="submit" formaction="updateRegister"
                                        										class="btn btn-default btn-info">Add Customer</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>