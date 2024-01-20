<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Single User</title>
</head>
<body>
    <c:forEach items="${customers}" var="customer">
	    <h1>Customer Id : ${customer.customerId}</h1>
	    <h1>Customer Name : ${customer.customerName}</h1>
	    <h1>Customer Monthly Income : ${customer.monthlyIncome}</h1>
	    <h1>Customer profession : ${customer.profession}</h1>
	    <h1>Customer designation : ${customer.designation}</h1>
	    <h1>Customer companyName : ${customer.companyName}</h1>
	    <h3>Customer Address  : ${customer.address} </h3>
	    <h1>Customer Date of Birth  : ${customer.dob} </h1s>
    </c:forEach>
</body>
</html>