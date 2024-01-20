package com.learn.events.publisher;

import com.learn.events.custom_events.TransactionFailureEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class Gpay {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void sendMoney(String name, Double amount, boolean condition) {
        try {
            if (!condition) {
                throw new RuntimeException("Transaction failed....");
            }

            log.info("hi, {}", name);
            log.info("sending amount of {} is successfully done", amount);
        } catch (Exception exception) {
            eventPublisher.publishEvent(new TransactionFailureEvent(name,amount));
        }
    }
}
