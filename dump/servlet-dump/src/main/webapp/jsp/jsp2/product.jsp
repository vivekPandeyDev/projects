<%@page import="java.util.*,entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product Page</title>
</head>
<body>
	<h1>This is Product Page</h1>
	<!-- start by using script tag -->
	<%
	List<Product> products = (ArrayList<Product>)session.getAttribute("products");
	Product product = new Product();
	String price = (String)session.getAttribute("price");
	product.setProductName((String)session.getAttribute("name"));
	product.setProductPrice(Double.parseDouble(price));
	if(products != null){
		boolean isAvailable = products.contains(product);
		out.print("is availabe " + isAvailable);
		if(isAvailable){
			session.setAttribute("msg3", "Enter new Product, already exists!!!");
			response.sendRedirect("../../html/jsp2/product.jsp");
		}else{
			products.add(product);
			session.setAttribute("products", products);
		}
		
	}else{
		products = new ArrayList<>();
		products.add(product);
		session.setAttribute("products", products);
	}
	
	for(Product product2 : products){
		out.print(product2);
		
	}
	
	
%>
<!-- end by using script tag  -->
<a href="../../html/jsp2/product.jsp">Add More Product</a>
</body>
</html>