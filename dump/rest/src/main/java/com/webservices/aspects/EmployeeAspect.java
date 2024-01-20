package com.webservices.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
public class EmployeeAspect {
	private static final Logger logger = LogManager.getLogger(EmployeeAspect.class);

	@Pointcut("execution(public * com.webservices.controller.EmployeeController.*() )")
	public void empPointcut() {
	}

	@Around("empPointcut()")
	public Object test(ProceedingJoinPoint point) throws Throwable {
		logger.info("method : {}", point.getSignature());
		Object obj = point.proceed();
		logger.info("returned value : {}", obj);
		return obj;
	}
}
