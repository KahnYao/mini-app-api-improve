package com.dpt.service.impl;

import com.dpt.mapper.CityMapper;
import com.dpt.model.City;
import com.dpt.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cityService")
public class CityServiceImpl  implements CityService{

    private final static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private CityMapper  cityMapper;

    @Override
    public City getCityByRegionId(Integer regionId) throws Exception {
        City city = this.cityMapper.getCityByRegionId(regionId);
        return city;
    }
}
