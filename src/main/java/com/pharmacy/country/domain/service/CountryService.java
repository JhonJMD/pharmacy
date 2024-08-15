package com.pharmacy.country.domain.service;

import java.util.Optional;

import com.pharmacy.country.domain.entity.Country;

public interface CountryService {
    void createCountry (Country country);
    void updateCountry (Country country);
    void deleteCountry (String codeCountry);
    Optional<Country> findCountryById(String codeCountry);
}
