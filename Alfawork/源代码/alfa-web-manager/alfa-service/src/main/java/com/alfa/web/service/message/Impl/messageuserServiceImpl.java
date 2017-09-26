package com.alfa.web.service.message.Impl;

import com.alfa.web.dao.message.messageuserMapper;
import com.alfa.web.pojo.messageuser;
import com.alfa.web.service.message.messageuserService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.messageuserMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public List<messageuser> selectByParams(Criteria example) {
        return this.messageuserMapper.selectByParams(example);
    }

    @Override
    public int Batchinsert(List<messageuser> recordlst) {
        for(messageuser messageuser:recordlst){
            WebUtil.prepareInsertParams(messageuser);
        }

        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("list",recordlst);
        return this.messageuserMapper.insertBatch(map);
    }

    @Override
    public int updateByParamsSelective(messageuser record) {
        WebUtil.prepareUpdateParams(record);
        return this.messageuserMapper.updateByParamsSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.messageuserMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }
}
