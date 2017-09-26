package com.alfa.web;

import com.alfa.web.pojo.CommentReply;
import com.alfa.web.service.comment.CommentReplyService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Administrator on 2017/8/17.
 */
public class CommentReplyServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(CommentReplyServiceTest.class);

    @Autowired
    private CommentReplyService commentReplyService;

    @Test
    public void insertCommentReply(){
        CommentReply commentReply=new CommentReply();
        commentReply.setContent("11111");
        commentReply.setMobile("18580043708");
        commentReply.setPhone("1");
        commentReply.setCommentid(29L);
        commentReplyService.insertSelective(commentReply);
    }


    @Test
    public void selectByParams(){
        Criteria criteria=new Criteria();
        criteria.put("mobile","18580043708");
        criteria.put("phone","1");
        List<CommentReply> commentReplyList=this.commentReplyService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(commentReplyList));
    }

    @Test
    public void countByParams(){
        Criteria criteria=new Criteria();
        criteria.put("mobile","18580043708");
        criteria.put("phone","1");
        int count=this.commentReplyService.countByParams(criteria);
        System.out.println(count);
    }

    @Test
    public void batchdeleteByPrimaryKey(){
       List<String> list=new ArrayList<String>();
       list.add("2");
       this.commentReplyService.batchdeleteByPrimaryKey(list);
    }


}
