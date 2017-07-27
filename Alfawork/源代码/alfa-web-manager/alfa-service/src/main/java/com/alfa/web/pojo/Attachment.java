package com.alfa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/25.
 */
public class Attachment extends Entity implements Serializable {

    /**
     * 附件SID
     */
    private Long attachmentSid;

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 附件URL
     */
    private String attachmentUrl;

    /**
     * 附件存放位置
     */
    private String attachmentLocation;

    /**
     * 附件类型
     */
    private String attachmentType;

    /**
     * 附件原始名称
     */
    private String oraginName;

    /**
     * 附件扩展名
     */
    private String extendName;

    /**
     * 附件大小
     */
    private Long attachmentSize;

    /**
     * 附件上传时间
     */
    private Date uploadTime;

    /**
     * 备注
     */
    private String remark;


    public Long getAttachmentSid() {
        return attachmentSid;
    }

    public void setAttachmentSid(Long attachmentSid) {
        this.attachmentSid = attachmentSid;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getOraginName() {
        return oraginName;
    }

    public void setOraginName(String oraginName) {
        this.oraginName = oraginName;
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public Long getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(Long attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
