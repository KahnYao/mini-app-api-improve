package com.dpt.mapper;

import com.dpt.model.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper {

    @Select("SELECT * FROM `shop` WHERE verify = 1 ORDER BY updatedTime DESC LIMIT 10")
    List<Shop> getShopsOfRecommend();


    @Insert("INSERT INTO shop (id, shopName, contacts, telephone, email, province, city, district, address, license, position,images,envImages, referee, amount, verify, createdTime, updatedTime) " +
            "VALUES(#{id}, #{shopName}, #{contacts}, #{telephone}, #{email}, #{province}, #{city}, #{district}, #{address}, #{license}, #{position},#{images},#{envImages}, #{referee}, #{amount}, #{verify}, #{createdTime}, #{updatedTime})")
    int insert(Shop shop);

    @Select("SELECT * FROM `shop` WHERE verify = 1 and referee = #{referee} ORDER BY updatedTime DESC LIMIT 10")
    List<Shop> getShopsByOpenIdRecommend(String referee);

}