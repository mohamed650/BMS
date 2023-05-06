package scii.training.service;

import java.util.List;

import scii.training.model.CityModel;
import scii.training.model.OccupationModel;
import scii.training.model.RegisterModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;
import scii.training.model.TransactionsModel;

public interface IService {
	
	public List<SBIAccountTypeModel> getAccountTypes(SBIAccountTypeModel accounts);
	
	public List<StateModel> getStatesList(StateModel states);
	
	public List<CityModel> getCitiesList(CityModel cities);
	
	public List<CityModel> getFilteredCitiesList(CityModel filteredCities);
	
	public List<OccupationModel> getOccupationsList(OccupationModel occupations);
	
	public List<RegisterModel> checkRegisteredCustomer(RegisterModel checkCustomer);
	
	public int insertCustomerDetails(RegisterModel registerCustomer);
	
	public int insertBalance(TransactionsModel insertBalance);
	
	public List<TransactionsModel> fetchBalance(TransactionsModel fetchBal);
	
	String sendCustomerDetails(String email, String customerId, String accountNumber, String password);
	
	public List<RegisterModel> userLogin(RegisterModel checkUser);
	
	public int updateLastLogin(RegisterModel userLastLogin);
	
	String sendTransOTP(String email, String otp);

	public int updateBalance(TransactionsModel updateBalance);

	public int transferAmount(TransactionsModel transferAmount);

	String sendTransactionDetails(String customer_Name, String email, int amount, int balance);

	public List<RegisterModel> getBeneficiaryDetails(RegisterModel beneficiaryDetails);

	public List<TransactionsModel> getCustomerTransactions(TransactionsModel getCustomerTransactions);
	
}
