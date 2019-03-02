<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Student Dashboard</title>
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
				<li class="active-border">
					<a href="pages/student-view-result.jsp" class="active">
						<i class="fas fa-chart-line"></i> View Result
					</a>
				</li>
				<li>
					<a href="/ResultApplicationWeb/RevaluationController?action=getNotAppliedResults">
						<i class="fas fa-check-double"></i> Apply for Revaluation
					</a>
				</li>
				<li>
					<a href="/ResultApplicationWeb/RevaluationController?action=checkRevaluationStatus">
						<i class="fas fa-question-circle"></i> Check Status
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
			<form action="" name="get-result-semesterwise-form">
				<div class="row">
					<i class="fas fa-book modal-form__input-icon"></i>
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
						<i class="fas fa-paper-plane"></i>Get Result
					</button>
				</div>
			</form>
			<br>
			<div class="table"></div>
		</div>
	</div>
	<div class="modal close"></div>
	<footer>
		<div class="footer-wrap">
			<p>© designed and developed by Sandeep Kumar</p>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/js/result-details.js"></script>
</body>
</html>