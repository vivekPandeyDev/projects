<%@page import="entity.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Training</title>
</head>
<body>
	<% 
	List<Customer> customers = new ArrayList<>();
	customers.add(new Customer());
	customers.add(new Customer());
	request.setAttribute("customers", customers);
	request.setAttribute("customer", new Customer());
	int num=1; 
	session.setAttribute("num", num); 
	%>

<%-- 	<jsp:useBean id="customer" class="entity.Customer" />
	<jsp:setProperty property="*" name="customer"/> --%>

	<c:set value="vivek pandey" target="${customer}" property="name" />
	<c:set value="1" target="${customer }" property="id"/>
	
	<c:set var="array" value="one,two,three,four" ></c:set>
	<c:forTokens var="str" items="${array }" delims=",">
		<c:out value="${str }" /> <br>
		<h3>my name is vivek</h3>
		<% out.print("hello world"); %>
	</c:forTokens>
	<%= request.getAttribute("customer") %>
	
</body>
</html>