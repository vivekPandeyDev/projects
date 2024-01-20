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
						<form id="customerForm" action="calculateEmi" method="post">
							<div class="row">
								<p class="fs-1">Emi Calculator</p>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Enter loan amount:</label> <input
										type="text" class="input form-control" name="loanAmount"
										placeholder="enter your amount" required>
								</div>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Enter tenure:</label> <input type="text"
										class="input form-control" name="tenure"
										placeholder="enter your tenure" required>
								</div>
								<div class="col-6 form-group">
									<label class="p-1 fs-5">Enter Roi:</label> <input type="text"
										class="input form-control" name="roi"
										placeholder="enter your roi" required>
								</div>


								<div class="btn-group mt-3 gap-4 ">
									<button name="add" type="submit"
										class="btn btn-default btn-success">Calculate Emi</button>

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