package com.alfa.web.service.money.Impl;

import com.alfa.web.dao.money.aftersendmoneyMapper;
import com.alfa.web.dao.money.beforesendmoneyMapper;
import com.alfa.web.pojo.aftersendmoney;
import com.alfa.web.service.money.aftersendmoneyService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.calculatemoneyandcount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 红包发送后的成功.
 */
@Service
public class aftersendmoneyServiceImpl implements aftersendmoneyService {

    private static final Logger logger = LoggerFactory.getLogger(aftersendmoneyServiceImpl.class);

    @Autowired
    private aftersendmoneyMapper aftersendmoneyMapper;

    @Override
    public int insertSelective(aftersendmoney record) {
        WebUtil.prepareInsertParams(record);
        return this.aftersendmoneyMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.aftersendmoneyMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.aftersendmoneyMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int updateByPrimaryKeySelective(aftersendmoney record) {
        WebUtil.prepareUpdateParams(record);
        return this.aftersendmoneyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<aftersendmoney> selectByParams(Criteria example) {
        return this.aftersendmoneyMapper.selectByParams(example);
    }

    @Override
    public List<calculatemoneyandcount> selectmoneycountByParams(Criteria example) {
        return this.aftersendmoneyMapper.selectmoneycountByParams(example);
    }
}
