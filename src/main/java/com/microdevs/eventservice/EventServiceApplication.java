package com.microdevs.eventservice;

import com.microdevs.baseservice.exception.GlobalExceptionHandler;
import com.microdevs.eventservice.config.CacheProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.microdevs.*")
@Import(GlobalExceptionHandler.class)
@EnableConfigurationProperties(CacheProperties.class)
public class EventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }

}
