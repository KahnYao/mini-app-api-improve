package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.RepairShop;
import com.dpt.model.Technician;
import com.dpt.model.User;
import com.dpt.service.RepairShopService;
import com.dpt.service.TechnicianService;
import com.dpt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api("RepairShopController 接口")
@RestController
@RequestMapping("/repairShop")
public class RepairShopController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RepairShopService repairShopService;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private UserService userService;

    /**
     * 新增推荐门店
     *
     * @param repairShop
     * @return
     */
    @ApiOperation(value = "推荐门店", notes = "推荐门店")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repairShop", value = "门店实体", required = true, dataType = "RepairShop")
    })
    @RequestMapping(value = "/insertShop", method = RequestMethod.PUT)
    public String insertUserInfo(@RequestBody RepairShop repairShop) throws Exception {
        try {
            int status = this.repairShopService.insertShop(repairShop);
            if(status==1){
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS,status ));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.ERROR_CREATESHOP, new Object()));
            }
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
                List<RepairShop> shops = repairShopService.getShopsByOpenIdRecommend(referee);
                if (shops.size() >0){
                    for (int i = 0;i<shops.size();i++) {
                        String[] image = shops.get(i).getMendianHead() != null ?shops.get(i).getMendianHead().toString().split(","):null;
                        if (image != null && !"".equals(image)){
                            for(int k=0;k<image.length;k++){
                                shops.get(i).setMendianHead(image[k]);
                            }
                        }
                    }
                }
                hashMap.put("shops", shops);
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, hashMap));
            } else {
                List<Technician> technicians = technicianService.getTechniciansByInviteCodeRecommend(referee);
                if (technicians.size() >0){
                    for (int i = 0;i<technicians.size();i++) {
                        String openId = technicians.get(i).getOpenId();
                        User user1 = userService.getUserByOpenId(openId);
                       if (user1 != null){
                           String[] images = user1.getAvatarUrl() != null ? user1.getAvatarUrl().split(",") : null;
                           if (images != null && !"".equals(images)){
                               for (int k = 0;k<images.length;k++){
                                   technicians.get(i).setAvatar(images[k]);
                               }
                           }
                       }
                    }
                }
                hashMap.put("technicians", technicians);
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, hashMap));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }
}
