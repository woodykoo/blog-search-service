package com.woody.client.naver.exception;

/**
 * Created by woody 2023.03.22
 * 네이버 API abstract Exception
 * */
abstract class NaverClientException extends RuntimeException {

    private final NaverErrorResponse errorResponse;

    protected NaverClientException(NaverErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public NaverErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
