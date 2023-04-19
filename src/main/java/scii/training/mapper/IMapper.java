package scii.training.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import scii.training.model.CityModel;
import scii.training.model.OccupationModel;
import scii.training.model.RegisterModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;

@Mapper
public interface IMapper {
	
	public List<SBIAccountTypeModel> getAccountTypes(SBIAccountTypeModel accounts);
	
	public List<StateModel> getStatesList(StateModel states);
	
	public List<CityModel> getCitiesList(CityModel cities);
	
	public List<CityModel> getFilteredCitiesList(CityModel filteredCities);
	
	public List<OccupationModel> getOccupationsList(OccupationModel occupations);
	
	public List<RegisterModel> checkRegisteredCustomer(RegisterModel checkCustomer);
	
	public int insertCustomerDetails(RegisterModel registerCustomer);
	
	public List<RegisterModel> userLogin(RegisterModel checkUser);
	
	public int updateLastLogin(RegisterModel userLastLogin);
}
