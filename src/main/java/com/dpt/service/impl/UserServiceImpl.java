package com.dpt.service.impl;

import com.dpt.mapper.UserMapper;
import com.dpt.model.User;
import com.dpt.service.UserService;
import com.dpt.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public User getUserById(int id) throws Exception {
        return this.userMapper.getUserById(id);
    }

    /**
     * @param name
     * @return
     * @throws Exception
     */
    public User getUserByName(String name) throws Exception {
        return this.userMapper.getUserByName(name);
    }

    /**
     * @param referee
     * @return
     * @throws Exception
     */
    public User getUserByReferee(String referee) throws Exception {
        return this.userMapper.getUserByReferee(referee);
    }

    /**
     * @param openId
     * @return
     * @throws Exception
     */
    public User getUserByOpenId(String openId) throws Exception {
        return this.userMapper.getUserByOpenId(openId);
    }

    /**
     * @param user
     * @return
     * @throws Exception
     */
    public int createUser(User user) throws Exception {
        try {
            User user1 = new User();
            if (user.getOpenId() != null) {
                user1.setOpenId(user.getOpenId());
            } else {
                return 0;
            }
            user1.setNickName(user.getNickName());
            user1.setGender(user.getGender());
            user1.setSessionKey(user.getSessionKey());
            user1.setLanguage(user.getLanguage());
            user1.setCity(user.getCity());
            user1.setAvatarUrl(user.getAvatarUrl());
            user1.setProvince(user.getProvince());
            user1.setCountry(user.getCountry());
            Boolean aBoolean  = true;
            String inviteCode = null;
            while (aBoolean) {
                //inviteCode = "HY" + CommonUtil.inviteCode(6);
                inviteCode = CommonUtil.inviteCode(6);
                User user2 = userMapper.getUserByInviteCode(inviteCode);
                if(user2 == null)
                {
                    aBoolean = false;
                }
            }
            user1.setInviteCode(inviteCode);
            if (user.getBonus()!= null) {
                user1.setBonus(user.getBonus());
            } else {
                user1.setBonus(0.0);
            }
            user1.setReferee(user.getReferee());
            Long seconds = System.currentTimeMillis() / 1000;
            user1.setUpdatedTime(seconds);
            user1.setCreatedTime(seconds);
            this.userMapper.insert(user1);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * @param user
     * @throws Exception
     */
    public int updateUser(User user) throws Exception {
        try {
            this.userMapper.update(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------------------ERROR");
            return 0;
        }
    }

    /**
     * @param user
     * @throws Exception
     */
    @Override
    public int updateUserBonus(User user) throws Exception {
        try {
            this.userMapper.updateBonus(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------------------ERROR");
            return 0;
        }
    }


    /**
     * @return
     * @throws Exception
     */
    public List<User> getUsers() throws Exception {
        List<User> users = this.userMapper.getUsers();
        return users;
    }

    /**
     * @param inviteCode
     * @return
     * @throws Exception
     */
    public User getUserByInviteCode(String inviteCode) throws Exception {
        return this.userMapper.getUserByInviteCode(inviteCode);
    }

    @Override
    public List<User> getUserBonus(String openId) throws Exception {
        List<User> list = this.userMapper.getUserBonus(openId);
        return list;
    }


}
