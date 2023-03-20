package com.woody.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Created by woody 2023.03.18
 * Feign 설정 파일
 * */
@Configuration
@EnableFeignClients(basePackages = "com.woody.client")
public class FeignConfig {
}
