package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysRoleMapper;
import com.alfa.web.pojo.SysRole;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
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
    public int countByParams(Criteria example) {
        int count=this.sysRoleMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public SysRole selectByPrimaryKey(Long roleSid) {
        return this.sysRoleMapper.selectByPrimaryKey(roleSid);
    }

    @Override
    public List<SysRole> selectByParams(Criteria example) {
        return this.sysRoleMapper.selectByParams(example);
    }

    @Override
    public List<SysRole> selectRoleByUserSid(Long userSid) {
        Criteria criteria = new Criteria();
        criteria.put("userId", userSid);
        return this.sysRoleMapper.selectByParams(criteria);
    }

    @Override
    public int deleteByPrimaryKey(Long roleSid) {
        int result=0;
        try{
            //删除角色
            this.sysRoleMapper.deleteByPrimaryKey(roleSid);
            result=1;
        }catch (Exception e){
            e.printStackTrace();
            result=0;
        }
        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        int result = 0;
        try {
            //修改角色信息
            WebUtil.prepareUpdateParams(record);
            this.sysRoleMapper.updateByPrimaryKeySelective(record);
            result = 1;
        }catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return this.sysRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.sysRoleMapper.deleteByParams(example);
    }

    @Override
    public int updateByParamsSelective(SysRole record, Criteria example) {
        return this.sysRoleMapper.updateByParamsSelective(record, example.getCondition());
    }

    @Override
    public int updateByParams(SysRole record, Criteria example) {
        return this.sysRoleMapper.updateByParams(record, example.getCondition());
    }

    @Override
    public int insert(SysRole record) {
        return this.sysRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(SysRole record) {
        int result = 0;
        try {
            //添加角色信息
            record.setStatus("0");
            WebUtil.prepareInsertParams(record);
            this.sysRoleMapper.insertSelective(record);
            result = 1;
        }catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }
}
