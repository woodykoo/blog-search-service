package com.woody.domain.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * Created by woody 2023.03.18
 * Redis 설정 파일
 * */
@Configuration
public class RedisConfig {

    @Bean(destroyMethod = "stop")
    public RedisServer redisServer() throws IOException {
        RedisServer redisServer = new RedisServer();
        redisServer.start();
        return redisServer;
    }

    @Bean(destroyMethod = "shutdown")
    @DependsOn("redisServer")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }
}
