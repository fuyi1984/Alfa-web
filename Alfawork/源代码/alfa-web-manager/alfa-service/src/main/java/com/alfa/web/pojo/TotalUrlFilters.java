package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/14.
 * Url过滤表
 */
public class TotalUrlFilters extends Entity implements Serializable {

    private String ApiAddress;

    private String Apiname;

    private String types;

    public String getApiAddress() {
        return ApiAddress;
    }

    public void setApiAddress(String apiAddress) {
        ApiAddress = apiAddress;
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

}
