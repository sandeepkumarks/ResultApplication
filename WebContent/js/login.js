document.forms["login-form"].onsubmit = function(e) {
	e.preventDefault();
	var registrationNumber = document.getElementById("registrationNumber").value;
	var password = document.getElementById("password").value;
	var validationResponse = validate(registrationNumber, password);
	if (validationResponse === true) {
		isValidUser(registrationNumber, password);
	} else {
		document.getElementsByClassName("message")[0].innerHTML = validationResponse;
		document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
		setTimeout(function(){
			document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
		},4000);
	}
	
}

function isValidUser(registrationNumber, password) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if (this.responseText == "admin") {
				window.location.href = "GetAllStudentDetails";
			} else if (this.responseText == "student") {
				window.location.href = "pages/student-view-result.jsp";
			} else if (this.responseText == "faculty") {
				window.location.href="FacultySubjectController";
			} else {
				document.getElementsByClassName("message")[0].innerHTML = this.responseText;
				document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
				setTimeout(function(){
					document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
				},4000);
			}
		}
	};
	xhttp.open("POST", "LoginController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("registrationNumber=" + registrationNumber + "&password="
			+ password);
}

function validate(registrationNumber, password) {
	var registrationNumberRegex = /^[0-9]+$/;
	var isValid = registrationNumberRegex.test(registrationNumber);
	if (!isValid) {
		return "Registration Number can only have numbers!";
	}
	if (password.includes(" ")) {
		return "Invalid Password!";
	}
	return true;
}