package com.vivek.pandey.api.movie.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TempController {

    private final TempService tempService;

    @GetMapping("/test")
    public String testAsync() {
        log.info("Processor Count: {}, Thread: {}", Runtime.getRuntime().availableProcessors(), Thread.currentThread());
        tempService.sendEmail();
        return "async test";
    }
}
