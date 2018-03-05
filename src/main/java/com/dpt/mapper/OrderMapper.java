package com.dpt.mapper;

import com.dpt.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    @Insert("INSERT INTO `order`(id,openId, payEndTime, prepayEndTime, prepayId, productId,total,createdTime,updatedTime,payStatus,verifyStatus) " +
            "VALUES(#{id},#{openId}, #{payEndTime}, #{prepayEndTime},#{prepayId}, #{productId},#{total},#{createdTime},#{updatedTime},#{payStatus},#{verifyStatus})")
    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    @Select("SELECT total from `order` WHERE openId =#{openId}")
    List<Order> selectOrderTotalCount(String openId);

    @Select("SELECT total from `order` WHERE openId =#{openId} AND createdTime >= '2018-1-1' AND createdTime < '2018-2-1'")
    List<Order> selectOrderTotalMonth(@Param("openId") String openId,@Param("createTime")int createTime);

    @Select("SELECT * from `order` WHERE openId =#{openId}")
    List<Order> selectOrderList(String openId);

}