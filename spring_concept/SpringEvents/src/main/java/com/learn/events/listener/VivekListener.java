package com.learn.events.listener;


import com.learn.events.custom_events.DemonSlayerEvent;
import com.learn.events.custom_events.TheBigBangTheoryEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class VivekListener {
//public class VivekListener implements ApplicationListener<TheBigBangTheoryEvent> {
    public void watchBigBangTheory(String episodeNo){
       log.info("Vivek: started watching BBT");
       log.info("Vivek: playing BBT: {}",episodeNo);
    }
    public void watchDemonSlayer(String episodeNo){
        log.info("Vivek: started watching demon slayer");
        log.info("Vivek: playing demon slayer: {}",episodeNo);
    }

//    @Override
    @EventListener
    public void bigBangTheoryListener(TheBigBangTheoryEvent event) {
        watchBigBangTheory(event.getEpisodeNumber());
    }

    @EventListener
    public void demonSlayerListener(DemonSlayerEvent event) {
        watchBigBangTheory(event.getEpisodeNo());
    }
}
