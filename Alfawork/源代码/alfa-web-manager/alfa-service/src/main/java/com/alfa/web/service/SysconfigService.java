package com.alfa.web.service;

import com.alfa.web.pojo.SysConfig;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface SysconfigService {
    //int countByParams(Criteria example);

    SysConfig selectByPrimaryKey(Long configSid);

    //List<Sysconfig> selectByParams(Criteria example);

    int deleteByPrimaryKey(Long configSid);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

//    int deleteByParams(Criteria example);
//
//    int updateByParamsSelective(Sysconfig record, Criteria example);
//
//    int updateByParams(Sysconfig record, Criteria example);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);
}
