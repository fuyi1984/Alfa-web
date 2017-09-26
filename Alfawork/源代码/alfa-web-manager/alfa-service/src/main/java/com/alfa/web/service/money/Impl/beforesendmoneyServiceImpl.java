package com.alfa.web.service.money.Impl;

import com.alfa.web.dao.money.beforesendmoneyMapper;
import com.alfa.web.dao.money.moneyactivitiesconcernMapper;
import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.service.money.beforesendmoneyService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 红包发送前的准备
 */
@Service
public class beforesendmoneyServiceImpl implements beforesendmoneyService {

    private static final Logger logger = LoggerFactory.getLogger(beforesendmoneyServiceImpl.class);

    @Autowired
    private beforesendmoneyMapper beforesendmoneyMapper;

    @Override
    public int insertSelective(beforesendmoney record) {
        WebUtil.prepareInsertParams(record);
        return this.beforesendmoneyMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.beforesendmoneyMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.beforesendmoneyMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int updateByPrimaryKeySelective(beforesendmoney record) {
        WebUtil.prepareUpdateParams(record);
        return this.beforesendmoneyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<beforesendmoney> selectByParams(Criteria example) {
        return this.beforesendmoneyMapper.selectByParams(example);
    }
}
