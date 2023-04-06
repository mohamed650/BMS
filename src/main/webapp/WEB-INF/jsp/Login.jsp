<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/images/sbi-logo.png" />
<link rel="stylesheet" href="/css/BuiltInCSS/Cssboot.css" />
<link rel="stylesheet" href="/css/CustomCSS/Login.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<title><spring:message code="bms.label.login.pageTitle"/></title>
</head>
<body>
<div class="logincontainer">
	<div class="logindiv">
		<div class="imagediv">
			<img src="/images/sbi-backimg.png" />
		</div>
		<div class="logincontentdiv" id="loginScreen">
			<form action="">
				<h2 class="title"><spring:message code="bms.label.login.welcome"/></h2>
				<!-- <div class="select-div">
           		    <select class="input" id="selectRole">
           		    	<option value="-1" disabled>-- Select Your Role --</option>
           		    	<option value="0">User</option>
           		    	<option value="1">Admin</option>
           		    </select>	
            	</div> -->
            	<div class="input-div custid">
           		   <div class="i"> 
           		    	<i class="fas fa-user"></i>
           		   </div>
           		   <div class="div">
           		    	<h5><spring:message code="bms.label.login.CustomerID"/></h5>
           		    	<input type="text" class="input" id="login_customer_Id">
            	   </div>
            	</div>
           		<div class="input-div pass">
           		   <div class="i"> 
           		    	<i class="fas fa-lock"></i>
           		   </div>
           		   <div class="div">
           		    	<h5><spring:message code="bms.label.login.Password"/></h5>
           		    	<input type="password" class="input" id="login_customer_Password">
            	   </div>
            	</div>
            	<!-- <div class="input-div otp" id="otpfield">
           		   <div class="i"> 
           		    	<i class="fas fa-key"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>OTP</h5>
           		    	<input type="text" class="input" id="otp">
            	   </div>
            	</div>
            	<input type="button" class="btn" id="otpbtn" value="Get Otp" onclick="getOtp()"> -->
            	<input type="button" class="btn" value=<spring:message code="bms.button.login"/> onclick="loginValidate()">
            	<div class="registerlink">
            		<span><spring:message code="bms.label.login.notMember"/></span><span class="changeContent" onclick="changeScreen()"><spring:message code="bms.label.login.createAccount"/></span>
            	</div>
			</form>
		</div>
	</div>
</div>
<script src="/js/CustomJS/Login.js"></script>
</body>
</html>