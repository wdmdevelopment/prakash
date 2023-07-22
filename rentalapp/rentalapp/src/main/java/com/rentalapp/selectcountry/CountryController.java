package com.rentalapp.selectcountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public Country[] getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/states")
    public State[] getStatesByCountry(@RequestParam String countryName) {
        return countryService.getStatesByCountry(countryName);
    }

    @GetMapping("/cities")
    public City[] getCitiesByState(@RequestParam String stateName) {
        return countryService.getCitiesByState(stateName);
    }

}
