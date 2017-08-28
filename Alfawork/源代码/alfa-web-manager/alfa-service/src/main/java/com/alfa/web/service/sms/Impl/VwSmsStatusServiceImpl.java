package com.alfa.web.service.sms.Impl;

import com.alfa.web.dao.sms.VwSmsStatusMapper;
import com.alfa.web.pojo.VwSmsStatus;
import com.alfa.web.service.sms.VwSmsStatusService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
@Service
public class VwSmsStatusServiceImpl implements VwSmsStatusService {

    private static final Logger logger = LoggerFactory.getLogger(VwSmsStatusServiceImpl.class);

    @Autowired
    private VwSmsStatusMapper vwSmsStatusMapper;

    @Override
    public List<VwSmsStatus> selectByParams(Criteria example) {
        return this.vwSmsStatusMapper.selectByParams(example);
    }
}
