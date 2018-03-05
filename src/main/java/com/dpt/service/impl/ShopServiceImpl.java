package com.dpt.service.impl;

import com.dpt.mapper.ShopMapper;
import com.dpt.model.Shop;
import com.dpt.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

    private final static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private ShopMapper shopMapper;

    public List<Shop> getShopsOfRecommend() throws Exception {
        List<Shop> shops = shopMapper.getShopsOfRecommend();
        return shops;
    }

    public int insertShop(Shop shop) throws Exception {
        return this.shopMapper.insert(shop);
    }

    @Override
    public List<Shop> getShopsByOpenIdRecommend(String referee) throws Exception {
        List<Shop> shops = this.shopMapper.getShopsByOpenIdRecommend(referee);
        return shops;
    }

}
