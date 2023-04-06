package scii.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scii.training.mapper.IMapper;
import scii.training.model.CityModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;

@Component
public class IServiceImplementation implements IService{
	
	@Autowired(required=false)
	IMapper imapper;
	
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

}
