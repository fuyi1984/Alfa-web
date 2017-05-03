package com.alfa.web.util;

import com.alfa.web.util.StringUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/27.
 */
public class LicenseUtil {
    private static final String LICENSE_KEY = "license.key";
    private static final String LICENSE_VALUE = "license.value";

    public static boolean isDateValid() {
        boolean isValid;
        // 解密后的字符串
        String regisCode = getRgsCode();
        if (StringUtil.isNullOrEmpty(regisCode)) {
            return false;
        }

        String initRegisCode = getInitRegisCode(regisCode.substring(0, 16));
        String licenseKey = PropertiesUtil.getProperty(LICENSE_KEY);

        if (!initRegisCode.startsWith(licenseKey)) {
            return false;
        }
        // 到期时间
        String overDateTime = replaceStr(getOverDateLicense(regisCode.substring(16)));
        if (!checkDate(overDateTime)) {
            return false;
        }
        isValid = Timestamp.valueOf(overDateTime + " 24:00:00").after(getCurrentTime());
        return isValid;
    }

    private static Timestamp getCurrentTime() {
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            String DATE_FORMAT_HMS_d = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat formatwithHMSd = new SimpleDateFormat(DATE_FORMAT_HMS_d);
            formatwithHMSd.setTimeZone(TimeZone.getDefault());
            Date currentdate_d = formatwithHMSd.parse(formatwithHMSd.format(cal.getTime()));
            long time = currentdate_d.getTime();
            return new Timestamp(time);
        } catch (Exception e) {
            return null;
        }

    }

    private static String getRgsCode() {
        String license = PropertiesUtil.getProperty(LICENSE_VALUE);
        if (StringUtil.isNullOrEmpty(license)) {
            return null;
        }
        String regigtSerlNumWith = changeLocation(license);
        if (StringUtil.isNullOrEmpty(regigtSerlNumWith)) {
            return null;
        }
        String regigtSerlNum = regigtSerlNumWith.replaceAll("-", "");
        // 解密后的字符串
        return getCpuidFromEncrypt(regigtSerlNum);
    }

    /**
     * 解析License注册码
     */
    private static String changeLocation(String str) {
        String result;
        String rlt = null;
        try {
            String aa = str.replaceAll("-", "").substring(0, 36);
            int v = new Integer(str.substring(44));
            char[] cary = aa.toCharArray();
            List<Character> list = new ArrayList<Character>();
            List<Character> listTo = new ArrayList<Character>();
            char[] caryTo = new char[36];

            for (char aCary : cary) {
                list.add(aCary);
            }
            int to;
            for (int i = list.size() - 1; i >= 0; i--) {
                to = i ^ v;
                caryTo[to] = list.get(i);
            }

            for (char aCaryTo : caryTo) {
                listTo.add(aCaryTo);
            }

            StringBuilder sb = new StringBuilder();
            for (Character aListTo : listTo) {
                sb.append(aListTo);
            }

            String after = sb.toString();
            String cut1 = after.substring(0, 14);
            String cut2 = after.substring(14, 24);
            String cut3 = after.substring(24);

            result = cut2 + cut1 + cut3;

            rlt = result.substring(0, 4) + "-" + result.substring(4, 8) + "-"
                    + result.substring(8, 12) + "-" + result.substring(12, 16)
                    + "-" + result.substring(16, 20) + "-"
                    + result.substring(20, 24) + "-" + result.substring(24, 28)
                    + "-" + result.substring(28, 32) + "-"
                    + result.substring(32, 36);

        } catch (Throwable ignored) {
        }

        return rlt;
    }

    /**
     * 解密
     *
     * @param encryptCpuid
     * @return
     */
    private static String getCpuidFromEncrypt(String encryptCpuid) {

        char[] sAr = encryptCpuid.toCharArray();
        char[] sb = new char[36];
        StringBuilder sbfer = new StringBuilder();

        for (int i = sAr.length; i > 0; i--) {
            long serL = charToLong(sAr[i - 1]);
            long serDcd = serL;
            serDcd ^= 5 % 9;
            if (serDcd < 48L || (57L < serDcd & serDcd < 65L) || serDcd > 90) {
                serDcd = serL;
            }
            sb[i - 1] = longTochar(serDcd);
        }

        for (char aSb : sb) {
            sbfer.append(aSb);
        }
        //log.info("解密后的字符串是" + sbfer.toString());
        return sbfer.toString();
    }

    private static long charToLong(char serChar) {
        return new Integer((int) serChar).longValue();
    }

    private static char longTochar(long serLong) {
        int i = (int) serLong;
        return (char) i;

    }

    private static String getOverDateLicense(String delDateStr) {
        return delDateStr.substring(0, 4) + "-"
                + delDateStr.substring(4, 6) + "-" + delDateStr.substring(6, 8);
    }

    private static String replaceStr(String result) {
        result = result.replace("H", "0")
                .replace("Y", "1")
                .replace("M", "2")
                .replace("I", "3")
                .replace("Q", "4")
                .replace("#", "5")
                .replace("P", "6")
                .replace("!", "7")
                .replace("&", "8")
                .replace("B", "9");
        return result;
    }

    private static boolean checkDate(String date) {
        String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(date);
        return m.matches();
    }

    /**
     * 获取初始化注册码
     *
     * @param encryptCpuid
     * @return
     */
    private static String getInitRegisCode(String encryptCpuid) {

        char[] sAr = encryptCpuid.toCharArray();
        char[] sb = new char[24];
        StringBuffer sbfer = new StringBuffer();

        for (int i = sAr.length; i > 0; i--) {
            long serL = charToLong(sAr[i - 1]);
            long serDcd = serL;
            serDcd ^= 3 % 9;
            if (serDcd < 48L || (57L < serDcd & serDcd < 65L) || serDcd > 90) {
                serDcd = serL;
            }
            sb[i - 1] = longTochar(serDcd);
        }

        for (char aSb : sb) {
            sbfer.append(aSb);
        }
        return sbfer.toString();
    }
}
