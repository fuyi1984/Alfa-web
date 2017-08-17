package com.alfa.web.service.Impl;

import com.alfa.web.dao.sys.SysModuleElementMapper;
import com.alfa.web.service.SysModuleElementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class SysModuleElementServiceImpl implements SysModuleElementService{
    @Autowired
    private SysModuleElementMapper sysModuleElementMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysModuleElementServiceImpl.class);
}
