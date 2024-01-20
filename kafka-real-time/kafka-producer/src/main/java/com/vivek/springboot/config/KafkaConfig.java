package com.vivek.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Slf4j
public class KafkaConfig {

    @Value("${wikimedia.topic-name}")
    private String topicName;
    @Bean
    public NewTopic topic(){
        log.info("topic name {}",topicName);
        return TopicBuilder.name(topicName).build();
    }
}
