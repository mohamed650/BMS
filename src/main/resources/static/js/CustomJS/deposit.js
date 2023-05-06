function onlyNumbers(event){
	var ascii = (event.which) ? event.which : event.keyCode;
	if(ascii > 31 && (ascii < 48 || ascii > 57)){
		return false;
	}else{
		return true;
	}
}


function calculateAmount(){
    var notseniorInterest = [3.00, 4.50, 5.25, 5.75, 6.80, 7.00, 6.50, 7.10];
    var seniorInterest = [3.50, 5.00, 5.75, 6.25, 7.30, 7.50, 7.00, 7.50];
    var investAmt = $("#investAmt").val();
    var seniorcitizenRadio = document.getElementsByName("senior");
    var seniorcitizen = $('input[name="senior"]:checked').val();
    var years = $("#years").val();
    var months = $("#months").val();
    var days = $("#days").val();
    var roi;

    if(investAmt == "" || investAmt == null){
        alert("Please Enter Investment Amount!...");
        return false;
    }else if(!(seniorcitizenRadio[0].checked || seniorcitizenRadio[1].checked)){
        alert("Please select whether you are senior citizen or not!...");
        return false;
    }else if((years == "" || years == null) || (months == "" || months == null) || (days == "" || days == null)){
        alert("Please enter the tenure in years, months and days!...");
        return false;
    }else if((investAmt < 1000 || investAmt > 9999999) && (seniorcitizen === "NO" || seniorcitizen === "No")){
        document.getElementById("investmentErrors").style.display="block";
        document.getElementById("senior").style.display = "none";
        setTimeout(function(){
            document.getElementById("senior").style.display = "block";
            document.getElementById("investmentErrors").style.display="none";
        }, 3000);
    }else if((investAmt < 10000 || investAmt > 9999999) && (seniorcitizen === "YES" || seniorcitizen === "Yes")){
        document.getElementById("investmentErrors").style.display = "block";
        document.getElementById("not-senior").style.display = "none";
        setTimeout(function(){
            document.getElementById("not-senior").style.display = "block";
            document.getElementById("investmentErrors").style.display="none";
        }, 3000);
    }else if(years > 10){
        document.getElementById("tenurewarnings").style.display = "block";
        setTimeout(function(){
            document.getElementById("tenurewarnings").style.display = "none";
        }, 3000);
    }else if(((months == 0 && days < 7) || (months == 00 && days < 7)) && years == 0){
        document.getElementById("tenurewarnings").style.display = "block";
        setTimeout(function(){
            document.getElementById("tenurewarnings").style.display = "none";
        }, 3000);
    }else if(years > 10 && days < 7){
        document.getElementById("tenurewarnings").style.display = "block";
        setTimeout(function(){
            document.getElementById("tenurewarnings").style.display = "none";
        }, 3000);
    }
}