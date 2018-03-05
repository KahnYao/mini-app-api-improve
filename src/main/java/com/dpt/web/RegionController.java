package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.Region;
import com.dpt.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("RegionController 接口")
@RestController
@RequestMapping("/region")
public class RegionController {
    private final static Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private RegionService regionService;

    /**
     * 省市区转换id
     *
     */
    @ApiOperation(value = "根据省市区名字获取省市区id", notes = "根据省市区名字获取省市区id")
    @RequestMapping(value = "/getRegionIdByName/{provincename},{cityname},{districtname}", method = RequestMethod.GET)
    @ResponseBody
    public String getProvinceIdByName(@PathVariable("provincename") String provincename,@PathVariable("cityname") String cityname,@PathVariable("districtname") String districtname) throws Exception {
        try {
            List<Region> regions = regionService.getIdByProvinceName(provincename);
            if(regions.size()> 0){
                Integer provinceId = regions.get(0).getId();
                String province = regions.get(0).getId().toString();
                List<Region> regionList = regionService.getCityIdByNameAndParentId(cityname,provinceId);
                Integer cityId = regionList.get(0).getId();
                String city = regionList.get(0).getId().toString();
                List<Region> districtList = regionService.getCityIdByNameAndParentId(districtname,cityId);
                String district = districtList.get(0).getId().toString();
                String regionId = province + "," + city +","+ district;
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, regionId));
            }else {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            }

        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }
}
