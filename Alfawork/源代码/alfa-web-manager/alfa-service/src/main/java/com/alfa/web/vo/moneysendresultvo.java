package com.alfa.web.vo;

/**
 * Created by Administrator on 2017/9/27.
 * 红包发送后的返回结果
 */
public class moneysendresultvo {

    private int code;

    private String content;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
