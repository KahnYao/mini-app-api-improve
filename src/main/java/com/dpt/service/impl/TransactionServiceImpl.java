package com.dpt.service.impl;

import com.dpt.mapper.TransactionMapper;
import com.dpt.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    private final static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionMapper transcationMapper;

}
