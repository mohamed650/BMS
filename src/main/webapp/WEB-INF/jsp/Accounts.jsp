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
<title><spring:message code="bms.label.accounts.accountsTitle"/></title>
<jsp:include page="common/header.jsp"></jsp:include>
<jsp:include page="common/sidenav.jsp"></jsp:include>
</head>
<body>
<div class="accountscontainer">
	<div class="user-details">
		<div class="account-details">
			<p><spring:message code="bms.label.accounts.accountNumber"/></p><span>${AccNumber}</span>
		</div>
		<div class="lastlogindetails">
			<p><spring:message code="bms.label.accounts.lastLoginDate"/></p><span>${LASTLOGIN}</span>
		</div>
	</div>
</div>
</body>
</html>