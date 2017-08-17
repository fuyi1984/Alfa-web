package com.alfa.web;

import com.alfa.web.pojo.CommentReply;
import com.alfa.web.service.CommentReplyService;
import com.alfa.web.service.CommonCommentService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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


}
