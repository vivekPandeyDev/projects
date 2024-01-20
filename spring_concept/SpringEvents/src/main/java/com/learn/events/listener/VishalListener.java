package com.learn.events.listener;


import com.learn.events.custom_events.TheBigBangTheoryEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
//public class VishalListener  implements ApplicationListener<TheBigBangTheoryEvent> {
public class VishalListener  {
    public void watchBigBangTheory(String episodeNo){
       log.info("Vishal: started watching BBT");
       log.info("Vishal: playing BBT: {}",episodeNo);
    }

//    @Override
    @EventListener
    public void onApplicationEvent(TheBigBangTheoryEvent event) {
        watchBigBangTheory(event.getEpisodeNumber());
    }
}
