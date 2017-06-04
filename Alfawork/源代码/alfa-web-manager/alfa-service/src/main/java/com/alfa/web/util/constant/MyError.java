package com.alfa.web.util.constant;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * CXF返回错误信息.
 */
@XmlRootElement
public class MyError {
    public String error;
    public String errorCode;
    public MyError(){

    }
    public MyError(String error,String errorCode){
        this.error=error;
        this.errorCode=errorCode;
    }

    //用户名与系统中已有用户名重复
    public static final MyError VERIFY_FAILED=new MyError("用户名或密码不正确！","0");

    //用户名与系统中已有用户名重复
    public static final MyError USERNAME_AND_SYSTEMUSERNAME_REPETITION=new MyError("该用户账号已存在，请重新填写！","1");

    //用户不存在
    public static final MyError USER_NO_EXIST=new MyError("该用户不存在！","2");
}
