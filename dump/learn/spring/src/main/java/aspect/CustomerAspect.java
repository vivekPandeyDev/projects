package aspect;

import entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import utility.CustomerNotFoundException;

import java.time.LocalDate;

@Aspect
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Component
public class CustomerAspect {
    private static final Logger logger = LogManager.getLogger(CustomerAspect.class);
    @Around("execution (public * service.ServiceLayer.save(..) )")
    public Object something(ProceedingJoinPoint point){
        Customer customer =  (Customer)point.getArgs()[0];
        if(LocalDate.now().getYear() - customer.getDob().getYear() > 21){
            Object obj = null;
            try {
                obj = point.proceed();
            } catch (Throwable e) {
                logger.error("cannot proceed {}",e.getMessage());
            }
            return obj;
        }
        logger.error("cannot insert customer age is not greater than 21");
        return false;
    }

    @Before("execution (public * service.ServiceLayer.getAllEntity(..) )")
    public void beforeAllEntity(){
        logger.info("running before printing all the entries!!!!");
    }

    @After("execution (public * service.ServiceLayer.update(..) )")
    public void afterUpdating(){
        logger.info("running after updating the customer!!!!");
    }

    @AfterThrowing(pointcut = "execution (public * service.ServiceLayer.getSingleEntity(..))",throwing = "ex")
    public void afterThrowing(JoinPoint pointcut, CustomerNotFoundException ex){
        int id=  (Integer)pointcut.getArgs()[0];
        logger.info("customer not found for id: {}",id);
    }

    @AfterReturning(pointcut = "execution (public * service.ServiceLayer.getSingleEntity(..))",returning = "value")
    public void afterThrowing(JoinPoint pointcut, Object value){
        int id=  (Integer)pointcut.getArgs()[0];
        logger.info("customer's id: {}",id);
    }


    @Pointcut("execution (public * service.ServiceLayer.getSingleEntity(..))")
    public void empty(){}

    @Before("empty()")
    @After("empty()")
    @AfterReturning("empty()")
    @AfterThrowing("empty()")
    @Around("empty()")
    public void getName(){
        logger.info("Running all aspects when getSingleEntityByName is called!!!!");
    }

}
