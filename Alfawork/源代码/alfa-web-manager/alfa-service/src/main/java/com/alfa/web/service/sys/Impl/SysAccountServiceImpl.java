package com.alfa.web.service.sys.Impl;

import com.alfa.web.dao.sys.SysAccountMapper;
import com.alfa.web.service.sys.SysAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class SysAccountServiceImpl implements SysAccountService{

    @Autowired
    private SysAccountMapper sysAccountMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysAccountServiceImpl.class);
}
