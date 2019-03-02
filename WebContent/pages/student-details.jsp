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

<body>
	<header class="row">
		<ul class="col-sm-12 col-md-6">
			<li><a href="">Welcome ${username}</a></li>
		</ul>
	</header>
	<div class="modal" id="add-modal">
		<div class="modal-content">
			<a href="#"><span class="close">&times;</span></a>
			<form action="" method="post" class="modal-form" name="add-student-form">
				<div class="modal-form__icon-wrap">
					<i class="fas fa-user-plus modal-form__body-icon"></i>
				</div>
				<div class="row">
					<i class="fas fa-user modal-form__input-icon"></i>
					<input type="text" placeholder="Name" name="studentName" required>
				</div>
				<div class="row">
					<i class="fas fa-building modal-form__input-icon"></i>
					<select name="collegeCode" required>
						<option value="">College</option>
						<c:forEach var="college" items="${collegeDetailsList}">
							<option value="${college.collegeCode}">${college.collegeName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row">
					<i class="fas fa-lock modal-form__input-icon"></i>
					<select name="department" required>
						<option value="">Department</option>
						<option value="CSE">CSE</option>
						<option value="ECE">ECE</option>
						<option value="EEE">EEE</option>
						<option value="IT">IT</option>
						<option value="MECH">MECH</option>
					</select>
				</div>
				<div class="row">
					<i class="fas fa-lock modal-form__input-icon"></i>
					<select name="semester" required>
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
				<div class="message-wrap hide">
					<label class="message"></label>
				</div>
			</form>
		</div>
	</div>
	<div class="modal" id="update-modal">
		<div class="modal-content">
			<a href="#"><span class="close">&times;</span></a>
			<form action="" method="post" class="modal-form" name="update-student-form">
				<div class="modal-form__icon-wrap">
					<i class="fas fa-user-plus modal-form__body-icon"></i>
				</div>
				<div class="row">
					<i class="fas fa-user modal-form__input-icon"></i>
					<input type="text" placeholder="Name" name="studentName" required>
				</div>
				<div class="row">
					<i class="fas fa-building modal-form__input-icon"></i>
					<select name="collegeCode" required>
						<option value="">College</option>
						<c:forEach var="college" items="${collegeDetailsList}">
							<option value="${college.collegeCode}">${college.collegeName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="row">
					<i class="fas fa-lock modal-form__input-icon"></i>
					<select name="department" required>
						<option value="">Department</option>
						<option value="CSE">CSE</option>
						<option value="ECE">ECE</option>
						<option value="EEE">EEE</option>
						<option value="IT">IT</option>
						<option value="MECH">MECH</option>
					</select>
				</div>
				<div class="row">
					<i class="fas fa-lock modal-form__input-icon"></i>
					<select name="semester" required>
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
						<i class="fas fa-edit"></i> Update Student
					</button>
				</div>
				<div class="message-wrap hide">
					<label class="message"></label>
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
		<br>
			<ul>
				<li class="active-border"><a href="GetAllStudentDetails" class="active"> <i
						class="fas fa-user-graduate"></i> Student
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
				<li><a href="GetAllApprovedRequest"> <i class="fas fa-check-double"></i> Revaluation
				</a></li>
				<li><a href="LogoutController"> <i class="fas fa-sign-out-alt"></i> Logout
				</a></li>
			</ul>
		</div>
		<br>
		<div class="col-sm-12 col-md-10 content">
			<div>
				<a type="button" class="btn add-modal-btn" href="#add-modal">
					<i class="fas fa-book"></i> Add Student
				</a>
			</div>
			<br>
			<div class="table">
				<div class="table-row">
					<div class="table-head">
						<p>Registration Number</p>
					</div>
					<div class="table-head">
						<p>Student Name</p>
					</div>
					<div class="table-head">
						<p>College Code</p>
					</div>
					<div class="table-head">
						<p>Department</p>
					</div>
					<div class="table-head">
						<p>Semester</p>
					</div>
					<div class="table-head">
						<p>Edit</p>
					</div>
					<div class="table-head">
						<p>Delete</p>
					</div>
				</div>
				<c:forEach var="student" items="${studentDetailsList}">
					<div class="table-row" id=row_${student.studentRegistrationNumber}>
						<div class="table-data">
							<p>${student.studentRegistrationNumber}</p>
						</div>
						<div class="table-data">
							<p>${student.name}</p>
						</div>
						<div class="table-data">
							<p>${student.collegeCode}</p>
						</div>
						<div class="table-data">
							<p>${student.department}</p>
						</div>
						<div class="table-data">
							<p>${student.semester}</p>
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
	<script src="${pageContext.request.contextPath}/js/student-details.js"></script>
</body>

</html>