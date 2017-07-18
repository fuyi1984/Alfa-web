package com.alfa.web.dao;

import com.alfa.web.pojo.VwSmsStatus;
import com.alfa.web.pojo.vwSysUser;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface vwSysUserMapper {
    /**
     * 根据条件查询记录集
     */
    List<vwSysUser> selectByParams(Criteria example);
}
