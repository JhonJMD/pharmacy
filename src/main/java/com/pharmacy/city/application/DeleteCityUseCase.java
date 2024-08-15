package com.pharmacy.city.application;

import com.pharmacy.city.domain.service.CityService;

public class DeleteCityUseCase {
    private final CityService cityService;

    public DeleteCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(String codecity){
        cityService.deleteCity(codecity);
    }
}
