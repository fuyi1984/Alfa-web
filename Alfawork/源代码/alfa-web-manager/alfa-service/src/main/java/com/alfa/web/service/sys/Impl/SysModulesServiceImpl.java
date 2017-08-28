package com.alfa.web.service.sys.Impl;

import com.alfa.web.dao.sys.SysModulesMapper;
import com.alfa.web.service.sys.SysModulesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class SysModulesServiceImpl implements SysModulesService {
    @Autowired
    private SysModulesMapper sysModulesMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysModulesServiceImpl.class);
}
