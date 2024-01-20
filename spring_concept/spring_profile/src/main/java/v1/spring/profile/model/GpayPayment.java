package v1.spring.profile.model;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Profile("dev")
public class GpayPayment implements Payment {
    @Override
    public void doPayment() {
        log.info("gpay payment started.......");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("gpay payment end .......");
    }
}
