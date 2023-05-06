package scii.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import scii.training.mapper.IMapper;
import scii.training.model.CityModel;
import scii.training.model.OccupationModel;
import scii.training.model.RegisterModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;
import scii.training.model.TransactionsModel;

@Component
public class IServiceImplementation implements IService{
	
	@Autowired(required=false)
	IMapper imapper;
	
	
	 @Autowired 
	 private JavaMailSender javaMailSender;
	 
	 @Value("${spring.mail.username}") 
	 private String sender;
	 
	
	@Override
	public List<SBIAccountTypeModel> getAccountTypes(SBIAccountTypeModel accounts) {
		List<SBIAccountTypeModel> getAccountTypeList = imapper.getAccountTypes(accounts);
		return getAccountTypeList;
	}

	@Override
	public List<StateModel> getStatesList(StateModel states) {
		List<StateModel> getStateList = imapper.getStatesList(states);
		return getStateList;
	}

	@Override
	public List<CityModel> getCitiesList(CityModel cities) {
		List<CityModel> getCityList = imapper.getCitiesList(cities);
		return getCityList;
	}

	@Override
	public List<CityModel> getFilteredCitiesList(CityModel filteredCities) {
		List<CityModel> getFilteredCityList = imapper.getFilteredCitiesList(filteredCities);
		return getFilteredCityList;
	}

	@Override
	public List<OccupationModel> getOccupationsList(OccupationModel occupations) {
		List<OccupationModel> getOccupationList = imapper.getOccupationsList(occupations);
		return getOccupationList;
	}

	@Override
	public int insertCustomerDetails(RegisterModel registerCustomer) {
		int status = imapper.insertCustomerDetails(registerCustomer);
		return status;
	}

	@Override
	public List<RegisterModel> checkRegisteredCustomer(RegisterModel checkCustomer) {
		List<RegisterModel> checkCustomerList = imapper.checkRegisteredCustomer(checkCustomer);
		return checkCustomerList;
	}

	@Override
	public String sendCustomerDetails(String email, String customerId, String accountNumber, String password) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			String recepient = email;
			String text = "Hi, "+email+"\n"
					+"\n"
					+"Please find your SBI Credentials below,"+"\n"
					+"\n"
					+"Customer Id: "+customerId+"\n"
					+"Account Number: "+accountNumber+"\n"
					+"Password: "+password
					+"\n"
					+"\n"
					+"Please Login to the Application using your Customer Id and Password."
					+"\n"
					+"\n"
					+"\n"
					+"Please note: This is a System generated mail Please do not reply to this mail."
					+"\n"
					+"--Regards"
					+"\n"
					+"  State Bank of India(SBI)";
			String subject = "SBI Account Created Successfully!";
			
			message.setFrom(sender);
			message.setTo(recepient);
			message.setSubject(subject);
			message.setText(text);
			
			javaMailSender.send(message);
			
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
	}

	@Override
	public List<RegisterModel> userLogin(RegisterModel checkUser) {
		List<RegisterModel> checkUserList = imapper.userLogin(checkUser);
		return checkUserList;
	}

	@Override
	public int updateLastLogin(RegisterModel userLastLogin) {
		int updateStatus = imapper.updateLastLogin(userLastLogin);
		return updateStatus;
	}

	@Override
	public int insertBalance(TransactionsModel insertBalance) {
		int insStatus = imapper.insertBalance(insertBalance);
		return insStatus;
	}

	@Override
	public List<TransactionsModel> fetchBalance(TransactionsModel fetchBal) {
		List<TransactionsModel> fetchBalanceList = imapper.fetchBalance(fetchBal);
		return fetchBalanceList;
	}

	@Override
	public String sendTransOTP(String email, String otp) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			String recepient = email;
			String text = "Hi, "+email+"\n"
					+"\n"
					+"Please find your OTP for the Transaction below,"+"\n"
					+"\n"
					+"OTP: "+otp+"\n"
					+"\n"
					+"\n"
					+"\n"
					+"Please note: This is a System generated mail Please do not reply to this mail."
					+"\n"
					+"--Regards"
					+"\n"
					+"  State Bank of India(SBI)";
			String subject = "SBI OTP";
			
			message.setFrom(sender);
			message.setTo(recepient);
			message.setSubject(subject);
			message.setText(text);
			javaMailSender.send(message);
			
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
	}

	@Override
	public int updateBalance(TransactionsModel updateBalance) {
		int updateStatus = imapper.updateBalance(updateBalance);
		return updateStatus;
	}

	@Override
	public int transferAmount(TransactionsModel transferAmount) {
		int transferStatus = imapper.transferAmount(transferAmount);
		return transferStatus;
	}

	@Override
	public String sendTransactionDetails(String customer_Name, String email, int amount, int balance) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			String recepient = email;
			String text = "Dear, "+customer_Name+"\n"
					+"\n"
					+"Your Account has a credit of Rs."+amount
					+"\n"
					+"Avl Bal Rs."+balance+" -SBI"
					+"\n"
					+"\n"
					+"\n"
					+"Please note: This is a System generated mail Please do not reply to this mail."
					+"\n"
					+"--Regards"
					+"\n"
					+"  State Bank of India(SBI)";
			String subject = "Amount Credited";
			
			message.setFrom(sender);
			message.setTo(recepient);
			message.setSubject(subject);
			message.setText(text);
			javaMailSender.send(message);
			
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FAILURE";
		}
	}

	@Override
	public List<RegisterModel> getBeneficiaryDetails(RegisterModel beneficiaryDetails) {
		List<RegisterModel> beneficiaryList = imapper.getBeneficiaryDetails(beneficiaryDetails);
		return beneficiaryList;
	}

	@Override
	public List<TransactionsModel> getCustomerTransactions(TransactionsModel getCustomerTransactions) {
		List<TransactionsModel> getTransactionList = imapper.getCustomerTransactions(getCustomerTransactions);
		return getTransactionList;
	}

}
