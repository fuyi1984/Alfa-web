package com.alfa.web;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.service.comment.OrderCommentService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Administrator on 2017/7/11.
 */
public class OrderCommentServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(OrderCommentServiceTest.class);

    @Autowired
    private OrderCommentService orderCommentService;

    @Test
    public void insertSelective(){
        OrderComment orderComment=new OrderComment();
        orderComment.setContent("1243");
        orderComment.setOrderId(1L);
        orderCommentService.insertSelective(orderComment);
    }

    @Test
    public void Batchinsert(){
        List<OrderComment> orderCommentList=new ArrayList<OrderComment>();

        OrderComment orderComment=new OrderComment();
        orderComment.setOne("1");
        orderComment.setTwo("2");
        orderComment.setThree("4");
        orderComment.setFour("3");
        orderComment.setMobile("18580043708");
        WebUtil.prepareInsertParams(orderComment);
        orderCommentList.add(orderComment);

        orderCommentService.Batchinsert(orderCommentList);
    }

    @Test
    public void selectByParams(){
        Criteria criteria = new Criteria();
        criteria.put("mobile", "18580043708");
        criteria.put("phone","1");

        List<OrderComment> orderCommentList=this.orderCommentService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(orderCommentList));
    }

    @Test
    public void batchdeleteordercomment(){
        List<String> idlist=new ArrayList<String>();
        idlist.add("5");
        idlist.add("6");
        idlist.add("7");
        idlist.add("8");
        System.out.println(this.orderCommentService.batchdeleteordercomment(idlist));
    }
}
