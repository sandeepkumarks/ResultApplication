window.onload=function() {
	attachClickEvent();
}

function attachClickEvent(){
	Array.from(document.getElementsByClassName("delete-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	var collegeCode=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	deleteCollegeDetails(collegeCode);
	    }, false);
	  });
	
	Array.from(document.getElementsByClassName("edit-icon-wrap")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.id;
	    	console.log(rowId);
	    	currentCollegeCode=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	document.getElementsByClassName("modal")[0].style.display="flex";
	    	document.getElementById("update-modal").style.display="block";
	    	document.getElementById("add-modal").style.display="none";
	    	console.log(document.getElementById(rowId).children[0].children[0]);
	    	document.forms["update-college-form"]["collegeCode"].value= document.getElementById(rowId).children[0].children[0].innerHTML;
	    	document.forms["update-college-form"]["collegeName"].value = document.getElementById(rowId).children[1].children[0].innerHTML;
	    }, false);
	});
}

document.forms["add-college-form"].onsubmit= function(e){
	e.preventDefault();
	var collegeCode=document.forms["add-college-form"]["collegeCode"].value;
	var collegeName=document.forms["add-college-form"]["collegeName"].value;
	if(validateCollegeCode("add-college-form",0) && validateCollegeName("add-college-form",0)) {
		addCollegeDetails(collegeCode, collegeName);
	}
}

function addCollegeDetails(collegeCode, collegeName) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[0].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[0].classList.toggle("hide");
			},4000);
			if(this.responseText=="College Added Successfully!"){
				document.forms["add-college-form"].reset();
				var collegeCodeParagraph=document.createElement("p");
				var collegeNameParagraph=document.createElement("p");
				var editParagraph=document.createElement("p");
				var deleteParagraph=document.createElement("p");
				var collegeCodeText = document.createTextNode(collegeCode);
				var collegeNameText = document.createTextNode(collegeName);
				var editIcon=document.createElement("i");
				editIcon.classList.add("fas");
				editIcon.classList.add("fa-edit");
				var deleteIcon=document.createElement("i");
				deleteIcon.classList.add("fas");
				deleteIcon.classList.add("fa-user-times");
				editParagraph.appendChild(editIcon);
				deleteParagraph.appendChild(deleteIcon);
				collegeCodeParagraph.appendChild(collegeCodeText);
				collegeNameParagraph.appendChild(collegeNameText);
				var collegeCodeDiv= document.createElement("div");
				var collegeNameDiv= document.createElement("div");
				var editDiv= document.createElement("div");
				var deleteDiv= document.createElement("div");
				editDiv.appendChild(editIcon);
				deleteDiv.appendChild(deleteIcon);
				collegeCodeDiv.appendChild(collegeCodeParagraph);
				collegeNameDiv.appendChild(collegeNameParagraph);
				collegeCodeDiv.classList.add("table-data");
				collegeNameDiv.classList.add("table-data");
				editDiv.classList.add("table-data");
				deleteDiv.classList.add("table-data");
				editDiv.classList.add("edit-icon-wrap");
				deleteDiv.classList.add("delete-icon-wrap");
				var mainDiv=document.createElement("div");
				mainDiv.appendChild(collegeCodeDiv);
				mainDiv.appendChild(collegeNameDiv);
				mainDiv.appendChild(editDiv);
				mainDiv.appendChild(deleteDiv);
				mainDiv.classList.add("table-row");
				mainDiv.id="row_"+collegeCode;
				document.getElementsByClassName("table")[0].appendChild(mainDiv);
				attachClickEvent();
			}
		}
	};
	xhttp.open("POST", "InsertCollegeDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("collegeCode=" + collegeCode +"&collegeName="+collegeName);
}

function deleteCollegeDetails(collegeCode) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			document.getElementById("row_"+collegeCode).parentNode.removeChild(document.getElementById("row_"+collegeCode));
		}
	};
	xhttp.open("POST", "DeleteCollegeDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("collegeCode=" + collegeCode);
}

document.forms["update-college-form"].onsubmit= function(e){
	e.preventDefault();
	var collegeCode=document.forms["update-college-form"]["collegeCode"].value;
	var collegeName=document.forms["update-college-form"]["collegeName"].value;
	updateCollegeDetails(currentCollegeCode, collegeCode, collegeName);
}

function updateCollegeDetails(currentCollegeCode, collegeCode, collegeName) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName("message")[1].innerHTML = this.responseText;
			document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			setTimeout(function(){
				document.getElementsByClassName("message-wrap")[1].classList.toggle("hide");
			},4000);
			document.getElementById("row_"+currentCollegeCode).children[0].children[0].innerHTML=collegeCode;
	    	document.getElementById("row_"+currentCollegeCode).children[1].children[0].innerHTML=collegeName;
			attachClickEvent();
		}
	};
	xhttp.open("POST", "UpdateCollegeDetailsController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("currentCollegeCode="+currentCollegeCode+"&collegeCode="+collegeCode+"&collegeName="+collegeName);
}

function validateCollegeCode(formName,index){
	console.log(formName);
	var subjectCode=document.forms[formName]["collegeCode"].value;
	var response=validate(subjectCode,/^[A-Z0-9]+$/);
	if(!response){
		document.getElementsByClassName("message")[index].innerHTML = "Invalid College Code!";
		document.getElementsByClassName("message-wrap")[index].classList.remove("hide");
		setTimeout(function(){
			document.getElementsByClassName("message-wrap")[index].classList.add("hide");
		},4000);
		return false;
	}
	return true;
}

function validateCollegeName(formName,index){
	var subjectName=document.forms[formName]["collegeName"].value;
	var response=validate(subjectName,/^[a-zA-Z]+$/);
	if(!response){
		document.getElementsByClassName("message")[index].innerHTML = "Invalid College Name!";
		document.getElementsByClassName("message-wrap")[index].classList.remove("hide");
		setTimeout(function(){
			document.getElementsByClassName("message-wrap")[index].classList.add("hide");
		},4000);
		return false;
	}
	return true;
}