package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysUsersMapper;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/26.
 */
@Service
public class SysUsersServiceImpl implements SysUsersService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysUsersServiceImpl.class);

    @Override
    public int countByParams(Criteria example) {
        return 0;
    }

    @Override
    public int insert(SysUsers record) {
        return this.sysUsersMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUsers record) {
        return this.sysUsersMapper.insertSelective(record);
    }
}
