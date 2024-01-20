<%@page import="java.lang.reflect.Parameter"%>
<%@page import="java.util.*,entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bean Product</title>
</head>
<body>
	<jsp:useBean id="product" class="entity.Product" />
	<jsp:setProperty name="product" property="*" />

	<%
	List<Product> products = (ArrayList<Product>) session.getAttribute("products");
	boolean isAvailable = products.contains(product);

	if (isAvailable) {
		session.setAttribute("msg3", "Enter new Product, already exists!!!");
		response.sendRedirect("html/jsp2/product.jsp");
	} else {
		products.add(product);
		session.setAttribute("products", products);
	}
	%>
	<h1>${products}</h1>
	<br>
	<a href="html/jsp2/product.jsp">Add More Product</a>


	<h1>Jstl</h1>
</body>
</html>