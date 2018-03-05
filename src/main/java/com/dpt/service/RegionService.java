package com.dpt.service;


import com.dpt.model.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionService {
    public List<Region> getIdByProvinceName(String name) throws Exception;

    public List<Region> getCityIdByNameAndParentId(String name, Integer parentId) throws Exception;
}
