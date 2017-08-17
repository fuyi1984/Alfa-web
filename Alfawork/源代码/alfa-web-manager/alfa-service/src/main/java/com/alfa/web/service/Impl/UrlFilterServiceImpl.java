package com.alfa.web.service.Impl;

import com.alfa.web.dao.url.UrlFilterMapper;
import com.alfa.web.pojo.TotalUrlFilters;
import com.alfa.web.service.UrlFilterService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Service
public class UrlFilterServiceImpl implements UrlFilterService {

    private static final Logger logger = LoggerFactory.getLogger(UrlFilterServiceImpl.class);

    @Autowired
    private UrlFilterMapper urlFilterMapper;

    @Override
    public List<TotalUrlFilters> selectByParams(Criteria example) {
        return this.urlFilterMapper.selectByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long Id) {
        return this.urlFilterMapper.deleteByPrimaryKey(Id);
    }

    @Override
    public int insertSelective(TotalUrlFilters record) {
        return this.urlFilterMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count = this.urlFilterMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public int batchdeleteurl(List<String> list) {
        return this.urlFilterMapper.batchdeleteurl(list);
    }
}
