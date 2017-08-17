package com.alfa.web.dao.comment;

import com.alfa.web.pojo.CommentReply;
import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface CommentReplyMapper {

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据主键批量删除记录
     */
    int batchdeleteByPrimaryKey(List<String> list);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CommentReply record);

    /**
     * 根据条件查询记录集
     */
    List<CommentReply> selectByParams(Criteria example);

}
