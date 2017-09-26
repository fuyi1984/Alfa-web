package com.alfa.web.service.sys.Impl;

import com.alfa.web.dao.sys.SysOrgMapper;
import com.alfa.web.service.sys.SysOrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class SysOrgServiceImpl implements SysOrgService {
    @Autowired
    private SysOrgMapper sysOrgMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysOrgServiceImpl.class);
}
