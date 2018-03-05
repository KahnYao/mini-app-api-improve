package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.mapper.TechnicianMapper;
import com.dpt.model.Technician;
import com.dpt.service.PlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("PlatformController 接口")
@RestController
@RequestMapping("/platform")
public class PlatformController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PlatformService platformService;

}
