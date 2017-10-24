package com.alfa.web.service.money.Impl;

import com.alfa.web.dao.money.moneyactivitiesMapper;
import com.alfa.web.dao.weixin.weixin_usersMapper;
import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.service.weixin.Impl.weixin_usersServiceImpl;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 红包活动
 */
@Service
public class moneyactivitiesServcieImpl implements moneyactivitiesServcie {

    private static final Logger logger = LoggerFactory.getLogger(moneyactivitiesServcieImpl.class);

    @Autowired
    private moneyactivitiesMapper moneyactivitiesMapper;

    @Override
    public int insertSelective(moneyactivities record) {
        WebUtil.prepareInsertParams(record);
        return this.moneyactivitiesMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.moneyactivitiesMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.moneyactivitiesMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int batchStartmoneyactivitiesByPrimaryKey(List<String> list) {
        return this.moneyactivitiesMapper.batchStartmoneyactivitiesByPrimaryKey(list);
    }

    @Override
    public int batchStopmoneyactivitiesByPrimaryKey(List<String> list) {
        return this.moneyactivitiesMapper.batchStopmoneyactivitiesByPrimaryKey(list);
    }

    @Override
    public int batchDeletemoneyactivitiesByPrimaryKey(List<String> list) {
        return this.moneyactivitiesMapper.batchDeletemoneyactivitiesByPrimaryKey(list);
    }

    @Override
    public int updateByPrimaryKeySelective(moneyactivities record) {
        WebUtil.prepareUpdateParams(record);
        return this.moneyactivitiesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<moneyactivities> selectByParams(Criteria example) {
        return this.moneyactivitiesMapper.selectByParams(example);
    }
}
