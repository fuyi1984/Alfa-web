package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/22.
 * 用户注册行为表
 */
public class userregisterbehavior extends Entity implements Serializable {

    /**
     * userid
     */
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 注册Id
     */
    private Long registerid;

    public Long getRegisterid() {
        return registerid;
    }

    public void setRegisterid(Long registerid) {
        this.registerid = registerid;
    }

}
