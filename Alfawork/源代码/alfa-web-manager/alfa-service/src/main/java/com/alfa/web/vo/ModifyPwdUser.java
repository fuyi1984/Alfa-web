package com.alfa.web.vo;

/**
 * Created by Administrator on 2017/6/11.
 */
public class ModifyPwdUser {
    /**
     * 用户SID
     */
    private Long userSid;
    private String newPassword;
    private String oldPwd;

    public Long getUserSid() {
        return userSid;
    }

    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
