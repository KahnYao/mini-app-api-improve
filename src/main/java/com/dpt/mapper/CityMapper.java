package com.dpt.mapper;

import com.dpt.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {

    @Select("SELECT * FROM `zh_city_code` WHERE regionId = #{regionId}")
    City getCityByRegionId(Integer regionId);

}
