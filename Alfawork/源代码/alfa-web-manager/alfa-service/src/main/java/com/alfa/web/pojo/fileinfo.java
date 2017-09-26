package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/3.
 * 文件信息表
 */
public class fileinfo extends Entity implements Serializable {

    /**
     * 文件名
     */
    private String file_name;
    /**
     * 服务器路径
     */
    private String url;
    /**
     * 文件类型
     */
    private String type;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "fileinfo{" +
                "file_name='" + file_name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
