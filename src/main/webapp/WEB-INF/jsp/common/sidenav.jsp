<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/images/sbi-logo.png" />
<link rel="stylesheet" href="/css/BuiltInCSS/Cssboot.css" />
<link rel="stylesheet" href="/css/CustomCSS/sidenav.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<script src="/js/BuiltInJS/Jquery1.16.0.js"></script>
<script src="/js/BuiltInJS/sweetalert2@11.js"></script>
</head>
<body>
<div class="sub-accountcontainer">
	<div class="accounts-sidenav">
		<div class="items-block">
			<p class="items-name"><a id="checkBal"><i class="fas fa-coins"></i><spring:message code="bms.label.accounts.checkBalance"/></a></p>
			<p class="items-name"><a href="/transactions"><i class="fa fa-exchange"></i><spring:message code="bms.label.accounts.transactions"/></a></p>
			<p class="items-name"><a href="/transactionHistory"><i class="fa fa-history"></i><spring:message code="bms.label.accounts.transactionHistory"/></a></p>
		</div>
	</div>
</div>
<script src="/js/CustomJS/sidenav.js"></script>
</body>
</html>