package com.alfa.web.vo;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.td_weixin_users;

/**
 * Created by Administrator on 2017/8/23.
 */
public class openidordercommentvo {

    private td_weixin_users openidusers;
    private OrderComment comment;
    private Orders order;

    public td_weixin_users getOpenidusers() {
        return openidusers;
    }

    public void setOpenidusers(td_weixin_users openidusers) {
        this.openidusers = openidusers;
    }

    public OrderComment getComment() {
        return comment;
    }

    public void setComment(OrderComment comment) {
        this.comment = comment;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
