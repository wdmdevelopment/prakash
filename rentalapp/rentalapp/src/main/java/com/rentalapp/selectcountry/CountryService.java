package com.rentalapp.selectcountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
 
public class CountryService {

    private final static String API_BASE_URL = "https://countriesnow.space/api/v0.1/countries";
    
    @Autowired
    RestTemplate restTemplate;

   
   

    public Country[] getAllCountries() {
    	 System.out.println(restTemplate.getForObject(API_BASE_URL , Country[].class).toString());
        return restTemplate.getForObject(API_BASE_URL, Country[].class);
       
    }

	public State[] getStatesByCountry(String countryName) {
		restTemplate.getForObject(API_BASE_URL, Country[].class);
		
		
       return null;
    }

    public City[] getCitiesByState(String stateName) {
        Country[] countries = restTemplate.getForObject(API_BASE_URL + "all", Country[].class);

       

        return null;
    }

}
