package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/14.
 * Url过滤表
 */
public class UrlFilter extends Entity implements Serializable {

    private String Url;

    private String Apiname;

    private String types;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getApiname() {
        return Apiname;
    }

    public void setApiname(String apiname) {
        Apiname = apiname;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "UrlFilter{" +
                "Url='" + Url + '\'' +
                ", Apiname='" + Apiname + '\'' +
                ", types='" + types + '\'' +
                '}';
    }
}
