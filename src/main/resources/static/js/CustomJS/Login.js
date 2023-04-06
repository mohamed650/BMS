$(document).ready(function(){
	$('#selectRole').on('change.otp', function(){
		$('#otpfield').toggle($(this).val() == '0');
		$('#otpbtn').toggle($(this).val() == '0');
	}).trigger('change.otp');
	
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