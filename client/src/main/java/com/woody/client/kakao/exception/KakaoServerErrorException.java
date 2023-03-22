package com.woody.client.kakao.exception;

/**
 * Created by woody 2023.03.22
 * 카카오 서버에러 Exception
 * */
public class KakaoServerErrorException extends KakaoClientException {

    public KakaoServerErrorException(KakaoErrorResponse kakaoErrorResponse) {
        super(kakaoErrorResponse);
    }
}
