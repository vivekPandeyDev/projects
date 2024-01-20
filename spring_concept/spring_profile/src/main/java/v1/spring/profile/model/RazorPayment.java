package v1.spring.profile.model;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Profile("prod")
public class RazorPayment  implements Payment{
    @Override
    public void doPayment() {
        log.info("razor pay payment started.......");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("razor pay payment payment end .......");
    }
}
