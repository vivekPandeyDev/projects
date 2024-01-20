package com.learn.events.publisher;

import com.learn.events.custom_events.DemonSlayerEvent;
import com.learn.events.custom_events.TheBigBangTheoryEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ZeeCafePublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    public void streamBigBangTheory(String episodeNo) {
        log.info("streamBigBangTheory => streaming big bang theory: {}", episodeNo);
//        eventPublisher.publishEvent(new TheBigBangTheoryEvent(this,episodeNo));
        eventPublisher.publishEvent(new TheBigBangTheoryEvent(episodeNo));
    }

    public void streamDemonSlayer(String episodeNo){
        log.info("streamDemonSlayer => streaming demon slayer: {}", episodeNo);
        eventPublisher.publishEvent(new DemonSlayerEvent(episodeNo));
    }
}
