$(document).ready(function(){
	/*$('#selectRole').on('change.otp', function(){
		$('#otpfield').toggle($(this).val() == '0');
		$('#otpbtn').toggle($(this).val() == '0');
	}).trigger('change.otp');*/
	generateCaptcha();
});
const inputs = document.querySelectorAll(".input");


function addcl(){
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl(){
	let parent = this.parentNode.parentNode;
	if(this.value == ""){
		parent.classList.remove("focus");
	}
}


inputs.forEach(input => {
	input.addEventListener("focus", addcl);
	input.addEventListener("blur", remcl);
});

function changeScreen(){
	window.location.href="/registerScreen";
}

function generateCaptcha(){
	$.ajax({
		type: 'POST',
		url: '/generateCaptcha',
		data: {},
		success: function(response){
			$("#fetchCaptcha").html(response);
		}
	});
}

function loginValidate(){
	let generatedCaptcha = $("#fetchCaptcha").text();
	let customerCaptcha = $("#login_customer_captcha").val();
	var params = {
		customer_Id: $("#login_customer_Id").val(),
		customer_Password: $("#login_customer_Password").val()
	};
	if(params.customer_Id == "" && params.customer_Password == ""){
		alert("Fields cannot be empty!...");
		return false;
	}else if(params.customer_Id == "" && params.customer_Password != ""){
		alert("Customer Id cannot be empty!...");
		return false;
	}else if(params.customer_Id != "" && params.customer_Password == ""){
		alert("Password cannot be empty!...");
		return false;
	}else if(customerCaptcha == ""){
		alert("Please Fill the captcha!...");
		return false;
	}else if(generatedCaptcha !== customerCaptcha){
		alert("Invalid Captcha Entered!..");
		return false;
	}else{
		$.ajax({
			type: 'POST',
			url: '/customerLogin',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE == "SUCCESS"){
					alert("Login Successfull...");
					window.location.href="/accounts";
				}else{
					alert("Invalid User!...");
					window.location.href="/";
					return false;
				}
			}
		})
	}
	
}