package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.Region;
import com.dpt.model.Shop;
import com.dpt.service.RegionService;
import com.dpt.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("ShopController 接口")
@RestController
@RequestMapping("/shop")
public class ShopController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ShopService shopService;




    /**
     * 新增推荐门店
     *
     * @param shop
     * @return
     */
    @ApiOperation(value = "推荐门店", notes = "推荐门店")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shop", value = "门店实体", required = true, dataType = "Shop")
    })
    @RequestMapping(value = "/insertShop", method = RequestMethod.PUT)
    public String insertUserInfo(@RequestBody Shop shop) throws Exception {
        return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, this.shopService.insertShop(shop)));
    }

    /**
     *
     * 平台所有推荐门店列表
     * @return list
     */
    @ApiOperation(value = "平台所有推荐门店列表",notes = "平台所有推荐门店列表")
    @RequestMapping(value = "/getShopsOfRecommendList",method = RequestMethod.GET)
    @ResponseBody
    public String getShopsOfRecommendList() throws Exception{
        try {
            List<Shop> shopList = this.shopService.getShopsOfRecommend();
            if(shopList == null){
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, shopList));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }


    /**
     * 用户已推荐门店列表
     *
     * @return list
     */
    @ApiOperation(value = "用户已推荐门店列表",notes = "通过openId查询用户已推荐门店列表")
    @RequestMapping(value = "/getShopsByOpenIdRecommend/{openId}",method = RequestMethod.GET)
    @ResponseBody
    public String getShopsByOpenIdRecommend(@PathVariable("openId")String openId) throws Exception{
        try {
            List<Shop> shopList = this.shopService.getShopsByOpenIdRecommend(openId);
            if(shopList == null){
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, shopList));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

}
