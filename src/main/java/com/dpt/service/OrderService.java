package com.dpt.service;

import com.dpt.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    public int insertOrder(Order order) throws Exception;

    public List<Order> selectOrderTotalCount(String openId) throws Exception;

    public List<Order> selectOrderTotalMonth(String openId,int createTime) throws Exception;

    public List<Order> selectOrderList(String openId) throws Exception;

}
