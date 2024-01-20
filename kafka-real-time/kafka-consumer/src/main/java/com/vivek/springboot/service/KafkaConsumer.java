package com.vivek.springboot.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vivek.springboot.dto.WikimediaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "wikimedia",
            groupId = "testGroup"
    )
    public void consumeMessage(String message) throws JsonProcessingException {

        //converting json string to dto
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        WikimediaDto root = objectMapper.readValue(message, WikimediaDto.class);
        //saving stream to database TODO

        //formatting
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        String formattedJson = objectWriter.writeValueAsString(root);
        log.info("message ->{}",formattedJson);
    }
}
