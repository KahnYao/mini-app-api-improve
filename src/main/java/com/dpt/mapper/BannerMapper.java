package com.dpt.mapper;

import com.dpt.model.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface BannerMapper {
    @Select("SELECT * FROM `zh_banner` WHERE isOwnerCar = 2 ")
    List<Banner> getBannnerList();
}
