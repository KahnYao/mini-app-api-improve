package com.dpt.mapper;

import com.dpt.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE nickName = #{nickName}")
    User getUserByName(@Param("nickName") String name);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(Integer id);

    @Select("SELECT * FROM user WHERE openId = #{openId}")
    User getUserByOpenId(String openId);

    @Select("SELECT * FROM user WHERE  referee = #{referee}")
    User getUserByReferee(String referee);

    @Select("SELECT * FROM user WHERE inviteCode = #{inviteCode}")
    User getUserByInviteCode(String inviteCode);

    @Select("SELECT * FROM user WHERE 1=1 ")
    List<User> getUsers();

    @Insert("INSERT INTO user(id,openId,nickName,gender,language,country,province,city,avatarUrl,unionId,sessionKey, inviteCode, bonus, referee, createdTime,updatedTime) " +
            "VALUES(#{id}, #{openId}, #{nickName},#{gender}, #{language}, #{country},#{province}, #{city}, #{avatarUrl},#{unionId}, #{sessionKey}, #{inviteCode}, #{bonus}, #{referee}, #{createdTime}, #{updatedTime})")
    int insert(User user);

    @Update("UPDATE user SET avatarUrl=#{avatarUrl}, " +
            "openId=#{openId}, " +
            "nickName=#{nickName}, " +
            "gender=#{gender}, " +
            "language=#{language}, " +
            "country=#{country}, " +
            "province=#{province}, " +
            "city=#{city}, " +
            "unionId=#{unionId}, " +
            "sessionKey=#{sessionKey}, " +
            "inviteCode=#{inviteCode}, " +
            "bonus=#{bonus}, " +
            "referee=#{referee}, " +
            "createdTime=#{createdTime}, " +
            "updatedTime=#{updatedTime} " +
            "WHERE id=#{id}")
    void update(User user);

    @Update("UPDATE user SET bonus=#{bonus} " +
            "WHERE openId=#{openId}"
            )
    void updateBonus(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(int id);

    @Results({@Result(property = "name", column = "name"), @Result(property = "age", column = "age")})
    @Select("SELECT name,age FROM user WHERE 1=1")
    List<User> queryById();

    @Select("SELECT * FROM user where openId = #{openId}")
    List<User> getUserBonus(String openId);

}