package com.dpt.mapper;

import com.dpt.model.RepairShop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RepairShopMapper {
    @Select("SELECT * FROM `zh_repair_shop` WHERE isrz = 1")
    List<RepairShop> getShopsOfRecommend();


    @Insert("INSERT INTO `zh_repair_shop` (createDate, modifyDate,id, name, contact, mobile, Email, province, city, distict, address, licenceImg,position,tjperson,isrz, mendianHead, provinceName,cityName,distictName,source,amount,code,mendianView,channel) " +
            "VALUES(#{createDate}, #{modifyDate}, #{id}, #{name}, #{contact}, #{mobile}, #{Email}, #{province}, #{city}, #{distict}, #{address}, #{licenceImg},#{position},#{tjperson},#{isrz},#{mendianHead},#{provinceName},#{cityName},#{distictName},#{source},#{amount},#{code},#{mendianView},#{channel})")
    int insert(RepairShop repairShop);

    @Select("SELECT * FROM `zh_repair_shop` WHERE tjperson = #{referee} ORDER BY modifyDate DESC LIMIT 10")
    List<RepairShop> getShopsByOpenIdRecommend(String referee);

    @Select("SELECT code FROM `zh_repair_shop` where city = #{city} order by id desc limit 1")
    List<RepairShop> getCodeByCityId(Integer city);

    @Select("SELECT * FROM `zh_repair_shop` WHERE tjperson=#{tjperson}")
    RepairShop getIsrz(String tjperson);

}
