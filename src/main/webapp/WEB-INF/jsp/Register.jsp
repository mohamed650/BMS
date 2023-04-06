<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/images/sbi-logo.png" />
<link rel="stylesheet" href="/css/BuiltInCSS/Cssboot.css" />
<link rel="stylesheet" href="/css/CustomCSS/Register.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<script src="/js/BuiltInJS/signature_pad.min.js"></script>
<title><spring:message code="bms.label.register.pageTitle"/></title>
</head>
<body>
<div class="maincontainer">
	<div class="bank-logo">
		<img alt="" src="/images/sbi-backimg.png" class="sbi-img"/>
	</div>
	<div class="container-fluid">
		<div class="registerScreen">
			<form action="">
				<div class="registerTitle">
					<h2><spring:message code="bms.label.register.registerTitle"/></h2>
				</div>
				<hr>
				<div class="accountinfodiv">
					<p class="info"><spring:message code="bms.label.register.accountInfo"/></p>
					<p class="sub-info"><spring:message code="bms.label.register.accountType"/></p>
					<select id="customer_AccountType">
					</select>
				</div>
				<hr>
				<div class="personalinfodiv">
					<p class="info"><spring:message code="bms.label.register.personalInfo"/></p>
					<p class="detail-info"><spring:message code="bms.label.register.detailInfo"/></p>
					<p class="sub-info"><spring:message code="bms.label.register.name"/></p>
					<div class="namediv">
						<div class="titlediv">
							<select id="selectTitle">
								<option value="-1">--Select Title--</option>
								<option value="1">Mr.</option>
								<option value="2">Ms.</option>
								<option value="3">Mrs.</option>
							</select>
							<input type="text" placeholder="First Name" id="customer_FirstName" onkeypress="return onlyAlphabets(event)"/>
						</div><br><br>
						<input type="text" placeholder="Last Name" id="customer_LastName" onkeypress="return onlyAlphabets(event)"/>
					</div>
					<p class="sub-info"><spring:message code="bms.label.register.gender"/></p>
					<input type="radio" name="gender" /><span style="margin-left: 15px;"><spring:message code="bms.label.register.genderMale"/></span><br>
					<input type="radio" name="gender" /><span style="margin-left: 15px;"><spring:message code="bms.label.register.genderFemale"/></span><br>
					<input type="radio" name="gender" /><span style="margin-left: 15px;"><spring:message code="bms.label.register.genderNottosay"/></span>
					<p class="sub-info" style="margin-top: 15px;"><spring:message code="bms.label.register.phoneNumber" /></p>
					<div class="phonediv">
						<input type="text" value="+91" style="width: 50px;" disabled/><span>-</span>
						<input type="text" placeholder="Phone Number" id="customer_Phone" onkeypress="return onlyNumbers(event)"/>
					</div>
					<p class="sub-info"><spring:message code="bms.label.register.email"/></p>
					<input type="text" placeholder="Email Id" id="customer_Email" style="margin-bottom: 15px; width: 300px;"/>
					<p class="sub-info"><spring:message code="bms.label.register.dob"/></p>
					<input type="date" id="customer_DOB" style="margin-bottom: 20px;"/>
					<p class="sub-info"><spring:message code="bms.label.register.address"/></p>
					<input type="text" placeholder="Address Line 1" id="addressLine1" style="width: 300px; margin-bottom: 15px"/><br>
					<input type="text" placeholder="Address Line 2" id="addressLine2" style="width: 300px; margin-bottom: 15px;"/><br>
					<input type="text" placeholder="Zip Code" id="customer_ZipCode" style="margin-bottom: 15px;" onkeypress="return onlyNumbers(event)"/><br>
					<select id="selectState" style="width: 200px; margin-bottom: 15px;">	
					</select>
					<select id="selectCity" style="width: 200px; margin-bottom: 15px;">		
					</select>
					<p class="sub-info"><spring:message code="bms.label.register.aadhar"/></p>
					<input type="text" placeholder="Aadhar Number" id="customer_Aadhar" style="margin-bottom: 15px;" onkeypress="return onlyNumbers(event)"/>
					<p class="sub-info"><spring:message code="bms.label.register.occupation"/></p>
					<select id="selectOccupation" style="margin-bottom: 15px;">
						<option value="-1">Select Occupation</option>
						<option value="1">Engineer</option>
						<option value="2">Doctor</option>
						<option value="3">Lawyer</option>
					</select>
					<p class="sub-info" style="margin-top: 15px;"><spring:message code="bms.label.register.password"/></p>
					<input type="password" placeholder="Password" id="customer_Password" style="width: 200px; margin-bottom: 15px;"/>
					<p class="sub-info" style="margin-top: 15px;"><spring:message code="bms.label.register.confirmPassword"/></p>
					<input type="password" placeholder="Confirm Password" id="customer_ConfirmPassword" style="width: 200px; margin-bottom: 15px;"/>
				</div>
				<hr>
				<div class="jointownerdiv">
					<p class="sub-info"><spring:message code="bms.label.register.jointOwnerInfo"/></p>
					<input type="radio" name="jointAccount" id="radioYes" onclick="checkRadio()"/><span style="margin-left: 15px;"><spring:message code="bms.label.register.radioYes"/></span><br>
					<input type="radio" name="jointAccount" onclick="checkRadio()"/><span style="margin-left: 15px;"><spring:message code="bms.label.register.radioNo"/></span>
				</div>
				<hr>
				<div class="personalinfodiv" id="jointOwnerdiv" style="display: none;">
					<p class="info"><spring:message code="bms.label.register.jointInfo"/></p>
					<p class="sub-info"><spring:message code="bms.label.register.name"/></p>
					<div class="namediv">
						<div class="titlediv">
							<select id="selectJointTitle">
								<option value="-1">Select Title</option>
								<option value="1">Mr.</option>
								<option value="2">Ms.</option>
								<option value="3">Mrs.</option>
							</select>
							<input type="text" placeholder="First Name" id="joint_customer_FirstName" onkeypress="return onlyAlphabets(event)"/>
						</div><br><br>
						<input type="text" placeholder="Last Name" id="joint_customer_LastName" onkeypress="return onlyAlphabets(event)"/>
					</div>
					<p class="sub-info"><spring:message code="bms.label.register.gender"/></p>
					<input type="radio" name="gender" /><span style="margin-left: 15px;"><spring:message code="bms.label.register.genderMale"/></span><br>
					<input type="radio" name="gender" /><span style="margin-left: 15px;"><spring:message code="bms.label.register.genderFemale"/></span><br>
					<input type="radio" name="gender" /><span style="margin-left: 15px;"><spring:message code="bms.label.register.genderNottosay"/></span>
					<p class="sub-info" style="margin-top: 15px;"><spring:message code="bms.label.register.phoneNumber"/></p>
					<div class="phonediv">
						<input type="text" value="+91" style="width: 50px;" disabled/><span>-</span>
						<input type="text" placeholder="Phone Number" id="joint_customer_Phone" onkeypress="return onlyNumbers(event)"/>
					</div>
					<p class="sub-info"><spring:message code="bms.label.register.email"/></p>
					<input type="text" placeholder="Email Id" id="joint_customer_Email" style="margin-bottom: 15px; width: 300px;"/>
					<p class="sub-info"><spring:message code="bms.label.register.dob"/></p>
					<input type="date" id="joint_customer_DOB" style="margin-bottom: 20px;"/>
					<p class="sub-info"><spring:message code="bms.label.register.address"/></p>
					<input type="text" placeholder="Address Line 1" id="joint_addressLine1" style="width: 300px; margin-bottom: 15px"/><br>
					<input type="text" placeholder="Address Line 2" id="joint_addressLine2" style="width: 300px; margin-bottom: 15px;"/><br>
					<input type="text" placeholder="Zip Code" id="joint_ZipCode" style="margin-bottom: 15px;" onkeypress="return onlyNumbers(event)"/><br>
					<select id="selectJointState" style="width: 200px; margin-bottom: 15px;">						
					</select>
					<select id="selectJointCity" style="width: 200px; margin-bottom: 15px;">
					</select>
					<p class="sub-info"><spring:message code="bms.label.register.aadhar"/></p>
					<input type="text" placeholder="Aadhar Number" id="joint_customer_Aadhar" style="margin-bottom: 15px;" onkeypress="return onlyNumbers(event)"/>
					<p class="sub-info"><spring:message code="bms.label.register.occupation"/></p>
					<select id="selectJointOccupation" style="margin-bottom: 15px;">
						<option value="-1">Select Occupation</option>
						<option value="1">Engineer</option>
						<option value="2">Doctor</option>
						<option value="3">Lawyer</option>
					</select>
				</div>
				<hr id="jointOwnerHR" style="display: none;">
				<p><spring:message code="bms.label.register.termsConditions"/></p>
				<input type="checkbox" id="termsCondition" style="width: 20px; height: 20px; vertical-align: middle;"/><span style="vertical-align: middle; margin-left: 10px; opacity: 0.8;"><spring:message code="bms.label.register.agree"/></span><span style="vertical-align: middle; color: #1166dd; cursor: pointer;" onMouseOver="this.style.textDecoration='underline'" onMouseOut="this.style.textDecoration='none'"><spring:message code="bms.label.register.conditions"/></span><sup style="color: red;">*</sup>
				<%-- <p class="sub-info" style="margin-top: 25px;"><spring:message code="bms.label.register.primarySignature"/></p>
				<div class="signaturediv">
					<div class="signaturebox">
						<canvas id="signature-pad"></canvas>
					</div>
					<div class="clear-btn">
						<input type="button" value=<spring:message code="bms.button.register.clear"/> id="clear" />
					</div>
				</div>
				<p class="sub-info" style="margin-top: 25px; display: block;" id="joint-signature-desc"><spring:message code="bms.label.register.jointSignature"/></p>
				<div class="signaturediv" id="jointOwnerSignaturePad">
					<div class="signaturebox">
						<canvas id="joint_signature-pad"></canvas>
					</div>
					<div class="clear-btn">
						<input type="button" value=<spring:message code="bms.button.register.clear"/> id="joint_clear" />
					</div>
				</div> --%>
				<!-- <div id="jointownersignature" style="display: none;">
					
				</div> -->
				<div class="submitdiv">
					<input type="button" value=<spring:message code="bms.button.register"/> class="submitbtn" />
				</div>
			</form>
		</div>
	</div>
</div>
<script src="/js/CustomJS/Register.js"></script>
</body>
</html>