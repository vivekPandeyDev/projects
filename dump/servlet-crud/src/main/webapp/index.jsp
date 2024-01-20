<%@page import="java.util.Set"%>
<%@page import="entity.Student"%>
<%@page import="dao.StudentDaoImpl"%>
<%@page import="dao.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="jsp/allcss.jsp"%>
<title>Home Page</title>
</head>
<body class="bg-light">
	<%@ include file="jsp/navbar.jsp"%>
	<%
	StudentDao studentDao = new StudentDaoImpl();
	Set<Student> students = studentDao.getStudents();
	request.setAttribute("students", students);
	%>
	<div class="container-md card mt-5 shadow p-3 mb-5 bg-body rounded">
		<div class="card-body">
			<table class="table table-dark table-hover">
				<thead class="table-info">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Name</th>
						<th scope="col">DateOfBirth</th>
						<th scope="col">Qualification</th>
						<th scope="col">Gender</th>
						<th scope="col">Email</th>
						<th scope="col">More Detail</th>
						<th scope="col">Action</th>

					</tr>
				</thead>
				<tbody>

					<c:forEach var="student" items="${students}">
						<tr class="fs-5">
							<th scope="row">${student.studentId}</th>
							<td>${ student.studentName}</td>
							<td>${ student.studentDateOfBirth}</td>
							<td>${	student.studentQualification }</td>
							<td>${student.gender }</td>
							<td>${student.studentEmail }</td>
							<td><a href="studentProfile?id=${student.studentId}"
								class="text-blue text-decoration-none">more details</a></td>
							<td class="fs-6 d-flex gap-3"><a type="button"
								class="btn btn-outline-danger btn-small "
								href="editStudent?id=${student.studentId}">Edit</a> <a
								type="button" class="btn btn-outline-info btn-small"
								href="deleteStudent?id=${student.studentId}">Delete</a></td>
						</tr>

					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

</body>
</html>