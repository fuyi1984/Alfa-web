package com.alfa.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/3.
 */
@Aspect
@Component
public class TraceAspect {

   /* @Autowired
    private HttpServletRequest request;

    @Autowired
    private TraceService traceServiceImpl;

    @Pointcut("execution(* com.alfa.web.rest..*.*(..))")
    public void serviceImpl(){}

    @Around(value="serviceImpl() && @annotation(userLog)",argNames = "userLog")
    public Object userLogAdvise(ProceedingJoinPoint pjp, UserLog userLog) throws Throwable{

        traceServiceImpl.insertTraceLog(userLog);
        Object result = pjp.proceed();
        return result;

    }*/
}
