window.onload=function() {
	attachClickEvent();
}

function attachClickEvent(){
	Array.from(document.getElementsByClassName("delete-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	var facultyRegistrationNumber=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	deleteStudentDetails(facultyRegistrationNumber);
	    }, false);
	  });
	
	Array.from(document.getElementsByClassName("edit-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	currentRegistrationNumber=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	document.getElementsByClassName("modal")[0].style.display="flex";
	    	document.getElementById("update-modal").style.display="block";
	    	document.getElementById("add-modal").style.display="none";
	    	
	    	document.forms["update-faculty-form"]["facultyName"].value = document.getElementById(rowId).children[1].children[0].innerHTML;
	    	document.forms["update-faculty-form"]["collegeCode"].value= document.getElementById(rowId).children[2].children[0].innerHTML;
	    	document.forms["update-faculty-form"]["department"].value= document.getElementById(rowId).children[3].children[0].innerHTML;
	    }, false);
	});
}

document.forms["add-faculty-form"].onsubmit= function(e){
	e.preventDefault();
	var facultyName=document.forms["add-faculty-form"]["facultyName"].value;
	var collegeCode=document.forms["add-faculty-form"]["collegeCode"].value;
	var department=document.forms["add-faculty-form"]["department"].value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			if(this.responseText=="9999"){
				facultyRegistrationNumber=collegeCode+"16104000";
			}
			else{
				facultyRegistrationNumber=this.responseText;
			}
			addFacultyDetails(facultyRegistrationNumber, facultyName, collegeCode, department);
		}
	};
	xhttp.open("GET", "GetAllFacultyDetails?collegeCode="+collegeCode+"&flag=1", false);
	xhttp.send();
}

function addFacultyDetails(facultyRegistrationNumber, facultyName, collegeCode, department, semester) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[0].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			},4000);
			document.forms["add-faculty-form"].reset();
			var newEntry="<div class='table-row' id=row_"+facultyRegistrationNumber+"><div class='table-data'><p>"+facultyRegistrationNumber+"</p></div><div class='table-data'><p>"+facultyName+"</p></div><div class='table-data'><p>"+collegeCode+"</p></div><div class='table-data'><p>"+department+"</p></div><div class='table-data edit-icon-wrap'><p><i class='fas fa-edit'></i></p></div><div class='table-data delete-icon-wrap'><p><i class='fas fa-user-times'></i></p></div></div>";  
			document.getElementsByClassName("table")[0].innerHTML +=newEntry;
			attachClickEvent();
		}
	};
	xhttp.open("POST", "InsertFacultyDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("facultyRegistrationNumber=" + facultyRegistrationNumber +"&facultyName="+facultyName+"&collegeCode="+collegeCode+ "&department="+department+"&semester="+semester);
}

function deleteStudentDetails(facultyRegistrationNumber) {
	console.log(facultyRegistrationNumber);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			document.getElementById("row_"+facultyRegistrationNumber).parentNode.removeChild(document.getElementById("row_"+facultyRegistrationNumber));
		}
	};
	xhttp.open("POST", "DeleteFacultyDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("facultyRegistrationNumber=" + facultyRegistrationNumber);
}

document.forms["update-faculty-form"].onsubmit= function(e){
	e.preventDefault();
	var facultyRegistrationNumber=currentRegistrationNumber;
	var facultyName=document.forms["update-faculty-form"]["facultyName"].value;
	var collegeCode=document.forms["update-faculty-form"]["collegeCode"].value;
	var department=document.forms["update-faculty-form"]["department"].value;
	updateFacultyDetails(currentRegistrationNumber, facultyRegistrationNumber, facultyName, collegeCode, department);
}

function updateFacultyDetails(currentRegistrationNumber, facultyRegistrationNumber, facultyName, collegeCode, department) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[1].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			},4000);
			document.getElementById("row_"+currentRegistrationNumber).children[0].children[0].innerHTML=facultyRegistrationNumber;
	    	document.getElementById("row_"+currentRegistrationNumber).children[1].children[0].innerHTML=facultyName;
	    	document.getElementById("row_"+currentRegistrationNumber).children[2].children[0].innerHTML=collegeCode;
	    	document.getElementById("row_"+currentRegistrationNumber).children[3].children[0].innerHTML=department;
			attachClickEvent();
		}
	};
	xhttp.open("POST", "UpdateFacultyDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("currentRegistrationNumber="+currentRegistrationNumber+"&facultyRegistrationNumber=" + facultyRegistrationNumber +"&facultyName="+facultyName+"&collegeCode="+collegeCode+ "&department="+department);
}