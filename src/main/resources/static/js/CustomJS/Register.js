$(document).ready(function(){
	loadAccountTypes();
	loadStates();
	loadCities();
	
	/*var canvas = document.getElementById("signature-pad");

	function resizeCanvas(){
		var ratio = Math.max(window.devicePixelRatio || 1, 1);
		canvas.width = canvas.offsetWidth * ratio;
		canvas.height = canvas.offsetHeight * ratio;
		canvas.getContext("2d").scale(ratio, ratio);
		console.log(canvas.getContext("2d").scale(ratio, ratio));
	}

	window.onresize = resizeCanvas;
	resizeCanvas();
	
	var signaturePad = new SignaturePad(canvas, {
		backgroundColor: 'rgb(250,250,250)'
	});
	
	document.getElementById("clear").addEventListener('click', function(){
		signaturePad.clear();
	});


	var joint_canvas = document.getElementById("joint_signature-pad");
	
	function joint_resizeCanvas(){
		var ratio = Math.max(window.devicePixelRatio || 1, 1);
		joint_canvas.width = joint_canvas.offsetWidth * ratio;
		joint_canvas.height = joint_canvas.offsetHeight * ratio;
		joint_canvas.getContext("2d").scale(ratio, ratio);
	}
	
	window.onresize = joint_resizeCanvas;
	joint_resizeCanvas();
	
	var joint_signaturePad = new SignaturePad(joint_canvas, {
		backgroundColor: 'rgb(250,250,250)'
	});
	
	document.getElementById("joint_clear").addEventListener('click', function(){
		joint_signaturePad.clear();
	});*/
});

function onlyNumbers(event){
	var ascii = (event.which) ? event.which : event.keyCode;
	if(ascii > 31 && (ascii < 48 || ascii > 57)){
		return false;
	}else{
		return true;
	}
}
	
function onlyAlphabets(event){
	var ascii = (event.which) ? event.which : event.keyCode;
	if((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123)){
		return true;
	}else{
		return false;
	}
}

function loadAccountTypes(){
	$.ajax({
		type: 'POST',
		url: '/getSbiAccountTypes',
		data: {},
		success: function(response){
			if(response.ERRORMSG == "MSG10"){
				alert("MSG10: "+ MSG10);
				return false;
			}else{
				var map = JSON.parse(response);
				loadAccountTypesInfo(map);
			}
		}
	});
}

function loadAccountTypesInfo(map){
	var s ='<option value="-1">--Select Account Type--</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';
	}
	$("#customer_AccountType").html(s);
}

function loadStates(){
	$.ajax({
		type: 'POST',
		url: '/getStates',
		data: {},
		success: function(response){
			if(response.ERRORMSG == "MSG10"){
				alert("MSG10: "+ MSG10);
				return false;
			}else{
				var map = JSON.parse(response);
				loadStatesInfo(map);
			}
		}
	});
}

function loadStatesInfo(map){
	var s ='<option value="-1">--Select State--</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';
	}
	$("#selectState").html(s);
	$("#selectJointState").html(s);
	
	$("#selectState").on('change', function(){
		var params = {
			state_code: $("#selectState").val()
		};
		if(params.state_code == -1){
			alert("Please Select Any State..");
			var city = sessionStorage.getItem("Cities");
			$("#selectCity").html(city);
			return false;
		}else{
			$.ajax({
				type: 'POST',
				url: '/getFilteredCities',
				data: params,
				dataType: 'json',
				success: function(response){
					if(response.ERRORMSG == "MSG10"){
						alert("MSG10: "+ MSG10);
						return false;
					}else{
						loadFilteredCities(response);
					}
				}
			});
		}
	});
	
	$("#selectJointState").on('change', function(){
		var params = {
			state_code: $("#selectJointState").val()
		};
		if(params.state_code == -1){
			alert("Please Select Any State..");
			var city = sessionStorage.getItem("Cities");
			$("#selectJointCity").html(city);
			return false;
		}else{
			$.ajax({
				type: 'POST',
				url: '/getJointFilteredCities',
				data: params,
				dataType: 'json',
				success: function(response){
					if(response.ERRORMSG == "MSG10"){
						alert("MSG10: "+ MSG10);
						return false;
					}else{
						loadJointFilteredCities(response);
					}
				}
			});
		}
	});
}

function loadFilteredCities(map){
	var s ='<option value="-1">--Select City--</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';
	}
	$("#selectCity").html(s);
}

function loadJointFilteredCities(map){
	var s ='<option value="-1">--Select City--</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';
	}
	$("#selectJointCity").html(s);
}

function loadCities(){
	$.ajax({
		type: 'POST',
		url: '/getCities',
		data: {},
		success: function(response){
			if(response.ERRORMSG == "MSG10"){
				alert("MSG10: "+ MSG10);
				return false;
			}else{
				var map = JSON.parse(response);
				loadCitiesInfo(map);
			}
		}
	});
}

function loadCitiesInfo(map){
	var s ='<option value="-1">--Select City--</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';
	}
	$("#selectCity").html(s);
	$("#selectJointCity").html(s);
	sessionStorage.setItem("Cities", s);
}

function checkRadio(){
	var radioChecked = document.getElementById("radioYes").checked;
	if(radioChecked == true){
		document.getElementById("jointOwnerdiv").style.display = "block";
		document.getElementById("jointOwnerHR").style.display = "block";
		//document.getElementById("joint-signature-desc").style.display = "block";
		//document.getElementById("jointOwnerSignaturePad").style.display = "flex";
	}else{
		document.getElementById("jointOwnerdiv").style.display = "none";
		document.getElementById("jointOwnerHR").style.display = "none";
		//document.getElementById("joint-signature-desc").style.display = "none";
		//document.getElementById("jointOwnerSignaturePad").style.display = "none";
	}
}


