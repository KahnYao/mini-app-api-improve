package com.dpt.service.impl;

import com.dpt.service.LoginService;
import com.dpt.service.UserService;
import com.dpt.util.HttpsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private final static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserService userService;

    /**
     *
     * @param code
     * @return
     */
    public String getSessionKey(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=wx432cb0e905847d2b&secret=6acb6a4faff701cc6030bb10b5d80f23&js_code=" + code + "&grant_type=authorization_code";
        String string = HttpsClient.getHttp(requestUrl);
        return string;
    }

}
