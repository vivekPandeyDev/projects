<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<nav
	class="navbar navbar-expand-lg navbar-light bg-light text-center fs-4 fs-md-auto">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"> <img
			src="/spring_advance/resources/images/logo2.png" alt="grg" width="80"
			height="50" class="d-inline-block align-text-center ">
			Bootstrap
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/spring_advance/">Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/spring_advance/customer/showRegister">Add Customer</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/spring_advance/loan/showLoan">Add Loan Product</a></li>
				<sec:authorize access="hasRole('ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="/spring_advance/admin/fetch">fetchCustomer</a></li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="/spring_advance/login">login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="/spring_advance/logout">logout</a></li>
				</sec:authorize>
			</ul>

		</div>
		<form action="/spring_advance/customer/search" class="d-flex">
			<input class="form-control me-2" type="search"
				placeholder="Enter customer id....." aria-label="Search" name="id">
			<button class="btn btn-outline-success" type="submit">Search</button>
		</form>
	</div>
</nav>