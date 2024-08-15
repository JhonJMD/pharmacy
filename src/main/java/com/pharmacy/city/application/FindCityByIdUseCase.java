package com.pharmacy.city.application;

import java.util.Optional;

import com.pharmacy.city.domain.entity.City;
import com.pharmacy.city.domain.service.CityService;

public class FindCityByIdUseCase {
    private final CityService cityService;

    public FindCityByIdUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public Optional<City> execute(String codecity){
        return cityService.findCityById(codecity);
    }
}
