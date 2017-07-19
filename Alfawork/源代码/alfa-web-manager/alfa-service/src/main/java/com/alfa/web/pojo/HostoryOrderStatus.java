package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * 订单完成历史记录表
 */
public class HostoryOrderStatus extends Entity implements Serializable {

    private String phone;

    private String realnum;

    private Long orderid;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealnum() {
        return realnum;
    }

    public void setRealnum(String realnum) {
        this.realnum = realnum;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
}
