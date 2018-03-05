package com.dpt.service.impl;

import com.dpt.mapper.OperationMapper;
import com.dpt.model.Operation;
import com.dpt.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("operationService")
public class OperationServiceImpl implements OperationService {

    private final static Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public int insertOperation(Operation operation) throws Exception {
        Operation op = new Operation();
        if(operation.getOperator() != null){
            op.setOperator(operation.getOperator());
        }
        if(operation.getType() != null){
            op.setType(operation.getType());
        }
        if(operation.getTargetId() != null){
            op.setTargetId(operation.getTargetId());
        }
        if(operation.getAmount() != null){
            op.setAmount(operation.getAmount());
        }
        if(operation.getRemark() != null){
            op.setRemark(operation.getRemark());
        }
        Long seconds = System.currentTimeMillis() / 1000;
        op.setUpdatedTime(seconds);
        op.setCreatedTime(seconds);
        int status = this.operationMapper.insert(op);
        return status;
    }

    @Override
    public int getOperationByType(Byte type) throws Exception {
        int count = operationMapper.getOperationByType(type);
        return count;
    }


}
