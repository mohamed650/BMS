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
    var investAmount = $("#investAmt").val();
    var seniorcitizenRadio = document.getElementsByName("senior");
    var seniorcitizen = $('input[name="senior"]:checked').val();
    var years = $("#years").val();
    var months = $("#months").val();
    var days = $("#days").val();
    var roi;
    var params = {
        years: years,
        months: months,
        days: days
    }

    if(investAmount == "" || investAmount == null){
        alert("Please Enter Investment Amount!...");
        return false;
    }else if(!(seniorcitizenRadio[0].checked || seniorcitizenRadio[1].checked)){
        alert("Please select whether you are senior citizen or not!...");
        return false;
    }else if((years == "" || years == null) || (months == "" || months == null) || (days == "" || days == null)){
        alert("Please enter the tenure in years, months and days!...");
        return false;
    }else if((investAmount < 1000 || investAmount > 9999999) && (seniorcitizen === "NO" || seniorcitizen === "No")){
        document.getElementById("investmentErrors").style.display="block";
        document.getElementById("senior").style.display = "none";
        setTimeout(function(){
            document.getElementById("senior").style.display = "block";
            document.getElementById("investmentErrors").style.display="none";
        }, 3000);
    }else if((investAmount < 10000 || investAmount > 9999999) && (seniorcitizen === "YES" || seniorcitizen === "Yes")){
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
    $.ajax({
        type: 'POST',
        url: '/getDifferenceDates',
        data : params,
        dataType: 'json',
        success: function(response){
            var time = response;
            if(seniorcitizenRadio[1].checked){
                if(time <= 31){
                    roi = notseniorInterest[0];
                }else if(time > 31 && time <= 153){
                    roi = notseniorInterest[1];
                }else if(time > 153 && time <= 184){
                    roi = notseniorInterest[2];
                }else if(time > 184 && time <= 336){
                    roi = notseniorInterest[3];
                }else if(time > 336 && time <= 365){
                    roi = notseniorInterest[4];
                }else if(time > 365 && time <= 730){
                    roi = notseniorInterest[5];
                }else if(time > 730 && time <= 3285){
                    roi = notseniorInterest[6];
                }else if(time > 3285 && time <= 3650){
                    roi = notseniorInterest[7];
                }
            }
            if(seniorcitizenRadio[0].checked){
                if(time <= 31){
                    roi = seniorInterest[0];
                }else if(time > 31 && time <= 153){
                    roi = seniorInterest[1];
                }else if(time > 153 && time <= 184){
                    roi = seniorInterest[2];
                }else if(time > 184 && time <= 336){
                    roi = seniorInterest[3];
                }else if(time > 336 && time <= 365){
                    roi = seniorInterest[4];
                }else if(time > 365 && time <= 730){
                    roi = seniorInterest[5];
                }else if(time > 730 && time <= 3285){
                    roi = seniorInterest[6];
                }else if(time > 3285 && time <= 3650){
                    roi = seniorInterest[7];
                }
            }
            var si = (investAmount * roi * time) / (365 * 100);
            var totalAmount = parseInt(investAmount) + si;
            si = parseFloat(si.toFixed(2));
            totalAmount = parseFloat(totalAmount.toFixed(2));
            $("#interestAmt").html(roi+'%');
            $("#investAmount").html(investAmount);
            $("#totalinterest").html(si);
            $("#maturityAmt").html(totalAmount);
        }
    });
    
}