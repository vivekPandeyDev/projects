package com.learn.events.listener;


import com.learn.events.custom_events.TransactionFailureEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SendEmailListener {

    @EventListener
    public void listener(TransactionFailureEvent event){
        log.info("---------------- send email start --------------------------");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Hi,{} transaction failed for amount: {}, sending email",event.getUserName(),event.getAmount());
        log.info("---------------- send email end --------------------------");
    }
}
