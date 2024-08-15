package com.pharmacy.region.application;

import com.pharmacy.region.domain.entity.Region;
import com.pharmacy.region.domain.service.RegionService;

public class CreateRegionUseCase {
    private final RegionService regionService;

    public CreateRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region){
        regionService.createRegion(region);
    }   
}
