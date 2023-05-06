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
<link rel="stylesheet" href="/css/BuiltInCSS/Tabulator.css" />
<link rel="stylesheet" href="/css/CustomCSS/header.css" />
<link rel="stylesheet" href="/css/CustomCSS/TransactionsHistory.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<script src="/js/BuiltInJS/Tabulator.js"></script>
<title><spring:message code="bms.label.transactionHistory.title"/></title>
<jsp:include page="common/header.jsp"></jsp:include>
<jsp:include page="common/sidenav.jsp"></jsp:include>
</head>
<body>
<div class="historycontainer">
    <div class="sub-historycontainer">
        <div class="detailscontainer">
            <div class="cust-details">
                <span class="subcustdetails"><spring:message code="bms.label.transactionHistory.accountNumber"/> </span>
                <span id="acc_Number">${AccNumber}</span>
            </div>
            <div class="cust-details">
                <span class="subcustdetails"><spring:message code="bms.label.transactionHistory.customerID"/> </span>
                <span>${CUSTID}</span>
            </div>
            <div class="cust-details">
                <span class="subcustdetails"><spring:message code="bms.label.transactionHistory.accountHolder"/> </span>
                <span>${CustomerName}</span>
            </div>
            <div class="cust-details">
                <span class="subcustdetails"><spring:message code="bms.label.transactionHistory.availableBalance"/> </span>
                <span>&#8377 ${Balance}</span>
            </div>
        </div>
        <div class="tabulator-container">
            <div id="transactionHistoryTabulator"></div>
        </div>
    </div>
</div>
<script src="/js/CustomJS/TransactionsHistory.js"></script>
</body>
</html>