package com.woody.client.kakao.exception;

public class KakaoClientException extends RuntimeException {
    private final KakaoErrorResponse errorResponse;

    protected KakaoClientException(KakaoErrorResponse kakaoErrorResponse) {
        this.errorResponse = kakaoErrorResponse;
    }

    public KakaoErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
