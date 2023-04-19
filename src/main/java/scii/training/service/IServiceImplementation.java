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
					+"Please Login to the Application using your Customer Id and Password.";
			String subject = "SBI Credentials";
			
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

}
