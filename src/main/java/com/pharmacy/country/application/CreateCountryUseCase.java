package com.pharmacy.country.application;

import com.pharmacy.country.domain.entity.Country;
import com.pharmacy.country.domain.service.CountryService;

public class CreateCountryUseCase {
    private final CountryService countryService;

    public CreateCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country){
        countryService.createCountry(country);
    }
}
