package com.pharmacy.country.application;

import java.util.*;

import com.pharmacy.country.domain.entity.Country;
import com.pharmacy.country.domain.service.CountryService;

public class FindCountryByIdUseCase {
    private final CountryService countryService;

    public FindCountryByIdUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public Optional<Country> execute(String codecountry){
        return countryService.findCountryById(codecountry);
    }
}
