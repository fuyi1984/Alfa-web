package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysOrgMapper;
import com.alfa.web.service.SysOrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/15.
 */
public class SysOrgServiceImpl implements SysOrgService {
    @Autowired
    private SysOrgMapper sysOrgMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysOrgServiceImpl.class);
}
