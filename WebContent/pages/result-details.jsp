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
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>

<body class="admin-getAllResults">
	<header class="row">
		<ul class="col-sm-12 col-md-6">
			<li><a href="">Welcome ${username}</a></li>
		</ul>
	</header>
	<div class="modal" id="add-modal">
		<div class="modal-content">
			<a href="#"><span class="close">&times;</span></a>
			<form action="" method="post" class="modal-form" name="add-result-form">
				<div class="modal-form__icon-wrap">
					<i class="fas fa-user-plus modal-form__body-icon"></i>
				</div>
				<div class="row">
					<i class="fas fa-user modal-form__input-icon"></i>
					<select name="registrationNumber">
						<option value="">registration Number</option>
						<c:forEach var="student" items="${studentDetailsList}">
							<option value="${student.studentRegistrationNumber}">${student.studentRegistrationNumber}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row">
					<i class="fas fa-book modal-form__input-icon"></i>
					<select name="subjectCode">
						<option value="">Subject</option>
						<c:forEach var="subject" items="${subjectDetailsList}">
							<option value="${subject.subjectCode}">${subject.subjectCode}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row">
					<i class="fas fa-book modal-form__input-icon"></i>
					<select name="grade" required>
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
				<div class="row">
					<i class="fas fa-lock modal-form__input-icon"></i>
					<select name="writtenIn" required>
						<option value="">Semester</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
					</select>
				</div>
				<div class="row">
					<button type="submit" class="btn">
						<i class="fas fa-paper-plane"></i> Add Student
					</button>
				</div>
				<div>
					<label class="error-message"></label>
				</div>
			</form>
		</div>
	</div>
	<div class="modal" id="update-modal">
		<div class="modal-content">
			<a href="#"><span class="close">&times;</span></a>
			<form action="" method="post" class="modal-form" name="update-result-form">
				<div class="modal-form__icon-wrap">
					<i class="fas fa-user-plus modal-form__body-icon"></i>
				</div>
				<div class="row">
					<i class="fas fa-male modal-form__input-icon"></i>
					<input type="text" placeholder="Registration Number" name="registrationNumber" required />
				</div>

				<div class="row">
					<i class="fas fa-book modal-form__input-icon"></i>
					<select name="grade" required>
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
				<div class="row">
					<button type="submit" class="btn">
						<i class="fas fa-paper-plane"></i> Add Student
					</button>
				</div>
				<div>
					<label class="error-message"></label>
				</div>
			</form>
		</div>
	</div>
	<br />
	<br />
	<div class="row sidebar-toggle-icon-wrap">
		<i class="fas fa-bars" id="sidebar-toggle-icon"></i>
	</div>
	<div class="row">
		<div class="sidebar collapsed col-md-2">
			<br />
			<ul>
				<li><a href="GetAllStudentDetails"> <i class="fas fa-user-graduate"></i> Student
				</a></li>
				<li><a href="GetAllFacultyDetails"> <i class="fas fa-chalkboard-teacher"></i> Faculty
				</a></li>
				<li><a href="GetAllSubjectDetails"> <i class="fas fa-book"></i> Subject
				</a></li>
				<li><a href="GetAllCollegeDetails"> <i class="fas fa-building"></i> College
				</a></li>
				<li class="active-border"><a href="ResultsController?action=getAllResults" class="active">
						<i class="fas fa-chart-line"></i> Result
				</a></li>
				<li><a href="GetAllApprovedRequest"> <i class="fas fa-check-double"></i> Revaluation
				</a></li>
				<li><a href="${pageContext.request.contextPath}/LogoutController"> <i
						class="fas fa-sign-out-alt"></i> Logout
				</a></li>
			</ul>
		</div>
		<div class="col-sm-12 col-md-10 content">
			<div>
				<a class="btn add-modal-btn" href="#add-modal"> <i class="fas fa-book"></i> Add Result
				</a>
			</div>
			<br>
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
					<div class="table-head">
						<p>Edit</p>
					</div>
					<div class="table-head">
						<p>Delete</p>
					</div>
				</div>
				<c:forEach var="result" items="${resultDetailsList}">
					<div class="table-row" id=row_${result.resultId}>
						<div class="table-data">
							<p>${result.studentRegistrationNumber}</p>
						</div>
						<div class="table-data">
							<p>${result.subjectCode}</p>
						</div>
						<div class="table-data">
							<p>${result.grade}</p>
						</div>
						<div class="table-data edit-icon-wrap">
							<p>
								<a href="#update-modal"><i class="fas fa-edit"></i></a>
							</p>
						</div>
						<div class="table-data delete-icon-wrap">
							<p>
								<i class="fas fa-user-times"></i>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<footer>
		<div class="footer-wrap">
			<p>© designed and developed by Sandeep Kumar</p>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/js/result-details.js"></script>
</body>

</html>