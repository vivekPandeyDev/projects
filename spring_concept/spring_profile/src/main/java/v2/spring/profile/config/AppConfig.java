package v2.spring.profile.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import v2.spring.profile.model.GpayPayment;
import v2.spring.profile.model.Payment;
import v2.spring.profile.model.PaymentService;
import v2.spring.profile.model.RazorPayment;

@Configuration
public class AppConfig {

    @Bean
    @Profile("dev")
    public GpayPayment gpayPayment(){
        return new GpayPayment();
    }

    @Bean
    @Profile("prod")
    public RazorPayment razorPayment(){
        return new RazorPayment();
    }

    @Bean
    public PaymentService paymentService(Payment payment){
        return new PaymentService(payment);
    }
}
