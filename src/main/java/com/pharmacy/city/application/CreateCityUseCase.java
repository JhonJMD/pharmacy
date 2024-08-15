package com.pharmacy.city.application;

import com.pharmacy.city.domain.entity.City;
import com.pharmacy.city.domain.service.CityService;

public class CreateCityUseCase {
    private final CityService cityService;

    public CreateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city){
        cityService.createCity(city);
    }
}
