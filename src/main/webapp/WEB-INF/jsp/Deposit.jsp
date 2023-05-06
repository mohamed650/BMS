<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/x-icon" href="/images/sbi-logo.png" />
<link rel="stylesheet" href="/css/BuiltInCSS/Cssboot.css" />
<link rel="stylesheet" href="/css/CustomCSS/header.css" />
<link rel="stylesheet" href="/css/CustomCSS/deposit.css">
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<title>Document</title>
<jsp:include page="common/header.jsp"></jsp:include>
</head>
<body>
<div class="depositcontainer">
    <div class="sub-depositcontainer">
         <div class="depositcalculate">
            <a href="">
                <span>Deposit Calculator</span>
            </a>
            <a href="">
                <span id="fd">Fixed Deposit</span>
            </a>
            <a href="">
                <span>Recurrring Deposit</span>
            </a>
         </div> 
    </div>
    <div class="depositcalculator">
        <h3>Deposit Calculator</h3>
        <div class="investAmount">
            <p>Invest Amount</p>
            <span><input type="text" id="investAmt" placeholder="&#8377 00" onkeypress="return onlyNumbers(event)"></span>
        </div>
        <div id="investmentErrors" style="display: none; color: red;">
            <span id="not-senior">Enter an amount between 1000/- and 99,99,999/-</span>
            <span id="senior">Enter an amount between 10000/- and 99,99,999/-</span>
        </div>
        <div class="deposit-seniordiv">
            <p>Are You a senior citizen?</p>
            <input type="radio" name="senior" value="Yes"><span>Yes</span>
            <input type="radio" name="senior" value="No"><span>No</span>
        </div>
        <div class="tenurediv">
            <p>Tenure</p>
            <div class="datediv">
                <span><input type="text" name="" id="years" placeholder="00" onkeypress="return onlyNumbers(event)">Years</span>
                <span><input type="text" name="" id="months" placeholder="00" onkeypress="return onlyNumbers(event)">Months</span>
                <span><input type="text" name="" id="days" placeholder="00" onkeypress="return onlyNumbers(event)">Days</span>
            </div>
        </div>
        <div id="tenurewarnings" style="display: none; color: red;">
            <span id="tenuremsg">Enter a duration between 7 days and 10 years.</span>
        </div>
        <div class="calculatebtn">
            <input type="button" value="Calculate" onclick="calculateAmount()">
        </div>
        <div class="outputresultdiv">
            <div class="sub-result">
                <div>
                    <p>Interest Amount</p>
                </div>
                <div>
                    <span><span id="interestAmt">--</span>per annum</span>
                </div>
            </div>
            <div class="sub-result">
                <div>
                    <p>Investment Amount</p>
                </div>
                <div>
                    <span>&#8377<span id="investAmt">--</span></span>
                </div>
            </div>
            <div class="sub-result">
                <div>
                    <p>Total Interest Amount</p>
                </div>
                <div>
                    <span>&#8377<span id="totalinterest">--</span></span>
                </div>
            </div>
            <hr>
            <div class="sub-result">
                <div>
                    <p>Maturity Amount</p>
                </div>
                <div>
                    <span>&#8377<span id="maturityAmt">--</span></span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/CustomJS/deposit.js"></script>
</body>
</html>