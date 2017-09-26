package com.alfa.web.util.Cache;

import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/24.
 */
public class MethodCacheInterceptor implements MethodInterceptor,
        InitializingBean {

    private static final Logger log = Logger.getLogger(MethodCacheInterceptor.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        String targetName = methodInvocation.getMethod().getDeclaringClass().getName();

        String methodName = methodInvocation.getMethod().getName();
        Object[] arguments = methodInvocation.getArguments();

        Object result = null;

        log.debug("############MethodCacheInterceptor invoke targetName="+targetName+" methodName="+methodName+" arguments="+ JsonUtil.toJson(arguments)+"############");

        String cacheKey = getCacheKey(targetName, methodName, arguments);

        Element element = null;

        synchronized (this){
            element=cache.get(cacheKey);
            if(element==null){
                log.info(cacheKey+"加入到缓存: "+cache.getName());
                result=methodInvocation.proceed();
                element=new Element(cacheKey,(Serializable)result);
                cache.put(element);
            }else{
                log.info(cacheKey+"使用缓存: "+cache.getName());
            }
        }

        return element.getValue();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(cache + "############MethodCacheInterceptor afterPropertiesSet cache############");
    }

    /**
     * 获取Key
     * @param targetName
     * @param methodName
     * @param arguments
     * @return
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {

                if(arguments[i] instanceof Criteria){
                    Criteria cc = (Criteria)(arguments[i]);
                    sb.append(".").append(JsonUtil.toJson(cc.getCondition()));
                }else{
                    sb.append(".").append(arguments[i]);
                }



            }
        }
        return sb.toString();
    }
}
