package com.alfa.web.util;

import com.alfa.web.util.pojo.RESTHttpResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/26.
 */
public class RSETClientPwdUtil {
    private static Log logger = LogFactory.getLog(RSETClientUtil.class);

    private static long startTime = 0L;
    private static long endTime = 0L;

    /**
     * POST调用WebService方法
     *
     * @param url
     *            WebService地址
     * @param paramters
     *            JSON格式的参数
     * @return 调用结果
     * @throws JSONException
     * @throws IOException
     */
    public static RESTHttpResponse post(String url, String paramters, String user, String pwd) throws JSONException, IOException {

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("调用WebService接口开始。Url：" + url);

            JSONObject jsonObj = new JSONObject(paramters);

            HttpClient httpClient = new HttpClient();

            PostMethod method = new PostMethod(url);

            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(user, pwd);
            httpClient.getParams().setAuthenticationPreemptive(true);
            httpClient.getState().setCredentials(AuthScope.ANY, creds);

            method.setDoAuthentication(true);

            method.setRequestEntity(new StringRequestEntity(jsonObj.toString(), "application/json", "utf-8"));

            httpClient.executeMethod(method);

            int statusCode = method.getStatusCode();
            // 设置返回状态码
            result.setStatus(statusCode);

            String contents = method.getResponseBodyAsString();
            // 获取返回内容
            result.setContent(contents);

            logger.debug("调用WebService接口结束。响应状态：" + statusCode + "，响应内容" + contents);
        } catch (JSONException ex) {
            // JSON出错
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        } catch (IOException ex) {
            // 发生网络异常
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }

        return result;
    }

    /**
     * PUT调用WebService方法
     *
     * @param url
     *            WebService地址
     * @param paramters
     *            JSON格式的参数
     * @return 调用结果
     * @throws JSONException
     * @throws IOException
     */
    public static RESTHttpResponse put(String url, String paramters, String user, String pwd) throws JSONException, IOException {

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("调用WebService接口开始。Url：" + url);

            JSONObject jsonObj = new JSONObject(paramters);

            HttpClient httpClient = new HttpClient();

            PutMethod method = new PutMethod(url);

            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(user, pwd);
            httpClient.getParams().setAuthenticationPreemptive(true);
            httpClient.getState().setCredentials(AuthScope.ANY, creds);

            method.setDoAuthentication(true);

            method.setRequestEntity(new StringRequestEntity(jsonObj.toString(), "application/json", "utf-8"));

            httpClient.executeMethod(method);

            int statusCode = method.getStatusCode();
            // 设置返回状态码
            result.setStatus(statusCode);

            String contents = method.getResponseBodyAsString();
            // 获取返回内容
            result.setContent(contents);

            logger.debug("调用WebService接口结束。响应状态：" + statusCode + "，响应内容" + contents);
        } catch (JSONException ex) {
            // JSON出错
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        } catch (IOException ex) {
            // 发生网络异常
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }

        return result;
    }

    /**
     * GET调用WebService方法
     *
     * @param url
     *            WebService地址
     * @return 调用结果
     * @throws JSONException
     * @throws IOException
     */
    public static RESTHttpResponse get(String url, String user, String pwd) throws IOException {

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("调用WebService接口开始。Url：" + url);

            HttpClient httpClient = new HttpClient();

            GetMethod method = new GetMethod(url);

            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(user, pwd);
            httpClient.getParams().setAuthenticationPreemptive(true);
            httpClient.getState().setCredentials(AuthScope.ANY, creds);
            method.setDoAuthentication(true);

//			method.setRequestEntity(new StringRequestEntity(jsonObj.toString(), "application/json", "utf-8"));

            httpClient.executeMethod(method);

            int statusCode = method.getStatusCode();
            // 设置返回状态码
            result.setStatus(statusCode);

            String contents = method.getResponseBodyAsString();
            // 获取返回内容
            result.setContent(contents);

            logger.debug("调用WebService接口结束。响应状态：" + statusCode + "，响应内容" + contents);
        }  catch (IOException ex) {
            // 发生网络异常
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }

        return result;
    }

    /**
     * delete调用WebService方法
     *
     * @param url
     *            WebService地址
     * @return 调用结果
     */
    public static RESTHttpResponse delete(String url, String user, String pwd) throws IOException {

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("调用WebService接口开始。Url：" + url);

            HttpClient httpClient = new HttpClient();

            DeleteMethod method = new DeleteMethod(url);

            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(user, pwd);
            httpClient.getParams().setAuthenticationPreemptive(true);
            httpClient.getState().setCredentials(AuthScope.ANY, creds);
            method.setDoAuthentication(true);

            httpClient.executeMethod(method);

            int statusCode = method.getStatusCode();
            // 设置返回状态码
            result.setStatus(statusCode);

            String contents = method.getResponseBodyAsString();
            // 获取返回内容
            result.setContent(contents);

            logger.debug("调用WebService接口结束。响应状态：" + statusCode + "，响应内容" + contents);
        }  catch (IOException ex) {
            // 发生网络异常
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }

        return result;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {

        return startTime;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {

        return endTime;
    }
}
