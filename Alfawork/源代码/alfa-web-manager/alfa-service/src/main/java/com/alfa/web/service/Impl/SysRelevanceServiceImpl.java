package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysRelevanceMapper;
import com.alfa.web.service.SysRelevanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/12.
 */
@Service
public class SysRelevanceServiceImpl implements SysRelevanceService {

    @Autowired
    private SysRelevanceMapper sysRelevanceMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysRelevanceServiceImpl.class);
}
