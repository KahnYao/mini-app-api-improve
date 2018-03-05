package com.dpt.service;

import com.dpt.model.Profit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfitService {

    public int insertProfit(Profit profit) throws Exception;

    public List<Profit> countAmountNum(@Param("openId") String openId) throws Exception;

    public List<Profit> selectAmountNumByMonth(@Param("openId") String openId,@Param("createTime")long createTime, @Param("endTime")long endTime) throws Exception;

    public List<Profit> selectProfitListMonth(@Param("openId") String openId,@Param("createTime")long createTime, @Param("endTime")long endTime) throws Exception;

    List<Profit> selectProfitList(String openId) throws Exception;


}
