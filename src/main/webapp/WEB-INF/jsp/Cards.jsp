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
<link rel="stylesheet" href="/css/CustomCSS/cards.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<title>Document</title>
<jsp:include page="common/header.jsp"></jsp:include>
</head>
<body>
<div class="cardscontainer">
    <div class="debit-cardscontainer">
        <div class="heading-container">
            <div class="cardtitle">
                <p>My Debit Cards</p>
            </div>
            <div class="addcard-icon">
                <span><i class="fa fa-solid fa-circle-plus"></i></span>
            </div>
        </div>
        <div class="cards-section">
            <div class="debit-card">
                <div class="debit-logo">
                    <span><img src="/images/sbi-logo.png" width="25px" height="25px" alt=""></span>
                    <span><i class="fa fa-credit-card"></i></span>
                </div>
                <div class="balance-div">
                    <p>Available Balance</p>
                    <p>&#8377 10000</p>
                </div>
                <div class="info-div">
                    <p>${accnum}</p>
                    <p>${CustomerName} <span>12/24</span></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>