package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.Shop;
import com.dpt.model.Technician;
import com.dpt.service.IndexService;
import com.dpt.service.ShopService;
import com.dpt.service.TechnicianService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api("IndexController 接口")
@RestController
@RequestMapping("/")
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private TechnicianService technicianService;

    /**
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "首页接口", notes = "首页接口")
    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    @ResponseBody
    public String getIndex() throws Exception {
        try {
            HashMap hashMap = indexService.getIndex();
            return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, hashMap));
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * @param type
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "已推荐接口", notes = "已推荐接口")
    @RequestMapping(value = "/getRecommendByType/{type},{referee}", method = RequestMethod.GET)
    @ResponseBody
    public String getRecommendByType(@PathVariable("type") Integer type,@PathVariable("referee")String referee) throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            if (type == 1) {
                List<Shop> shops = shopService.getShopsByOpenIdRecommend(referee);
                hashMap.put("shops", shops);
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, hashMap));
            } else {
                List<Technician> technicians = technicianService.getTechniciansByInviteCodeRecommend(referee);
                hashMap.put("technicians", technicians);
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, hashMap));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

}
