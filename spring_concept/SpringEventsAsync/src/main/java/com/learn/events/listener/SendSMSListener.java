package com.learn.events.listener;


import com.learn.events.custom_events.TransactionFailureEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SendSMSListener {

    @EventListener
    public void listener(TransactionFailureEvent event){
        log.info("---------------- send sms start --------------------------");
        log.info("Hi,{} transaction failed for amount: {}, sending sms",event.getUserName(),event.getAmount());
        log.info("---------------- send sms end --------------------------");
    }
}
