package com.alfa.web.service.news.Impl;

import com.alfa.web.dao.news.newstopMapper;
import com.alfa.web.pojo.newstop;
import com.alfa.web.service.news.newstopService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class newstopServiceImpl implements newstopService {

    private static final Logger logger = LoggerFactory.getLogger(newstopServiceImpl.class);

    @Autowired
    private newstopMapper newstopMapper;

    @Override
    public int countByParams(Criteria example) {
        int count=this.newstopMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.newstopMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int insertSelective(newstop record) {
        WebUtil.prepareInsertParams(record);
        return this.newstopMapper.insertSelective(record);
    }

    @Override
    public List<newstop> selectByParams(Criteria example) {
        return this.newstopMapper.selectByParams(example);
    }

    @Override
    public int updateByPrimaryKeySelective(newstop record) {
        WebUtil.prepareUpdateParams(record);
        return this.newstopMapper.updateByPrimaryKeySelective(record);
    }
}
