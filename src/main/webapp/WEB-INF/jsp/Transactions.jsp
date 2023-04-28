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
<link rel="stylesheet" href="/css/CustomCSS/Transactions.css" />
<script src="/js/BuiltInJS/fontAwesome.js"></script>
<script src="/js/BuiltInJS/jquery.js"></script>
<title><spring:message code="bms.label.transactions.transactionsTitle"/></title>
<jsp:include page="common/header.jsp"></jsp:include>
<jsp:include page="common/sidenav.jsp"></jsp:include>
</head>
<body>
<div class="transactionscontainer">
	<div class="sub-transactionscontainer">
		<div class="accbalancediv">
			<p class="accountType">${AccountType}</p>
			<p class="balance"><spring:message code="bms.label.transactions.balance"/></p>
			<div class="balanceAmtdiv"><p class="balanceAmt">&#8377</p><span id="balAmt">${Balance}</span></div>
			<hr>
			<p class="accName"><spring:message code="bms.label.transactions.accName"/></p>
			<p class="accNum">${AccNumber}</p>
			<p class="accOwner"><spring:message code="bms.label.transactions.accOwner"/></p>
			<p class="accOwnerName">${CustomerName}</p>
			<div class="otheraccountsdiv" onclick="showOtherAccDiv()">
				<p class="otheraccounts"><spring:message code="bms.label.transactions.otherAccountsLabel"/></p>
			</div>
			<div class="deposittransactions" onclick="showDepositDiv()">
				<p class="deposit"><spring:message code="bms.label.transactions.depositLabel"/></p>
			</div>
			<div class="withdrawtransactions" onclick="showWithdrawDiv()">
				<p class="withdraw"><spring:message code="bms.label.transactions.withdrawLabel"/></p>
			</div>
		</div>
		<div class="acctransferdiv" id="otherAccDiv" style="display: block;">
			<p class="transfertitle"><spring:message code="bms.label.transactions.transferTitle"/></p>
			<hr>
			<div class="transferinfo">
				<p class="beneficiary"><spring:message code="bms.label.transactions.beneficiary"/></p>
				<div class="inputdivs">
					<input type="text" maxlength=14 id="to_account_Number" placeholder="Account Number *" onkeypress="return onlyAlphaNumeric(event)"/>
				</div>
				<div class="inputdivs">
					<input type="text" placeholder="Amount *" id="amount" onkeyup="enableOtp()" onkeypress="return onlyNumbers(event)"/>
				</div>
				<div class="inputdivs">
					<input type="text" maxlength=6 placeholder="OTP *" id="otp" style="display: none;" onkeypress="return onlyNumbers(event)"/>
				</div>
				<div class="inputdivs">
					<input type="text" placeholder="Description" id="description"/>
				</div>
				<div class="inputdivs">
					<input type="button" onclick="transferAmount()" value=<spring:message code="bms.button.transactions.pay"/>  />
				</div>
			</div>
		</div>
		<div class="acctransferdiv" id="depositDiv" style="display: none;">
			<p class="transfertitle"><spring:message code="bms.label.transactions.depositTitle"/></p>
			<hr>
			<div class="transferinfo">
				<div class="inputdivs">
					<input type="text" maxlength=14 id="depAccountNumber" placeholder="Account Number *" onkeypress="return onlyAlphaNumeric(event)" value=${AccNumber} disabled/>
				</div>
				<div class="inputdivs">
					<input type="text" placeholder="Amount *" id="depamount" onkeyup="enableDepOtp()" onkeypress="return onlyNumbers(event)"/>
				</div>
				<div class="inputdivs">
					<input type="text" maxlength=6 placeholder="OTP *" id="depotp" style="display: none;" onkeypress="return onlyNumbers(event)"/>
				</div>
				<div class="inputdivs">
					<input type="button" onclick="depositAmount()" value=<spring:message code="bms.button.transactions.deposit"/> />
				</div>
			</div>
		</div>
		<div class="acctransferdiv" id="withdrawDiv" style="display: none;">
			<p class="transfertitle"><spring:message code="bms.label.transactions.withdrawTitle"/></p>
			<hr>
			<div class="transferinfo">
				<div class="inputdivs">
					<input type="text" maxlength=14 id="withAccountNumber" placeholder="Account Number *" onkeypress="return onlyAlphaNumeric(event)" value=${AccNumber} disabled/>
				</div>
				<div class="inputdivs">
					<input type="text" placeholder="Amount *" id="withamount" onkeyup="enableWithOtp()" onkeypress="return onlyNumbers(event)"/>
				</div>
				<div class="inputdivs">
					<input type="text" maxlength=6 placeholder="OTP *" id="withotp" style="display: none;" onkeypress="return onlyNumbers(event)"/>
				</div>
				<div class="inputdivs">
					<input type="button" onclick="withdrawAmount()" value=<spring:message code="bms.button.transactions.withdraw"/> />
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/js/CustomJS/Transactions.js"></script>
</body>
</html>