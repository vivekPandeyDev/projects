package v3.spring.profile.config;


import org.springframework.context.annotation.*;
import v3.spring.profile.model.Payment;
import v3.spring.profile.model.PaymentService;


@Configuration
@Import(value = {DevAppConfig.class, ProdAppConfig.class})
public class AppConfig  {
    @Bean
    public PaymentService paymentService(Payment payment) {
        return new PaymentService(payment);
    }

}
