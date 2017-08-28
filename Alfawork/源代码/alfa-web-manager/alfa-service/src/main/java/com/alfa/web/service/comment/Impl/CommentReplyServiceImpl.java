package com.alfa.web.service.comment.Impl;

import com.alfa.web.dao.comment.CommentReplyMapper;
import com.alfa.web.pojo.CommentReply;
import com.alfa.web.service.comment.CommentReplyService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService {

    private static final Logger logger = LoggerFactory.getLogger(CommentReplyServiceImpl.class);

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public int countByParams(Criteria example) {
        int count=this.commentReplyMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.commentReplyMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public int insertSelective(CommentReply record) {
        WebUtil.prepareInsertParams(record);
        return this.commentReplyMapper.insertSelective(record);
    }

    @Override
    public List<CommentReply> selectByParams(Criteria example) {
        return this.commentReplyMapper.selectByParams(example);
    }
}
