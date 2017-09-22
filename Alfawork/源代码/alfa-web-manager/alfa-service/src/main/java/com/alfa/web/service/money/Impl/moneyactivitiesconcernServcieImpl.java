package com.alfa.web.service.money.Impl;

import com.alfa.web.dao.money.moneyactivitiesMapper;
import com.alfa.web.dao.money.moneyactivitiesconcernMapper;
import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 红包活动关注
 */
@Service
public class moneyactivitiesconcernServcieImpl implements moneyactivitiesconcernServcie {

    private static final Logger logger = LoggerFactory.getLogger(moneyactivitiesconcernServcieImpl.class);

    @Autowired
    private moneyactivitiesconcernMapper moneyactivitiesconcernMapper;

    @Override
    public int insertSelective(moneyactivitiesconcern record) {
        WebUtil.prepareInsertParams(record);
        return this.moneyactivitiesconcernMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.moneyactivitiesconcernMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.moneyactivitiesconcernMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int updateByPrimaryKeySelective(moneyactivitiesconcern record) {
        WebUtil.prepareUpdateParams(record);
        return this.moneyactivitiesconcernMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<moneyactivitiesconcern> selectByParams(Criteria example) {
        return this.moneyactivitiesconcernMapper.selectByParams(example);
    }
}
