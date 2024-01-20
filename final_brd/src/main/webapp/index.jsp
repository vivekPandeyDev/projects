<%@include file="/WEB-INF/views/all_tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Final BRD HomePage</title>
<%@include file="/WEB-INF/views/all_css_js.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/index.css' />">
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						if (localStorage.getItem("currentLang") === "hi") {
							$("#changeLanguage").prop('checked', true);
						}
						$("#changeLanguage")
								.click(
										function(e) {
											if (localStorage
													.getItem("currentLang") === "hi") {
												localStorage.setItem(
														"currentLang", "en_US");
											} else {
												localStorage.setItem(
														"currentLang", "hi");
											}

											window.location.href = "http://localhost:8080/final_brd/changeLanguage?lang="
													+ localStorage
															.getItem("currentLang");

										})
					})
</script>
</head>
<body>
	<div class="switchLangauge">
		<span><spring:message code="index.language" /> &ensp;</span> <label class="switch"> <input
			id="changeLanguage" type="checkbox"> <span
			class="slider round"></span>
		</label>
	</div>
	<header>

		<div class="wrapper">
			<ul class="nav-area">
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/user' />"><spring:message code="index.login" /></a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/logout' />"><spring:message code="index.logout" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ADMIN')">
					<li class="nav-item"><a href="<c:url value='user/admin' />"><spring:message code="index.admin" /></a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('MAKER','CHECKER')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/user/action/update' />"><spring:message code="index.update.user" /></a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/user/action/delete/${pageContext[\"request\"].userPrincipal.principal.username}' />">
						<spring:message code="index.delete.user" />
						</a></li>
				</sec:authorize>
			</ul>
		</div>
		<div class="welcome-text">
			<h1><spring:message code="index.brd" /></h1>

			<sec:authorize access="hasRole('MAKER')">
				<a href="maker/"><spring:message code="index.dashboard" /></a>
			</sec:authorize>
			<sec:authorize access="hasRole('CHECKER')">
				<a href="checker/"><spring:message code="index.language" /></a>
			</sec:authorize>
		</div>
	</header>



</body>
</html>