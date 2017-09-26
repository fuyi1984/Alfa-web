package com.alfa.web.service.file.Impl;

import com.alfa.web.dao.file.AttachmentMapper;
import com.alfa.web.pojo.Attachment;
import com.alfa.web.service.file.AttachmentService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    @Override
    public int countByParams(Criteria example) {
        int count = this.attachmentMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public Attachment selectByPrimaryKey(Long attachmentSid) {
        return this.attachmentMapper.selectByPrimaryKey(attachmentSid);
    }

    @Override
    public List<Attachment> selectByParams(Criteria example) {
        return this.attachmentMapper.selectByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long attachmentSid) {
        return this.attachmentMapper.deleteByPrimaryKey(attachmentSid);
    }

    @Override
    public int updateByPrimaryKeySelective(Attachment record) {
        return this.attachmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Attachment record) {
        return this.attachmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.attachmentMapper.deleteByParams(example);
    }

    @Override
    public int updateByParamsSelective(Attachment record, Criteria example) {
        return this.attachmentMapper.updateByParamsSelective(record, example.getCondition());
    }

    @Override
    public int updateByParams(Attachment record, Criteria example) {
        return this.attachmentMapper.updateByParams(record, example.getCondition());
    }

    @Override
    public int insert(Attachment record) {
        return this.attachmentMapper.insert(record);
    }

    @Override
    public int insertSelective(Attachment record) {
        return this.attachmentMapper.insertSelective(record);
    }
}
