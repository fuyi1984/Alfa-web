package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/22.
 * 用户注册行为表
 */
public class userregisterbehavior extends Entity implements Serializable {

    /**
     * 业务人员userid
     */
    private Long userid;

    /**
     * 被注册用户的userid
     */
    private Long registerid;

    /**
     * 注册人员的真实姓名
     */
    private String regrealname;

    /**
     *  注册人员的手机号
     */
    private String regphone;

    /**
     *  注册人员的地址
     */
    private String regaddress;

    /**
     *  注册人员的单位名称

     */
    private String regorgname;

    public String getRegstatus() {
        return regstatus;
    }

    public void setRegstatus(String regstatus) {
        this.regstatus = regstatus;
    }

    /**
     * 注册人员的状态
     */

    private String regstatus;


    /**
     * 业务人员的真实姓名
     */
    private String businessrealname;

    /**
     *  业务人员的手机号
     */
    private String businessphone;

    /**
     * 文件ID
     */
    private Long fileid;

    /**
     * 文件链接
     */
    private String regurl;

    public String getRegtype() {
        return regtype;
    }

    public void setRegtype(String regtype) {
        this.regtype = regtype;
    }

    /**
     * 文件类型

     */
    private String regtype;

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public String getRegurl() {
        return regurl;
    }

    public void setRegurl(String regurl) {
        this.regurl = regurl;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRegisterid() {
        return registerid;
    }

    public void setRegisterid(Long registerid) {
        this.registerid = registerid;
    }

    public String getRegrealname() {
        return regrealname;
    }

    public void setRegrealname(String regrealname) {
        this.regrealname = regrealname;
    }

    public String getRegphone() {
        return regphone;
    }

    public void setRegphone(String regphone) {
        this.regphone = regphone;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress;
    }

    public String getRegorgname() {
        return regorgname;
    }

    public void setRegorgname(String regorgname) {
        this.regorgname = regorgname;
    }

    public String getBusinessrealname() {
        return businessrealname;
    }

    public void setBusinessrealname(String businessrealname) {
        this.businessrealname = businessrealname;
    }

    public String getBusinessphone() {
        return businessphone;
    }

    public void setBusinessphone(String businessphone) {
        this.businessphone = businessphone;
    }
}
