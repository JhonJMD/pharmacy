package com.pharmacy.region.domain.service;

import java.util.Optional;

import com.pharmacy.region.domain.entity.Region;

public interface RegionService {
    void createRegion (Region region);
    void updateRegion (Region region);
    void deleteRegion (String codereg);
    Optional<Region> findRegionById (String codeReg);
}
