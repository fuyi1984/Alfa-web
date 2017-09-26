package com.alfa.web.service.file.Impl;

import com.alfa.web.dao.file.fileinfoMapper;
import com.alfa.web.pojo.fileinfo;
import com.alfa.web.service.file.fileinfoService;
import com.alfa.web.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/26.
 */
@Service
public class fileinfoServiceImpl implements fileinfoService {

    private static final Logger logger = LoggerFactory.getLogger(fileinfoServiceImpl.class);

    @Autowired
    private fileinfoMapper fileinfoMapper;

    @Override
    public int insertSelective(fileinfo record) {
        WebUtil.prepareInsertParams(record);
        return this.fileinfoMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.fileinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(fileinfo record) {
        WebUtil.prepareUpdateParams(record);
        return this.fileinfoMapper.updateByPrimaryKeySelective(record);
    }
}
