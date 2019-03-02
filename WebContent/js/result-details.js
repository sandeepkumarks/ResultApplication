if(document.getElementsByTagName("body")[0].classList.contains("admin-getAllResults")){
	window.onload=function() {
		attachClickEvent();
	}

	function attachClickEvent(){
		Array.from(document.getElementsByClassName("delete-icon-wrap")).forEach(function(element) {
		    element.addEventListener('click', function(){
		    	var rowId=element.parentNode.id;
		    	var resultId=rowId.substring(rowId.indexOf('_')+1,rowId.length);
		    	deleteResultDetails(resultId);
		    }, false);
		  });
		
		Array.from(document.getElementsByClassName("edit-icon-wrap")).forEach(function(element) {
		    element.addEventListener('click', function(){
		    	var rowId=element.parentNode.id;
		    	resultId=rowId.substring(rowId.indexOf('_')+1,rowId.length);
		    	document.getElementsByClassName("modal")[0].style.display="flex";
		    	document.getElementById("update-modal").style.display="block";
		    	document.getElementById("add-modal").style.display="none";
		    	
		    	console.log(document.getElementById(rowId).childNodes[3].childNodes[1].innerHTML);
		    	document.forms["update-subject-form"]["subjectCode"].value= document.getElementById(rowId).childNodes[1].childNodes[1].innerHTML;
		    	document.forms["update-subject-form"]["subjectName"].value = document.getElementById(rowId).childNodes[3].childNodes[1].innerHTML;
		    }, false);
		});
	}

	document.forms["add-result-form"].onsubmit= function(e){
		e.preventDefault();
		var studentRegistrationNumber=document.forms["add-result-form"]["registrationNumber"].value;
		var subjectCode=document.forms["add-result-form"]["subjectCode"].value;
		var grade=document.forms["add-result-form"]["grade"].value;
		var writtenIn=document.forms["add-result-form"]["writtenIn"].value;
		addResultDetails(studentRegistrationNumber, subjectCode, grade, writtenIn);
	}

	function addResultDetails(studentRegistrationNumber, subjectCode, grade, writtenIn) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				document.getElementsByClassName("error-message")[0].innerHTML = this.responseText;
				var newEntry="<div class='table-row' id=row_${result.resultId}><div class='table-data'><p>"+studentRegistrationNumber+"</p></div><div class='table-data'><p>"+subjectCode+"</p></div><div class='table-data'><p>"+grade+"</p></div><div class='table-data edit-icon-wrap'><p><i class='fas fa-edit'></i></p></div><div class='table-data delete-icon-wrap'><p><i class='fas fa-user-times'></i></p></div></div>";  
				document.getElementsByClassName("table")[0].innerHTML +=newEntry;
				attachClickEvent();
			}
		};
		xhttp.open("POST", "InsertResultDetailsController", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("studentRegistrationNumber="+studentRegistrationNumber+"&subjectCode="+subjectCode+"&grade="+grade+"&writtenIn="+writtenIn);
	}


	function deleteResultDetails(resultId) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				document.getElementById("row_"+resultId).parentNode.removeChild(document.getElementById("row_"+resultId));
			}
		};
		xhttp.open("POST", "DeleteResultDetailsController", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("resultId=" + resultId);
	}

	document.forms["update-result-form"].onsubmit= function(e){
		e.preventDefault();
		var studentRegistrationNumber=document.forms["update-result-form"]["registrationNumber"].value;
		var subjectCode=document.forms["update-result-form"]["subjectCode"].value;
		var grade=document.forms["update-result-form"]["grade"].value;
		updateResultDetails(resultId, studentRegistrationNumber, subjectCode, grade);
	}

	function updateResultDetails(resultId, studentRegistrationNumber, subjectCode, grade) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementsByClassName("error-message")[0].innerHTML = this.responseText;
				document.getElementById("row_"+resultId).childNodes[1].childNodes[1].innerHTML=studentRegistrationNumber;
		    	document.getElementById("row_"+resultId).childNodes[3].childNodes[1].innerHTML=subjectCode;
		    	document.getElementById("row_"+resultId).childNodes[3].childNodes[1].innerHTML=grade;
				attachClickEvent();
			}
		};
		xhttp.open("POST", "UpdateResultDetailsController", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("resultId="+resultId+"&studentRegistrationNumber="+studentRegistrationNumber+"&subjectCode="+subjectCode+"&grade="+grade);
	}
}
else {
	document.forms["get-result-semesterwise-form"].onsubmit = function(e) {
	    e.preventDefault();
	    var semester = document.forms["get-result-semesterwise-form"]["semester"].value;
	    var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	        if (this.readyState == 4 && this.status == 200) {
	            if (this.responseText == "null") {
	                document.getElementsByClassName("table")[0].innerHTML = "<h1>No Records Found!</h1>";
	            } else {
	                var tableHeading = "<div class='table-row'>" +
	                    "<div class='table-head'>" +
	                    "<p>Registration Number</p>" +
	                    "</div>" +
	                    "<div class='table-head'>" +
	                    "<p>Subject Code</p>" +
	                    "</div>" +
	                    "<div class='table-head'>" +
	                    "<p>Subject Name</p>" +
	                    "</div>" +
	                    "<div class='table-head'>" +
	                    "<p>Grade</p>" +
	                    "</div>" +
	                    "</div>";
	                document.getElementsByClassName("table")[0].innerHTML = tableHeading;
	                var resultList = JSON.parse(this.responseText);
	                resultList.forEach(function(result) {
	                    var tableData = "<div class='table-row'>" +
	                        "<div class='table-data'>" +
	                        "<p>" + result.resultId + "</p>" +
	                        "</div>" +
	                        "<div class='table-data'>" +
	                        "<p>" + result.subjectCode + "</p>" +
	                        "</div>" +
	                        "<div class='table-data'>" +
	                        "<p>" + result.subjectName + "</p>" +
	                        "</div>" +
	                        "<div class='table-data'>" +
	                        "<p>" + result.grade + "</p>" +
	                        "</div>" +
	                        "</div>";
	                    document.getElementsByClassName("table")[0].innerHTML += tableData;
	                });
	            }

	        }
	    };
	    xhttp.open("GET", "../ResultsController?semester=" + semester + "&action=getResultsBySemester", true);
	    xhttp.send();

	}
}
