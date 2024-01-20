package v3.spring.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import v3.spring.profile.annotation.GpayProfile;
import v3.spring.profile.model.GpayPayment;

@Configuration
@GpayProfile
public class DevAppConfig {
    @Bean
    public GpayPayment gpayPayment(){
        return new GpayPayment();
    }

}
