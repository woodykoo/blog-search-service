package com.woody.client.kakao.exception;

/**
 * Created by woody 2023.03.22
 * 카카오 4xx 에러 Exception
 * */
public class KakaoBadRequestException extends KakaoClientException {
    public KakaoBadRequestException(KakaoErrorResponse kakaoErrorResponse) {
        super(kakaoErrorResponse);
    }
}
