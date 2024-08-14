package com.pharmacy.country.application;

import com.pharmacy.country.domain.entity.Country;
import com.pharmacy.country.domain.service.CountryService;

public class UpdateCountryUseCase {
    private final CountryService countryService;

    public UpdateCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country){
        countryService.createCountry(country);
    }
}
