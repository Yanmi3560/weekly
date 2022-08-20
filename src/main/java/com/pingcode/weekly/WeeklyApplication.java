package com.pingcode.weekly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeeklyApplication {
    private static final Logger logger = LoggerFactory.getLogger(WeeklyApplication.class);
    public static void main(String[] args) {

     SpringApplication.run(WeeklyApplication.class, args);

     logger.info("WeeklyApplication start!");


    }
}
