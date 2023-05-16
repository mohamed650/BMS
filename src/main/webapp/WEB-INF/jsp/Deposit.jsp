<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
<title><spring:message code="bms.label.deposit.title"/></title>
<jsp:include page="common/header.jsp"></jsp:include>
</head>
<body>
<div class="depositcontainer">
    <div class="sub-depositcontainer">
         <div class="depositcalculate">
            <a href="">
                <span><spring:message code="bms.label.deposit.depositCalculator"/></span>
            </a>
            <a href="">
                <span id="fd"><spring:message code="bms.label.deposit.fixedDeposit"/></span>
            </a>
            <a href="">
                <span><spring:message code="bms.label.deposit.recurringDeposit"/></span>
            </a>
         </div> 
    </div>
    <div class="depositcalculator" id="depCalculator">
        <h3><spring:message code="bms.label.deposit.depositCalculator"/></h3>
        <div class="investAmount">
            <p><spring:message code="bms.label.deposit.investAmount"/></p>
            <span><input type="text" id="investAmt" placeholder="&#8377 00" onkeypress="return onlyNumbers(event)"></span>
        </div>
        <div id="investmentErrors" style="display: none; color: red;">
            <span id="not-senior"><spring:message code="bms.label.deposit.investmentnotsenior"/></span>
            <span id="senior"><spring:message code="bms.label.deposit.investmentsenior"/></span>
        </div>
        <div class="deposit-seniordiv">
            <p><spring:message code="bms.label.deposit.seniorCitizen"/></p>
            <input type="radio" name="senior" value="Yes"><span><spring:message code="bms.label.deposit.seniorYes"/></span>
            <input type="radio" name="senior" value="No"><span><spring:message code="bms.label.deposit.seniorNo"/></span>
        </div>
        <div class="tenurediv">
            <p><spring:message code="bms.label.deposit.tenure"/></p>
            <div class="datediv">
                <span><input type="text" name="" id="years" placeholder="00" onkeypress="return onlyNumbers(event)"><spring:message code="bms.label.deposit.tenureYears"/></span>
                <span><input type="text" name="" id="months" placeholder="00" onkeypress="return onlyNumbers(event)"><spring:message code="bms.label.deposit.tenureMonths"/></span>
                <span><input type="text" name="" id="days" placeholder="00" onkeypress="return onlyNumbers(event)"><spring:message code="bms.label.deposit.tenureDays"/></span>
            </div>
        </div>
        <div id="tenurewarnings" style="display: none; color: red;">
            <span id="tenuremsg"><spring:message code="bms.label.deposit.tenureWarnings"/></span>
        </div>
        <div class="calculatebtn">
            <input type="button" value="<spring:message code='bms.button.deposit.depositCalculator'/>" onclick="calculateAmount()">
        </div>
        <div class="outputresultdiv">
            <div class="sub-result">
                <div>
                    <p><spring:message code="bms.label.deposit.interestAmount"/></p>
                </div>
                <div>
                    <span><span id="interestAmt"><spring:message code="bms.label.deposit.hyphen"/></span><spring:message code="bms.label.deposit.perAnnum"/></span>
                </div>
            </div>
            <div class="sub-result">
                <div>
                    <p><spring:message code="bms.label.deposit.investmentAmount"/></p>
                </div>
                <div>
                    <span><spring:message code="bms.label.deposit.currencySymbol"/><span id="investAmount"><spring:message code="bms.label.deposit.hyphen"/></span></span>
                </div>
            </div>
            <div class="sub-result">
                <div>
                    <p><spring:message code="bms.label.deposit.totalInterestAmount"/></p>
                </div>
                <div>
                    <span><spring:message code="bms.label.deposit.currencySymbol"/><span id="totalinterest"><spring:message code="bms.label.deposit.hyphen"/></span></span>
                </div>
            </div>
            <hr>
            <div class="sub-result">
                <div>
                    <p><spring:message code="bms.label.deposit.maturityAmount"/></p>
                </div>
                <div>
                    <span><spring:message code="bms.label.deposit.currencySymbol"/><span id="maturityAmt"><spring:message code="bms.label.deposit.hyphen"/></span></span>
                </div>
            </div>
        </div>
    </div>
    <div class="depositcalculator" style="display: none;">
        <h3>Fixed Deposit</h3>
    </div>
</div>
<script src="/js/CustomJS/deposit.js"></script>
</body>
</html>