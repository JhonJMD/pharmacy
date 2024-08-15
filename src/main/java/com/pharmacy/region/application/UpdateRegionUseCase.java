package com.pharmacy.region.application;

import com.pharmacy.region.domain.entity.Region;
import com.pharmacy.region.domain.service.RegionService;

public class UpdateRegionUseCase {
    private final RegionService regionService;

    public UpdateRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region){
        regionService.updateRegion(region);
    }   
}
