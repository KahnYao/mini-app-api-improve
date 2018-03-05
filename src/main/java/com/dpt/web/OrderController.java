package com.dpt.web;


import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.model.Order;
import com.dpt.service.OperationService;
import com.dpt.service.OrderService;
import com.dpt.service.ProfitService;
import com.dpt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("OrderController 接口")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private ProfitService profitService;

    @Autowired
    private UserService userService;


    /**
     * 生成订单
     *
     * @param order
     * @return
     */
    @ApiOperation(value = "生成订单", notes = "生成订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order", value = "操作记录实体", required = true, dataType = "Order")
    })
    @RequestMapping(value = "/insertOrder", method = RequestMethod.PUT)
    public String insertOrder(@RequestBody Order order) throws Exception {
        return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, this.orderService.insertOrder(order)));
    }

    /**
     * 通过openId 查询出提现金额
     * @param openId
     * return list
     */
    @ApiOperation(value = "通过openId 查询出提现金额", notes = "通过openId 查询出提现金额")
    @RequestMapping(value = "/selectOrderTotalCount/{openId}",method = RequestMethod.GET)
    @ResponseBody
    public String selectOrderTotalCount(@PathVariable("openId") String openId) throws Exception{
        try {
            List<Order> orderList = this.orderService.selectOrderTotalCount(openId);
            if (orderList.size()>0) {
                double total = 0.0;
                for (int i = 0;i<orderList.size();i++ ){
                    total += orderList.get(i).getTotal();
                }
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, total));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            }
        }catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 通过openId 查询出提现列表
     * @param openId
     * return list
     */
    @ApiOperation(value = "通过openId 查询出提现列表", notes = "通过openId 查询出提现列表")
    @RequestMapping(value = "/selectOrderList/{openId}",method = RequestMethod.GET)
    @ResponseBody
    public String selectOrderList(@PathVariable("openId") String openId) throws Exception{
        try {
            List<Order> orderList = this.orderService.selectOrderList(openId);
            if (orderList.size()>0) {
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, orderList));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            }
        }catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

}
