package com.alfa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/26.
 * 多对多关系集中映射
 */
public class SysRelevance extends Entity implements Serializable {
    /**
     * 描述
     */
    private String Description;
    /**
     * 映射标识
     */
    private String MappKey;
    /**
     * 状态
     */
    private String Status;
    /**
     * 状态名称
     */
    private String Statusname;
    /**
     * 授权时间
     */
    private Date OperatorTime;
    /**
     * 授权
     */
    private int OperatorId;
    /**
     * 第一个表主键ID
     */
    private Long FirstId;
    /**
     * 第二个表主键ID
     */
    private Long SecondId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMappKey() {
        return MappKey;
    }

    public void setMappKey(String mappKey) {
        MappKey = mappKey;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatusname() {
        return Statusname;
    }

    public void setStatusname(String statusname) {
        Statusname = statusname;
    }

    public Date getOperatorTime() {
        return OperatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        OperatorTime = operatorTime;
    }

    public int getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(int operatorId) {
        OperatorId = operatorId;
    }

    public Long getFirstId() {
        return FirstId;
    }

    public void setFirstId(Long firstId) {
        FirstId = firstId;
    }

    public Long getSecondId() {
        return SecondId;
    }

    public void setSecondId(Long secondId) {
        SecondId = secondId;
    }
}
