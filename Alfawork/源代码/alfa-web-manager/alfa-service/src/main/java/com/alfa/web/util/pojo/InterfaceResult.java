package com.alfa.web.util.pojo;

import com.alfa.web.util.JsonUtil;

/**
 * 接口返回结果类
 */
public class InterfaceResult {
    /**
     * 返回状态
     */
    private String status;

    /**
     * 返回内容
     */
    private String content;

    public InterfaceResult(String status, String content) {
        this.status = status;
        this.content = content;
    }

    /**
     * 设置返回状态和内容，并返回Json
     *
     * @param status
     * @param content
     * @return
     */
    public static String setResult(String status, String content){
        InterfaceResult result=new InterfaceResult(status, content);
        String json= JsonUtil.toJson(result);
        return json;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
