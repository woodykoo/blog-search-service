package com.woody.client.kakao.exception;

abstract class KakaoClientException extends RuntimeException {
    private final KakaoErrorResponse errorResponse;

    protected KakaoClientException(KakaoErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public KakaoErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
