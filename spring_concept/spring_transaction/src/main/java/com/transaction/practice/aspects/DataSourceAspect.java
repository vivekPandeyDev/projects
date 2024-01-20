package com.transaction.practice.aspects;

import com.transaction.practice.handler.JdbcInvocationHandler;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Component
@Aspect
@Log4j2
public class DataSourceAspect {
    @Around("target(javax.sql.DataSource)")
    public Object dataSourceLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("method invoked: {}::{}",joinPoint.getTarget().getClass().getSimpleName(),joinPoint.getSignature().getName());
        Connection returnValue = (Connection) joinPoint.proceed();
        return  Proxy.newProxyInstance(returnValue.getClass().getClassLoader(), new Class[]{Connection.class},new JdbcInvocationHandler(returnValue));

    }
}
