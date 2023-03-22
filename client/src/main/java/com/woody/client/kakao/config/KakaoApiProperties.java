package com.woody.client.kakao.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("kakao.api")
public class KakaoApiProperties {
    private final String url;
    private final String key;

    public KakaoApiProperties(String url, String key) {
        this.url = url;
        this.key = key;
    }
}
