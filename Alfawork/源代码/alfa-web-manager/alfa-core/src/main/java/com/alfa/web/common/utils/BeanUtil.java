package com.alfa.web.common.utils;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/4/27.
 */
public class BeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 把非空属性转换为map<br>
     * <p>
     * 用法：在写REST时，在findByCriteria方法中判断属性是否为空时，可以使用此方法代替<br>
     * <p>
     * 例如：<pre class="code">
     * Criteria example = new Criteria();
     * if (account != null) {
     * if (!StringUtil.isNullOrEmpty(account.getAccountName())) {
     * example.put("accountNameLike", account.getAccountName());
     * }
     * if (!StringUtil.isNullOrEmpty(account.getAccountType())) {
     * example.put("accountType", account.getAccountType());
     * }
     * if (!StringUtil.isNullOrEmpty(account.getAccountLevelSid())) {
     * example.put("accountLevelSid", account.getAccountLevelSid());
     * }
     * if (!StringUtil.isNullOrEmpty(account.getStatus())) {
     * example.put("status", account.getStatus());
     * }
     * }
     * 可以替换为：
     * Criteria example = new Criteria();
     * try {
     * example.setCondition(BeanUtil.collectProperties(account));
     * } catch (IllegalAccessException e) {
     * e.printStackTrace();
     * } catch (InvocationTargetException e) {
     * e.printStackTrace();
     * }
     *
     * @param orig
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Map<String, Object> collectProperties(Object orig)
            throws IllegalAccessException, InvocationTargetException {
        if (orig == null) {
            throw new IllegalArgumentException("No accountin bean specified");
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PropertyUtilsBean pub = BeanUtilsBean.getInstance().getPropertyUtils();
        PropertyDescriptor[] origDescriptors = pub.getPropertyDescriptors(orig);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            if ("class".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            if (pub.isReadable(orig, name)) {
                try {
                    Object value = pub.getSimpleProperty(orig, name);
                    if (value != null
                            && StringUtils.isNotBlank(value.toString())) {
                        resultMap.put(name, value);
                    }
                } catch (NoSuchMethodException e) {
                    // Should not happen
                }
            }
        }
        return resultMap;
    }

    /**
     * 对实体Bean进行序列化操作.
     *
     * @param
     * @return
     * @throws Exception
     */
    public static byte[] serialize(Object source) {
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream ObjOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            ObjOut = new ObjectOutputStream(byteOut);
            ObjOut.writeObject(source);
            ObjOut.flush();
        } catch (IOException e) {
            logger.error(source.getClass().getName() + " serialized error !", e);
        } finally {
            try {
                if (null != ObjOut) {
                    ObjOut.close();
                }
            } catch (IOException e) {
                ObjOut = null;
            }
        }
        return byteOut.toByteArray();
    }

    /**
     * 反序列化bean
     *
     * @param source
     * @return
     */
    public static Object deserialize(byte[] source) {
        ObjectInputStream ObjIn = null;
        Object retVal = null;
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(source);
            ObjIn = new ObjectInputStream(byteIn);
            retVal = ObjIn.readObject();
        } catch (Exception e) {
            logger.error("deserialized error  !", e);
        } finally {
            try {
                if (null != ObjIn) {
                    ObjIn.close();
                }
            } catch (IOException e) {
                ObjIn = null;
            }
        }
        return retVal;
    }
}
