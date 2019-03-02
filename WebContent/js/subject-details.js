window.onload=function() {
	attachClickEvent();
}

function attachClickEvent(){
	Array.from(document.getElementsByClassName("delete-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	var subjectCode=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	deleteSubjectDetails(subjectCode);
	    }, false);
	  });
	
	Array.from(document.getElementsByClassName("edit-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	currentSubjectCode=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	document.getElementsByClassName("modal")[0].style.display="flex";
	    	document.getElementById("update-modal").style.display="block";
	    	document.getElementById("add-modal").style.display="none";
	    	
	    	console.log(document.getElementById(rowId).childNodes[3].childNodes[1].innerHTML);
	    	document.forms["update-subject-form"]["subjectCode"].value= document.getElementById(rowId).children[0].children[0].innerHTML;
	    	document.forms["update-subject-form"]["subjectName"].value = document.getElementById(rowId).children[1].children[0].innerHTML;
	    }, false);
	});
}

document.forms["add-subject-form"].onsubmit= function(e){
	e.preventDefault();
	var subjectCode=document.forms["add-subject-form"]["subjectCode"].value;
	var subjectName=document.forms["add-subject-form"]["subjectName"].value;
	if(validateSubjectCode("add-subject-form",0) && validateSubjectName("add-subject-form",0)) {
		addSubjectDetails(subjectCode, subjectName);
	}
}

function addSubjectDetails(subjectCode, subjectName) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[0].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			},4000);
			if(this.responseText=="Subject Added Successfully!") {
				document.forms["add-subject-form"].reset();
				var newEntry="<div class='table-row' id=row_"+subjectCode+"><div class='table-data'><p>"+subjectCode+"</p></div><div class='table-data'><p>"+subjectName+"</p></div><div class='table-data edit-icon-wrap'><p><i class='fas fa-edit'></i></p></div><div class='table-data delete-icon-wrap'><p><i class='fas fa-user-times'></i></p></div></div>";  
				document.getElementsByClassName("table")[0].innerHTML +=newEntry;
				attachClickEvent();
			}
		}
	};
	xhttp.open("POST", "InsertSubjectDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("subjectCode=" + subjectCode +"&subjectName="+subjectName);
}


function deleteSubjectDetails(subjectCode) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			document.getElementById("row_"+subjectCode).parentNode.removeChild(document.getElementById("row_"+subjectCode));
		}
	};
	xhttp.open("POST", "DeleteSubjectDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("subjectCode=" + subjectCode);
}

document.forms["update-subject-form"].onsubmit= function(e){
	e.preventDefault();
	var subjectCode=document.forms["update-subject-form"]["subjectCode"].value;
	var subjectName=document.forms["update-subject-form"]["subjectName"].value;
	updateSubjectDetails(currentSubjectCode, subjectCode, subjectName);
}

function updateSubjectDetails(currentSubjectCode, subjectCode, subjectName) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[1].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			},4000);
			document.getElementById("row_"+currentSubjectCode).children[0].children[0].innerHTML=subjectCode;
	    	document.getElementById("row_"+currentSubjectCode).children[1].children[0].innerHTML=subjectName;
			attachClickEvent();
		}
	};
	xhttp.open("POST", "UpdateSubjectDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("currentSubjectCode="+currentSubjectCode+"&subjectCode="+subjectCode+"&subjectName="+subjectName);
}

function validateSubjectCode(formName,index){
	console.log(formName);
	var subjectCode=document.forms[formName]["subjectCode"].value;
	var response=validate(subjectCode,/^[A-Z0-9]+$/);
	if(!response){
		document.getElementsByClassName("message")[index].innerHTML = "Invalid Subject Code!";
		document.getElementsByClassName("message-wrap")[index].classList.remove("hide");
		setTimeout(function(){
			document.getElementsByClassName("message-wrap")[index].classList.add("hide");
		},4000);
		return false;
	}
	return true;
}

function validateSubjectName(formName,index){
	var subjectName=document.forms[formName]["subjectName"].value;
	var response=validate(subjectName,/^[a-zA-Z]+$/);
	if(!response){
		document.getElementsByClassName("message")[index].innerHTML = "Invalid Subject Name!";
		document.getElementsByClassName("message-wrap")[index].classList.remove("hide");
		setTimeout(function(){
			document.getElementsByClassName("message-wrap")[index].classList.add("hide");
		},4000);
		return false;
	}
	return true;
}