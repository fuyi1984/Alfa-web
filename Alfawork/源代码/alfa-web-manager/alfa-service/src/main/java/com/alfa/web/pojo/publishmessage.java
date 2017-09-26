package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/20.
 * 消息通知
 */
public class publishmessage extends Entity implements Serializable {

    private String title;

    private String content;

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
}
