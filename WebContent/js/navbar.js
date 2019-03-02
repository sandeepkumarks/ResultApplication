document.getElementById("navbar-toggle-icon").onclick=function(){
    var x=document.getElementsByClassName("hide-link");
    for (var i=0;i<x.length;i+=1){
        if (x[i].className === "hide-link") {
            x[i].className += " show-link";
        } else {
            x[i].className = "hide-link";
        }
    }
}