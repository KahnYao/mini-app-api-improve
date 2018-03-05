package com.dpt.mapper;

import com.dpt.model.Profit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProfitMapper {

    @Insert("INSERT INTO `profit`(id, openId, operateId, title, status, amount, remark, createdTime, updatedTime) " +
            "VALUES(#{id}, #{openId}, #{operateId}, #{title}, #{status}, #{amount}, #{remark}, #{createdTime}, #{updatedTime})")
    int insert(Profit profit);

    @Select("SELECT COUNT(amount) total FROM `profit` WHERE openId = 'kahn' AND type = 1 AND createdTime >= '2018-1-1' AND createdTime < '2018-2-1'")
    double countAmountByMonth();

    @Select("SELECT COUNT(amount) total FROM `profit` WHERE openId = 'kahn' AND type = 1")
    double countAmount();

    @Select("SELECT  amount from `profit` WHERE openId = #{openId} AND status in (1,2) ")
    List<Profit> countAmountNum(@Param("openId") String openId);

    @Select("SELECT amount from `profit` WHERE openId = #{openId} AND status in (1,2) AND createdTime >= #{createTime} AND createdTime <= #{endTime}")
    List<Profit> selectAmountNumByMonth(@Param("openId") String openId, @Param("createTime")long createTime, @Param("endTime")long endTime);

    @Select("select * from `profit` where openId = #{openId} ORDER BY createdTime DESC ")
    List<Profit> selectProfitList(String openId);

    @Select("SELECT * from `profit`" +
            "WHERE openId = #{openId} AND createdTime >= #{createTime} AND createdTime <= #{endTime}")
    List<Profit> selectProfitListMonth(@Param("openId") String openId, @Param("createTime")long createTime, @Param("endTime")long endTime);


}