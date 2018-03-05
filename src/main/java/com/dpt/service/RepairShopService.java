package com.dpt.service;

import com.dpt.model.RepairShop;

import java.util.List;

public interface RepairShopService {
    public List<RepairShop> getShopsOfRecommend() throws Exception;

    public int insertShop(RepairShop repairShop) throws Exception;

    public List<RepairShop> getShopsByOpenIdRecommend(String referee) throws Exception;

    public List<RepairShop> getCodeByRegionId(Integer city) throws Exception;

    public RepairShop getIsrz(String tjperson) throws Exception;
}
