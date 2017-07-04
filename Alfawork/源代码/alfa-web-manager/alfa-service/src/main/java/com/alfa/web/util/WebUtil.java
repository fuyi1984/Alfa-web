package com.alfa.web.util;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.*;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.Random;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alfa.web.util.StringUtil;
import com.alfa.web.util.pojo.UserManager;
import com.alfa.web.util.pojo.UserSession;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;

/**
 * Created by Administrator on 2017/4/27.
 * Web层相关的实用工具类
 */
public class WebUtil {

    /**
     * 下拉列表框显示值Key
     */
    public static final String COMBOX_TEXT_FIELD = "textFieldKey";

    /**
     * 下拉列表框实际值Key
     */
    public static final String COMBOX_VALUE_FIELD = "valueFieldKey";

    private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 将请求参数封装为Map<br>
     * request中的参数t1=1&t1=2&t2=3<br>
     * 形成的map结构：<br>
     * key=t1;value=1,2<br>
     * key=t2;value=3<br>
     *
     * @param request HTTP请求对象
     * @return 封装后的Map
     */
    public static HashMap<String, String> getParameterMap(HttpServletRequest request) {

        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值HaspMap
        HashMap<String, String> returnMap = new HashMap<String, String>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 设置分页查询条件相关参数。
     *
     * @param request              HTTP请求对象(从前台获取以"qm."开头的查询条件)
     * @param pager                分页请求参数对象
     * @param criteria             查询条件
     * @param defaultOrderByClause 默认排序条件
     */
    public static void preparePageParams(HttpServletRequest request, BasePager pager, Criteria criteria,
                                         String defaultOrderByClause) {

        preparePageParams(pager, criteria, defaultOrderByClause);

        // 将请求参数Map
        HashMap<String, String> paramMap = getParameterMap(request);
        // 将查询条件设置到criteria中
        Iterator<Entry<String, String>> keyIterator = paramMap.entrySet().iterator();
        while (keyIterator.hasNext()) {
            Entry<String, String> entry = keyIterator.next();
            String key = entry.getKey();
            if (key.indexOf("qm.") == 0 && StringUtils.isNotBlank(entry.getValue())) {
                criteria.put(key.substring(3), entry.getValue());
            }
        }
    }

    /**
     * 设置分页查询条件相关参数。
     *
     * @param criteria             查询条件
     * @param defaultOrderByClause 默认排序条件
     */
    public static void preparePageParamsNew(String param, Criteria criteria,
                                            String defaultOrderByClause) {
        Map map = WebUtil.getParamsMap(param, "utf-8");
        //分页排序处理
        BasePager pager = new BasePager();

        if (!StringUtil.isNullOrEmpty(map.get("pagenum"))) {
            pager.setPageIndex(Integer.parseInt(map.get("pagenum").toString()));
        }
        if (!StringUtil.isNullOrEmpty(map.get("pagesize"))) {
            pager.setPageSize(Integer.parseInt(map.get("pagesize").toString()));
        }
        if (!StringUtil.isNullOrEmpty(map.get("sortdatafield"))) {
            pager.setSortField(map.get("sortdatafield").toString());
        }
        if (!StringUtil.isNullOrEmpty(map.get("sortorder"))) {
            pager.setSortOrder(map.get("sortorder").toString());
        }
        if (StringUtil.isNullOrEmpty(pager.getPageSize()) || pager.getPageSize() == 0) {
            criteria.setOrderByClause(defaultOrderByClause);
        } else {
            preparePageParams(pager, criteria, defaultOrderByClause);
        }

        // 将请求参数Map
        // 将查询条件设置到criteria中
        Iterator<Entry<String, String>> keyIterator = map.entrySet().iterator();
        while (keyIterator.hasNext()) {
            Entry<String, String> entry = keyIterator.next();
            String key = entry.getKey();
            if (key.indexOf("qm.") == 0 && StringUtils.isNotBlank(entry.getValue())) {
                criteria.put(key.substring(3), entry.getValue());
            }
        }
    }

    /**
     * 不含排序功能
     *
     * @param pager
     * @param criteria
     */
    public static void preparePageParams(BasePager pager, Criteria criteria) {
        if (pager == null || criteria == null) {
            return;
        }

        // 设置分页信息
        if (pager.getRecordStart() != null && pager.getRecordEnd() != null) {
            // TODO
            criteria.setMysqlLength(pager.getRecordEnd());
            criteria.setMysqlOffset(pager.getRecordStart());
        }
    }

    /**
     * 分页,排序
     *
     * @param pager
     * @param criteria
     * @param defaultOrderByClause
     */
    public static void preparePageParams(BasePager pager, Criteria criteria,
                                         String defaultOrderByClause) {

        preparePageParams(pager, criteria);

        if (pager != null && StringUtils.isNotBlank(pager.getSortOrder()) && StringUtils.isNotBlank(pager.getSortField())) {
            criteria.setOrderByClause(pager.getSortField() + " " + pager.getSortOrder() + ", " + defaultOrderByClause);
        } else {
            // 默认以帐号排序
            if (StringUtils.isNotBlank(defaultOrderByClause)) {
                criteria.setOrderByClause(defaultOrderByClause);
            }
        }
    }

    /**
     * 把pojo字段转为数据库字段<br>
     * fileName -> FILE_NAME
     *
     * @param field 变量名
     * @return 字段名
     */
    public static String toClumn(String field) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.length(); i++) {
            char c = field.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append("_").append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }

    /**
     * 使用Response输出指定格式内容.
     */
    protected static void render(HttpServletResponse response, String text, String contentType) {

        try {
            response.setContentType(contentType);
            if (StringUtil.isNullOrEmpty(text)) {
                text = "";
            }
            response.getWriter().write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接输出字符串.
     */
    public static void renderText(HttpServletResponse response, String text) {

        render(response, text, "text/plain;charset=UTF-8");
    }

    /**
     * 直接输出JSON.
     */
    public static void renderJson(HttpServletResponse response, String text) {

        render(response, text, "application/json; charset=utf-8");
    }

    /**
     * 直接输出HTML.
     */
    public static void renderHtml(HttpServletResponse response, String html) {

        render(response, html, "text/html;charset=UTF-8");
    }

    /**
     * 直接输出XML.
     */
    public static void renderXML(HttpServletResponse response, String xml) {

        render(response, xml, "text/xml;charset=UTF-8");
    }

    /**
     * MD5加密5
     *
     * @param data 数据值
     * @param salt 加密添加字符串
     * @return 加密后字符串
     */
    public static String encrypt(String data, String salt) {

        // 可以更换算法:sha512Hex
        return DigestUtils.md5Hex("loongrender" + data);
    }

    /**
     * BASE64加密
     *
     * @param data
     * @return
     */
    public static String encryptBase64(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    /**
     * BASE64解密
     *
     * @param data
     * @return
     */
    public static String decryptBase64(String data) {
        return new String(Base64.decodeBase64(data));
    }

    /**
     * 新增时添加用户以及当前时间信息
     *
     * @param obj
     */
    public static <T> void prepareInsertParams(T obj) {

        if (AuthUtil.getAuthUser() != null && AuthUtil.getAuthUser().getUsername()!= null) {
            // 创建人
            invokeSet(obj, "createdBy", AuthUtil.getAuthUser().getUsername());
            // 更新人
            invokeSet(obj, "updatedBy", AuthUtil.getAuthUser().getUsername());
        }

        Date date = new Date();
        // 更新时间
        invokeSet(obj, "createdDt", date);
        // 更新时间
        invokeSet(obj, "updatedDt", date);
        // 初始版本号
        invokeSet(obj, "version", 1L);
    }

    /**
     * 新增时添加用户以及当前时间信息(For Activiti)
     *
     * @param obj
     */
    public static <T> void prepareInsertParams(T obj, String user) {
        if (user != null && !StringUtil.EMPTY.equals(user)) {
            // 创建人
            invokeSet(obj, "createdBy", user);
            // 更新人
            invokeSet(obj, "updatedBy", user);
        }
        Date date = new Date();
        // 更新时间
        invokeSet(obj, "createdDt", date);
        // 更新时间
        invokeSet(obj, "updatedDt", date);
        // 初始版本号
        invokeSet(obj, "version", 1L);
    }

    /**
     * 新增时添加管理员用户以及当前时间信息
     *
     * @param obj
     */
    public static <T> void prepareInsertAdminParams(T obj) {
        // 创建人
        invokeSet(obj, "createdBy", "admin");
        // 更新人
        invokeSet(obj, "updatedBy", "admin");

        Date date = new Date();
        // 更新时间
        invokeSet(obj, "createdDt", date);
        // 更新时间
        invokeSet(obj, "updatedDt", date);
        // 初始版本号
        invokeSet(obj, "version", 1L);
    }

    /**
     * 更新时添加用户以及当前时间信息
     *
     * @param obj
     */
   public static <T> void prepareUpdateParams(T obj) {
        if (AuthUtil.getAuthUser() != null && AuthUtil.getAuthUser().getUsername() != null) {
            // 更新人
            invokeSet(obj, "updatedBy", AuthUtil.getAuthUser().getUsername());
        }

        //invokeSet(obj, "updatedBy", "test");
        // 更新时间
        Date date = new Date();
        invokeSet(obj, "updatedDt", date);
    }

    /**
     * 更新时添加用户以及当前时间信息
     *
     * @param obj
     */
    public static <T> void prepareUpdateParams(T obj, String user) {
        if (user != null && !StringUtil.EMPTY.equals(user)) {
            // 更新人
            invokeSet(obj, "updatedBy", user);
        }
        // 更新时间
        Date date = new Date();
        invokeSet(obj, "updatedDt", date);
    }

    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    // TODO
    // private static Method getGetMethod(Class<? extends Object> objectClass, String fieldName) {
    // StringBuffer sb = new StringBuffer();
    // sb.append("get");
    // sb.append(fieldName.substring(0, 1).toUpperCase());
    // sb.append(fieldName.substring(1));
    // try {
    // return objectClass.getMethod(sb.toString());
    // } catch (Exception e) {
    // }
    // return null;
    // }

    /**
     * java反射bean的set方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Method getSetMethod(Class<? extends Object> objectClass, String fieldName) {

        try {
            Class[] parameterTypes = new Class[1];
            Field field = getField(objectClass, fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取类已经类的父的某一声明变量
     *
     * @param clazz
     * @param fieldName 变量名
     * @return Field 变量
     * @throws NoSuchFieldException
     */
    @SuppressWarnings("rawtypes")
    private static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    /**
     * 执行set方法
     *
     * @param o
     * @param value
     * @param fieldName
     */
    private static void invokeSet(Object o, String fieldName, Object value) {

        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行get方法
     *
     * @param o执行对象
     * @param fieldName属性
     */
    // TODO
    // private static Object invokeGet(Object o, String fieldName) {
    // Method method = getGetMethod(o.getClass(), fieldName);
    // try {
    // return method.invoke(o, new Object[0]);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    /**
     * 取得系统消息
     *
     * @param msgId 消息ID
     * @param arg   消息设置参数
     * @return 消息内容
     */
    public static String getMessage(String msgId, Object[] arg) {
        String message = StringUtils.EMPTY;
        try {
            // 获取消息处理类
            MessageSource messageSource = new ClassPathXmlApplicationContext(
                    "classpath:/config/spring/spring-message-bean.xml");
            UserSession currentUser = UserManager.getUserSession();
            if (StringUtil.isNullOrEmpty(currentUser) || StringUtil.isNullOrEmpty(currentUser.getLanguageCode()))
                return messageSource.getMessage(msgId, arg, Locale.CHINA);
            if (currentUser.getLanguageCode().equals("en")) {
                message = messageSource.getMessage(msgId, arg, Locale.US);
            } else {
                message = messageSource.getMessage(msgId, arg, Locale.CHINA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    /**
     * 取得系统消息
     *
     * @param msgId 消息ID
     * @return 消息内容
     */
    public static String getMessage(String msgId) {
        return WebUtil.getMessage(msgId, null);
    }

    /**
     * 取得前后N年分下拉列表Map
     *
     * @param yearCount   指定年数
     * @param addPreYear  是否取得前N年份(true:是;false:否)
     * @param addNextYear 是否取得后N年份(true:是;false:否)
     * @return 下拉列表Map
     */
    public static Map<String, String> getYearMap(int yearCount, boolean addPreYear, boolean addNextYear) {

        Map<String, String> yearMap = new LinkedHashMap<String, String>();

        // 日历取得
        Calendar cder = Calendar.getInstance();

        int cYear = cder.get(Calendar.YEAR);

        // 前N年
        if (addPreYear) {
            for (int i = yearCount; i >= 1; i--) {
                yearMap.put(String.valueOf(cYear - i), String.valueOf(cYear - i));
            }
        }
        // 今年
        yearMap.put(String.valueOf(cYear), String.valueOf(cYear));
        // 后N年
        if (addNextYear) {
            for (int j = 1; j <= yearCount; j++) {
                yearMap.put(String.valueOf(cYear + j), String.valueOf(cYear + j));
            }
        }

        return yearMap;
    }

    /**
     * 取得月份下拉列表Map
     *
     * @return 下拉列表Map
     */
    public static Map<String, String> getMonthMap() {

        Map<String, String> monthMap = new LinkedHashMap<String, String>();

        // 12月份
        monthMap.put("1", "01");
        monthMap.put("2", "02");
        monthMap.put("3", "03");
        monthMap.put("4", "04");
        monthMap.put("5", "05");
        monthMap.put("6", "06");
        monthMap.put("7", "07");
        monthMap.put("8", "08");
        monthMap.put("9", "09");
        monthMap.put("10", "10");
        monthMap.put("11", "11");
        monthMap.put("12", "12");

        return monthMap;
    }

    /**
     * 取得前后N年年份中文下拉列表Map
     *
     * @param yearCount   指定年数
     * @param addPreYear  是否取得前N年份(true:是;false:否)
     * @param addNextYear 是否取得后N年份(true:是;false:否)
     * @return 下拉列表Map
     */
    public static Map<String, String> getYearChineseMap(int yearCount, boolean addPreYear, boolean addNextYear) {

        Map<String, String> yearMap = new LinkedHashMap<String, String>();

        // 日历取得
        Calendar cder = Calendar.getInstance();

        int cYear = cder.get(Calendar.YEAR);

        // 前N年
        if (addPreYear) {
            for (int i = yearCount; i >= 1; i--) {
                yearMap.put(String.valueOf(cYear - i), String.valueOf(cYear - i) + "年");
            }
        }
        // 今年
        yearMap.put(String.valueOf(cYear), String.valueOf(cYear) + "年");
        // 后N年
        if (addNextYear) {
            for (int j = 1; j <= yearCount; j++) {
                yearMap.put(String.valueOf(cYear + j), String.valueOf(cYear + j) + "年");
            }
        }

        return yearMap;
    }

    public static Map<String, String> getMonthChineseMap() {
        Map<String, String> monthMap = new LinkedHashMap<String, String>();

        // 12月份
        monthMap.put("1", "1月");
        monthMap.put("2", "2月");
        monthMap.put("3", "3月");
        monthMap.put("4", "4月");
        monthMap.put("5", "5月");
        monthMap.put("6", "6月");
        monthMap.put("7", "7月");
        monthMap.put("8", "8月");
        monthMap.put("9", "9月");
        monthMap.put("10", "10月");
        monthMap.put("11", "11月");
        monthMap.put("12", "12月");

        return monthMap;
    }

    public static Map<String, String> getWeekMap() {
        Map<String, String> weekMap = new LinkedHashMap<String, String>();
        weekMap.put("0", "星期日");
        weekMap.put("1", "星期一");
        weekMap.put("2", "星期二");
        weekMap.put("3", "星期三");
        weekMap.put("4", "星期四");
        weekMap.put("5", "星期五");
        weekMap.put("6", "星期六");
        return weekMap;
    }

    public static Map<String, String> getQuarterMap() {
        Map<String, String> quarterMap = new LinkedHashMap<String, String>();
        quarterMap.put("1", "第一季度");
        quarterMap.put("2", "第二季度");
        quarterMap.put("3", "第三季度");
        quarterMap.put("4", "第四季度");
        return quarterMap;
    }

	/*
     * public static String getYearMapJson(int yearCount, boolean addPreYear, boolean addNextYear) {
	 * ArrayList<Map<String, String>> yearList = new ArrayList<Map<String, String>>(); // 日历取得 Calendar cder =
	 * Calendar.getInstance(); int cYear = cder.get(Calendar.YEAR); Map<String, String> yearMap = null; // 前N年 if
	 * (addPreYear) { for (int i = yearCount; i >= 1; i--) { yearMap = new LinkedHashMap<String, String>();
	 * yearMap.put(WebUtil.COMBOX_TEXT_FIELD, String.valueOf(cYear - i) + "年"); yearMap.put(WebUtil.COMBOX_VALUE_FIELD,
	 * String.valueOf(cYear - i)); yearList.add(yearMap); } } // 今年 yearMap = new LinkedHashMap<String, String>();
	 * yearMap.put(WebUtil.COMBOX_TEXT_FIELD, String.valueOf(cYear) + "年"); yearMap.put(WebUtil.COMBOX_VALUE_FIELD,
	 * String.valueOf(cYear)); yearList.add(yearMap); // 后N年 if (addNextYear) { for (int j = 1; j <= yearCount; j++) {
	 * yearMap = new LinkedHashMap<String, String>(); yearMap.put(WebUtil.COMBOX_TEXT_FIELD, String.valueOf(cYear + j) +
	 * "年"); yearMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(cYear + j)); yearList.add(yearMap); } } return
	 * JsonUtil.Encode(yearList); }
	 *
	 * public static String getMonthChineseMapJson() { ArrayList<Map<String, String>> monthList = new
	 * ArrayList<Map<String, String>>();
	 *
	 * for (int i = 1; i <= 12; i++) { Map<String, String> monthMap = new HashMap<String, String>();
	 * monthMap.put(WebUtil.COMBOX_TEXT_FIELD, String.valueOf(i) + "月"); monthMap.put(WebUtil.COMBOX_VALUE_FIELD,
	 * String.valueOf(i));
	 *
	 * monthList.add(monthMap); }
	 *
	 * return JsonUtil.Encode(monthList); }
	 *
	 * public static String getWeekMapJson() { ArrayList<Map<String, String>> weekList = new ArrayList<Map<String,
	 * String>>(); String[] num = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; for (int i = 0; i <=
	 * 6; i++) { Map<String, String> weekMap = new LinkedHashMap<String, String>();
	 * weekMap.put(WebUtil.COMBOX_TEXT_FIELD, num[i]); weekMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(i + 1));
	 * weekList.add(weekMap); } return JsonUtil.Encode(weekList); }
	 *
	 * public static String getQuarterMapJson() { ArrayList<Map<String, String>> quarterList = new ArrayList<Map<String,
	 * String>>(); String[] num = new String[] { "第一季度", "第二季度", "第三季度", "第四季度" }; for (int i = 0; i < 4; i++) {
	 * Map<String, String> quarterMap = new LinkedHashMap<String, String>(); quarterMap.put(WebUtil.COMBOX_TEXT_FIELD,
	 * num[i]); quarterMap.put(WebUtil.COMBOX_VALUE_FIELD, String.valueOf(i + 1)); quarterList.add(quarterMap); } return
	 * JsonUtil.Encode(quarterList); }
	 */

    /**
     * 取得客户端真实IP
     *
     * @param request
     * @return 客户端真实IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 自定义获取客户端的IP地址
     * @param request
     * @return
     */
    public static String GetCustomIpAddr(HttpServletRequest request){
        String client_ip = request.getHeader("x-forwarded-for");
        if(client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
            client_ip = request.getHeader("Proxy-Client-IP");
        }
        if(client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
            client_ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
            client_ip = request.getRemoteAddr();
            if(client_ip.equals("127.0.0.1") || client_ip.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                client_ip = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(client_ip != null && client_ip.length() > 15){ //"***.***.***.***".length() = 15
            if(client_ip.indexOf(",") > 0){
                client_ip = client_ip.substring(0,client_ip.indexOf(","));
            }
        }

        //String server_ip = InetAddress.getLocalHost().getHostAddress();
        return client_ip;
    }

    /**
     * 根据当前系统时间生成ID
     *
     * @return 精确到毫秒
     */
    public static long IdGenerator() {

        long baseId;
        long t = System.currentTimeMillis();
        // 52~43
        baseId = t;
        baseId &= 0x1FF0000000L;
        baseId <<= 14;
        // 28~15
        t &= 0xFFFC000L;
        baseId |= t;
        // 42~29
        SecureRandom ng = new SecureRandom();
        t = ng.nextLong();
        t &= 0x3FFF0000000L;
        // 14~1
        baseId |= t;
        baseId |= t;
        baseId /= 10000;
        baseId *= 10000;
        baseId &= 0x1FFFFFFFFFFFFL;
        return baseId;

    }

    /**
     * 查询数据字典
     *
     * @param category
     *            数据字典类型
     * @return List<Code> 返回

    public static List<Code> findListCode(String category) {
    List<Code> list = new ArrayList<Code>();
    Criteria criteria = new Criteria();
    criteria.put("codeCategory", category);
    criteria.setOrderByClause("sort");
    CodeService codeService = SpringContextHolder.getBean("codeServiceImpl");
    list = codeService.selectByParams(criteria);
    return list;
    }
     */

    /**
     * 根据字段值和数据字典类型查询字段显示值
     *
     * @param codeValue
     * @param category
     * @return public static String findDisplayByParams(String codeValue, String category) {
    Criteria criteria = new Criteria();
    criteria.put("codeValue", codeValue);
    criteria.put("codeCategory", category);
    CodeService codeService = SpringContextHolder.getBean("codeServiceImpl");
    List<Code> list = codeService.selectByParams(criteria);
    Code code = list.get(0);
    return code.getCodeDisplay();
    }
     */
    /**
     * 生成指定位数随机密码
     *
     * @return 随机密码
     */
    public static String randomPwd(int length) {
        String randomPwd = "";
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            randomPwd += buffer.charAt(r.nextInt(range));
        }
        return randomPwd;
    }

    /**
     * generate captcha
     *
     * @param length
     * @return
     */
    public static String randomCaptcha(int length) {
        String randomPwd = "";
        StringBuffer buffer = new StringBuffer("0123456789");
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            randomPwd += buffer.charAt(r.nextInt(range));
        }
        return randomPwd;
    }

    /**
     * 生成随机UUID值
     *
     * @return 随机密码
     */
    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成指定位数随机帐号+日期
     *
     * @return 随机帐号+日期
     */
    public static String randomAccount(int length) {
        String randomAccount = "";
        StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            randomAccount += buffer.charAt(r.nextInt(range));
        }
        Calendar cal = Calendar.getInstance();//使用日历类
        String year = cal.get(Calendar.YEAR) + "";//得到年
        String month = (cal.get(Calendar.MONTH) + 1) + "";//得到月，因为从0开始的，所以要加1
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = cal.get(Calendar.DAY_OF_MONTH) + "";//得到天
        if (day.length() == 1) {
            day = "0" + day;
        }
        String h = cal.get(Calendar.HOUR_OF_DAY) + "";
        if (h.length() == 1) {
            h = "0" + h;
        }
        String m = cal.get(Calendar.MINUTE) + "";
        if (m.length() == 1) {
            m = "0" + m;
        }
        String s = cal.get(Calendar.SECOND) + "";
        if (s.length() == 1) {
            s = "0" + s;
        }
        randomAccount = PropertiesUtil.getProperty("user.account.prefix") + year.substring(2, 4) + month + day + h + m + s;
        return randomAccount;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            logger.info("sendPost, url:" + url);
            logger.info("sendPost, param:" + param);
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(1000 * 360);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String contentType) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(1000 * 360);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendOctetPost(String url, String param, String contentType, DataHandler dataHandler) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(1000 * 360);
            // 获取URLConnection对象对应的输出流
            OutputStream os = conn.getOutputStream();
            out = new PrintWriter(os);
            // 发送请求参数
            out.print(param);
            dataHandler.writeTo(os);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param urlstr 发送请求的 URL
     * @param param  请求参数，Json串
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostJson(String urlstr, String param) {
        StringBuffer sb = new StringBuffer("");
        try {
            //创建连接
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;

            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static Map getParamsMap(String queryString, String enc) {
        Map paramsMap = new HashMap();
        if (queryString != null && queryString.length() > 0) {
            int ampersandIndex, lastAmpersandIndex = 0;
            String subStr, param, value;
            String[] paramPair, values, newValues;
            do {
                ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
                if (ampersandIndex > 0) {
                    subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);
                    lastAmpersandIndex = ampersandIndex;
                } else {
                    subStr = queryString.substring(lastAmpersandIndex);
                }
                paramPair = subStr.split("=");
                param = paramPair[0];
                value = paramPair.length == 1 ? "" : paramPair[1];
                try {
                    value = URLDecoder.decode(value, enc);
                } catch (UnsupportedEncodingException ignored) {
                }
                if (paramsMap.containsKey(param)) {
                    values = (String[]) paramsMap.get(param);
                    int len = values.length;
                    newValues = new String[len + 1];
                    System.arraycopy(values, 0, newValues, 0, len);
                    newValues[len] = value;
                } else {
                    newValues = new String[]{value};
                }
                paramsMap.put(param, value);
            } while (ampersandIndex > 0);
        }
        return paramsMap;
    }

    /**
     * 新增时  对某个字段赋值
     *
     * @param obj
     * @param property
     * @param value    add by qct 2014-07-30
     */
    public static <T> void prepareProperty(T obj, String property, Object value) {
        invokeSet(obj, property, value);
    }
}
