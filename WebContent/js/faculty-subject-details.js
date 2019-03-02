Array.from(document.getElementsByClassName("remove-subject")).forEach(function(element) {
	    element.addEventListener('click', function(){
	    	var rowId=element.parentNode.parentNode.id;
	    	var facultySubjectId=rowId.substring(rowId.indexOf('_')+1,rowId.length);
	    	deleteFacultySubjectDetails(facultySubjectId);
	    }, false);
	});

document.forms["add-facultySubject-form"].onsubmit=function(e) {
	e.preventDefault();
	var subjectCode=document.forms["add-facultySubject-form"]["subjectCode"].value;
	addFacultySubjectDetails(subjectCode);
}

function addFacultySubjectDetails(subjectCode) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
		}
	};
	xhttp.open("POST", "FacultySubjectController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("subjectCode=" + subjectCode+"&action=addFacultySubjectDetails");
}

function deleteFacultySubjectDetails(facultySubjectId) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var subjectDetails=document.getElementById("row_"+facultySubjectId).children[0].children[0].innerHTML;
			var subjectCode=subjectDetails.substring(0,subjectDetails.indexOf('-'));
			document.forms["add-facultySubject-form"]["subjectCode"].innerHTML+="<option value='"+subjectCode+"'>"+subjectDetails+"</option>";
			document.getElementById("row_"+facultySubjectId).parentNode.removeChild(document.getElementById("row_"+facultySubjectId));
		}
	};
	xhttp.open("POST", "FacultySubjectController", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("facultySubjectId=" + facultySubjectId+"&action=deleteFacultySubjectDetails");
}