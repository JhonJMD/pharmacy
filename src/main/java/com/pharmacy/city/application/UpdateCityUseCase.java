package com.pharmacy.city.application;

import com.pharmacy.city.domain.entity.City;
import com.pharmacy.city.domain.service.CityService;

public class UpdateCityUseCase {
    private final CityService cityService;

    public UpdateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city){
        cityService.updateCity(city);
    }
}
