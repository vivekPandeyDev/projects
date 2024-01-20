package com.learn.events.listener;


import com.learn.events.custom_events.DemonSlayerEvent;
import com.learn.events.custom_events.TheBigBangTheoryEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AnupListener {
//public class AnupListener implements ApplicationListener<TheBigBangTheoryEvent> {
    public void watchBigBangTheory(String episodeNo){
       log.info("Anup: started watching BBT");
       log.info("Anup: playing BBT: {}",episodeNo);
    }
    public void watchDemonSlayer(String episodeNo){
        log.info("Anup: started watching demon slayer");
        log.info("Anup: playing demon slayer: {}",episodeNo);
    }
//    @Override
    @EventListener
    public void bigBangTheoryEvent(TheBigBangTheoryEvent event ) {
        watchBigBangTheory(event.getEpisodeNumber());
    }
    @EventListener
    public void demonSlayer(DemonSlayerEvent event ) {
        watchBigBangTheory(event.getEpisodeNo());
    }
}
