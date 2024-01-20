package main;

import app_config.BeanConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import temp_entity.Calculator;

public class Main4 {
    private static final Logger logger = LogManager.getLogger(Main4.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Calculator calculator = context.getBean("calculator", Calculator.class);
        logger.info("add int: {}",calculator.add(5,4));
        logger.info("add double: {}",calculator.add(5d,4d));
        logger.info("sub int: {}",calculator.subtract(5,4));
        logger.info("sub double: {}",calculator.subtract(5d,4d));
        logger.info("multiply int: {}",calculator.multiply(5d,4d));
        logger.info("multiply double: {}",calculator.multiply(5d,4d));
        try {
            logger.info("divide int: {}", calculator.divide(5, 0));
        }catch (Exception e){
            logger.info("handle exception here!!!!");
        }
        logger.info("divide double: {}",calculator.divide(5d,2d));
    }
}
