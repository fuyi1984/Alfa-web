package com.alfa.web.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/5.
 * 订单状态视图
 */
public class VwOrderStatus {

    private Long orderid;

    private String orgstatus;

    private Date createdDt;

    private Date updatedDt;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrgstatus() {
        return orgstatus;
    }

    public void setOrgstatus(String orgstatus) {
        this.orgstatus = orgstatus;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }
}
