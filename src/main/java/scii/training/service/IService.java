package scii.training.service;

import java.util.List;

import scii.training.model.CityModel;
import scii.training.model.OccupationModel;
import scii.training.model.RegisterModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;

public interface IService {
	
	public List<SBIAccountTypeModel> getAccountTypes(SBIAccountTypeModel accounts);
	
	public List<StateModel> getStatesList(StateModel states);
	
	public List<CityModel> getCitiesList(CityModel cities);
	
	public List<CityModel> getFilteredCitiesList(CityModel filteredCities);
	
	public List<OccupationModel> getOccupationsList(OccupationModel occupations);
	
	public List<RegisterModel> checkRegisteredCustomer(RegisterModel checkCustomer);
	
	public int insertCustomerDetails(RegisterModel registerCustomer);
	
	String sendCustomerDetails(String email, String customerId, String accountNumber, String password);
	
	public List<RegisterModel> userLogin(RegisterModel checkUser);
	
	public int updateLastLogin(RegisterModel userLastLogin);
	
}
