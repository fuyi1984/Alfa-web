package com.alfa.web.service;

import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.pojo.SysConfig;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface SysconfigService {

    int countByParams(Criteria example);

    SysConfig selectByPrimaryKey(Long configSid);

    List<SysConfig> selectByParams(Criteria example);

    int deleteByPrimaryKey(Long configSid);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

    int deleteByParams(Criteria example);

    int updateByParamsSelective(SysConfig record, Criteria example);

    int updateByParams(SysConfig record, Criteria example);

    int insertSysConfig(SysConfig record);

    int insertSelective(SysConfig record);
}
