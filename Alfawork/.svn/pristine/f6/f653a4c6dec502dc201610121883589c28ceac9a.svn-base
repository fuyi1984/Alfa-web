package com.alfa.web.service.Impl;

import com.alfa.web.common.pojo.Criteria;
import com.alfa.web.dao.SysConfigMapper;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.SysconfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
@Service("sysconfigServiceImpl")
public class SysconfigServiceImpl implements SysconfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysconfigServiceImpl.class);

    @Override
    public int countByParams(Criteria example) {
        int count = this.sysConfigMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public SysConfig selectByPrimaryKey(Long configSid) {
        return this.sysConfigMapper.selectByPrimaryKey(configSid);
    }

    @Override
    public List<SysConfig> selectByParams(Criteria example) {
        return this.sysConfigMapper.selectByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long configSid) {
        return this.sysConfigMapper.deleteByPrimaryKey(configSid);
    }

    @Override
    public int updateByPrimaryKeySelective(SysConfig record) {
        return this.sysConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysConfig record) {
        return this.sysConfigMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.sysConfigMapper.deleteByParams(example);
    }

    @Override
    public int updateByParamsSelective(SysConfig record, Criteria example) {
        return this.sysConfigMapper.updateByParamsSelective(record, example.getCondition());
    }

    @Override
    public int updateByParams(SysConfig record, Criteria example) {
        return this.sysConfigMapper.updateByParams(record, example.getCondition());
    }

    @Override
    public int insert(SysConfig record) {
        return this.sysConfigMapper.insert(record);
    }

    @Override
    public int insertSelective(SysConfig record) {
        return this.sysConfigMapper.insertSelective(record);
    }
}
