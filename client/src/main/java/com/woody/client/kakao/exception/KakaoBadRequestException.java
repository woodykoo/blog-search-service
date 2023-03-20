package com.woody.client.kakao.exception;

public class KakaoBadRequestException extends KakaoClientException {

    public KakaoBadRequestException(KakaoErrorResponse kakaoErrorResponse) {
        super(kakaoErrorResponse);
    }
}
