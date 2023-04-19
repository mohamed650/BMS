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
<link rel="stylesheet" href="/css/CustomCSS/Accounts.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<title>SBI Home</title>
<jsp:include page="common/header.jsp"></jsp:include>
</head>
<body>
<div class="accountscontainer">
	<div class="sub-accountcontainer">
		<div class="accounts-sidenav">
			<div class="items-block">
				<p class="items-name"><i class="fas fa-coins"></i>Check Balance</p>
				<p class="items-name"><i class="fa fa-exchange"></i>Transfer Amount</p>
				<p class="items-name"><i class="fa fa-history"></i>Transaction History</p>
			</div>
		</div>
		<div class="user-details">
			<div class="account-details">
				<p>A/C No: </p><span>${AccNumber}</span>
			</div>
			<div class="lastlogindetails">
				<p>Last Login Date: </p><span>${LASTLOGIN}</span>
			</div>
		</div>
	</div>
	
</div>
</body>
</html>