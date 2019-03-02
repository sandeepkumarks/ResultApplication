<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Approve Request</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/grid.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>
<body>
	<div class="row sidebar-toggle-icon-wrap">
		<i class="fas fa-bars" id="sidebar-toggle-icon"></i>
	</div>
	<div class="row">
		<div class="sidebar collapsed col-md-2">
			<ul>
				<li>
					<a href="">Welcome ${username}</a>
				</li>
				<li>
					<a href="#">
						<i class="fas fa-chart-line"></i> View Result
					</a>
				</li>
				<li class="active-border">
					<a class="active" href="FacultySubjectController">
						<i class="fas fa-plus-circle"></i> Enroll Subject
					</a>
				</li>
				<li>
					<a href="/ResultApplicationWeb/RevaluationController?action=getRevaluationListForFaculty">
						<i class="fas fa-thumbs-up"></i> Approve/Reject Request
					</a>
				</li>
				<li>
					<a href="/ResultApplicationWeb/LogoutController">
						<i class="fas fa-sign-out-alt"></i> Logout
					</a>
				</li>
			</ul>
		</div>
		<div class="col-md-10">
			<br>
			<div class="row">
				<form action="" name="add-facultySubject-form">
					<select name="subjectCode">
						<c:if test="${!empty(subjectList)}">
							<c:forEach var="subject" items="${subjectList}">
								<option value="${subject.subjectCode}">${subject.subjectCode}-${subject.subjectName}</option>
							</c:forEach>
						</c:if>
					</select>
					<div class="row">
						<button type="submit" class="btn">
							<i class="fas fa-paper-plane"></i> Add Subject
						</button>
					</div>
				</form>
			</div>

			<div class="table">
				<c:if test="${empty(facultySubjectList)}">
					<option value="">No Records Found!</option>
				</c:if>
				<c:if test="${!empty(facultySubjectList)}">
					<c:forEach var="facultySubject" items="${facultySubjectList}">
						<div class="table-row" id="row_${facultySubject.facultySubjectId}">
							<div class="table-head">
								<p>${facultySubject.subjectCode}-${facultySubject.subjectName}</p>
							</div>
							<div class="table-head">
								<button class="btn remove-subject">Remove</button>
							</div>
						</div>
					</c:forEach>

				</c:if>
			</div>
		</div>
	</div>
	<footer>
		<div class="footer-wrap">
			<p>© designed and developed by Sandeep Kumar</p>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/js/faculty-subject-details.js"></script>
</body>
</html>