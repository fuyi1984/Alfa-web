package com.alfa.web.util.pojo;

import com.alfa.web.pojo.SysAccount;
import com.alfa.web.pojo.SysUsers;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/27.
 */
public class UserSession implements Serializable {
    private String id;
    private Long creationTime;
    private SysUsers user;
    private SysAccount account;
    //private MsgConfig msgConfig;
    private String platformIntranetUrl;
    private String platformInternetUrl;
    private String ftpInternetUrl;
    private String ftpIntranetUrl;
    private String ftpInternetUrlList;
    private String ftpIntranetUrlList;
    private String pythonIntranetUrl;
    private String pythonInternetUrl;
    private String clientFtpUsername;
    private String clientFtpPassword;
    private String languageCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public SysUsers getUser() {
        return user;
    }

    public void setUser(SysUsers user) {
        this.user = user;
    }

    public SysAccount getAccount() {
        return account;
    }

    public void setAccount(SysAccount account) {
        this.account = account;
    }

    public String getPlatformIntranetUrl() {
        return platformIntranetUrl;
    }

    public void setPlatformIntranetUrl(String platformIntranetUrl) {
        this.platformIntranetUrl = platformIntranetUrl;
    }

    public String getPlatformInternetUrl() {
        return platformInternetUrl;
    }

    public void setPlatformInternetUrl(String platformInternetUrl) {
        this.platformInternetUrl = platformInternetUrl;
    }

    public String getFtpInternetUrl() {
        return ftpInternetUrl;
    }

    public void setFtpInternetUrl(String ftpInternetUrl) {
        this.ftpInternetUrl = ftpInternetUrl;
    }

    public String getFtpIntranetUrl() {
        return ftpIntranetUrl;
    }

    public void setFtpIntranetUrl(String ftpIntranetUrl) {
        this.ftpIntranetUrl = ftpIntranetUrl;
    }

    public String getFtpInternetUrlList() {
        return ftpInternetUrlList;
    }

    public void setFtpInternetUrlList(String ftpInternetUrlList) {
        this.ftpInternetUrlList = ftpInternetUrlList;
    }

    public String getFtpIntranetUrlList() {
        return ftpIntranetUrlList;
    }

    public void setFtpIntranetUrlList(String ftpIntranetUrlList) {
        this.ftpIntranetUrlList = ftpIntranetUrlList;
    }

    public String getPythonIntranetUrl() {
        return pythonIntranetUrl;
    }

    public void setPythonIntranetUrl(String pythonIntranetUrl) {
        this.pythonIntranetUrl = pythonIntranetUrl;
    }

    public String getPythonInternetUrl() {
        return pythonInternetUrl;
    }

    public void setPythonInternetUrl(String pythonInternetUrl) {
        this.pythonInternetUrl = pythonInternetUrl;
    }

    public String getClientFtpUsername() {
        return clientFtpUsername;
    }

    public void setClientFtpUsername(String clientFtpUsername) {
        this.clientFtpUsername = clientFtpUsername;
    }

    public String getClientFtpPassword() {
        return clientFtpPassword;
    }

    public void setClientFtpPassword(String clientFtpPassword) {
        this.clientFtpPassword = clientFtpPassword;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
