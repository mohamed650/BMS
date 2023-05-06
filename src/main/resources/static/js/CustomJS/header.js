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