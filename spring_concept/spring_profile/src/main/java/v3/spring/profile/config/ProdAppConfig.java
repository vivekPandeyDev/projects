package v3.spring.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import v3.spring.profile.annotation.RazorPayProfile;
import v3.spring.profile.model.RazorPayment;

@Configuration
@RazorPayProfile
public class ProdAppConfig {
    @Bean
    public RazorPayment razorPayment(){
        return new RazorPayment();
    }
}
