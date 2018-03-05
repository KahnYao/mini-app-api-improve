package com.dpt.service.impl;

import com.dpt.mapper.RegionMapper;
import com.dpt.model.Region;
import com.dpt.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("regionService")
public class RegionServiceImpl implements RegionService {

    private final static Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

    @Autowired
    private RegionMapper regionMapper;
    @Override
    public List<Region>  getIdByProvinceName(String name) throws Exception {
        List<Region> region = this.regionMapper.getRegionIdByName(name);
        return region;
    }

    @Override
    public List<Region> getCityIdByNameAndParentId(String name, Integer parentId) throws Exception {
        List<Region> regionList = this.regionMapper.getCityIdByNameAndParentId(name, parentId);
        return regionList;
    }
}
