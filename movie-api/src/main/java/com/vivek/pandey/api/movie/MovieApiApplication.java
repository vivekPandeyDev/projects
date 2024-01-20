package com.vivek.pandey.api.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableCaching
@EnableAsync
public class MovieApiApplication implements CommandLineRunner {
    private final CacheManager cacheManager;

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }


    @Override
    public void run(String... args) {
        log.info("********************* INIT APPLICATION CACHE **************************");
        Cache cache = cacheManager.getCache("movie");
        assert cache != null;
        // remove all the movie related cache
        cache.invalidate();

    }
}
