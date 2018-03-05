package com.dpt.service;

import com.dpt.model.Shop;
import com.dpt.model.Technician;

import java.util.List;

public interface ShopService {

    public List<Shop> getShopsOfRecommend() throws Exception;

    public int insertShop(Shop shop) throws Exception;

    public List<Shop> getShopsByOpenIdRecommend(String referee) throws Exception;

}
