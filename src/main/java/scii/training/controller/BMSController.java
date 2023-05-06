package scii.training.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import scii.training.model.CityModel;
import scii.training.model.OccupationModel;
import scii.training.model.RegisterModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;
import scii.training.model.TransactionsModel;
import scii.training.service.IService;
import scii.training.util.GenerateCaptcha;
import scii.training.util.GenerateOTP;

@RestController
public class BMSController {
	
	@Autowired
	IService iservice;
	
	Gson gson = new Gson();
	
	@GetMapping("/")
	public ModelAndView login() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	
	@GetMapping("/registerScreen")
	public ModelAndView registerScreen() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Register");
		return modelAndView;
	}
	
	@PostMapping("/customerLogin")
	public @ResponseBody void customerLogin(@ModelAttribute ("loginUser") RegisterModel loginUser, HttpServletResponse response, HttpSession httpSession) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<RegisterModel> checkLoginUser = iservice.userLogin(loginUser);
			if(checkLoginUser.size() > 0) {
				httpSession.setAttribute("EMAIL", checkLoginUser.get(0).getCustomer_Email());
				System.out.println(checkLoginUser.get(0).getLast_login_dateTime());
				if(checkLoginUser.get(0).getLast_login_dateTime() == null){
					httpSession.setAttribute("LASTLOGIN", " ");
				}else{
					httpSession.setAttribute("LASTLOGIN", checkLoginUser.get(0).getLast_login_dateTime().trim());
				}
				httpSession.setAttribute("AccNumber", checkLoginUser.get(0).getAccount_Number().trim());
				String customer_Name = checkLoginUser.get(0).getCustomer_FirstName().trim().concat(" ").concat(checkLoginUser.get(0).getCustomer_LastName().trim());
				httpSession.setAttribute("CustomerName", customer_Name);
				httpSession.setAttribute("AccountType", checkLoginUser.get(0).getCustomer_AccountType());
				httpSession.setAttribute("CUSTID", checkLoginUser.get(0).getCustomer_Id());
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date currentDate = new Date();
				loginUser.setLast_login_dateTime(dateFormat.format(currentDate));
				TransactionsModel transactions = new TransactionsModel();
				String AccNumber = (String) httpSession.getAttribute("AccNumber");
				transactions.setAccount_Number(AccNumber);
				List<TransactionsModel> fetchBalanceList = iservice.fetchBalance(transactions);
				httpSession.setAttribute("Balance", fetchBalanceList.get(0).getBalance());
				int updateStatus = iservice.updateLastLogin(loginUser);
				if(updateStatus == 1) {
					map.put("MESSAGE", "UPDATE SUCCESS");
				}else {
					map.put("MESSAGE", "UPDATE FAILURE");
				}
				map.put("MESSAGE", "SUCCESS");
			}else {
				map.put("MESSAGE", "INVALID USER");
			}
			String json = gson.toJson(map);
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/accounts")
	public ModelAndView accountsScreen(HttpSession httpSession) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Accounts");
		modelAndView.addObject(httpSession.getAttribute("CustomerName"));
		modelAndView.addObject(httpSession.getAttribute("LASTLOGIN"));
		modelAndView.addObject(httpSession.getAttribute("AccNumber"));
		return modelAndView;
	}
	
	@GetMapping("/transactions")
	public ModelAndView transferAmountScreen(HttpSession httpSession) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Transactions");
		modelAndView.addObject(httpSession.getAttribute("AccNumber"));
		modelAndView.addObject(httpSession.getAttribute("AccountType"));
		modelAndView.addObject(httpSession.getAttribute("CustomerName"));
		modelAndView.addObject(httpSession.getAttribute("Balance"));
		return modelAndView;
	}

	@GetMapping("/transactionHistory")
	public ModelAndView transactionsHistoryScreen(HttpSession httpSession) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("TransactionsHistory");
		modelAndView.addObject(httpSession.getAttribute("AccNumber"));
		modelAndView.addObject(httpSession.getAttribute("CustomerName"));
		modelAndView.addObject(httpSession.getAttribute("Balance"));
		modelAndView.addObject(httpSession.getAttribute("CUSTID"));
		return modelAndView;
	}

	@GetMapping("/investment-deposit")
	public ModelAndView depositScreen(HttpSession httpSession) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Deposit");
		return modelAndView;
	}
	
	@PostMapping("/getSbiAccountTypes")
	public @ResponseBody void getAccountTypes(HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			SBIAccountTypeModel accountType = new SBIAccountTypeModel();
			List<SBIAccountTypeModel> getAccountsList = iservice.getAccountTypes(accountType);
			if(getAccountsList.size() > 0) {
				for(int i=0; i< getAccountsList.size(); i++) {
					map.put(String.valueOf(getAccountsList.get(i).getAccount_TypeId()), getAccountsList.get(i).getAccount_Type());
				}
			}else {
				map.put("ERRORMSG", "MSG10");
			}
			String json = gson.toJson(map);
			json = json.trim();
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/getStates")
	public @ResponseBody void getStates(HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			StateModel state = new StateModel();
			List<StateModel> getStateList = iservice.getStatesList(state);
			if(getStateList.size() > 0) {
				for(int i=0; i<getStateList.size(); i++) {
					map.put(String.valueOf(getStateList.get(i).getState_code()), getStateList.get(i).getState_name());
				}
			}else {
				map.put("ERRORMSG", "MSG10");
			}
			String json = gson.toJson(map);
			json = json.trim();
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/getCities")
	public @ResponseBody void getCities(HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			CityModel city = new CityModel();
			List<CityModel> getCityList = iservice.getCitiesList(city);
			if(getCityList.size() > 0) {
				for(int i=0; i<getCityList.size(); i++) {
					map.put(getCityList.get(i).getCity_code(), getCityList.get(i).getCity_name());
				}
			}else {
				map.put("ERRORMSG", "MSG10");
			}
			String json = gson.toJson(map);
			json = json.trim();
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/getFilteredCities")
	public @ResponseBody void getFilteredCities(@ModelAttribute ("filteredCities") CityModel filteredCities, HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			CityModel city = filteredCities;
			List<CityModel> filteredCitiesList = iservice.getFilteredCitiesList(city);
			if(filteredCitiesList.size() > 0) {
				for(int i=0; i<filteredCitiesList.size(); i++) {
					map.put(filteredCitiesList.get(i).getCity_code(), filteredCitiesList.get(i).getCity_name());
				}
			}else {
				map.put("ERRORMSG", "MSG10");
			}
			String json = gson.toJson(map);
			json = json.trim();
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/getJointFilteredCities")
	public @ResponseBody void getJointFilteredCities(@ModelAttribute ("filteredCities") CityModel filteredCities, HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			CityModel city = filteredCities;
			List<CityModel> filteredCitiesList = iservice.getFilteredCitiesList(city);
			if(filteredCitiesList.size() > 0) {
				for(int i=0; i<filteredCitiesList.size(); i++) {
					map.put(filteredCitiesList.get(i).getCity_code(), filteredCitiesList.get(i).getCity_name());
				}
			}else {
				map.put("ERRORMSG", "MSG10");
			}
			String json = gson.toJson(map);
			json = json.trim();
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/getOccupations")
	public @ResponseBody void getOccupations(HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			OccupationModel occupation = new OccupationModel();
			List<OccupationModel> getOccupationsList = iservice.getOccupationsList(occupation);
			if(getOccupationsList.size() > 0) {
				for(int i=0; i < getOccupationsList.size(); i++) {
					map.put(getOccupationsList.get(i).getCustomer_OccupationId(), getOccupationsList.get(i).getCustomer_Occupation());
				}
			}else {
				map.put("ERRORMSG", "MSG10");
			}
			String json = gson.toJson(map);
			json = json.trim();
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/generateCaptcha")
	public @ResponseBody void generateCaptcha(HttpServletResponse response) {
		try {
			int length = 6;
			GenerateCaptcha captcha = new GenerateCaptcha();
			String generatedCaptcha = captcha.generateCaptcha(length);
			System.out.println("Captcha is: "+generatedCaptcha);
			response.getWriter().print(generatedCaptcha);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/registerCustomer")
	public @ResponseBody void registerCustomer(@ModelAttribute ("registerCustomer") RegisterModel registerCustomer, HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<RegisterModel> checkExistingCustomer = iservice.checkRegisteredCustomer(registerCustomer);
			if(checkExistingCustomer.size() > 0) {
				map.put("MESSAGE", "CUSTOMER EXIST");
			}else {
				int status = iservice.insertCustomerDetails(registerCustomer);
				List<RegisterModel> fetchCustomerList = iservice.checkRegisteredCustomer(registerCustomer);
				String email = fetchCustomerList.get(0).getCustomer_Email();
				String customerId = fetchCustomerList.get(0).getCustomer_Id();
				String accountNumber = fetchCustomerList.get(0).getAccount_Number();
				String password = fetchCustomerList.get(0).getCustomer_Password();
				email = email.trim();
				customerId = customerId.trim();
				accountNumber = accountNumber.trim();
				password = password.trim();
				String mailStatus = iservice.sendCustomerDetails(email, customerId, accountNumber, password);
				System.out.println(mailStatus);
				
				TransactionsModel transactions = new TransactionsModel();
				transactions.setAccount_Number(accountNumber);
				transactions.setBalance(0);
				int availableBalanceStatus = iservice.insertBalance(transactions);
				if(status == 1 && mailStatus=="SUCCESS" && availableBalanceStatus == 1) {
					map.put("MESSAGE", "SUCCESS");
				}else {
					map.put("MESSAGE", "FAILURE");
				}
			}
			String json = gson.toJson(map);
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/sendTransOTP")
	public @ResponseBody void sendDepositOTP(HttpServletResponse response, HttpSession httpSession){
		try {
			Map<String, String> map = new HashMap<String, String>();
			int length = 6;
			String email = (String) httpSession.getAttribute("EMAIL");
			email = email.trim();
			GenerateOTP generateOTP = new GenerateOTP();
			String otp = generateOTP.generateOtp(length);
			httpSession.setAttribute("GENOTP", otp);
			System.out.println("OTP is: "+ otp);
			String mailStatus = iservice.sendTransOTP(email, otp);
			if(mailStatus == "SUCCESS"){
				map.put("MESSAGE", "SUCCESS");
			}else{
				map.put("MESSAGE", mailStatus);
			}
			String json = gson.toJson(map);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/depositAmount")
	public @ResponseBody void depositAmount(@ModelAttribute ("depositAmt") TransactionsModel depositAmt, HttpServletResponse response, HttpSession httpSession){
		try{
			Map<String, String> map = new HashMap<String, String>();
			List<TransactionsModel> balanceList = iservice.fetchBalance(depositAmt);
			String genOtp = (String) httpSession.getAttribute("GENOTP");
			String userOtp = depositAmt.getOtp();
			if(userOtp == null || userOtp == ""){
				int amount = depositAmt.getAmount();
				int balance = balanceList.get(0).getBalance();
				balance = balance + amount;
				depositAmt.setBalance(balance);
				int updateStatus = iservice.updateBalance(depositAmt);
				if(updateStatus == 1){
					List<TransactionsModel> fetchBalanceList = iservice.fetchBalance(depositAmt);
					String balanceAmt = String.valueOf(fetchBalanceList.get(0).getBalance());
					httpSession.setAttribute("Balance", balanceAmt);
					map.put("MESSAGE", "SUCCESS");
					map.put("BALANCE", balanceAmt);
				}else{
					map.put("MESSAGE", "FAILURE");
				}
			}else if(userOtp.equals(genOtp)){
				int amount = depositAmt.getAmount();
				int balance = balanceList.get(0).getBalance();
				balance = balance + amount;
				depositAmt.setBalance(balance);
				int updateStatus = iservice.updateBalance(depositAmt);
				if(updateStatus == 1){
					List<TransactionsModel> fetchBalanceList = iservice.fetchBalance(depositAmt);
					String balanceAmt = String.valueOf(fetchBalanceList.get(0).getBalance());
					httpSession.setAttribute("Balance", balanceAmt);
					map.put("MESSAGE", "SUCCESS");
					map.put("BALANCE", balanceAmt);
				}else{
					map.put("MESSAGE", "FAILURE");
				}
			}else{
				map.put("MESSAGE", "ERROROTP");
			}
			String json = gson.toJson(map);
				response.getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@PostMapping("/withdrawAmount")
	public @ResponseBody void withdrawAmount(@ModelAttribute ("withdrawAmt") TransactionsModel withdrawAmt, HttpServletResponse response, HttpSession httpSession){
		try{
			Map<String, String> map = new HashMap<String, String>();
			List<TransactionsModel> balanceList = iservice.fetchBalance(withdrawAmt);
			String genOtp = (String) httpSession.getAttribute("GENOTP");
			String userOtp = withdrawAmt.getOtp();
			if(userOtp == null || userOtp == ""){
				int amount = withdrawAmt.getAmount();
				int balance = balanceList.get(0).getBalance();
				if(amount > balance){
					map.put("MESSAGE", "WITHERROR");
				}else{
					balance = balance - amount;
					withdrawAmt.setBalance(balance);
					int updateStatus = iservice.updateBalance(withdrawAmt);
					if(updateStatus == 1){
						List<TransactionsModel> fetchBalanceList = iservice.fetchBalance(withdrawAmt);
						String balanceAmt = String.valueOf(fetchBalanceList.get(0).getBalance());
						httpSession.setAttribute("Balance", balanceAmt);
						map.put("MESSAGE", "SUCCESS");
						map.put("BALANCE", balanceAmt);
					}else{
						map.put("MESSAGE", "FAILURE");
					}
				}
			}else if(userOtp.equals(genOtp)){
				int amount = withdrawAmt.getAmount();
				int balance = balanceList.get(0).getBalance();
				if(amount > balance){
					map.put("MESSAGE", "WITHERROR");
				}else{
					balance = balance - amount;
					withdrawAmt.setBalance(balance);
					int updateStatus = iservice.updateBalance(withdrawAmt);
					if(updateStatus == 1){
						List<TransactionsModel> fetchBalanceList = iservice.fetchBalance(withdrawAmt);
						String balanceAmt = String.valueOf(fetchBalanceList.get(0).getBalance());
						httpSession.setAttribute("Balance", balanceAmt);
						map.put("MESSAGE", "SUCCESS");
						map.put("BALANCE", balanceAmt);
					}else{
						map.put("MESSAGE", "FAILURE");
					}
				}
			}else{
				map.put("MESSAGE", "ERROROTP");
			}
			String json = gson.toJson(map);
			response.getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@PostMapping("/transferAmount")
	public @ResponseBody void transferAmount(@ModelAttribute ("transferAmount") TransactionsModel transferAmount, HttpServletResponse response, HttpSession httpSession){
		try {
			Map<String, String> map = new HashMap<String, String>();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = new Date();
			String from_account_Number = (String) httpSession.getAttribute("AccNumber");
			String genOtp = (String) httpSession.getAttribute("GENOTP");
			String userOtp = transferAmount.getOtp();
			transferAmount.setAccount_Number(from_account_Number);
			List<TransactionsModel> balanceList = iservice.fetchBalance(transferAmount);
			RegisterModel beneficiaryDetails = new RegisterModel();
			beneficiaryDetails.setAccount_Number(transferAmount.getTo_account_Number());
			List<RegisterModel> check_to_accountNum = iservice.getBeneficiaryDetails(beneficiaryDetails);
			if(check_to_accountNum.size() > 0){
				if(userOtp == null || userOtp == ""){
					int amount = transferAmount.getAmount();
					int balance = balanceList.get(0).getBalance();
					if(amount > balance){
						map.put("MESSAGE", "TRANSERROR");
					}else{
						transferAmount.setFrom_account_Number(from_account_Number);
						transferAmount.setTransaction_Date(dateFormat.format(currentDate));
						transferAmount.setCr_dr("dr");
						balance = balance - amount;
						transferAmount.setBalance(balance);
						int transStatus = iservice.transferAmount(transferAmount);
						if(transStatus == 1){
							transferAmount.setAccount_Number(from_account_Number);
							int fromUpdateStatus = iservice.updateBalance(transferAmount);
							List<TransactionsModel> fetchBalanceList = iservice.fetchBalance(transferAmount);
							String balanceAmt = String.valueOf(fetchBalanceList.get(0).getBalance());
							httpSession.setAttribute("Balance", balanceAmt);
							map.put("BALANCE", balanceAmt);
							if(fromUpdateStatus == 1){
								transferAmount.setAccount_Number(transferAmount.getTo_account_Number());
								List<TransactionsModel> toBalanceList = iservice.fetchBalance(transferAmount);
								int toBalance = toBalanceList.get(0).getBalance();
								toBalance = toBalance + amount;
								transferAmount.setBalance(toBalance);
								int toUpdateStatus = iservice.updateBalance(transferAmount);
								if(toUpdateStatus == 1){
									beneficiaryDetails.setAccount_Number(transferAmount.getTo_account_Number());
									List<RegisterModel> beneficiaryList = iservice.getBeneficiaryDetails(beneficiaryDetails);
									String customer_Name = beneficiaryList.get(0).getCustomer_FirstName().trim().concat(" ").concat(beneficiaryList.get(0).getCustomer_LastName().trim());
									String email = beneficiaryList.get(0).getCustomer_Email();
									String mailStatus = iservice.sendTransactionDetails(customer_Name, email, amount, toBalance);
									System.out.println("Mail Status: "+ mailStatus);
									map.put("MESSAGE", "SUCCESS");
								}else{
									map.put("MESSAGE", "FAILURE");
								}
							}else{
								map.put("MESSAGE", "FAILURE");
							}
						}else{
							map.put("MESSAGE", "FAILURE");
						}
					}
				}else if(userOtp.equals(genOtp)){
					int amount = transferAmount.getAmount();
					int balance = balanceList.get(0).getBalance();
					if(amount > balance){
						map.put("MESSAGE", "TRANSERROR");
					}else{
						transferAmount.setFrom_account_Number(from_account_Number);
						transferAmount.setTransaction_Date(dateFormat.format(currentDate));
						transferAmount.setCr_dr("dr");
						balance = balance - amount;
						transferAmount.setBalance(balance);
						int transStatus = iservice.transferAmount(transferAmount);
						if(transStatus == 1){
							transferAmount.setAccount_Number(from_account_Number);
							int fromUpdateStatus = iservice.updateBalance(transferAmount);
							if(fromUpdateStatus == 1){
								transferAmount.setAccount_Number(transferAmount.getTo_account_Number());
								List<TransactionsModel> toBalanceList = iservice.fetchBalance(transferAmount);
								int toBalance = toBalanceList.get(0).getBalance();
								toBalance = toBalance + amount;
								transferAmount.setBalance(toBalance);
								int toUpdateStatus = iservice.updateBalance(transferAmount);
								if(toUpdateStatus == 1){
									beneficiaryDetails.setAccount_Number(transferAmount.getTo_account_Number());
									List<RegisterModel> beneficiaryList = iservice.getBeneficiaryDetails(beneficiaryDetails);
									String customer_Name = beneficiaryList.get(0).getCustomer_FirstName().trim().concat(" ").concat(beneficiaryList.get(0).getCustomer_LastName().trim());
									String email = beneficiaryList.get(0).getCustomer_Email();
									String mailStatus = iservice.sendTransactionDetails(customer_Name, email, amount, toBalance);
									System.out.println("Mail Status: "+ mailStatus);
									map.put("MESSAGE", "SUCCESS");
								}else{
									map.put("MESSAGE", "FAILURE");
								}
							}else{
								map.put("MESSAGE", "FAILURE");
							}
						}else{
							map.put("MESSAGE", "FAILURE");
						}
					}
				}else{
					map.put("MESSAGE", "ERROROTP");
				}
			}else{
				map.put("MESSAGE", "USERNOTEXIST");
			}
			
			String json = gson.toJson(map);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/loadTransactionsHistory")
	public @ResponseBody Object loadTransactionsHistory(HttpSession httpSession){
		try {
			TransactionsModel transactionsHistory = new TransactionsModel();
			String account_Number = (String) httpSession.getAttribute("AccNumber");
			transactionsHistory.setFrom_account_Number(account_Number);
			transactionsHistory.setTo_account_Number(account_Number);
			List<TransactionsModel> transactionsList = iservice.getCustomerTransactions(transactionsHistory);
			for(int i=0; i<transactionsList.size(); i++){
				if(transactionsList.get(i).getTo_account_Number().trim().equals(account_Number)){
					transactionsList.get(i).setCr_dr("cr");
				}
			}
			return transactionsList;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PostMapping("/showBalance")
	public @ResponseBody void showBalance(HttpSession httpSession, HttpServletResponse response){
		try {
			TransactionsModel fetchBal = new TransactionsModel();
			String accNumber = (String) httpSession.getAttribute("AccNumber");
			fetchBal.setAccount_Number(accNumber);
			List<TransactionsModel> fetchBalAmt = iservice.fetchBalance(fetchBal);
			String responseAmount = null;
			if(fetchBalAmt.size() > 0){
				responseAmount = String.valueOf(fetchBalAmt.get(0).getBalance());
			}
			String json = gson.toJson(responseAmount);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/checkSession")
	public @ResponseBody void checkSession(HttpServletRequest request ,HttpServletResponse response){
		try {
			Map<String, String> map = new HashMap<String, String>();
			HttpSession httpSession = request.getSession(false);
			if(httpSession == null){
				map.put("MESSAGE", "EXPIRED");
			}else{
				map.put("MESSAGE", "ALIVE");
			}
			String json = gson.toJson(map);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@GetMapping("/logout")
	public @ResponseBody Object logout(HttpSession httpSession){
		try {
			ModelAndView modelAndView = new ModelAndView();
			httpSession.invalidate();
			modelAndView.setViewName("Login");
			return modelAndView;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
