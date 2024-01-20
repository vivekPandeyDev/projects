package v3.spring.profile.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
