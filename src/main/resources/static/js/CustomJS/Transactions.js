function enableOtp(){
	var amount = document.getElementById("amount");
	var otp = document.getElementById("otp");
	if(amount.value > 100000){
		otp.style.display = "block";
		$.ajax({
			type: 'POST',
			url: '/sendTransOTP',
			data: {},
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE === "SUCCESS"){
					alert("OTP is sent to your Registered Mail ID...");
				}else{
					alert("Error Sending OTP!...");
					return false;
				}
			}
		});
	}else{
		otp.style.display = "none";
	}
}

function enableDepOtp(){
	var amount = document.getElementById("depamount");
	var otp = document.getElementById("depotp");
	if(amount.value > 49999){
		otp.style.display = "block";
		$.ajax({
			type: 'POST',
			url: '/sendTransOTP',
			data: {},
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE === "SUCCESS"){
					alert("OTP is sent to your Registered Mail ID...");
				}else{
					alert("Error Sending OTP!...");
					return false;
				}
			}
		});
	}else{
		otp.style.display = "none";
	}
}

function enableWithOtp(){
	var amount = document.getElementById("withamount");
	var otp = document.getElementById("withotp");
	if(amount.value > 10000){
		otp.style.display = "block";
		$.ajax({
			type: 'POST',
			url: '/sendTransOTP',
			data: {},
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE === "SUCCESS"){
					alert("OTP is sent to your Registered Mail ID...");
				}else{
					alert("Error Sending OTP!...");
					return false;
				}
			}
		});
	}else{
		otp.style.display = "none";
	}
}

function onlyAlphabets(event){
	var ascii = (event.which) ? event.which : event.keyCode;
	if((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123) || ascii == 32){
		return true;
	}else{
		return false;
	}
}

function onlyNumbers(event){
	var ascii = (event.which) ? event.which : event.keyCode;
	if(ascii > 31 && (ascii < 48 || ascii > 57)){
		return false;
	}else{
		return true;
	}
}

function onlyAlphaNumeric(event){
	var ascii = (event.which) ? event.which : event.keyCode;
	if((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123) || (ascii > 47 && ascii < 58)){
		return true;
	}else{
		return false;
	}
}

function showOtherAccDiv(){
	otherAccDiv = document.getElementById("otherAccDiv");
	depositDiv = document.getElementById("depositDiv");
	withdrawDiv = document.getElementById("withdrawDiv");
	
	otherAccDiv.style.display = "block";
	depositDiv.style.display = "none";
	withdrawDiv.style.display = "none";
}

function showDepositDiv(){
	otherAccDiv = document.getElementById("otherAccDiv");
	depositDiv = document.getElementById("depositDiv");
	withdrawDiv = document.getElementById("withdrawDiv");
	
	otherAccDiv.style.display = "none";
	depositDiv.style.display = "block";
	withdrawDiv.style.display = "none";
}

function showWithdrawDiv(){
	otherAccDiv = document.getElementById("otherAccDiv");
	depositDiv = document.getElementById("depositDiv");
	withdrawDiv = document.getElementById("withdrawDiv");
	
	otherAccDiv.style.display = "none";
	depositDiv.style.display = "none";
	withdrawDiv.style.display = "block";
}

function depositAmount(){
	var depositotp = document.getElementById("depotp");
	var params = {
		account_Number: $("#depAccountNumber").val(),
		amount: $("#depamount").val(),
		otp: $("#depotp").val()
	}
	
	if(params.amount == "" || params.amount == null){
		alert("Please Enter the Amount to Deposit!...");
		return false;
	}else if(window.getComputedStyle(depositotp).display == "block" && params.otp == "" || params.otp == null){
		alert("Please Enter OTP received!...");
		return false;
	}else{
		$.ajax({
			type: 'POST',
			url: '/depositAmount',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE === "SUCCESS"){
					alert("Amount Deposited Successfully...");
					$("#depamount").val("");
					$("#depotp").val("");
					document.getElementById("depotp").style.display = "none";
					$("#balAmt").html(response.BALANCE);
				}else if(response.MESSAGE === "ERROROTP"){
					alert("Please Enter Correct Otp!...");
					return false;
				}else{
					alert("Cannot Deposit Amount Error Occured!...");
					return false;
				}
			}
		});
	}
}


function withdrawAmount(){
	var withdrawotp = document.getElementById("withotp");
	var params = {
		account_Number: $("#withAccountNumber").val(),
		amount: $("#withamount").val(),
		otp: $("#withotp").val()
	}
	
	if(params.amount == "" || params.amount == null){
		alert("Please Enter the Amount to Withdraw!...");
		return false;
	}else if(window.getComputedStyle(withdrawotp).display == "block" && params.otp == "" || params.otp == null){
		alert("Please Enter OTP received!...");
		return false;
	}else{
		$.ajax({
			type: 'POST',
			url: '/withdrawAmount',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE === "SUCCESS"){
					alert("Amount Withdrawn Successfully...");
					$("#withamount").val("");
					$("#withotp").val("");
					document.getElementById("withotp").style.display = "none";
					$("#balAmt").html(response.BALANCE);
				}else if(response.MESSAGE === "ERROROTP"){
					alert("Please Enter Correct Otp!...");
					return false;
				}else if(response.MESSAGE === "WITHERROR"){
					alert("Please Enter amount less than Balance amount!...");
					return false;
				}else{
					alert("Cannot Withdraw Amount Error Occured!...");
					return false;
				}
			}
		});
	}
}


function transferAmount(){
	var otp = document.getElementById("otp");
	var params = {
		to_account_Number: $("#to_account_Number").val(),
		amount: $("#amount").val(),
		otp: $("#otp").val(),
		description: $("#description").val()
	}

	if(params.to_account_Number == null || params.to_account_Number == ""){
		alert("Please Enter the Account Number!...");
		return false;
	}else if(params.amount == null || params.amount == ""){
		alert("Please Enter the Amount to Transfer!...");
		return false;
	}else if(window.getComputedStyle(otp).display == "block" && params.otp == "" || params.otp == null){
		alert("Please Enter OTP received!...");
		return false;
	}else{
		$.ajax({
			type: 'POST',
			url: '/transferAmount',
			data: params,
			dataType: 'json',
			success: function(response){
				if(response.MESSAGE === "USERNOTEXIST"){
					alert("Invalid Account Number!...");
					return false;
				}else if(response.MESSAGE === "SUCCESS"){
					alert("Amount Transferred Successfully...");
					$("#to_account_Number").val("");
					$("#amount").val("");
					$("#otp").val("");
					document.getElementById("otp").style.display = "none";
					$("#description").val("");
					$("#balAmt").html(response.BALANCE);
				}else{
					alert("Error While Transferring Amount!...");
					return false;
				}
			}
		});
	}
}