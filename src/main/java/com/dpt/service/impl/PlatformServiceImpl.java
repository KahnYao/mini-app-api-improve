package com.dpt.service.impl;

import com.dpt.mapper.PlatformMapper;
import com.dpt.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("platformService")
public class PlatformServiceImpl implements PlatformService {

    private final static Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);

    @Autowired
    private PlatformMapper platformMapper;

}
