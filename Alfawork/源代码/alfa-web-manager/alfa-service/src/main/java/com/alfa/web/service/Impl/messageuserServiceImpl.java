package com.alfa.web.service.Impl;

import com.alfa.web.dao.LogForPayMapper;
import com.alfa.web.dao.messageuserMapper;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.service.messageuserService;
import com.alfa.web.util.WebUtil;
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
    public int insertSelective(publishmessage record) {
        WebUtil.prepareInsertParams(record);
        return this.messageuserMapper.insertSelective(record);
    }

    @Override
    public int batchdeleteByMessageid(List<String> list) {
        return this.batchdeleteByMessageid(list);
    }
}
