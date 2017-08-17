package com.alfa.web.service.Impl;

import com.alfa.web.dao.weixin.weixin_usersMapper;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.weixin_usersService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
@Service
public class weixin_usersServiceImpl implements weixin_usersService {

    private static final Logger logger = LoggerFactory.getLogger(weixin_usersServiceImpl.class);

    @Autowired
    private weixin_usersMapper weixin_usersMapper;

    @Override
    public int countByParams(Criteria example) {
        int count=this.weixin_usersMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.weixin_usersMapper.deleteByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.weixin_usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(td_weixin_users record) {
        WebUtil.prepareInsertParams(record);
        return this.weixin_usersMapper.insertSelective(record);
    }

    @Override
    public List<td_weixin_users> selectByParams(Criteria example) {
        return this.weixin_usersMapper.selectByParams(example);
    }

    @Override
    public td_weixin_users selectByPrimaryKey(Long id) {
        return this.weixin_usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(td_weixin_users record) {
        WebUtil.prepareUpdateParams(record);
        return this.weixin_usersMapper.updateByPrimaryKeySelective(record);
    }
}
