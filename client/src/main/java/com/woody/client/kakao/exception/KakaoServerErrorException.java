package com.woody.client.kakao.exception;

public class KakaoServerErrorException extends KakaoClientException {

    public KakaoServerErrorException(KakaoErrorResponse kakaoErrorResponse) {
        super(kakaoErrorResponse);
    }
}
