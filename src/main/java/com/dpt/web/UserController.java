package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.User;
import com.dpt.mapper.UserMapper;
import com.dpt.service.LoginService;
import com.dpt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Api("UserController 接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    /**
     * 创建新用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "创建新用户", notes = "创建新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    })
    @RequestMapping(value = "/insertUserInfo", method = RequestMethod.PUT)
    public String insertUserInfo(@RequestBody User user) throws Exception {
        try {
            int status = this.userService.createUser(user);
            if(status == 1){
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, status));
            }else {
                return JSON.toJSONString(new ApiResult(ResultConstant.ERROR_CREATEUSER, new Object()));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 根据Name获取用户信息
     *
     * @param nickName
     * @return
     */
    @ApiOperation(value = "根据Name获取用户信息", notes = "根据Name获取用户信息")
    @RequestMapping(value = "/getUserByName/{nickName}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserByName(@PathVariable("nickName") String nickName) throws Exception {
        try {
            User user = userService.getUserByName(nickName);
            if (user == null) {
                return nickName + "不存在";
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, user));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 根据Id获取用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据Id获取用户信息", notes = "根据Id获取用户信息")
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserById(@PathVariable("id") Integer id) throws Exception {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return id + "不存在";
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, user));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 根据openId获取用户信息
     *
     * @param openId
     * @return
     */
    @ApiOperation(value = "根据openId获取用户信息", notes = "根据openId获取用户信息")
    @RequestMapping(value = "/getUserByOpenId/{openId}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserByOpenId(@PathVariable("openId") String openId) throws Exception {
        try {
            User user = userService.getUserByOpenId(openId);
            if (user == null) {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LOGINED_IN, new Object()));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, user));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public String getUsers() throws Exception {
        try {
            List<User> user = userService.getUsers();
            if (user == null) {
                return "不存在";
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, user));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 更新用户信息
     *
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    })
    @RequestMapping(value = "/updateUserInfo/{id}", method = RequestMethod.PUT)
    public String updateUserInfo(@PathVariable("id") Integer id, @RequestBody User user) throws Exception {
        try {
            User user1 = userService.getUserById(id);
            if (user1 != null) {
                if (!user.getNickName().isEmpty()) {
                    user1.setNickName(user.getNickName());
                }
                if (user.getOpenId() != null) {
                    user1.setOpenId(user.getOpenId());
                }
                if (user.getGender() != null) {
                    user1.setGender(user.getGender());
                }
                if (user.getCountry() != null) {
                    user1.setCountry(user.getCountry());
                }
                if (user.getProvince() != null) {
                    user1.setProvince(user.getProvince());
                }
                if (user.getAvatarUrl() != null) {
                    user1.setAvatarUrl(user.getAvatarUrl());
                }
                if (user.getCity() != null) {
                    user1.setCity(user.getCity());
                }
                if (user.getLanguage() != null) {
                    user1.setLanguage(user.getLanguage());
                }
                if (user.getSessionKey() != null) {
                    user1.setSessionKey(user.getSessionKey());
                }
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, this.userService.updateUser(user1)));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * @param code
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据Code获取openId", notes = "根据Code获取openId")
    @RequestMapping(value = "/getOpenIdByCode/{code}", method = RequestMethod.GET)
    @ResponseBody
    public String getOpenIdByCode(@PathVariable("code") String code) throws Exception {
        try {
            String string = loginService.getSessionKey(code);
            return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, string));
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }

    }

    /**
     * @param openId
     * @param session
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login/{openId}", method = RequestMethod.GET)
    @ResponseBody
    public String login(@PathVariable("openId") String openId, HttpSession session) throws Exception {
        try {
            User user = userService.getUserByOpenId(openId);
            if (user != null) {
                session.setAttribute("SESSION_KEY", user.getOpenId());
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, "登录成功。"));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LOGINED_IN, new Object()));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户登录状态", notes = "用户登录状态")
    @RequestMapping(value = "/hasLogin", method = RequestMethod.GET)
    @ResponseBody
    public boolean hasLogin(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("SESSION_KEY") != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return false;
        }
    }

    /**
     * 根据openId 更新用户余额
     * param openId
     * param bonus
     *
     */
    @ApiOperation(value = "更新用户余额", notes = "更新用户余额")
    @RequestMapping(value = "/updateUserBonus/{openId},{award}", method = RequestMethod.PUT)
    public String updateUserBonus(@PathVariable("openId") String openId,@PathVariable("award") double award) throws Exception {
        try {
            User user1 = userService.getUserByOpenId(openId);
            user1.setBonus(user1.getBonus()+award);
            int status = this.userService.updateUserBonus(user1);
            if(status == 1){
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, status));
            }else {
                return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
            }
        }catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 用户的收益总额
     * @param openId
     * return bonus
     */
    @ApiOperation(value = "根据openId用户的收益总额", notes = "根据openId用户的收益总额")
    @RequestMapping(value = "/getUserBonus/{openId}", method = RequestMethod.GET)
    @ResponseBody
    public String getUserBonus(@PathVariable("openId")String openId) throws Exception{
        try {
            List<User> list = userService.getUserBonus(openId);
            if (list.size() >=0){
                double bonus = list.get(0).getBonus();
                if (bonus != 0){
                    return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, bonus));
                } else {
                    return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, 0.0));
                }

            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            }
        } catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

}
