package spring.batch.practice.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import spring.batch.practice.handler.JdbcInvocationHandler;

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
