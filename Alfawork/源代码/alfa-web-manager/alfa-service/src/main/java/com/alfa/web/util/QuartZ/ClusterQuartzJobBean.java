package com.alfa.web.util.QuartZ;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/8.
 */
public class ClusterQuartzJobBean extends QuartzJobBean {

    private static final Logger log = Logger.getLogger(ClusterQuartzJobBean.class);
    private String targetObject;
    private String targetMethod;

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try {
            log.info("ClusterQuartzJobBean invoke targetObject=" + targetObject + " targetMethod" + targetMethod);

            ApplicationContext appCtx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
            Object otargetObject = appCtx.getBean(targetObject);

            Method method = null;
            try {
                method = otargetObject.getClass().getMethod(targetMethod);
                method.invoke(otargetObject);
            } catch (SecurityException e) {
                log.error(e);
            } catch (NoSuchMethodException e) {
                log.error(e);
            }
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
