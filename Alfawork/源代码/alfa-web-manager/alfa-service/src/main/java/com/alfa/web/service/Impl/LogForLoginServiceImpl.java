package com.alfa.web.service.Impl;

import com.alfa.web.dao.LogForLoginMapper;
import com.alfa.web.dao.SysAccountMapper;
import com.alfa.web.service.LogForLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class LogForLoginServiceImpl implements LogForLoginService {
    @Autowired
    private LogForLoginMapper logForLoginMapper;

    private static final Logger logger = LoggerFactory.getLogger(LogForLoginServiceImpl.class);
}
