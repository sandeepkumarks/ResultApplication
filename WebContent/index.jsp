<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Result Management System</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/grid.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/home.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

</head>
<body>
	<header class="row">
		<ul class="col-sm-12 col-md-6">
			<ul class="nav-heading">
				<li><a href="#">Result Management System</a></li>
				<li class="toggle-icon-wrap"><a href="#"> <i class="fa fa-bars" id="navbar-toggle-icon"></i>
				</a></li>
			</ul>
			<li class="hide-link active-border"><a href="#" class="active">Home</a></li>
			<li class="hide-link"><a href="#">Contact</a></li>
			<li class="hide-link" id="login-link"><a href="#modal">Login</a></li>
		</ul>
	</header>

	<div class="modal" id="modal">
		<div class="modal-content">
			<a href="#"><span class="close">&times;</span></a>
			<form action="../LoginController" method="post" class="modal-form" name="login-form">
				<div class="modal-form__icon-wrap">
					<i class="fas fa-user-circle modal-form__body-icon"></i>
				</div>
				<div class="row">
					<i class="fas fa-user modal-form__input-icon"></i>
					<div class="form-group">
						<input type="text" placeholder="Registration Number" name="registrationNumber"
							id="registrationNumber" required>
						<span class="focus-border"></span>
					</div>
				</div>
				<div class="row">
					<i class="fas fa-lock modal-form__input-icon"></i>
					<div class="form-group">
						<input type="password" placeholder="Password" name="password" id="password" required>
						<span class="focus-border"></span>
					</div>
				</div>
				<div class="row">
					<button type="submit" class="btn" id="login-btn">
						<i class="fas fa-paper-plane"></i> Login
					</button>
				</div>
				<div class="message-wrap hide">
					<label class="message"></label>
				</div>
			</form>
		</div>
	</div>

	<section>
		<div class="homepage-image-wrap">
			<figure>
				<img src="${pageContext.request.contextPath}/images/img1.jpg" alt="">
				<img src="${pageContext.request.contextPath}/images/img2.jpg" alt="">
				<img src="${pageContext.request.contextPath}/images/img3.jpg" alt="">
			</figure>
		</div>
		<br>
		<div>
			<center>
				<h1>Result Management System</h1>
			</center>
			<br>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
				ut labore et dolore magna aliqua. Sit amet commodo nulla facilisi nullam vehicula ipsum a arcu.
				Cursus vitae congue mauris rhoncus aenean vel elit scelerisque mauris. Nisi scelerisque eu
				ultrices vitae auctor eu augue ut. Quisque egestas diam in arcu cursus euismod quis viverra
				nibh. Morbi blandit cursus risus at ultrices. Vestibulum mattis ullamcorper velit sed. Viverra
				suspendisse potenti nullam ac tortor. Vel eros donec ac odio tempor orci. Eget nunc scelerisque
				viverra mauris in aliquam. Quam viverra orci sagittis eu volutpat odio. Natoque penatibus et
				magnis dis. In cursus turpis massa tincidunt dui ut ornare lectus. Sit amet est placerat in
				egestas erat imperdiet. Lacinia at quis risus sed vulputate odio ut. Amet commodo nulla facilisi
				nullam vehicula ipsum a arcu cursus. Tempor orci dapibus ultrices in.</p>
			<br>
			<p>In nisl nisi scelerisque eu ultrices vitae. Habitant morbi tristique senectus et netus. In
				cursus turpis massa tincidunt dui ut ornare. Massa tincidunt dui ut ornare lectus. Mattis
				vulputate enim nulla aliquet porttitor lacus luctus accumsan. Viverra justo nec ultrices dui
				sapien. Libero justo laoreet sit amet cursus sit. Turpis tincidunt id aliquet risus feugiat in
				ante. Lorem ipsum dolor sit amet consectetur adipiscing. Nam aliquam sem et tortor. Sed odio
				morbi quis commodo. Dignissim diam quis enim lobortis. Arcu risus quis varius quam quisque.
				Libero enim sed faucibus turpis in. Turpis egestas maecenas pharetra convallis. Bibendum arcu
				vitae elementum curabitur vitae nunc. Vitae justo eget magna fermentum iaculis eu non diam
				phasellus. Tellus pellentesque eu tincidunt tortor aliquam. Feugiat nibh sed pulvinar proin.</p>
		</div>
	</section>
	<br>

	<footer>
		<div class="footer-wrap">
			<strong><p>Anna University</p></strong>
			<p>Sarder Patel Road, Old Highways Building, Guindy, Chennai - 600 025</p>
			<br />
			<p>© Designed and developed by Sandeep Kumar</p>
		</div>
	</footer>

	<script src="${pageContext.request.contextPath}/js/navbar.js"></script>
	<script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>