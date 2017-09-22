package com.alfa.web.service.money.Impl;

import com.alfa.web.dao.money.activitiesorderMapper;
import com.alfa.web.dao.money.aftersendmoneyMapper;
import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.service.money.activitiesorderService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 红包活动订单
 */
@Service
public class activitiesorderServiceImpl implements activitiesorderService {

    private static final Logger logger = LoggerFactory.getLogger(activitiesorderServiceImpl.class);

    @Autowired
    private activitiesorderMapper activitiesorderMapper;

    @Override
    public int insertSelective(activitiesorder record) {
        WebUtil.prepareInsertParams(record);
        return this.activitiesorderMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.activitiesorderMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.activitiesorderMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int updateByPrimaryKeySelective(activitiesorder record) {
        WebUtil.prepareUpdateParams(record);
        return this.activitiesorderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<activitiesorder> selectByParams(Criteria example) {
        return this.activitiesorderMapper.selectByParams(example);
    }
}
