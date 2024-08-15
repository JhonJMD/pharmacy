package com.pharmacy.region.application;

import com.pharmacy.region.domain.service.RegionService;

public class DeleteRegionUseCase {
    private final RegionService regionService;

    public DeleteRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(String codereg){
        regionService.deleteRegion(codereg);
    }   
}
