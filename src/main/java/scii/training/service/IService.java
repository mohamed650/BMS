package scii.training.service;

import java.util.List;

import scii.training.model.CityModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;

public interface IService {
	
	public List<SBIAccountTypeModel> getAccountTypes(SBIAccountTypeModel accounts);
	
	public List<StateModel> getStatesList(StateModel states);
	
	public List<CityModel> getCitiesList(CityModel cities);
	
	public List<CityModel> getFilteredCitiesList(CityModel filteredCities);
}
