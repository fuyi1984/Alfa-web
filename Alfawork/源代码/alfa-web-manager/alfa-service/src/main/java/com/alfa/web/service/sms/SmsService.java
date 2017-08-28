package com.alfa.web.service.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 短信服务接口
 * @author chxiaoqi
 *
 */
public interface SmsService {

    /**
     * 激活序列号,初次使用、已注销后再次使用时调用该方法.
     * @return
     * @throws IOException
     */
    public String registAndLogout() throws IOException;


    /**
     * 余额查询
     * @return
     */
    public String getBalance();


    /**
     * 发送带有信息ID的短信,可供查询状态报告
     * @param mdn
     * @param message
     * @return
     */
    public String sendSMS(String mdn,String message) throws UnsupportedEncodingException;

    /**
     * 获取上行短信
     * @return
     */
    public int getMos();

    /**
     * 获取报告数量
     * @return
     */
    public int getReports();

    /**
     * 注销序列号
     * @return
     */
    public String logout();

    /**
     * 发送一条短信，手机使用逗号隔开，少于100个
     * 信息字数少于60字符
     * 返回发送短信成功条数。小于等于0表示有错误。
     * >0：成功条数；
     * -1：帐号不存在，请检查用户名或者密码是否正确；
     * -2：账户余额不足；
     * -3：帐号已被禁用；
     * -4：ip鉴权失败（需要ip校验的场合）；
     * -8：缺少请求参数或参数不正确（请检查用户名，密码，下发号码，下发内容是否为空，或者下发号码数量是否大于100个）；
     * -9：内容不合法（含有非法内容，请检查下发内容）。
     * -10：账户当日发送短信量已经超过允许的每日最大发送量（账户被限制每日发送短信数量的情况有用）
     * -11:账号接入方式不对
     * -99:客户购买产品未生效
     *
     * @param mobile
     * @param message
     * @return
     */
    public int sendMessage(String mobile, String message) throws IOException;

    /**
     * 批量发送短信，可以大于100条
     * 超过100条，将分批次发送，每批次100条。
     * @return
     */
    public int sendMessage(List<String> mobile, String message) throws IOException;


    /**
     * 查询剩余短信条数，返回条数，如果为-1查询出错。
     *
     * -1：帐号登陆失败；
     * -3：此帐号被禁用；
     * -4：ip鉴权失败；
     * -8：缺少请求参数。
     *
     * @return
     */
    public int left() throws IOException;
}
