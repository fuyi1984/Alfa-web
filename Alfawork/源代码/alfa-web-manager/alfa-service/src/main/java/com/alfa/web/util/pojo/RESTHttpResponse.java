package com.alfa.web.util.pojo;

import org.apache.http.HttpStatus;

/**
 * REST返回对象
 *
 * @author 张荣
 */
public class RESTHttpResponse {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    /**
     * 返回状态.
     * 成功"success"失败:"error"
     */
    private String status;
    /**
     * 返回内容.
     */
    private String content;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param statusCode
     */
    public void setStatus(int statusCode) {
        if (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
            this.status = SUCCESS;
        } else {
            this.status = ERROR;
        }
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
