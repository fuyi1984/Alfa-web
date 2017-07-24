package com.alfa.web.service.Impl;

import com.alfa.web.dao.SysUsersMapper;
import com.alfa.web.dao.userregisterbehaviorMapper;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.userregisterbehavior;
import com.alfa.web.service.userregisterbehaviorService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
@Service
public class userregisterbehaviorServiceImpl implements userregisterbehaviorService {

    private static final Logger logger = LoggerFactory.getLogger(userregisterbehaviorServiceImpl.class);

    @Autowired
    private userregisterbehaviorMapper userregisterbehaviorMapper;

    @Override
    public int insertSelective(userregisterbehavior record) {
        WebUtil.prepareInsertParams(record);
        return this.userregisterbehaviorMapper.insertSelective(record);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.userregisterbehaviorMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public List<userregisterbehavior> selectByParams(Criteria example) {
        List<userregisterbehavior> userregisterbehaviorlist=this.userregisterbehaviorMapper.selectByParams(example);
        return userregisterbehaviorlist;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.userregisterbehaviorMapper.batchdeleteByPrimaryKey(list);
    }
}
