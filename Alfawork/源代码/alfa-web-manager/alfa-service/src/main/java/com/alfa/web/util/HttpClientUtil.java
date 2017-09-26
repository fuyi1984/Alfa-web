package com.alfa.web.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

/**
 * Created by Administrator on 2017/4/26.
 */
public class HttpClientUtil {
    private static Log log = LogFactory.getLog(HttpClientUtil.class);

    private Integer connectTimeout = 3000;

    private Integer readTimeout = 2000;

    private String encoding = "UTF-8";

    private boolean useProxy = false;

    private String proxyUrl = "web-proxy.corp.hp.com";
    private int proxyPort = 8080;
    private Type proxyType = Type.HTTP;

    /**
     * 调用url，将结果以字符串形式返回
     * @param url
     * @return
     * @throws IOException
     */
    public String connect(String url,String method,String content) throws IOException{
        if(url==null) return null;
        if(url.trim().isEmpty()) return null;
        if(method.equalsIgnoreCase("get")&&!StringUtil.isNullOrEmpty(content)){
            url = url+"?"+content;
        }
        URL u = new URL(url);
        HttpURLConnection connection = null;
        if(useProxy){
            InetSocketAddress addr = new InetSocketAddress(proxyUrl,proxyPort);
            Proxy proxy = new Proxy(proxyType, addr);
            connection = (HttpURLConnection) u.openConnection(proxy);
        }else{
            connection = (HttpURLConnection) u.openConnection();
        }
        connection.setConnectTimeout(connectTimeout);
        connection.setUseCaches(false);
        connection.setReadTimeout(readTimeout);
        connection.setRequestMethod(method.toUpperCase());
        connection.setDoOutput(true);
        connection.setDoInput(true);

        PrintWriter printWriter = null;
        if(method.equalsIgnoreCase("post")){
            connection.setRequestProperty("Content-Length", String.valueOf(content.length()));
            printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(content);
            printWriter.flush();
        }

        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        int code = 0;
        try {
            code = connection.getResponseCode();
            if (code == 200) {
                log.debug(url + "connected ok!");
                InputStream in = null;
                in = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(in, encoding));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } else {
                log.error("connected failure with code " + code);
                connection.disconnect();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {}
            }
            if(null!=printWriter){
                printWriter.close();
            }
            connection.disconnect();
        }
        return sb.toString();
    }

    public String connect(String url, String method, byte[] content) throws IOException {
        if(url==null) return null;
        if(url.trim().isEmpty()) return null;
        if(method.equalsIgnoreCase("get")){
            return null;
        }
        URL u = new URL(url);
        HttpURLConnection connection = null;
        if(useProxy){
            InetSocketAddress addr = new InetSocketAddress(proxyUrl,proxyPort);
            Proxy proxy = new Proxy(proxyType, addr);
            connection = (HttpURLConnection) u.openConnection(proxy);
        }else{
            connection = (HttpURLConnection) u.openConnection();
        }
        connection.setConnectTimeout(connectTimeout);
        connection.setUseCaches(false);
        connection.setReadTimeout(readTimeout);
        connection.setRequestMethod(method.toUpperCase());
        connection.setDoOutput(true);
        connection.setDoInput(true);

        PrintWriter printWriter = null;
        if(method.equalsIgnoreCase("post")){
            OutputStream out = connection.getOutputStream();
            out.write(content);
            out.flush();
            out.close();
//			connection.setRequestProperty("Content-Length", String.valueOf(content.length()));
//			printWriter = new PrintWriter(connection.getOutputStream());
//	        printWriter.write(content);
//	        printWriter.flush();
        }

        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        int code = 0;
        try {
            code = connection.getResponseCode();
            if (code == 200) {
                log.debug(url + "connected ok!");
                InputStream in = null;
                in = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(in, encoding));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } else {
                log.error("connected failure with code " + code);
                connection.disconnect();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {}
            }
            if(null!=printWriter){
                printWriter.close();
            }
            connection.disconnect();
        }
        return sb.toString();
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isUseProxy() {
        return useProxy;
    }

    public void setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Type getProxyType() {
        return proxyType;
    }

    public void setProxyType(Type proxyType) {
        this.proxyType = proxyType;
    }
}
