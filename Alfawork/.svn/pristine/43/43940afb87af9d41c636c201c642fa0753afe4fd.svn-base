package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysRoleMapper;
import com.alfa.web.pojo.SysRole;
import com.alfa.web.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Override
    public int insertRole(SysRole role) {
        int count=this.sysRoleMapper.insertRole(role);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public List<SysRole> findRole() {
        List<SysRole> list= this.sysRoleMapper.findRole();
        logger.debug("count:{}",list.size());
        return list;
    }


}
