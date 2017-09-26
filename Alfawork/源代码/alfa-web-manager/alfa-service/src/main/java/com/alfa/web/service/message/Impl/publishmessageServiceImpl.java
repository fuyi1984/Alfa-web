package com.alfa.web.service.message.Impl;

import com.alfa.web.dao.message.publishmessageMapper;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.service.message.publishmessageService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class publishmessageServiceImpl implements publishmessageService {

    @Autowired
    private publishmessageMapper publishmessageMapper;

    private static final Logger logger = LoggerFactory.getLogger(publishmessageServiceImpl.class);

    @Override
    public int insertSelective(publishmessage record) {
        WebUtil.prepareInsertParams(record);
        return this.publishmessageMapper.insertSelective(record);
    }

    @Override
    public List<publishmessage> selectByParams(Criteria example) {
        return this.publishmessageMapper.selectByParams(example);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.publishmessageMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.publishmessageMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int updateByPrimaryKeySelective(publishmessage record) {
        WebUtil.prepareUpdateParams(record);
        return this.publishmessageMapper.updateByPrimaryKeySelective(record);
    }
}

