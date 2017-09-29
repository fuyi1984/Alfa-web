package com.alfa.web.service.money.Impl;

import com.alfa.web.dao.money.aftersendmoneyMapper;
import com.alfa.web.dao.money.aftersendmoneyfailtureMapper;
import com.alfa.web.pojo.aftersendmoneyfailture;
import com.alfa.web.service.money.aftersendmoneyfailtureService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */
@Service
public class aftersendmoneyfailtureServiceImpl implements aftersendmoneyfailtureService {


    private static final Logger logger = LoggerFactory.getLogger(aftersendmoneyfailtureServiceImpl.class);

    @Autowired
    private aftersendmoneyfailtureMapper aftersendmoneyfailtureMapper;

    @Override
    public int insertSelective(aftersendmoneyfailture record) {
        WebUtil.prepareInsertParams(record);
        return this.aftersendmoneyfailtureMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.aftersendmoneyfailtureMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.aftersendmoneyfailtureMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int updateByPrimaryKeySelective(aftersendmoneyfailture record) {
        WebUtil.prepareUpdateParams(record);
        return this.aftersendmoneyfailtureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<aftersendmoneyfailture> selectByParams(Criteria example) {
        return this.aftersendmoneyfailtureMapper.selectByParams(example);
    }
}
