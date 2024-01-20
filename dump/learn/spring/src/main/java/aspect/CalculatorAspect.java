package aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@EnableAspectJAutoProxy
@Component
public class CalculatorAspect {

    private static final Logger logger = LogManager.getLogger(CalculatorAspect.class);


    @After("execution (public * temp_entity.Calculator.add(..) ) ")
    public void afterLogging(JoinPoint point){
        Object[] args =  point.getArgs();
        String value="";
        for(Object arg : args){
            value += arg + ", ";
        }
        logger.info("argument passed to add {} : {}",point.getSignature(),value);
    }

    @Before("execution (public * temp_entity.Calculator.subtract(..) ) ")
    public void beforeLogging(JoinPoint point){
        Object[] args =  point.getArgs();
        String value="";
        for(Object arg : args){
            value += arg + ", ";
        }
        logger.info("argument passed to add {} : {}",point.getSignature(),value);
    }

    @AfterReturning(pointcut = "execution (public * temp_entity.Calculator.multiply(..) )",returning = "result")
    public void afterReturn(JoinPoint point,Object result){
        logger.info("value of multiply {} ",result);

    }
    @AfterThrowing(pointcut = "execution (public * temp_entity.Calculator.*(..) )",throwing="ex")
    public void afterException(JoinPoint point ,ArithmeticException ex){
        logger.info("exception {}",ex.getMessage());
    }


}
