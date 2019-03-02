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
			<form action="" method="post" class="modal-form" name="add-subject-form">
				<div class="modal-form__icon-wrap">
					<i class="fas fa-user-plus modal-form__body-icon"></i>
				</div>
				<div class="row">
					<i class="fas fa-male modal-form__input-icon"></i>
					<input type="text" placeholder="Subject Code" name="subjectCode" required>
				</div>
				<div class="row">
					<i class="fas fa-user modal-form__input-icon"></i>
					<input type="text" placeholder="Subject Name" name="subjectName" required>
				</div>

				<div class="row">
					<button type="submit" class="btn">
						<i class="fas fa-paper-plane"></i> Add Subject
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
			<form action="" method="post" class="modal-form" name="update-subject-form">
				<div class="modal-form__icon-wrap">
					<i class="fas fa-user-plus modal-form__body-icon"></i>
				</div>
				<div class="row">
					<i class="fas fa-male modal-form__input-icon"></i>
					<input type="text" placeholder="Subject Code" name="subjectCode" required>
				</div>
				<div class="row">
					<i class="fas fa-user modal-form__input-icon"></i>
					<input type="text" placeholder="Subject Name" name="subjectName" required>
				</div>

				<div class="row">
					<button type="submit" class="btn">
						<i class="fas fa-edit"></i> update Subject
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
				<li><a href="GetAllStudentDetails"> <i class="fas fa-user-graduate"></i> Student
				</a></li>
				<li><a href="GetAllFacultyDetails"> <i class="fas fa-chalkboard-teacher"></i> Faculty
				</a></li>
				<li class="active-border"><a href="GetAllSubjectDetails" class="active"> <i
						class="fas fa-book"></i> Subject
				</a></li>
				<li><a href="GetAllCollegeDetails"> <i class="fas fa-building"></i> College
				</a></li>
				<li><a href="ResultsController?action=getAllResults"> <i class="fas fa-chart-line"></i>
						Result
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
				<a class="btn add-modal-btn" href="#add-modal"> <i class="fas fa-book"></i> Add Subject
				</a>
			</div>
			<br>
			<div class="table">

				<div class="table-row">
					<div class="table-head">
						<p>Subject Code</p>
					</div>
					<div class="table-head">
						<p>Subject Name</p>
					</div>
					<div class="table-head">
						<p>Edit</p>
					</div>
					<div class="table-head">
						<p>Delete</p>
					</div>
				</div>
				<c:forEach var="subject" items="${subjectDetailsList}">
					<div class="table-row" id=row_${subject.subjectCode}>
						<div class="table-data">
							<p>${subject.subjectCode}</p>
						</div>
						<div class="table-data">
							<p>${subject.subjectName}</p>
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
	<script src="${pageContext.request.contextPath}/js/subject-details.js"></script>
</body>

</html>