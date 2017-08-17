package com.alfa.web.service.Impl;

import com.alfa.web.dao.comment.CommonCommentMapper;
import com.alfa.web.pojo.CommonComment;
import com.alfa.web.service.CommonCommentService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Service
public class CommonCommentServiceImpl implements CommonCommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommonCommentServiceImpl.class);

    @Autowired
    private CommonCommentMapper commonCommentMapper;

    @Override
    public int countByParams(Criteria example) {
        int count=this.commonCommentMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.commonCommentMapper.deleteByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.commonCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(CommonComment record) {
        WebUtil.prepareInsertParams(record);
        return this.commonCommentMapper.insertSelective(record);
    }

    @Override
    public List<CommonComment> selectByParams(Criteria example) {
        return this.commonCommentMapper.selectByParams(example);
    }

    @Override
    public CommonComment selectByPrimaryKey(Long id) {
        return this.commonCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CommonComment record) {
        WebUtil.prepareUpdateParams(record);
        return this.commonCommentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.commonCommentMapper.batchdeleteByPrimaryKey(list);
    }
}
