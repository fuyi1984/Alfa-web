package com.alfa.web.service.log.Impl;

import com.alfa.web.dao.log.LogForPayMapper;
import com.alfa.web.service.log.LogForPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class LogForPayServiceImpl implements LogForPayService{
    @Autowired
    private LogForPayMapper logForPayMapper;

    private static final Logger logger = LoggerFactory.getLogger(LogForPayServiceImpl.class);
}
