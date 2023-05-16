$(document).ready(function(){
    $("#logout").unbind('click').bind('click', function(e){
        $.ajax({
            type: 'GET',
            url: '/logout',
            success: function(){
                window.location.href = "/";
            }
        });
    });
});

function makeResponsive(){
    var x = document.getElementById("my-subcontainer");
    if(x.className === "sub-navcontainer"){
        x.className += " responsive";
        document.getElementById("fa-bar").style.display = "none";
        document.getElementById("fa-xmark").style.display = "block";
    }else{
        x.className = "sub-navcontainer";
        document.getElementById("fa-bar").style.display = "block";
        document.getElementById("fa-xmark").style.display = "none";
    }
}