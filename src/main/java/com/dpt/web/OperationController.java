package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.Operation;
import com.dpt.service.OperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Api("OperationController 接口")
@RestController
@RequestMapping("/operation")
public class OperationController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OperationService operationService;

    /**
     * 生成操作记录
     *
     * @param operation
     * @return
     */
    @ApiOperation(value = "生成操作记录", notes = "生成操作记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "operation", value = "操作记录实体", required = true, dataType = "Operation")
    })
    @RequestMapping(value = "/insertOperation", method = RequestMethod.PUT)
    public String insertOperation(@RequestBody Operation operation) throws Exception {
        return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, this.operationService.insertOperation(operation)));
    }

    /**
     * 通过type查询操作记录
     * @param type
     * return List
     */
    @ApiOperation(value = "通过type查询操作记录",notes = "通过type查询操作记录")
    @RequestMapping(value = "/getOperationListByType/{type}",method = RequestMethod.GET)
    @ResponseBody
    public String getOperationListByType(@PathVariable("type")Byte type) throws Exception{
        try {
            int count = operationService.getOperationByType(type);
            if(count == 0){
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, count));
            }
        }catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }

    }


}
