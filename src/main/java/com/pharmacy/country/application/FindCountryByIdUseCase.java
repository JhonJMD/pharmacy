package com.pharmacy.country.application;

import com.pharmacy.country.domain.service.CountryService;

public class FindCountryByIdUseCase {
    private final CountryService countryService;

    public FindCountryByIdUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(String codecountry){
        countryService.findCountryById(codecountry);
    }
}
