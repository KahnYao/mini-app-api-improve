package com.dpt.mapper;

import com.dpt.model.Transaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);
}