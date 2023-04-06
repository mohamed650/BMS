package scii.training.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import scii.training.model.CityModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;

@Mapper
public interface IMapper {
	
	public List<SBIAccountTypeModel> getAccountTypes(SBIAccountTypeModel accounts);
	
	public List<StateModel> getStatesList(StateModel states);
	
	public List<CityModel> getCitiesList(CityModel cities);
	
	public List<CityModel> getFilteredCitiesList(CityModel filteredCities);
}
