window.onload=function() {
	attachClickEvent();
}

function attachClickEvent(){
	Array.from(document.getElementsByClassName("delete-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	var studentRegistrationNumber=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	deleteStudentDetails(studentRegistrationNumber);
	    }, false);
	});
	
	Array.from(document.getElementsByClassName("edit-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	currentRegistrationNumber=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	document.getElementsByClassName("modal")[0].style.display="flex";
	    	document.getElementById("update-modal").style.display="block";
	    	document.getElementById("add-modal").style.display="none";
	    	document.forms["update-student-form"]["studentName"].value = document.getElementById(rowId).children[1].children[0].innerHTML;
	    	document.forms["update-student-form"]["collegeCode"].value= document.getElementById(rowId).children[2].children[0].innerHTML;
	    	document.forms["update-student-form"]["department"].value= document.getElementById(rowId).children[3].children[0].innerHTML;
	    	document.forms["update-student-form"]["semester"].value= document.getElementById(rowId).children[4].children[0].innerHTML;
	    }, false);
	});
}

document.forms["add-student-form"].onsubmit= function(e){
	e.preventDefault();
	var studentRegistrationNumber="";
	var studentName=document.forms["add-student-form"]["studentName"].value;
	var collegeCode=document.forms["add-student-form"]["collegeCode"].value;
	var department=document.forms["add-student-form"]["department"].value;
	var semester=document.forms["add-student-form"]["semester"].value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if(this.responseText=="9999"){
				studentRegistrationNumber=collegeCode+"15104000";
			}
			else{
				studentRegistrationNumber=this.responseText;
			}
			addStudentDetails(studentRegistrationNumber, studentName, collegeCode, department, semester);
		}
	};
	xhttp.open("GET", "GetAllStudentDetails?collegeCode="+collegeCode+"&flag=1", true);
	xhttp.send();
}


function addStudentDetails(studentRegistrationNumber, studentName, collegeCode, department, semester) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[0].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			},4000);
			document.forms["add-student-form"].reset();
			var newEntry="<div class='table-row' id=row_"+studentRegistrationNumber+"><div class='table-data'><p>"+studentRegistrationNumber+"</p></div><div class='table-data'><p>"+studentName+"</p></div><div class='table-data'><p>"+collegeCode+"</p></div><div class='table-data'><p>"+department+"</p></div><div class='table-data'><p>"+semester+"</p></div><div class='table-data edit-icon-wrap'><p><i class='fas fa-edit'></i></p></div><div class='table-data delete-icon-wrap'><p><i class='fas fa-user-times'></i></p></div></div>";  
			document.getElementsByClassName("table")[0].innerHTML +=newEntry;
			attachClickEvent();
		}
	};
	xhttp.open("POST", "InsertStudentDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("studentRegistrationNumber=" + studentRegistrationNumber +"&studentName="+studentName+"&collegeCode="+collegeCode+ "&department="+department+"&semester="+semester);
}


function deleteStudentDetails(studentRegistrationNumber) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("row_"+studentRegistrationNumber).parentNode.removeChild(document.getElementById("row_"+studentRegistrationNumber));
		}
	};
	xhttp.open("POST", "DeleteStudentDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("studentRegistrationNumber=" + studentRegistrationNumber);
}

document.forms["update-student-form"].onsubmit= function(e){
	e.preventDefault();
	var studentRegistrationNumber=currentRegistrationNumber;
	var studentName=document.forms["update-student-form"]["studentName"].value;
	var collegeCode=document.forms["update-student-form"]["collegeCode"].value;
	var department=document.forms["update-student-form"]["department"].value;
	var semester=document.forms["update-student-form"]["semester"].value;
	updateStudentDetails(currentRegistrationNumber, studentRegistrationNumber, studentName, collegeCode, department, semester);
}

function updateStudentDetails(currentRegistrationNumber, studentRegistrationNumber, studentName, collegeCode, department, semester) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[1].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			},4000);
			document.getElementById("row_"+currentRegistrationNumber).children[0].children[0].innerHTML=studentRegistrationNumber;
	    	document.getElementById("row_"+currentRegistrationNumber).children[1].children[0].innerHTML=studentName;
	    	document.getElementById("row_"+currentRegistrationNumber).children[2].children[0].innerHTML=collegeCode;
	    	document.getElementById("row_"+currentRegistrationNumber).children[3].children[0].innerHTML=department;
	    	document.getElementById("row_"+currentRegistrationNumber).children[4].children[0].innerHTML=semester;
			attachClickEvent();
		}
	};
	xhttp.open("POST", "UpdateStudentDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("currentRegistrationNumber="+currentRegistrationNumber+"&studentRegistrationNumber=" + studentRegistrationNumber +"&studentName="+studentName+"&collegeCode="+collegeCode+ "&department="+department+"&semester="+semester);
}
