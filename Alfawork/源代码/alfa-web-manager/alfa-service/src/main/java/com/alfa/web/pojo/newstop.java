package com.alfa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻头条
 */
public class newstop extends Entity implements Serializable {

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻内容
     */
    private String content;

    /**
     * 发布日期
     */
    private Date publishDt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDt() {
        return publishDt;
    }

    public void setPublishDt(Date publishDt) {
        this.publishDt = publishDt;
    }
}
