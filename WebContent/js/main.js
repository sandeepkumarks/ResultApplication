document.getElementById("sidebar-toggle-icon").onclick = function () {
    document.getElementsByClassName('sidebar')[0].classList.toggle('collapsed');
}

function validate (userdata, regex){
	if(!regex.test(userdata)) {
		return false; 
	}
	return true;
}