package com.alfa.web.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/24.
 * 实体类基类
 */
public class Entity {

    private Long id;

    /**
     * 创建时间
     */
    private Date create_date;

    /**
     * 创建人ID
     */
    private Long bycreater_id;

    /**
     * 版本号
     */
    private Long version;

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getBycreater_id() {
        return bycreater_id;
    }

    public void setBycreater_id(Long bycreater_id) {
        this.bycreater_id = bycreater_id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     *  更新时间
     */
    private Date updatedDt;

    /**
     * 更新人ID
     */
    private Long updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
