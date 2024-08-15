package com.pharmacy.city.domain.service;

import java.util.Optional;

import com.pharmacy.city.domain.entity.City;

public interface CityService {
    void createCity (City city);
    void updateCity (City city);
    void deleteCity (String codecity);
    Optional<City> findCityById(String codecity);
}
