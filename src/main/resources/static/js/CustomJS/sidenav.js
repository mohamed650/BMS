$(document).ready(function(){
    $("#checkBal").click(function(){
        $.ajax({
            type: 'GET',
            url: '/checkSession',
            dataType: 'json',
            success: function(response){
                console.log(response.MESSAGE)
                if(response.MESSAGE === "ALIVE"){
                    console.log(response.MESSAGE)
                    showBalance();
                }else{
                    alert("Session Timed Out, Please Login Once again!...");
                    window.location.href="/";
                }
            }
        });
    });
});

function showBalance(){
    $.ajax({
        type: 'POST',
        url: '/showBalance',
        data: {},
        dataType: 'json',
        success: function(response){
            Swal.fire({
                icon: 'success',
                title: 'Available Balance... ' + '\u20B9 ' +response,
                allowOutsideClick: false,
                confirmButtonColor: '#12a8e0',
                confirmButtonText: 'Close',
            });
        }
    })
}