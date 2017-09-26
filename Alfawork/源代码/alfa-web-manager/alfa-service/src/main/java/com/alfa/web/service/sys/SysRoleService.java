package com.alfa.web.service.sys;

import com.alfa.web.pojo.SysRole;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface SysRoleService {
    int countByParams(Criteria example);

    SysRole selectByPrimaryKey(Long roleSid);

    List<SysRole> selectByParams(Criteria example);

    /**
     * 根据用户主键查询用户拥有的角色
     *
     * @param userSid 用户主键
     * @return List<Role> 角色列表
     */
    List<SysRole> selectRoleByUserSid(Long userSid);

    int deleteByPrimaryKey(Long roleSid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    int deleteByParams(Criteria example);

    int batchdeleteByPrimaryKey(List<String> list);

    int updateByParamsSelective(SysRole record, Criteria example);

    int updateByParams(SysRole record, Criteria example);

    int insert(SysRole record);

    int insertSelective(SysRole record);
}
