package com.vivek.springboot.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.vivek.springboot.handler.WikimediaHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class WikimediaProducer {

    private final WikimediaHandler wikimediaHandler;

    @Value("${wikimedia.url}")
    private String url;

    public void sendMessage() throws InterruptedException {
        log.info("wikimedia url ->{}",url);
        // event handler to send real time data to kafka
        EventSource eventSource =  new EventSource.Builder((EventHandler) wikimediaHandler, URI.create(url)).build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
