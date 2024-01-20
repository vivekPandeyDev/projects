package com.vivek.pandey.api.movie.temp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TempService {

    @Async
    public void sendEmail(){
        try {
            Thread.sleep(5000);
            log.info("THREAD: {}",Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("********************* something went wrong **************************");
    }
}
