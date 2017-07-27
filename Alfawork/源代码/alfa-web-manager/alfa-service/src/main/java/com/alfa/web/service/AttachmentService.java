package com.alfa.web.service;

import com.alfa.web.pojo.Attachment;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface AttachmentService {
    int countByParams(Criteria example);

    Attachment selectByPrimaryKey(Long attachmentSid);

    List<Attachment> selectByParams(Criteria example);

    int deleteByPrimaryKey(Long attachmentSid);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);

    int deleteByParams(Criteria example);

    int updateByParamsSelective(Attachment record, Criteria example);

    int updateByParams(Attachment record, Criteria example);

    int insert(Attachment record);

    int insertSelective(Attachment record);
}
