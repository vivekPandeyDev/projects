package v2.spring.profile.model;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class PaymentService {
    private final Payment payment;

    public void processPayment(double amount){
        log.info("payment service implementation starts......" );
        log.info("processing payment amount: {}",amount);
        payment.doPayment();
        log.info("done processing of  payment amount: {}",amount);
        log.info("payment service implementation ends.......");
    }

}
