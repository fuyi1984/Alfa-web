package com.alfa.web.service.Impl;

import com.alfa.web.dao.order.HistoryAddressMapper;
import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.service.HistoryAddressService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */
@Service
public class HistoryAddressServiceImpl implements HistoryAddressService {


    @Autowired
    private HistoryAddressMapper historyAddressMapper;

    private static final Logger logger = LoggerFactory.getLogger(HistoryAddressServiceImpl.class);

    @Override
    public int countByParams(Criteria example) {
        int count=this.historyAddressMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.historyAddressMapper.deleteByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.historyAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(HistoryAddress record) {
        WebUtil.prepareInsertParams(record);
        return this.historyAddressMapper.insertSelective(record);
    }

    @Override
    public List<HistoryAddress> selectByParams(Criteria example) {
        return this.historyAddressMapper.selectByParams(example);
    }

    @Override
    public HistoryAddress selectByPrimaryKey(Long id) {
        return this.historyAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(HistoryAddress record) {
        WebUtil.prepareUpdateParams(record);
        return this.historyAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.historyAddressMapper.batchdeleteByPrimaryKey(list);
    }
}
