package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.Technician;
import com.dpt.service.TechnicianService;
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
import java.util.Map;

@Api("TechnicianController 接口")
@RestController
@RequestMapping("/technician")
public class TechnicianController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TechnicianService technicianService;

    /**
     * 创建新用户
     *
     * @param technician
     * @return
     */
    @ApiOperation(value = "创建新技师", notes = "创建新技师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "technician", value = "用户实体", required = true, dataType = "Technician")
    })
    @RequestMapping(value = "/insertTechnician", method = RequestMethod.PUT)
    public String insertUserInfo(@RequestBody Technician technician) throws Exception {
        return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, this.technicianService.insertTechnician(technician)));
    }

    /**
     * 根据Id获取技师信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据Id获取用户信息", notes = "根据Id获取用户信息")
    @RequestMapping(value = "/getTechnicianById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getTechnicianById(@PathVariable("id") Integer id) throws Exception {
        try {
            Technician technician = technicianService.getTechnicianById(id);
            return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, technician));
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 用户已推荐技师列表
     *
     * @return list
     */
    @ApiOperation(value = "通过inviteCode查询推荐技师列表",notes = "通过inviteCode查询推荐技师列表")
    @RequestMapping(value = "/getTechniciansByInviteCodeRecommend/{referee}",method = RequestMethod.GET)
    @ResponseBody
    public String getTechniciansByInviteCodeRecommend(@PathVariable("referee")String referee) throws Exception{
        try {
            List<Technician> technicianList = this.technicianService.getTechniciansByInviteCodeRecommend(referee);
            if(technicianList == null){
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, technicianList));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 平台所有推荐技师列表
     *
     * @return list
     */
    @ApiOperation(value = "通过inviteCode查询推荐技师列表",notes = "通过inviteCode查询推荐技师列表")
    @RequestMapping(value = "/getTechniciansOfRecommend",method = RequestMethod.GET)
    @ResponseBody
    public String getTechniciansOfRecommend() throws Exception{
        try {
            List<Technician> technicianList = this.technicianService.getTechniciansOfRecommend();
            if(technicianList == null){
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, technicianList));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 认证状态
     * 根据openId
     */
    @ApiOperation(value = "根据openId查询认证状态",notes = "根据openId查询认证状态")
    @RequestMapping(value = "/getVerify/{openId}",method = RequestMethod.GET)
    @ResponseBody
    public String getVerify(@PathVariable("openId")String openId) throws Exception{
        try {
            Map<String,Object> map = new HashMap<>();
            Technician technician = this.technicianService.getVerify(openId);
            Integer verify = 0;
            if (technician == null){
                verify = 3;
            } else {
                verify = technician.getVerify();
            }
            map.put("status",verify);
            map.put("technician",technician);
            return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, map));
        }catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }


    /**
     * 更新技师信息
     *
     * @param id
     * @param technician
     * @return
     */
    @ApiOperation(value = "更新技师信息", notes = "更新技师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "technician", value = "技师实体", required = true, dataType = "Technician")
    })
    @RequestMapping(value = "/updateTechnicianInfo/{id}", method = RequestMethod.PUT)
    public String updateTechnicianInfo(@PathVariable("id") Integer id, @RequestBody Technician technician) throws Exception {
        try {
            Technician tech = this.technicianService.getTechnicianById(id);
            if (tech != null){
                if (technician.getOpenId() != null){
                    tech.setOpenId(technician.getOpenId());
                }
                if (technician.getRealname() != null){
                    tech.setRealname(technician.getRealname());
                }
                if (technician.getAvatar() != null){
                    tech.setAvatar(technician.getAvatar());
                }
                if (technician.getMobile() != null){
                    tech.setMobile(technician.getMobile());
                }
                if (technician.getExperience() != null){
                    tech.setExperience(technician.getExperience());
                }
                if (technician.getAdept() != null){
                    tech.setAdept(technician.getAdept());
                }
                if (technician.getProvince() != null){
                    tech.setProvince(technician.getProvince());
                }
                if (technician.getCity() != null){
                    tech.setCity(technician.getCity());
                }
                if (technician.getDistrict() != null){
                    tech.setDistrict(technician.getDistrict());
                }
                tech.setVerify(0);
               /* if (technician.getVerify() != null){
                    tech.setVerify(0);
                }*/
                if (technician.getShopName() != null){
                    tech.setShopName(technician.getShopName());
                }
                if (technician.getReferee() != null){
                    tech.setReferee(technician.getReferee());
                }
                if (technician.getAmount() != null){
                    tech.setAmount(technician.getAmount());
                }
                Long seconds = System.currentTimeMillis() / 1000;
                tech.setUpdatedTime(seconds);
                this.technicianService.updateTechnician(tech);
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, 1));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
            }
        }catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

}
