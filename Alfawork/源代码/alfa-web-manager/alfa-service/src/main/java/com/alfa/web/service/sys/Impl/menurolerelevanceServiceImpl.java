package com.alfa.web.service.sys.Impl;

import com.alfa.web.dao.sys.menurolerelevanceMapper;
import com.alfa.web.pojo.OrderComment;
import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.service.sys.menurolerelevanceService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单角色多对多关系表
 */
@Service
public class menurolerelevanceServiceImpl implements menurolerelevanceService {

    private static final Logger logger = LoggerFactory.getLogger(menurolerelevanceServiceImpl.class);

    @Autowired
    private menurolerelevanceMapper menurolerelevanceMapper;


    @Override
    public int countByParams(Criteria example) {
        int count = this.menurolerelevanceMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.menurolerelevanceMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int insertSelective(menurolerelevance record) {
        WebUtil.prepareInsertParams(record);
        return this.menurolerelevanceMapper.insertSelective(record);
    }

    @Override
    public List<menurolerelevance> selectByParams(Criteria example) {
        return this.menurolerelevanceMapper.selectByParams(example);
    }

    @Override
    public int updateByPrimaryKeySelective(menurolerelevance record) {
        WebUtil.prepareUpdateParams(record);
        return this.menurolerelevanceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int Batchinsert(List<menurolerelevance> recordlst) {

        for(menurolerelevance menurolerelevance:recordlst){
            WebUtil.prepareInsertParams(menurolerelevance);
        }

        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("list",recordlst);
        return this.menurolerelevanceMapper.insertBatch(map);

    }
}
