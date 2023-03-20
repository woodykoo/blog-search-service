package com.woody.client.naver.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woody.client.kakao.exception.KakaoBadRequestException;
import com.woody.client.kakao.exception.KakaoErrorResponse;
import com.woody.client.kakao.exception.KakaoServerErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class NaverFeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public NaverFeignErrorDecoder() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        NaverErrorResponse errorResponse = null;
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            errorResponse = objectMapper.readValue(bodyIs, NaverErrorResponse.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()) {
            case 400:
            case 404:
                throw new NaverBadRequestException(errorResponse);
            default:
                throw new NaverServerErrorException(errorResponse);
        }
    }
}
