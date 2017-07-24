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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 被注册用户的userid
     */
    private Long registerid;

    public Long getRegisterid() {
        return registerid;
    }

    public void setRegisterid(Long registerid) {
        this.registerid = registerid;
    }

    /**
     * 注册人员的真实姓名
     */
    public String regrealname;

    /**
     *  注册人员的手机号
     */
    public String regphone;

    /**
     *  注册人员的地址
     */
    private String regaddress;

    /**
     *  注册人员的单位名称

     */
    private String regorgname;


    /**
     * 业务人员的真实姓名
     */
    public String businessrealname;

    /**
     *  业务人员的手机号
     */
    public String businessphone;

}
