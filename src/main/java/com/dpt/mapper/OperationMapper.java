package com.dpt.mapper;

import com.dpt.model.Operation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OperationMapper {
    int deleteByPrimaryKey(Integer id);

    @Insert("INSERT INTO operation(id,operator, type, targetId, amount, remark, createdTime, updatedTime) " +
            "VALUES(#{id},#{operator}, #{type}, #{targetId}, #{amount}, #{remark}, #{createdTime}, #{updatedTime})")
    int insert(Operation record);

    int insertSelective(Operation record);

    Operation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKey(Operation record);

    @Select("SELECT COUNT(amount) FROM `operation` WHERE type = #{type}")
    int getOperationByType(@Param("type") Byte type);
}