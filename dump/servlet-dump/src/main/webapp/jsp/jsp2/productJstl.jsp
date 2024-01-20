<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.util.*,entity.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
</head>
<body>
	<jsp:useBean id="product" class="entity.Product" />
	<jsp:setProperty name="product" property="*" />
	<c:set var="products" value="${sessionScope.products}" />
	<c:set var="isAvailable" value="${products.contains(product) }" />
	<c:choose>
		<c:when test="${isAvailable}">
			<c:set var="msg3" value="Enter new Product, already exists!!!"
				scope="session" />
			<c:redirect url="html/jsp2/product.jsp" />
		</c:when>
		<c:otherwise>
			${ products.add(product)} 
			<c:set var="products" value="${products}" scope="session" />
		</c:otherwise>
	</c:choose>

	<h1>
		<c:forEach items="${products}" var="product">
		${product }
	</c:forEach>
	</h1>
	<br>
	<a href="html/jsp2/product.jsp">Add More Product</a>


	<h1>Jstl</h1>
</body>
</html>