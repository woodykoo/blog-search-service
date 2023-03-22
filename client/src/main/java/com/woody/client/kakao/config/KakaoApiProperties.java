package com.woody.client.kakao.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Created By woody 2023.03.22
 * Kakao API 호출 정보
 * */
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
