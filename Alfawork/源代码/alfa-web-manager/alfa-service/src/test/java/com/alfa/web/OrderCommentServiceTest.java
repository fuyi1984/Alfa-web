package com.alfa.web;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.service.HistoryAddressService;
import com.alfa.web.service.OrderCommentService;
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
        orderComment.setContent("1243");
        orderComment.setOrderId(1L);
        WebUtil.prepareInsertParams(orderComment);
        orderCommentList.add(orderComment);

        OrderComment orderComment2=new OrderComment();
        orderComment2.setContent("124333");
        orderComment2.setOrderId(2L);
        WebUtil.prepareInsertParams(orderComment2);
        orderCommentList.add(orderComment2);

        orderCommentService.Batchinsert(orderCommentList);
    }

    @Test
    public void selectByParams(){
        Criteria criteria = new Criteria();
        criteria.put("orderId", "1");
        List<OrderComment> orderCommentList=this.orderCommentService.selectByParams(criteria);
        System.out.println(orderCommentList.size());
        int result=this.orderCommentService.countByParams(criteria);
        System.out.println(result);
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
