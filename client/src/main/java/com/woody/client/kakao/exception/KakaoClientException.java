package com.woody.client.kakao.exception;

/**
 * Created by woody 2023.03.22
 * 카카오 API abstract Exception
 * */
abstract class KakaoClientException extends RuntimeException {
    private final KakaoErrorResponse errorResponse;

    protected KakaoClientException(KakaoErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public KakaoErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
