package com.pharmacy.region.application;

import java.util.Optional;

import com.pharmacy.region.domain.entity.Region;
import com.pharmacy.region.domain.service.RegionService;

public class FindRegionByIdUseCase {
    private final RegionService regionService;

    public FindRegionByIdUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Optional<Region> execute(String codeReg){
        return regionService.findRegionById(codeReg);
    }   
}
