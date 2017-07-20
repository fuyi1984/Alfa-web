package com.alfa.web.service.Impl;

import com.alfa.web.dao.LogForPayMapper;
import com.alfa.web.dao.messageuserMapper;
import com.alfa.web.pojo.messageuser;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.service.messageuserService;
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
public class messageuserServiceImpl implements messageuserService {

    @Autowired
    private messageuserMapper messageuserMapper;

    private static final Logger logger = LoggerFactory.getLogger(messageuserServiceImpl.class);

    @Override
    public int insertSelective(messageuser record) {
        WebUtil.prepareInsertParams(record);
        return this.messageuserMapper.insertSelective(record);
    }

    @Override
    public int batchdeleteByMessageid(List<String> list) {
        return this.messageuserMapper.batchdeleteByMessageid(list);
    }

    @Override
    public List<messageuser> selectByParams(Criteria example) {
        return this.messageuserMapper.selectByParams(example);
    }
}
