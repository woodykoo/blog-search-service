package com.woody.client.kakao.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class KakaoFeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public KakaoFeignErrorDecoder() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        KakaoErrorResponse errorResponse = null;
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            errorResponse = objectMapper.readValue(bodyIs, KakaoErrorResponse.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()) {
            case 400:
            case 404:
                throw new KakaoBadRequestException(errorResponse);
            default:
                throw new KakaoServerErrorException(errorResponse);
        }
    }
}
