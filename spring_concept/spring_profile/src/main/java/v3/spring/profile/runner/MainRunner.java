package v3.spring.profile.runner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import v3.spring.profile.config.AppConfig;
import v3.spring.profile.model.PaymentService;

/*
 * Problem:  we have 2 payment method gpay and razor pay.
 * Gpay run -> dev environment
 * RazorPay run -> prod environment
 */

/*
*   Solution : use @Profile annotation to use create the bean only when given environment is active
*   @Profile at class level -> @Configuration and @Component
*   @Profile at method level -> @Bean
 */

public class MainRunner {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment environment =  applicationContext.getEnvironment();
        environment.setActiveProfiles(environment.getProperty("dev_env"));
//        environment.setActiveProfiles(System.getProperty("prod"));
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();
        applicationContext.getBean(PaymentService.class).processPayment(5000);
    }
}
