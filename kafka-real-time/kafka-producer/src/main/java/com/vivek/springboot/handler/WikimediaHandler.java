package com.vivek.springboot.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class WikimediaHandler implements EventHandler {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${wikimedia.topic-name}")
    private String topicName;

    @Override
    public void onOpen() throws Exception {
      log.info("wikimedia open called");
    }

    @Override
    public void onClosed() throws Exception {
        log.info("wikimedia closed called");
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        log.info("event data for topic-name: {} -> {}",topicName,messageEvent.getData());

        kafkaTemplate.send(topicName,messageEvent.getData());

    }

    @Override
    public void onComment(String s) throws Exception {
        log.info("wikimedia comment called");
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("wikimedia error called");
    }
}
