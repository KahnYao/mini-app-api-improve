package com.dpt.mapper;

import com.dpt.model.Platform;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Platform record);

    int insertSelective(Platform record);

    Platform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKey(Platform record);
}