package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysConfigMapper;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.SysconfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/4/26.
 */
public class SysconfigServiceImpl implements SysconfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Override
    public SysConfig selectByPrimaryKey(Long configSid) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Long configSid) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(SysConfig record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysConfig record) {
        return 0;
    }

    @Override
    public int insert(SysConfig record) {
        return 0;
    }

    @Override
    public int insertSelective(SysConfig record) {
        return 0;
    }
}
