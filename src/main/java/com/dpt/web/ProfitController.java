package com.dpt.web;

import com.alibaba.fastjson.JSON;
import com.dpt.common.ApiResult;
import com.dpt.common.ResultConstant;
import com.dpt.mapper.TechnicianMapper;
import com.dpt.model.Profit;
import com.dpt.model.Shop;
import com.dpt.model.Technician;
import com.dpt.service.ProfitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Api("DepositController 接口")
@RestController
@RequestMapping("/deposit")
public class ProfitController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProfitService profitService;

    /**
     * 生成收益
     *
     * @param profit
     * @return
     */
    @ApiOperation(value = "生成收益", notes = "生成收益")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "profit", value = "收益实体", required = true, dataType = "Profit")
    })
    @RequestMapping(value = "/insertProfit", method = RequestMethod.PUT)
    public String insertProfit(@RequestBody Profit profit) throws Exception {
        return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, this.profitService.insertProfit(profit)));
    }

    /**
     * 通过type,openId查询收益总额  20180125
     * @param openId
     * return List
     */
    @ApiOperation(value = "通过type,openId查询收益总额",notes = "通过type,openId查询收益总额")
    @RequestMapping(value = "/countAmountNum/{openId}",method = RequestMethod.GET)
    @ResponseBody
    public String countAmountNum(@PathVariable("openId")String openId) throws Exception{
        try {
            List<Profit> profitList = profitService.countAmountNum(openId);
            if(profitList.size() >= 0){
                double amount = 0.0;
                for (int i=0;i<profitList.size();i++){
                    amount += profitList.get(i).getAmount();
                }
               /* List<Profit> profits = profitService.countAmountNum(openId,2);
                double total = 0.0;
                if (profits.size()>0) {
                    for (int j = 0; j < profits.size(); j++) {
                        total += profits.get(j).getAmount();
                    }
                }
                double money = account+total;*/
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, amount));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, 0.0));
            }
        }catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }

    }

    /**
     * 查询本月收益余额 20180125
     * @param openId type createTime
     * Result
     */
    @ApiOperation(value = "查询本月收益余额",notes = "查询本月收益余额")
    @RequestMapping(value = "/selectAmountNumByMonth/{openId}",method = RequestMethod.GET)
    @ResponseBody
   public String selectAmountNumByMonth(@PathVariable("openId")String openId) throws Exception{
        try {
            //获取本月第一天
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH,0);
            c.set(Calendar.DAY_OF_MONTH,1);
            String first = format.format(c.getTime());
            long createTime = format.parse(first).getTime()/1000;
            //获取本月最后一天
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.DAY_OF_MONTH,calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
            String last1 = format.format(calendar1.getTime());
            long endTime = format.parse(last1).getTime()/1000;

           List<Profit> profitList = profitService.selectAmountNumByMonth(openId,createTime, endTime);
           if(profitList.size()>0){
               double amount = 0.0;
               for (int i = 0;i<profitList.size();i++){
                   amount += profitList.get(i).getAmount();
               }
              return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, amount));
           } else {
               return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, 0.0));
           }
        } catch (Exception e){
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }

    /**
     * 查询收益列表 20180125
     * @param openId
     * Result  list
     */
    @ApiOperation(value = "查询收益列表",notes = "查询收益列表")
    @RequestMapping(value = "/selectProfitList/{openId}",method = RequestMethod.GET)
    @ResponseBody
    public String selectProfitList(@PathVariable("openId")String openId) throws Exception{
        try {
            //List<Profit> profitList = this.profitService.selectProfitList(openId);

            //获取当前月份
            Calendar calendar=Calendar.getInstance();
            //获得当前时间的月份，月份从0开始所以结果要加1
            int month=calendar.get(Calendar.MONTH)+1;
            List<Object> list = new ArrayList<>();
            String months = null;
            List<Profit> profitList = new ArrayList<>();

            for (int i = 0;i < month;i++) {
                //获取本月第一天
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM");
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH,-i);
                c.set(Calendar.DAY_OF_MONTH,1);
                String first = format.format(c.getTime());
                months = formats.format(c.getTime());
                long createTime = format.parse(first).getTime()/1000;
                //获取本月最后一天
                Calendar calendar1 = Calendar.getInstance();
                calendar1.add(Calendar.MONTH,-i);
                calendar1.set(Calendar.DAY_OF_MONTH,calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
                String last1 = format.format(calendar1.getTime());
                long endTime = format.parse(last1).getTime()/1000;
                profitList = this.profitService.selectProfitListMonth(openId,createTime,endTime);
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                stringObjectHashMap.put("months",months);
                stringObjectHashMap.put("list",profitList);
                list.add(stringObjectHashMap);
            }
            if(list.size()>0){
                return JSON.toJSONString(new ApiResult(ResultConstant.SUCCESS, list));
            } else {
                return JSON.toJSONString(new ApiResult(ResultConstant.NO_LIST, new Object()));
            }
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return JSON.toJSONString(new ApiResult(ResultConstant.ERROR, new Object()));
        }
    }



}
