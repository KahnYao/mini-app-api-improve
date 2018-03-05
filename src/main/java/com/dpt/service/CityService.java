package com.dpt.service;

import com.dpt.model.City;

public interface CityService {
    public City getCityByRegionId(Integer regionId) throws Exception;
}
