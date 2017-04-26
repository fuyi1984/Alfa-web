package com.alfa.web.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.util.Log;

/**
 * Created by Administrator on 2017/4/26.
 */
public class StringUtil {

    /** 日期格式字符串 */
    public static final String DF_YMD = "yyyy-MM-dd";
    /** 日期格式字符串 */
    public static final String DF_YMD_24 = "yyyy-MM-dd HH:mm:ss";
    /** 日期格式字符串 */
    public static final String DF_EEE_MMM = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 判断字符串是否为空
     *
     * @param obj
     * @return ture:为空 false:不为空
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || EMPTY.equals(value);
    }

    /**
     * 判断对象字符串是否为空
     *
     * @param obj
     * @return ture:为空 false:不为空
     */
    public static boolean isNullOrEmpty(Object value) {
        return value == null || EMPTY.equals(value);
    }

    /**
     * 判断字符串是否是数字
     *
     * @param obj
     * @return ture:是 false:否
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否是数字
     *
     * @param obj
     * @return ture:是 false:否
     */
    public static boolean isNumericS(String str){
        if(StringUtil.isNullOrEmpty(str))
            return false;
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * 删除前后全角半角空格和tab
     *
     * @param value
     *            处理值
     * @return 处理字符串
     */
    public static String delSpace(String value) {
        if (isNullOrEmpty(value)) {
            return EMPTY;
        }
        value = value.replaceAll("^[　 \t]+|[　 \t]+$", "");
        return value;
    }

    /**
     * 当字符串为null的时候、返回空字符串 ("") 。<br>
     * 不为null的场合返回传入字符串。
     *
     * @param str
     *            处理值
     * @return 处理字符串
     */
    public static String nullToEmpty(String str) {
        return (str != null) ? str : EMPTY;
    }

    /**
     * 当对象为null的时候、返回空字符串 ("") 。<br>
     * 不为null的场合返回传入对象的字符串。
     *
     * @param obj
     *            对象
     * @return 处理字符串
     */
    public static String nullToEmpty(Object obj) {
        return (obj != null) ? obj.toString() : EMPTY;
    }

    /**
     * 字符串转换成日期<br>
     * value为yyyy-MM-dd格式
     *
     * @param value
     *            处理值
     * @return 转换字符串
     */
    public static Date strToDate(String value) {
        return strToDate(value, DF_YMD);
    }

    /**
     * 字符串转换成日期
     *
     * @param value
     *            处理值
     * @param format
     *            处理值日期格式
     * @return 转换字符串
     */
    public static Date strToDate(String value, String format) {
        Date result;
        if (value == null) {
            result = null;
        } else {
            SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
            sdf.applyPattern(format);
            sdf.setLenient(false);
            result = sdf.parse(value, new ParsePosition(0));
        }
        return result;
    }

    /**
     * 字符串转换成日期
     *
     * @param value
     *            处理值
     * @param format
     *            处理值日期格式 locale
     * @param locale
     *            locale
     * @return 转换字符串
     */
    public static Date strToDate(String value, String format, Locale locale) {
        if(value == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(format, locale).parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转换字符串<br>
     * 默认yyyy-MM-dd格式
     *
     * @param value
     *            处理值
     * @return String 转换值
     *
     */
    public static String dateFormat(Date value) {
        return dateFormat(value, DF_YMD);
    }

    /**
     * 日期转换字符串
     *
     * @param value
     *            处理值
     * @param format
     *            日期格式
     * @return String 转换值
     *
     */
    public static String dateFormat(Date value, String format) {
        if (value == null || format == null) {
            return EMPTY;
        }

        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
        sdf.applyPattern(format);
        return sdf.format(value);
    }

    /**
     * 将整数转换成指定长度字符传，前面加0补位
     *
     * @param value
     *            要转换的整数
     * @param length
     *            长度
     * @return
     */
    public static String formatIntToString(Long value, Integer length) {
        if (value == null) {
            return null;
        }
        String strValue = value.toString();
        while (strValue.length() < length) {
            strValue = "0" + strValue;
        }
        return strValue;
    }

    /**
     * StringBuffer替换
     *
     * @param target
     *            目标字符串
     * @param value
     *            替换值
     * @param source
     *            替换字符串
     * @return StringBuffer
     */
    public static StringBuffer strBufReplace(String target, String value, StringBuffer source) {
        if (source == null || target == null) {
            return source;
        }
        if (StringUtil.isNullOrEmpty(value)) {
            value = "";
        }

        int index = -1;

        while ((index = source.indexOf(target)) != -1) {
            source.replace(index, index + target.length(), value);
        }

        return source;
    }

    /**
     * 获取字符串中第N次出现的字符位置
     *
     * @param value
     *            要获取的字符串
     * @param character
     *            字符
     * @param pos
     *            第几次出现
     * @return
     */
    public static int getCharacterPosition(String value, String character, int pos){
        //这里是获取"subString"符号的位置
        Matcher slashMatcher = Pattern.compile(character).matcher(value);
        int resultPos = 0;
        int mIdx = 0;
        while(slashMatcher.find()) {
            mIdx++;
            //当"subString"符号第pos次出现的位置
            if(mIdx == pos){
                resultPos = slashMatcher.start();
                break;
            }
        }
        return resultPos;
    }


    /**
     * 获取文件绝对路径中的文件扩展名
     *
     * @param path
     *            文件绝对路径
     * @return
     */
    public static String getFileExt(String path) {
        String result = "";
        result = path.substring(path.lastIndexOf(".")+1);
        return result;
    }

    public static String getFileNameWithoutExt(String path) {
        String result = "";
        result = path.substring(0, path.lastIndexOf("."));
        return result;
    }


    /**
     * 获取文件绝对路径中的文件名
     *
     * @param path
     *            文件绝对路径
     * @return
     */
    public static String getFileName(String path) {
        String result = "";
        path = path.replace("\\", "/");
        result = path.substring(path.lastIndexOf("/")+1);
        return result;
    }

    /**
     * 获取文件绝对路径中的绝对路径
     *
     * @param path
     *            文件绝对路径
     * @return
     */
    public static String getFilePath(String path) {
        String result = "";
        path = path.replace("\\", "/");
        if (path.lastIndexOf("/") == -1) {
            return path;
        }
        result = path.substring(0, path.lastIndexOf("/"));
        return result;
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }

    public static String writeToString(InputStream ins) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int i = -1;
        while ((i = ins.read(b)) != -1) {
            out.write(b, 0, i);
        }
        ins.close();
        return new String(out.toByteArray(), "UTF-8");
    }


    /**
     * 两个时间实际相差的天数
     *
     * @param statrDate
     * @param endDate
     * @return
     */
    public static double daysOfTwoDate(Date fromDate,Date toDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(toDate);
        long time2 = cal.getTimeInMillis();
        BigDecimal sw = new BigDecimal(time2-time1).divide(new BigDecimal(1000*3600*24),6,BigDecimal.ROUND_HALF_UP);
        return sw.doubleValue();
    }

    /**
     * 两个时间相差
     *
     * @param statrDate
     * @param endDate
     * @param format (返回的是天/时/分/秒)
     * @return
     */
    public static double dateDiff(Date fromDate,Date toDate,String format){
        long l = fromDate.getTime() - toDate.getTime();
        BigDecimal ll = new BigDecimal(l);
        BigDecimal sw = null;
        if(format.equals("d")){
            sw = ll.divide(new BigDecimal(1000*3600*24),6,BigDecimal.ROUND_HALF_UP);
        }else if(format.equals("h")){
            sw = ll.divide(new BigDecimal(1000*3600),6,BigDecimal.ROUND_HALF_UP);
        }else if(format.equals("m")){
            sw = ll.divide(new BigDecimal(1000*60),6,BigDecimal.ROUND_HALF_UP);
        }else if(format.equals("s")){
            sw = ll.divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
        }
        return sw.doubleValue();
    }


    /**
     * 去掉字符串前后指定字符
     *
     * @param statrDate
     * @param endDate
     * @return
     */
    public static String trimAll(String str, char c){
        if (str.length() > 0) {
            String beginChar = str.substring(0, 1);
            while (beginChar.equalsIgnoreCase(String.valueOf(c))) {
                str = str.substring(1, str.length());
                beginChar = str.substring(0, 1);
            }

            String endChar = str.substring(str.length() - 1, str.length());
            while (endChar.equalsIgnoreCase(String.valueOf(c))) {
                str = str.substring(0, str.length() - 1);
                endChar = str.substring(str.length() - 1, str.length());
            }
        }
        return str;
    }


    /**
     * 获取两时间点的时间间隔差
     * @param c1   c1.set(2014, 12, 17, 12, 24, 30);
     * @param c2   c2.set(2014, 12, 17, 13, 28, 54);
     * @oaram format 00:00:00:00/00:00:00/00:00时分
     * @return 00:01:04:24
     * @throws
     */
    public static String getElapsedTime(Calendar c1,Calendar c2,String format){
        String formatTime = "";
        long difference=c2.getTimeInMillis()-c1.getTimeInMillis();
        long seconds=difference/1000;
        long day = 0;
        long hour = 0;
        long minute = 0;
        long second = 0;
        minute = seconds/60;
        second = seconds%60;
        if(format.equals("00:00:00:00")){
            if(minute/60 >0){
                hour = minute/60;
                minute = (minute-60==1?0:minute%60);
                if(hour/24>0){
                    day = hour/24;
                    hour = (hour-24==1?0:hour%24);
                }
            }
            formatTime = ""+(day>=10?day:"0"+day)+":"+(hour>=10?hour:"0"+hour)+":"+(minute>=10?minute:"0"+minute)+":"+(second>=10?second:"0"+second);
        }else if(format.equals("00:00:00")){
            if(minute/60 >0){
                hour = minute/60;
                minute = (minute-60==1?0:minute%60);
            }
            formatTime = ""+(hour>=10?hour:"0"+hour)+":"+(minute>=10?minute:"0"+minute)+":"+(second>=10?second:"0"+second);
        }else if(format.equals("00:00")){
            if(minute/60 >0){
                hour = minute/60;
                minute = (minute-60==1?0:minute%60);
            }
            formatTime = ""+(hour>=10?hour:"0"+hour)+":"+(minute>=10?minute:"0"+minute);
        }else{
            Log.error("can't solve the format info "+format);
        }

        return formatTime;
    }
    public static String getElapsedTime(Date d1,Date d2,String format){
        Calendar c1=Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2=Calendar.getInstance();
        c2.setTime(d2);
        return getElapsedTime(c1,c2,format);
    }
    /**
     * 判断是否是GUID
     * @param guid str
     * @return
     */
    public static Boolean isGUID(String guid){
        Boolean isGUID = true;
        try{
            UUID.fromString(guid);
        }catch(IllegalArgumentException e){
            isGUID = false;
        }
        return isGUID;
    }
}
