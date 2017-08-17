package com.alfa.web.service.Impl;

import com.alfa.web.dao.common.CompanyinfoMapper;
import com.alfa.web.pojo.Companyinfo;
import com.alfa.web.service.CompanyinfoService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */
@Service
public class CompanyinfoServiceImpl implements CompanyinfoService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyinfoServiceImpl.class);

    @Autowired
    private CompanyinfoMapper CompanyinfoMapper;

    @Override
    public List<Companyinfo> selectByParams(Criteria example) {
        return this.CompanyinfoMapper.selectByParams(example);
    }
}
