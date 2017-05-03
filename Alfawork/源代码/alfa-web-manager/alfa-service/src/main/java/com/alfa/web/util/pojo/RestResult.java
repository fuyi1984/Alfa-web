package com.alfa.web.util.pojo;

/**
 * RestAPI返回对象
 *
 * @author 刘洋
 */
public class RestResult {

    /** 成功 */
    public static final String SUCCESS = "success";

    /** 失败 */
    public static final String FAILURE = "failure";

    /**
     * 返回状态
     */
    private String status;

    /**
     * 返回消息
     */
    private Object message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 构造方法
     */
    public RestResult() {
    }

    /**
     * 构造方法(状态)
     *
     * @param status
     *            返回状态
     * @param message
     *            返回消息
     */
    public RestResult(String status) {
        this.status = status;
        this.message = "";
        this.data = "";
    }

    /**
     * 构造方法(状态、消息)
     *
     * @param status
     *            返回状态
     * @param message
     *            返回消息
     */
    public RestResult(String status, Object message) {
        this.status = status;
        this.message = message;
        this.data = "";
    }

    /**
     * 构造方法(状态、消息、数据)
     *
     * @param status
     *            返回状态
     * @param message
     *            返回消息
     * @param other
     *            返回数据
     */
    public RestResult(String status, Object message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * @return 返回状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            返回状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 返回消息
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @param message
     *            返回消息
     */
    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * @return 返回数据
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     *            返回数据
     */
    public void setData(Object data) {
        this.data = data;
    }

}
