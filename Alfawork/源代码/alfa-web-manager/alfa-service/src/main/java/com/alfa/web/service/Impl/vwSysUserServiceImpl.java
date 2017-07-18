package com.alfa.web.service.Impl;

import com.alfa.web.dao.VwSmsStatusMapper;
import com.alfa.web.dao.vwSysUserMapper;
import com.alfa.web.pojo.vwSysUser;
import com.alfa.web.service.vwSysUserService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
@Service
public class vwSysUserServiceImpl implements vwSysUserService {

    private static final Logger logger = LoggerFactory.getLogger(vwSysUserServiceImpl.class);

    @Autowired
    private vwSysUserMapper vwSysUserMapper;

    @Override
    public List<vwSysUser> selectByParams(Criteria example) {
        return this.vwSysUserMapper.selectByParams(example);
    }
}
