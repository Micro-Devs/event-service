package com.microdevs.eventservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties("spring.cache.redis")
public class CacheProperties {
    private String hostName;
    private int port;
    private Duration timeToLive;
    private Map<String, Long> cacheConfig = new HashMap<>();
}
