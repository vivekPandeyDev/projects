package v2.spring.profile.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
