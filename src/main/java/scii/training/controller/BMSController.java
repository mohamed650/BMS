package scii.training.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import scii.training.model.CityModel;
import scii.training.model.SBIAccountTypeModel;
import scii.training.model.StateModel;
import scii.training.service.IService;

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
			System.out.println(getCityList.size());
			if(getCityList.size() > 0) {
				for(int i=0; i<getCityList.size(); i++) {
					map.put(getCityList.get(i).getCity_code(), getCityList.get(i).getCity_name());
				}
			}else {
				map.put("ERRORMSG", "MSG10");
			}
			String json = gson.toJson(map);
			json = json.replaceAll("\\s", "");
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
			json = json.replaceAll("\\s", "");
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
			json = json.replaceAll("\\s", "");
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
