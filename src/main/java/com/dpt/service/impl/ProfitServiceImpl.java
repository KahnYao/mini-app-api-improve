package com.dpt.service.impl;

import com.dpt.mapper.ProfitMapper;
import com.dpt.model.Profit;
import com.dpt.service.ProfitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("profitService")
public class ProfitServiceImpl implements ProfitService {

    private final static Logger logger = LoggerFactory.getLogger(ProfitServiceImpl.class);

    @Autowired
    private ProfitMapper profitMapper;

    @Override
    public int insertProfit(Profit profit) throws Exception {
        Profit p = new Profit();
        if(profit.getOpenId() != null){
            p.setOpenId(profit.getOpenId());
        }

        if(profit.getOperateId() != null){
            p.setOperateId(profit.getOperateId());
        }
        if(profit.getTitle() != null){
            p.setTitle(profit.getTitle());
        }
        if(profit.getStatus() != null){
            p.setStatus(profit.getStatus());
        }
        if(profit.getAmount() != null){
            p.setAmount(profit.getAmount());
        }
        if(profit.getRemark() != null){
            p.setRemark(profit.getRemark());
        }
        Long seconds = System.currentTimeMillis() / 1000;
        p.setUpdatedTime(seconds);
        p.setCreatedTime(seconds);

        return this.profitMapper.insert(p);
    }

    @Override
    public List<Profit> countAmountNum(String openId) throws Exception {
        List<Profit> count = this.profitMapper.countAmountNum(openId);
        return count;
    }

    @Override
    public List<Profit> selectAmountNumByMonth(String openId, long createTime, long endTime) throws Exception {
        List<Profit> profitList = this.profitMapper.selectAmountNumByMonth(openId,createTime,endTime);
        return profitList;
    }

    @Override
    public List<Profit> selectProfitListMonth(String openId, long createTime, long endTime) throws Exception {
        List<Profit> profitList = this.profitMapper.selectProfitListMonth(openId,createTime,endTime);
        return profitList;
    }

    @Override
    public List<Profit> selectProfitList(String openId) throws Exception {
        List<Profit> profitList = this.profitMapper.selectProfitList(openId);
        return profitList;
    }

}
