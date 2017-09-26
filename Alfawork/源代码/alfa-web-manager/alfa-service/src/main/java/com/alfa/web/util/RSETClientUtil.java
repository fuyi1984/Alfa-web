package com.alfa.web.util;

import com.alfa.web.util.pojo.RESTHttpResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by Administrator on 2017/4/26.
 */
public class RSETClientUtil {
    private static Log logger = LogFactory.getLog(RSETClientUtil.class);

    private static long startTime = 0L;
    private static long endTime = 0L;

    /**
     * POST����WebService����
     *
     * @param url
     *            WebService��ַ
     * @param paramters
     *            JSON��ʽ�Ĳ���
     * @return ���ý��
     * @throws JSONException
     * @throws IOException
     */
    public static RESTHttpResponse post(String url, String paramters) throws JSONException, IOException {

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("����WebService�ӿڿ�ʼ��Url��" + url);

            Object jsonObj = null;
            try{
                if(StringUtils.isNotEmpty(paramters)){
                    if(paramters.startsWith("["))
                        jsonObj = new JSONArray(paramters);
                    else
                        jsonObj = new JSONObject(paramters) ;
                }
            }catch (JSONException ex) {
                jsonObj = null;
            }
            HttpPost httpPost = new HttpPost(url);
            if(null !=jsonObj){
                StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }else{
                StringEntity entity = new StringEntity(paramters);
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpPost);
            StatusLine sl = response.getStatusLine();
            int statusCode = sl.getStatusCode();
            // ���÷���״̬��
            result.setStatus(statusCode);

            // ��ȡ��������
            InputStream ins = response.getEntity().getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(ins, writer, "UTF-8");
            result.setContent(writer.toString());

            logger.debug("����WebService�ӿڽ�������Ӧ״̬��" + statusCode + "����Ӧ����" + writer.toString());
        } catch (IOException ex) {
            // ���������쳣
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }

        return result;
    }

    /**
     * POST����WebService����
     *
     * @param url
     *            WebService��ַ
     * @param paramters
     *            JSON��ʽ�Ĳ���
     * @param contentType default "application/json"
     * @param method (post/put/delete/get)
     * @return ���ý��
     * @throws JSONException
     * @throws IOException
     */
    public static RESTHttpResponse request(String url, String paramters,String contentType,String method) throws JSONException, IOException {
        String cntType = contentType;
        if(cntType == null || "".equals(cntType))
            cntType = "application/json";

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("����WebService�ӿڿ�ʼ��Url��" + url);

            Object jsonObj = null;
            if(paramters != null && paramters.startsWith("["))
                jsonObj = new JSONArray(paramters);
            else
                jsonObj = new JSONObject(paramters) ;

            HttpUriRequest httpRequest = null;
            if("post".equals((""+method).toLowerCase())) {
                HttpPost httpPost = new HttpPost(url);
                StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
                entity.setContentType(cntType);
                httpPost.setEntity(entity);
                httpRequest = httpPost;
            }else if("put".equals((""+method).toLowerCase())) {
                HttpPut httpPut = new HttpPut(url);
                StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
                entity.setContentType(cntType);
                httpPut.setEntity(entity);
                httpRequest = httpPut;
            }else if("get".equals((""+method).toLowerCase())) {
                String finalUrl = ""+url;
                if(paramters!= null && paramters.contains("=")) {
                    //��paramters��ӵ�url����
                    if (url != null && url.contains("&"))
                        finalUrl = finalUrl + "&" + paramters;
                    else if (url != null && url.contains("?"))
                        finalUrl = finalUrl + paramters;
                    else finalUrl = finalUrl + "?" + paramters;
                }
                httpRequest = new HttpGet(finalUrl);
            }else if("delete".equals((""+method).toLowerCase())) {
                String finalUrl = ""+url;
                if(paramters!= null && paramters.contains("=")) {
                    //��paramters��ӵ�url����
                    if (url != null && url.contains("&"))
                        finalUrl = finalUrl + "&" + paramters;
                    else if (url != null && url.contains("?"))
                        finalUrl = finalUrl + paramters;
                    else finalUrl = finalUrl + "?" + paramters;
                }

                httpRequest = new HttpDelete(finalUrl);
            }

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpRequest);
            StatusLine sl = response.getStatusLine();
            int statusCode = sl.getStatusCode();
            // ���÷���״̬��
            result.setStatus(statusCode);

            // ��ȡ��������
            InputStream ins = response.getEntity().getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(ins, writer, "UTF-8");
            result.setContent(writer.toString());

            logger.debug("����WebService�ӿڽ�������Ӧ״̬��" + statusCode + "����Ӧ����" + writer.toString());
        } catch (JSONException ex) {
            // JSON����
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        } catch (IOException ex) {
            // ���������쳣
            logger.error(ExceptionUtils.getStackTrace(ex));
            throw ex;
        }

        return result;
    }

    /**
     * get����WebService����
     *
     * @param url
     *            WebService��ַ
     * @return ���ý��
     * @throws IOException
     */
    public static RESTHttpResponse get(String url) throws IOException {

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("����WebService�ӿڿ�ʼ��Url��" + url);
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/json; charset=" + HTTP.UTF_8);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            // ���÷���״̬��
            result.setStatus(statusCode);
            logger.info("statusCode:" + statusCode);

            // ��ȡ��������
            InputStream ins = response.getEntity().getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(ins, writer, "UTF-8");
            result.setContent(writer.toString());
            logger.debug("Response Infromation:" + writer.toString());
        } catch (IOException e) {
            // ���������쳣
            logger.error("exception occurred!\n" + ExceptionUtils.getFullStackTrace(e));
            throw e;
            // �������
        }

        return result;
    }

    /**
     * delete����WebService����
     *
     * @param url
     *            WebService��ַ
     * @return ���ý��
     */
    public static RESTHttpResponse delete(String url) {

        RESTHttpResponse result = new RESTHttpResponse();

        try {
            logger.debug("����WebService�ӿڿ�ʼ��Url��" + url);
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.addHeader("Content-Type", "application/json; charset=" + HTTP.UTF_8);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpDelete);
            int statusCode = response.getStatusLine().getStatusCode();
            // ���÷���״̬��
            result.setStatus(statusCode);

            // ��ȡ��������
            InputStream ins = response.getEntity().getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(ins, writer, "UTF-8");
            result.setContent(writer.toString());
            logger.debug("Response Infromation:" + writer.toString());
        } catch (IOException e) {
            // ���������쳣
            logger.error("exception occurred!\n" + ExceptionUtils.getFullStackTrace(e));
            // �������
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
