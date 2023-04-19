$(document).ready(function(){
	loadAccountTypes();
	loadStates();
	loadCities();
	loadOccupations();
	
	var currDate = new Date();
	var month;
	if((currDate.getMonth()+1) < 10){
		month = "0"+(currDate.getMonth()+1);
	}else{
		month = (currDate.getMonth()+1);
	}
	currDate = currDate.getFullYear()+"-"+month+"-"+currDate.getDate();
	
	$("#customer_DOB").attr('max', currDate);
	$("#joint_customer_DOB").attr('max', currDate);
	
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
	
	$("#customer_AccountType").on('change', function(){
		var customer_AccountType = $("#customer_AccountType :selected").text();
		if(customer_AccountType.trim() !== "Joint Account"){
			document.getElementById("jointOwnerDivision").style.display = "none";
			document.getElementById("jointOwnerdivHr").style.display = "none";
			document.getElementById("jointOwnerdiv").style.display = "none";
			document.getElementById("jointOwnerHR").style.display = "none";
		}else{
			document.getElementById("jointOwnerDivision").style.display = "block";
			document.getElementById("jointOwnerdivHr").style.display = "block";
			/*document.getElementById("jointOwnerdiv").style.display = "block";*/
		}
	});
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

function loadOccupations(){
	$.ajax({
		type: 'POST',
		url: '/getOccupations',
		data: {},
		success: function(response){
			var map = JSON.parse(response);
			loadOccupationsInfo(map);
		}
	});
}

function loadOccupationsInfo(map){
	var s = '<option value="-1">--Select Occupation--</option>';
	for(data in map){
		s += '<option value="' + data + '">' + map[data] + '</option>';
	}
	$("#selectOccupation").html(s);
	$("#selectJointOccupation").html(s);
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

function getCustomerAge(dob){
	var birthDate = new Date(dob);
	var today = new Date();
	var customerAge = today.getFullYear() - birthDate.getFullYear();
	var month = today.getMonth() - birthDate.getMonth();
	if(month < 0 || (month === 0 && today.getDate() < birthDate.getDate())){
		customerAge--;
	}
	return customerAge;
}


function registerCustomer(){
	const notAllowedSpecials = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
	const regex_pattern = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	const phoneValid = /^[6-9]\d{9}$/;
	let accountType = $("#customer_AccountType").val();
	let customerTitle = $("#selectTitle").val();
	let customerSelectedTitle = $("#selectTitle :selected").text();
	let customerJointTitle = $("#selectJointTitle").val();
	let customerSelectedJointTitle = $("#selectJointTitle :selected").text();
	let radioOptions = document.getElementsByName("gender");
	let joint_radioOptions = document.getElementsByName("joint_gender");
	let customerState = $("#selectState").val();
	let customerCity = $("#selectCity").val();
	let customerJointState = $("#selectJointState").val();
	let customerJointCity = $("#selectJointCity").val();
	let customerOccupation = $("#selectOccupation").val();
	let customerJointOccupation = $("#selectJointOccupation").val();
	let countryCode = $("#countryCode").val();
	let jointCountryCode = $("#jointCountryCode").val();
	let addressLine1 = $("#addressLine1").val();
	let addressLine2 = $("#addressLine2").val();
	let joint_addressLine1 = $("#joint_addressLine1").val();
	let joint_addressLine2 = $("#joint_addressLine2").val();
	let confirmPassword = $("#customer_ConfirmPassword").val();
	let jointAccountRadio = document.getElementsByName("jointAccount");
	let termsCondition = document.getElementById("termsCondition");
	
	var params = {
		customer_AccountTypeId: accountType,
		customer_AccountType: $("#customer_AccountType :selected").text(),
		customer_FirstName: customerSelectedTitle.concat($("#customer_FirstName").val()),
		customer_LastName: $("#customer_LastName").val(),
		customer_Gender: $('input[name="gender"]:checked').val(),
		customer_Phone: countryCode.concat($("#customer_Phone").val()),
		customer_Email: $("#customer_Email").val(),
		customer_DOB: $("#customer_DOB").val(),
		customer_Address: addressLine1.concat(" ").concat(addressLine2),
		customer_ZipCode: $("#customer_ZipCode").val(),
		state_code: customerState,
		state_name: $("#selectState :selected").text(),
		city_code: customerCity,
		city_name: $("#selectCity :selected").text(),
		customer_Aadhar: $("#customer_Aadhar").val(),
		customer_OccupationId: customerOccupation,
		customer_Occupation: $("#selectOccupation :selected").text(),
		customer_Password: $("#customer_Password").val(),
		joint_customer_FirstName: customerSelectedJointTitle.concat($("#joint_customer_FirstName").val()),
		joint_customer_LastName: $("#joint_customer_LastName").val(),
		joint_customer_Gender: $('input[name="joint_gender"]:checked').val(),
		joint_customer_Phone: jointCountryCode.concat($("#joint_customer_Phone").val()),
		joint_customer_Email: $("#joint_customer_Email").val(),
		joint_customer_DOB: $("#joint_customer_DOB").val(),
		joint_customer_Address: joint_addressLine1.concat(" ").concat(joint_addressLine2),
		joint_customer_ZipCode: $("#joint_ZipCode").val(),
		joint_state_code: customerJointState,
		joint_state_name: $("#selectJointState :selected").text(),
		joint_city_code: customerJointCity,
		joint_city_name: $("#selectJointCity :selected").text(),
		joint_customer_Aadhar: $("#joint_customer_Aadhar").val(),
		joint_customer_OccupationId: customerJointOccupation,
		joint_customer_Occupation: $("#selectJointOccupation :selected").text()
	};
	
	let customerAge = getCustomerAge(params.customer_DOB);
	let jointCustomerAge = getCustomerAge(params.joint_customer_DOB);
	
	if(accountType == -1){
		alert("Please select Account Type!..");
		return false;
	}else if(customerTitle == -1){
		alert("Please select Title!..");
		return false;
	}else if(params.customer_FirstName.substring(customerSelectedTitle.length) == ""){
		alert("First Name cannot be empty!..");
		return false;
	}else if(params.customer_FirstName.substring(customerSelectedTitle.length).match(notAllowedSpecials)){
		alert("First Name cannot contain special characters!..");
		return false;
	}else if(params.customer_LastName == ""){
		alert("Last Name cannot be empty!..");
		return false;
	}else if(params.customer_LastName.match(notAllowedSpecials)){
		alert("Last Name cannot contain special characters!..");
		return false;
	}else if(!(radioOptions[0].checked || radioOptions[1].checked || radioOptions[2].checked)){
		alert("Please select your gender!..");
		return false;
	}else if(params.customer_Phone .substring(countryCode.length) == ""){
		alert("Phone Number cannot be empty!..");
		return false;
	}else if(!(params.customer_Phone.substring(countryCode.length).match(phoneValid))){
		alert("Invalid Phone Number!..");
		return false;
	}else if(params.customer_Email == ""){
		alert("Email cannot be empty!..");
		return false;
	}else if(!(params.customer_Email.match(regex_pattern))){
		alert("Invalid Email Id!..");
		return false;
	}else if(params.customer_DOB == ""){
		alert("Date of Birth cannot be null!..");
		return false;
	}else if(customerAge < 2){
		alert("Age should be above 2 years!..");
		return false;
	}else if((customerAge >= 2 && customerAge <10) && ((params.customer_AccountType).trim() !== "Joint Account")){
		alert("For Minors, Please select Joint Account!..");
		return false;
	}else if(params.customer_Address == " "){
		alert("Address Cannot be empty!..");
		return false;
	}else if(params.customer_ZipCode == ""){
		alert("Zip code/ Pin code cannot be empty!..");
		return false;
	}else if((params.customer_ZipCode).length != 6){
		alert("Zip Code must be 6 digits only!..");
		return false;
	}else if(customerState == -1){
		alert("Please select the State!..");
		return false;
	}else if(customerCity == -1){
		alert("Please select the City!..");
		return false;
	}else if(params.customer_Aadhar == ""){
		alert("Aadhar number cannot be empty!..");
		return false;
	}else if((params.customer_Aadhar).length != 12){
		alert("Aadhar Number must be 12 digits only!..");
		return false;
	}else if(customerOccupation == -1){
		alert("Please select Occupation!..");
		return false;
	}else if(params.customer_Password == ""){
		alert("Password cannot be empty!..");
		return false;
	}else if((params.customer_Password).length < 6){
		alert("Password should be atleast six characters!..");
		return false;
	}else if((params.customer_Password).length > 12){
		alert("Password should not exceed more than 12 characters!..");
		return false;
	}else if(confirmPassword == ""){
		alert("Confirm password cannot be empty!..");
		return false;
	}else if(params.customer_Password !== confirmPassword){
		alert("Confirm Password should be same as Password!..");
		return false;
	}else if(((params.customer_AccountType).trim() === "Joint Account") && (!(jointAccountRadio[0].checked || jointAccountRadio[1].checked))){
		alert("Please choose whether you want to add joint owner or not!..");
		return false;
	}else if(jointAccountRadio[0].checked){
		
		if(customerJointTitle == -1){
			alert("Please select Joint Owners Title!..");
			return false;
		}else if(params.joint_customer_FirstName.substring(customerSelectedJointTitle.length) == ""){
			alert("Joint Owners First Name cannot be empty!..");
			return false;
		}else if(params.joint_customer_FirstName.substring(customerSelectedJointTitle.length).match(notAllowedSpecials)){
			alert("Joint Owners First Name cannot contain special characters!..");
			return false;
		}else if(params.joint_customer_LastName == ""){
			alert("Joint Owners Last Name cannot be empty!..");
			return false;
		}else if(params.joint_customer_LastName.match(notAllowedSpecials)){
			alert("Joint Owners Last Name cannot contain special characters!..");
			return false;
		}else if(!(joint_radioOptions[0].checked || joint_radioOptions[1].checked || joint_radioOptions[2].checked)){
			alert("Please select your Joint Owners gender!..");
			return false;
		}else if(params.joint_customer_Phone.substring(jointCountryCode.length) == ""){
			alert("Joint Owners Phone Number cannot be empty!..");
			return false;
		}else if(!(params.joint_customer_Phone.substring(jointCountryCode.length).match(phoneValid))){
			alert("Invalid Phone Number!..");
			return false;
		}else if(params.joint_customer_Email == ""){
			alert("Joint Owners Email cannot be empty!..");
			return false;
		}else if(!(params.joint_customer_Email.match(regex_pattern))){
			alert("Invalid Email Id!..");
			return false;
		}else if(params.joint_customer_DOB == ""){
			alert("Joint Owners Date of Birth cannot be null!..");
			return false;
		}else if(jointCustomerAge < 10){
			alert("Joint Owners Age should be above 10 years!..");
			return false;
		}else if(params.joint_customer_Address == " "){
			alert("Joint Owners Address Cannot be empty!..");
			return false;
		}else if(params.joint_customer_ZipCode == ""){
			alert("Joint Owners Zip code/ Pin code cannot be empty!..");
			return false;
		}else if((params.joint_customer_ZipCode).length != 6){
			alert("Joint Owners Zip Code must be 6 digits only!..");
			return false;
		}else if(customerJointState == -1){
			alert("Please select the Joint Owners State!..");
			return false;
		}else if(customerJointCity == -1){
			alert("Please select the Joint Owners City!..");
			return false;
		}else if(params.joint_customer_Aadhar == ""){
			alert("Joint Owners Aadhar number cannot be empty!..");
			return false;
		}else if((params.joint_customer_Aadhar).length != 12){
			alert("Joint Owners Aadhar Number must be 12 digits only!..");
			return false;
		}else if(customerJointOccupation == -1){
			alert("Please select Joint Owners Occupation!..");
			return false;
		}else if(!termsCondition.checked){
			alert("Please accept the Terms & Conditions!..");
			return false;
		}else{
			$.ajax({
				type: 'POST',
				url: '/registerCustomer',
				data: params,
				dataType: 'json',
				success: function(response){
					if(response.MESSAGE == "CUSTOMER EXIST"){
						alert("User Already Exist!...");
						return false;
					}else if(response.MESSAGE == "SUCCESS"){
						alert("Registered Successfully...");
						window.location.href="/";
					}else{
						alert("Unable to register!...");
						return false;
					}
				}
			});
		}
	}else if(!termsCondition.checked){
		alert("Please accept the Terms & Conditions!..");
		return false;
	}else{
		$.ajax({
			type: 'POST',
			url: '/registerCustomer',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE == "CUSTOMER EXIST"){
					alert("User Exist Already!...");
					return false;
				}else if(response.MESSAGE == "SUCCESS"){
					alert("Registered Successfully...");
					window.location.href="/";
				}else{
					alert("Unable to register!...");
					return false;
				}
			}
		});
	}
}


