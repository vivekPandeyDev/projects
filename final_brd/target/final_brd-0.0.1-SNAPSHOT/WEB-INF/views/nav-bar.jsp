<nav
	class="navbar navbar-expand-lg navbar-light  text-center fs-4 fs-md-auto"
	style="background-color: #a2a9ab36;">

	<div class="container-fluid">
		<a class="navbar-brand" href="#"> <img
			src="<c:url value='/resources/images/logo2.png' />" alt="front logo"
			width="80" height="50" class="d-inline-block align-text-center ">
			FINAL BRD APPLICATION
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse text-light" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<c:url value='/' />">Home</a></li>

				<sec:authorize access="hasRole('ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/user/admin' />">show users</a></li>
				</sec:authorize>
				<!--maker specific  -->
				<sec:authorize access="hasRole('MAKER')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/maker/show/table' />">Maker's Table</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('MAKER')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/maker/' />">Maker's DashBoard</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('MAKER')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/maker/customer/fileList' />">Files</a></li>
				</sec:authorize>
				<!--checker specific  -->
				<sec:authorize access="hasRole('CHECKER')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/checker/show/table' />">Checker's Table</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('CHECKER')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/checker/' />">Checker's DashBoard</a></li>
				</sec:authorize>
				<!--login and logout   -->
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/login' />">login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/logout' />">logout</a></li>
				</sec:authorize>
			</ul>

		</div>
	</div>
</nav>