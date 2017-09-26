package com.alfa.web.service.sys.Impl;

import com.alfa.web.dao.sys.EMenuInfosMapper;
import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.service.sys.EMenuInfosService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class EMenuInfosServiceImpl implements EMenuInfosService {

    private static final Logger logger = LoggerFactory.getLogger(EMenuInfosServiceImpl.class);

    @Autowired
    private EMenuInfosMapper EMenuInfosMapper;

    @Override
    public int countByParams(Criteria example) {
        int count=this.EMenuInfosMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.EMenuInfosMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int insertSelective(EMenuInfos record) {
        WebUtil.prepareInsertParams(record);
        return this.EMenuInfosMapper.insertSelective(record);
    }

    @Override
    public List<EMenuInfos> selectByParams(Criteria example) {
        return this.EMenuInfosMapper.selectByParams(example);
    }

    @Override
    public int updateByPrimaryKeySelective(EMenuInfos record) {
        WebUtil.prepareUpdateParams(record);
        return this.EMenuInfosMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.EMenuInfosMapper.deleteByPrimaryKey(id);
    }
}
