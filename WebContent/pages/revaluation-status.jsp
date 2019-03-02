<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Apply Revaluation</title>
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
					<a href="pages/student-view-result.jsp">
						<i class="fas fa-chart-line"></i> View Result
					</a>
				</li>
				<li>
					<a href="/ResultApplicationWeb/RevaluationController?action=getNotAppliedResults">
						<i class="fas fa-check-double"></i> Apply for Revaluation
					</a>
				</li>
				<li class="active-border">
					<a href="RevaluationController?action=checkRevaluationStatus">
						<i class="fas fa-question-circle" class="active"></i> Check Status
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
			<c:if test="${!empty(revaluationStatusList)}">
				<div class="table">
					<div class="table-row">
						<div class="table-head">
							<p>Registration Number</p>
						</div>
						<div class="table-head">
							<p>Subject Code</p>
						</div>
						<div class="table-head">
							<p>Subject Name</p>
						</div>
						<div class="table-head">
							<p>Status</p>
						</div>
						<div class="table-head">
							<p>Updated Grade</p>
						</div>
					</div>
					<c:forEach var="revaluation" items="${revaluationStatusList}">
						<div class="table-row">
							<div class="table-data">
								<p>${currentUser.registrationNumber}</p>
							</div>
							<div class="table-data">
								<p>${revaluation.subjectCode}</p>
							</div>
							<div class="table-data">
								<p>${revaluation.subjectName}</p>
							</div>
							<div class="table-data">
								<p>${revaluation.revaluationStatus}</p>
							</div>
							<div class="table-data">
								<p>${revaluation.updatedGrade}</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${empty(revaluationStatusList)}">
				<h1>You haven't applied for any revaluation!</h1>
			</c:if>
		</div>
	</div>
	<div class="modal close"></div>
	<footer>
		<div class="footer-wrap">
			<p>© designed and developed by Sandeep Kumar</p>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>