package com.dpt.service.impl;

import com.dpt.mapper.OrderMapper;
import com.dpt.model.Order;
import com.dpt.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insertOrder(Order order) throws Exception {
        try {
            Order order1 = new Order();
            if (order.getOpenId() != null){
                order1.setOpenId(order.getOpenId());
            }
            if (order.getPrepayId() != null){
                order1.setPrepayId(order.getPrepayId());
            }
            if (order.getTotal() != null){
                order1.setTotal(order.getTotal());
            }
            order1.setPayStatus(0);
            order1.setVerifyStatus(0);
            Long seconds = System.currentTimeMillis() / 1000;
            order1.setCreatedTime(seconds);
            order1.setUpdatedTime(seconds);
            order1.setPrepayEndTime(seconds);
            order1.setPayEndTime(seconds);
            this.orderMapper.insert(order1);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("----------------------------------ERROR");
            return 0;
        }

    }

    @Override
    public List<Order> selectOrderTotalCount(String openId) throws Exception {
        List<Order> orderList = this.orderMapper.selectOrderTotalCount(openId);
        return orderList;
    }

    @Override
    public List<Order> selectOrderTotalMonth(String openId, int createTime) throws Exception {
        List<Order> orderList = this.orderMapper.selectOrderTotalMonth(openId,createTime);
        return orderList;
    }

    @Override
    public List<Order> selectOrderList(String openId) throws Exception {
        List<Order> orderList = this.orderMapper.selectOrderList(openId);
        return orderList;
    }
}
