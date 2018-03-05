package com.dpt.web;

import com.dpt.util.HttpsClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("HelloController 接口")
@RestController
public class HelloController {

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx432cb0e905847d2b&secret=6acb6a4faff701cc6030bb10b5d80f23&js_code=061CSbTw0YK99i1ld5Uw08NeTw0CSbT2&grant_type=authorization_code";
        String string = HttpsClient.getHttp(url);
        return string;
    }

    @RequestMapping("/hi")
    public String error() throws Exception {
        throw new Exception("发生错误");
    }

}
