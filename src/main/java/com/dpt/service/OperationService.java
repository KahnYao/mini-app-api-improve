package com.dpt.service;

import com.dpt.model.Operation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperationService {
    public int insertOperation(Operation operation) throws Exception;

    public int getOperationByType(@Param("type") Byte type) throws Exception;
}
