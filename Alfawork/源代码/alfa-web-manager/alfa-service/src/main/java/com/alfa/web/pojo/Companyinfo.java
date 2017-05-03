package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/3.
 * 企业信息表
 */
public class Companyinfo extends Entity implements Serializable {

    /**
     * 企业名称
     */
    private String company_name;

    /***
     * 社会公共信息代码
     */
    private String company_code;

    /**
     * 企业英文名称
     */
    private String company_eng;

    /**
     * 企业类型
     */
    private String company_typecode;

    /**
     * 企业类型名称
     */
    private String company_type;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 经度
     */
    private String addr_lon;

    /**
     * 纬度
     */
    private String addr_lat;

    /**
     * 法人
     */
    private String corporate;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 企业状态
     */
    private String status;

    /**
     * 产废单位许可证
     */
    private Long fileid;

    /**
     * 审核状态
     */
    private String checkstatus;

    /**
     * 工商营业执照附件
     */
    private Long license_fileid;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_eng() {
        return company_eng;
    }

    public void setCompany_eng(String company_eng) {
        this.company_eng = company_eng;
    }

    public String getCompany_typecode() {
        return company_typecode;
    }

    public void setCompany_typecode(String company_typecode) {
        this.company_typecode = company_typecode;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddr_lon() {
        return addr_lon;
    }

    public void setAddr_lon(String addr_lon) {
        this.addr_lon = addr_lon;
    }

    public String getAddr_lat() {
        return addr_lat;
    }

    public void setAddr_lat(String addr_lat) {
        this.addr_lat = addr_lat;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public String getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(String checkstatus) {
        this.checkstatus = checkstatus;
    }

    public Long getLicense_fileid() {
        return license_fileid;
    }

    public void setLicense_fileid(Long license_fileid) {
        this.license_fileid = license_fileid;
    }

    @Override
    public String toString() {
        return "Companyinfo{" +
                "company_name='" + company_name + '\'' +
                ", company_code='" + company_code + '\'' +
                ", company_eng='" + company_eng + '\'' +
                ", company_typecode='" + company_typecode + '\'' +
                ", company_type='" + company_type + '\'' +
                ", address='" + address + '\'' +
                ", addr_lon='" + addr_lon + '\'' +
                ", addr_lat='" + addr_lat + '\'' +
                ", corporate='" + corporate + '\'' +
                ", contacts='" + contacts + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", fileid=" + fileid +
                ", checkstatus='" + checkstatus + '\'' +
                ", license_fileid=" + license_fileid +
                '}';
    }
}
