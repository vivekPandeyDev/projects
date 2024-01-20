package com.learn.events.custom_events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
@AllArgsConstructor
public class TheBigBangTheoryEvent  {
//public class TheBigBangTheoryEvent  extends ApplicationEvent {
    private String episodeNumber;

//    public TheBigBangTheoryEvent(Object source) {
//        super(source);
//    }
//
//    public TheBigBangTheoryEvent(Object source,String episodeNumber){
//        super(source);
//        this.episodeNumber = episodeNumber;
//    }



}
