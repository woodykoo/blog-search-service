package com.woody.client.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by woody 2023.03.18
 * Feign 설정 파일
 * */
@Configuration
@EnableFeignClients(basePackages = "com.woody.client")
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
