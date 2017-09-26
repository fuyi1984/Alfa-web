package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/7.
 * 用户角色关系表
 */
public class UserOrder extends Entity implements Serializable {

    private Long UserId;

    private Long OrderId;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }
}
