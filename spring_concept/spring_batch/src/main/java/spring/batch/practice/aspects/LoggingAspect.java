package spring.batch.practice.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Pointcut("within(spring.batch.practice.repo.*) || within(spring.batch.practice.service.*)")
    public void loggingServiceAndRepo(){}

    @Around("loggingServiceAndRepo()")
    public Object beforeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        //pre-processing
        log.info("method invoked: {}::{}",joinPoint.getTarget().getClass().getSimpleName(),joinPoint.getSignature().getName());
        //calling actual object
        Object returnValue =  joinPoint.proceed();
        log.info("-------------------------------------------------------------------");
        // post-processing
        log.info("method executed: {}::{}",joinPoint.getTarget().getClass().getSimpleName(),joinPoint.getSignature().getName());
        return returnValue;
    }
}
