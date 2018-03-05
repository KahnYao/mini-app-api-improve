package com.dpt.service;

import com.dpt.model.User;

import java.util.List;

public interface UserService {

    public User getUserById(int id) throws Exception;

    public User getUserByName(String name) throws Exception;

    public User getUserByOpenId(String openId) throws Exception;

    public int createUser(User user) throws Exception;

    public int updateUser(User user) throws Exception;

    public int updateUserBonus(User user) throws Exception;

    public List<User> getUsers() throws Exception;

    public User getUserByInviteCode(String inviteCode) throws Exception;

    public List<User> getUserBonus(String openId)throws Exception;

}
