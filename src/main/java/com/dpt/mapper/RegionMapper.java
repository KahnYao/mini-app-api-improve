package com.dpt.mapper;

import com.dpt.model.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper {
    @Select("SELECT * FROM `zh_region` where name = #{name} ")
    List<Region> getRegionIdByName(String name);

    @Select("SELECT * FROM `zh_region` where name = #{name} and parentId = #{parentId}")
    List<Region> getCityIdByNameAndParentId(@Param("name") String name,@Param("parentId") Integer parentId);
}
