package com.alfa.web.vo;

/**
 * Created by Administrator on 2017/7/24.
 */
public class registerbehaviorvo {

    /**
     * 业务人员userid
     */
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 真实姓名
     */
    public String realname;

    /**
     * 手机号
     */
    public String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 单位名称

     */
    private String orgname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
}
