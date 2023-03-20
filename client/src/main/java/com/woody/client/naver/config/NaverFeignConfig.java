package com.woody.client.naver.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by woody 2023.03.18
 * Naver API 전용 Feign 설정 파일
 * */
@Configuration
public class NaverFeignConfig {

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor naverRequestInterceptor() {
        return requestTemplate -> requestTemplate
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret);
    }
}
