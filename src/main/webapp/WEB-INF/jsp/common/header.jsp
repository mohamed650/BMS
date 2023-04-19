<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/images/sbi-logo.png" />
<link rel="stylesheet" href="/css/BuiltInCSS/Cssboot.css" />
<link rel="stylesheet" href="/css/CustomCSS/header.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<script src="/js/BuiltInJS/Jquery1.16.0.js"></script>
</head>
<body>
<div class="maincontainer">
	<div class="navcontainer">
		<div class="imagecontainer">
			<img src="/images/sbi-backimg.png" class="logo-img"/>
			<p class="bank-title"><spring:message code="bms.label.header.bankTitle"/></p>
		</div>
		<div class="greetingsdiv">
			<p class="greetings-title"><spring:message code="bms.label.header.greetings"/></p><span>${CustomerName}</span>
			<i class="fa fa-sign-out fa-lg"></i>
		</div>
		<div class="hamburger-icon" onclick="makeResponsive()">
			<i class="fa fa-bars fa-2x"></i>
		</div>
	</div>
	<div class="sub-navcontainer">
		<div id="accounts">
			<a href="/accounts">
				<img src="/images/sbi-accounts.png"/>
				<span><spring:message code="bms.label.header.accounts"/></span>
			</a>
		</div>
		<div id="investment-deposit">
			<a href="/investment-deposit">
				<img src="/images/investments_deposits.png"/>
				<span><spring:message code="bms.label.header.investment-deposits"/></span>
			</a>
		</div>
		<div id="loans">
			<a href="/loans">
				<img src="/images/loans.png"/>
				<span><spring:message code="bms.label.header.loans"/></span>
			</a>
		</div>
		<div id="cards">
			<a href="/cards">
				<img src="/images/cards.png"/>
				<span><spring:message code="bms.label.header.cards"/></span>
			</a>
		</div>
		<div id="digital">
			<a href="/digital">
				<img src="/images/digital.png"/>
				<span><spring:message code="bms.label.header.digital"/></span>
			</a>
		</div>
		<div id="information-service">
			<a href="/information-service">
				<img src="images/information_services.png"/>
				<span><spring:message code="bms.label.header.information-services"/></span>
			</a>
		</div>
	</div>
</div>
</body>
</html>