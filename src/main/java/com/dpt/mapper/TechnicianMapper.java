package com.dpt.mapper;

import com.dpt.model.Technician;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TechnicianMapper {
    @Select("SELECT * FROM technician WHERE id = #{id}")
    Technician getTechnicianById(Integer id);

    @Select("SELECT * FROM technician WHERE 1=1 ")
    List<Technician> getTechnicians();

    @Insert("INSERT INTO technician(id, openId, realname, avatar, mobile, level,experience,source, adept,province, city,district, verify, shopName, referee, amount, createdTime, updatedTime,reason) " +
            "VALUES(#{id}, #{openId}, #{realname}, #{avatar}, #{mobile},#{level},#{experience},#{source}, #{adept},#{province}, #{city},#{district}, #{verify}, #{shopName}, #{referee}, #{amount}, #{createdTime}, #{updatedTime},#{reason})")
    int insert(Technician technician);

    @Update("UPDATE technician SET age=#{age}, name=#{name} WHERE id=#{id}")
    void update(Technician technician);

    @Delete("DELETE FROM technician WHERE id =#{id}")
    void delete(int id);

    @Select("SELECT * FROM `technician` WHERE verify = 1 ORDER BY updatedTime DESC LIMIT 10")
    List<Technician> getTechniciansOfRecommend();

    @Select("SELECT * FROM `technician` WHERE referee=#{referee} ORDER BY updatedTime DESC LIMIT 10")
    List<Technician> getTechniciansByInviteCodeRecommend(String referee);

    @Select("SELECT * FROM technician WHERE openId=#{openId}")
    Technician getVerify(String openId);

    @Update("UPDATE technician SET realname=#{realname}, " +
            "openId=#{openId}, " +
            "avatar=#{avatar}, " +
            "mobile=#{mobile}, " +
            "experience=#{experience}, " +
            "adept=#{adept}, " +
            "province=#{province}, " +
            "city=#{city}, " +
            "district=#{district}, " +
            "verify=#{verify}, " +
            "shopName=#{shopName}, " +
            "referee=#{referee}, " +
            "amount=#{amount}, " +
            "createdTime=#{createdTime}, " +
            "updatedTime=#{updatedTime} " +
            "WHERE id=#{id}")
    void updateTechnician(Technician technician);
}