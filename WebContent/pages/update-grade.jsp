<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	if (session.getAttribute("username") == null) {
		response.sendRedirect("index.jsp?isValiduser=false");
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/grid.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>

<body class="update-grade">
	<div class="row sidebar-toggle-icon-wrap">
		<i class="fas fa-bars" id="sidebar-toggle-icon"></i>
	</div>
	<div class="row">
		<div class="sidebar collapsed col-md-2">
			<ul>
				<li><a href="">Welcome Admin</a></li>
				<li><a href="GetAllStudentDetails"> <i class="fas fa-user-graduate"></i> Student
				</a></li>
				<li><a href="GetAllFacultyDetails"> <i class="fas fa-chalkboard-teacher"></i> Faculty
				</a></li>
				<li><a href="GetAllSubjectDetails"> <i class="fas fa-book"></i> Subject
				</a></li>
				<li><a href="GetAllCollegeDetails"> <i class="fas fa-building"></i> College
				</a></li>
				<li><a href="ResultsController?action=getAllResults"> <i class="fas fa-chart-line"></i>
						Result
				</a></li>
				<li class="active-border"><a href="GetAllApprovedRequest" class="active" > <i
						class="fas fa-check-double"></i> Revaluation
				</a></li>
				<li><a href="${pageContext.request.contextPath}/LogoutController"> <i
						class="fas fa-sign-out-alt"></i> Logout
				</a></li>
			</ul>
		</div>
		<div class="col-md-10">
			<br>
			<c:if test="${empty(revaluationList)}">
				<h1>No Records Found!</h1>
			</c:if>
			<c:if test="${!empty(revaluationList)}">
				<div class="table">
					<div class="table-row">
						<div class="table-head">
							<p>Registration Number</p>
						</div>
						<div class="table-head">
							<p>Subject Code</p>
						</div>
						<div class="table-head">
							<p>Grade</p>
						</div>
					</div>
				</div>
				<c:forEach var="revaluation" items="${revaluationList}">
					<div class="table-row" id="row_${revaluation.revaluationId}">
						<div class="table-data">
							<p>${revaluation.studentRegistrationNumber}</p>
						</div>
						<div class="table-data">
							<p>${revaluation.subjectCode}</p>
						</div>
						<div class="table-data">
							<select name="grade" id="select_${revaluation.revaluationId}">
								<option value="">Grade</option>
								<option value="S">S</option>
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
								<option value="E">E</option>
								<option value="U">U</option>
							</select>
						</div>
					</div>
				</c:forEach>
				<div class="row">
					<button type="button" class="btn update-grade">Update</button>
				</div>
			</c:if>
		</div>
	</div>
	<footer>
		<div class="footer-wrap">
			<p>© Designed and developed by Sandeep Kumar</p>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/js/revaluation.js"></script>
</body>

</html>